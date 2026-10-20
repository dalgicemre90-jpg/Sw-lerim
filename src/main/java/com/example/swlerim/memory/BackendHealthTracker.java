package com.example.swlerim.memory;

import java.util.*;

public final class BackendHealthTracker {
    private final Map<String, Integer> health = new HashMap<>();

    public void markHealthy(String backendId) {
        health.put(backendId, 0);
    }

    public void markWarning(String backendId, int penalty) {
        health.put(backendId, penalty);
    }

    public Map<String, Integer> snapshot() {
        return new HashMap<>(health);
    }

    public String selectBestBackend(List<String> candidates) {
        if (candidates == null || candidates.isEmpty()) {
            throw new IllegalArgumentException("At least one backend candidate is required");
        }

        return candidates.stream()
                .min(Comparator.comparingInt(id -> health.getOrDefault(id, 0)))
                .orElse(candidates.get(0));
    }
}
