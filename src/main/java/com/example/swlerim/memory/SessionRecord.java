package com.example.swlerim.memory;

import java.util.Objects;
import java.util.UUID;

public final class SessionRecord {
    private final UUID uuid;
    private final String username;
    private final String backendId;
    private long lastSeenEpochMs;
    private boolean active;

    public SessionRecord(UUID uuid, String username, String backendId, long nowEpochMs) {
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

    public void touch(long nowEpochMs) {
        this.lastSeenEpochMs = nowEpochMs;
    }

    public boolean active() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isStale(long nowEpochMs, long ttlMs) {
        return nowEpochMs - lastSeenEpochMs > ttlMs;
    }

    public void clear() {
        this.active = false;
        this.lastSeenEpochMs = 0L;
    }
}
