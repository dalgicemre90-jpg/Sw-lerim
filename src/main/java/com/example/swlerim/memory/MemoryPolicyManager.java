package com.example.swlerim.memory;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class MemoryPolicyManager {
    private final MemoryPolicyConfig config;
    private final Map<UUID, SessionLifecycleState> activeSessionMap = new ConcurrentHashMap<>();

    public MemoryPolicyManager(MemoryPolicyConfig config) {
        this.config = Objects.requireNonNull(config, "config");
    }

    public void register(UUID uuid, String username, String backendId) {
        long now = System.currentTimeMillis();
        activeSessionMap.put(uuid, new SessionLifecycleState(uuid, username, backendId, now));
    }

    public void touch(UUID uuid, long nowEpochMs) {
        SessionLifecycleState state = activeSessionMap.get(uuid);
        if (state != null) {
            long now = nowEpochMs == 0L ? System.currentTimeMillis() : nowEpochMs;
            state.touch(now);
        }
    }

    public void onDisconnect(UUID uuid) {
        SessionLifecycleState state = activeSessionMap.remove(uuid);
        if (state != null) {
            state.deactivate();
        }
    }

    public void evictStaleSessions() {
        long now = System.currentTimeMillis();
        Iterator<Map.Entry<UUID, SessionLifecycleState>> iterator = activeSessionMap.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<UUID, SessionLifecycleState> entry = iterator.next();
            SessionLifecycleState state = entry.getValue();

            if (state.isExpired(now, config.sessionTtlMs())) {
                state.deactivate();
                iterator.remove();
            }
        }
    }

    public int activeSessionCount() {
        return activeSessionMap.size();
    }
}
