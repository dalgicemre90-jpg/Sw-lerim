package com.example.swlerim.memory;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerSessionStore {
    private final MemoryPolicyConfig config;
    private final Map<UUID, SessionRecord> activeSessions = new ConcurrentHashMap<>();

    public PlayerSessionStore(MemoryPolicyConfig config) {
        this.config = Objects.requireNonNull(config, "config");
    }

    public void register(UUID uuid, String username, String backendId) {
        Objects.requireNonNull(uuid, "uuid");
        Objects.requireNonNull(username, "username");
        Objects.requireNonNull(backendId, "backendId");

        long now = System.currentTimeMillis();
        activeSessions.put(uuid, new SessionRecord(uuid, username, backendId, now));
    }

    public void touch(UUID uuid, long nowEpochMs) {
        SessionRecord record = activeSessions.get(uuid);
        if (record != null) {
            record.touch(nowEpochMs == 0L ? System.currentTimeMillis() : nowEpochMs);
        }
    }

    public void unregister(UUID uuid) {
        SessionRecord record = activeSessions.remove(uuid);
        if (record != null) {
            record.clear();
        }
    }

    public boolean contains(UUID uuid) {
        return activeSessions.containsKey(uuid);
    }

    public int activeCount() {
        return activeSessions.size();
    }

    public void evictStaleSessions() {
        long now = System.currentTimeMillis();
        Iterator<Map.Entry<UUID, SessionRecord>> iterator = activeSessions.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<UUID, SessionRecord> entry = iterator.next();
            SessionRecord record = entry.getValue();

            if (record.isStale(now, config.sessionTtlMs())) {
                record.clear();
                iterator.remove();
            }
        }
    }

    public void runCleanupLoop() {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(config.staleCleanupIntervalMs());
                    evictStaleSessions();
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }, "player-session-cleanup");

        thread.setDaemon(true);
        thread.start();
    }
}
