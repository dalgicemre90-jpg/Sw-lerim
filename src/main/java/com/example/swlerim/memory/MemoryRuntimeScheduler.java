package com.example.swlerim.memory;

public final class MemoryRuntimeScheduler {
    private final SessionRegistry registry;
    private final MemoryPolicyConfig config;
    private final Thread cleanupThread;

    public MemoryRuntimeScheduler(SessionRegistry registry, MemoryPolicyConfig config) {
        this.registry = registry;
        this.config = config;

        this.cleanupThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(config.staleCleanupIntervalMs());
                    registry.evictStaleSessions();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }, "memory-runtime-scheduler");

        this.cleanupThread.setDaemon(true);
    }

    public void start() {
        cleanupThread.start();
    }

    public void stop() {
        cleanupThread.interrupt();
    }
}
