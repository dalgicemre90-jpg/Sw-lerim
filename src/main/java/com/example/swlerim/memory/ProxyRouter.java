package com.example.swlerim.memory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ProxyRouter {
    private final List<BackendNode> backends;

    public ProxyRouter(List<BackendNode> backends) {
        this.backends = List.copyOf(backends);
    }

    public String selectBackendFor(UUID playerId) {
        if (backends.isEmpty()) {
            throw new IllegalStateException("No backends configured");
        }

        BackendNode selected = BackendNode.selectLeastLoaded(backends);
        return selected.id();
    }

    public Map<String, Integer> distributePlayers(List<UUID> players) {
        Map<String, Integer> counts = new HashMap<>();
        for (BackendNode backend : backends) {
            counts.put(backend.id(), 0);
        }

        for (UUID ignored : players) {
            String backendId = selectBackendFor(ignored);
            counts.compute(backendId, (key, value) -> value + 1);
        }

        return counts;
    }
}
