package com.example.swlerim.memory;

public record MemoryPolicyConfig(
        long sessionTtlMs,
        long staleCleanupIntervalMs,
        long inactiveHeartbeatMs,
        int maxBackends,
        int maxPlayersPerBackend
) {
    public MemoryPolicyConfig {
        if (sessionTtlMs <= 0L) {
            throw new IllegalArgumentException("sessionTtlMs must be positive");
        }
        if (staleCleanupIntervalMs <= 0L) {
            throw new IllegalArgumentException("staleCleanupIntervalMs must be positive");
        }
        if (inactiveHeartbeatMs <= 0L) {
            throw new IllegalArgumentException("inactiveHeartbeatMs must be positive");
        }
        if (maxBackends <= 0) {
            throw new IllegalArgumentException("maxBackends must be positive");
        }
        if (maxPlayersPerBackend <= 0) {
            throw new IllegalArgumentException("maxPlayersPerBackend must be positive");
        }
    }
}
