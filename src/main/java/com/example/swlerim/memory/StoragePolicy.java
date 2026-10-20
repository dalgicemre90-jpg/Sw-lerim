package com.example.swlerim.memory;

public final class StoragePolicy {
    public static final class InMemory {
        public static final long PLAYER_SESSION_TTL_MS = 300_000L;
        public static final long TEMP_STATE_TTL_MS = 60_000L;
    }

    public static final class External {
        public static final long PROFILE_CACHE_TTL_MS = 300_000L;
        public static final long LOBBY_STATE_TTL_MS = 120_000L;
    }
}
