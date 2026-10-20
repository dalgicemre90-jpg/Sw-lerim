package com.example.swlerim.memory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public final class SessionRegistry {
    private final MemoryPolicyConfig config;
    private final Map<UUID, SessionLifecycleState> activeSessions = new ConcurrentHashMap<>();
    private final Map<String, Integer> backendLoad = new ConcurrentHashMap<>();

    public SessionRegistry(MemoryPolicyConfig config) {
        this.config = Objects.requireNonNull(config, "config");
    }

    public void register(UUID uuid, String username, String backendId) {
        long now = System.currentTimeMillis();
        SessionLifecycleState state = new SessionLifecycleState(uuid, username, backendId, now);
        activeSessions.put(uuid, state);
        backendLoad.merge(backendId, 1, Integer::sum);
    }

    public void touch(UUID uuid) {
        SessionLifecycleState state = activeSessions.get(uuid);
        if (state != null) {
            state.touch(System.currentTimeMillis());
        }
    }

    public void disconnect(UUID uuid) {
        SessionLifecycleState state = activeSessions.remove(uuid);
        if (state != null) {
            state.deactivate();
            backendLoad.computeIfPresent(state.backendId(), (id, value) -> Math.max(0, value - 1));
        }
    }

    public void evictStaleSessions() {
        long now = System.currentTimeMillis();
        Iterator<Map.Entry<UUID, SessionLifecycleState>> iterator = activeSessions.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<UUID, SessionLifecycleState> entry = iterator.next();
            SessionLifecycleState state = entry.getValue();

            if (state.isExpired(now, config.sessionTtlMs())) {
                state.deactivate();
                backendLoad.computeIfPresent(state.backendId(), (id, value) -> Math.max(0, value - 1));
                iterator.remove();
            }
        }
    }

    public int activeSessionCount() {
        return activeSessions.size();
    }

    public Map<String, Integer> backendLoadSnapshot() {
        return new HashMap<>(backendLoad);
    }
}
