package com.example.swlerim.memory;

import java.util.Objects;
import java.util.UUID;

public final class SessionLifecycleState {
    private final UUID uuid;
    private final String username;
    private final String backendId;
    private volatile long lastSeenEpochMs;
    private volatile boolean active;

    public SessionLifecycleState(UUID uuid, String username, String backendId, long nowEpochMs) {
        this.uuid = Objects.requireNonNull(uuid, "uuid");
        this.username = Objects.requireNonNull(username, "username");
        this.backendId = Objects.requireNonNull(backendId, "backendId");
        this.lastSeenEpochMs = nowEpochMs;
        this.active = true;
    }

    public UUID uuid() {
        return uuid;
    }

    public String username() {
        return username;
    }

    public String backendId() {
        return backendId;
    }

    public long lastSeenEpochMs() {
        return lastSeenEpochMs;
    }

    public boolean active() {
        return active;
    }

    public void touch(long nowEpochMs) {
        this.lastSeenEpochMs = nowEpochMs;
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
        this.lastSeenEpochMs = 0L;
    }

    public boolean isExpired(long nowEpochMs, long ttlMs) {
        return nowEpochMs - lastSeenEpochMs > ttlMs;
    }
}
