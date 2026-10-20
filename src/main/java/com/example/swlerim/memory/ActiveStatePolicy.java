package com.example.swlerim.memory;

public interface ActiveStatePolicy {
    boolean shouldRetain(SessionRecord record, long nowEpochMs);
}
