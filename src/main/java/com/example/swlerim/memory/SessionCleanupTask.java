package com.example.swlerim.memory;

import java.util.Objects;

public final class SessionCleanupTask implements CleanupTask {
    private final MemoryPolicyManager manager;
    private final MemoryPolicyConfig config;

    public SessionCleanupTask(MemoryPolicyManager manager, MemoryPolicyConfig config) {
        this.manager = Objects.requireNonNull(manager, "manager");
        this.config = Objects.requireNonNull(config, "config");
    }

    @Override
    public void runCleanup() {
        manager.evictStaleSessions();
    }

    public long intervalMs() {
        return config.staleCleanupIntervalMs();
    }
}
