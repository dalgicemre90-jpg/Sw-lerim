package com.example.swlerim.memory;

public final class ExternalStateStore {
    private final MemoryPolicyConfig config;

    public ExternalStateStore(MemoryPolicyConfig config) {
        this.config = config;
    }

    public long profileCacheTtlMs() {
        return config.sessionTtlMs();
    }

    public long lobbyStateTtlMs() {
        return config.inactiveHeartbeatMs();
    }
}
