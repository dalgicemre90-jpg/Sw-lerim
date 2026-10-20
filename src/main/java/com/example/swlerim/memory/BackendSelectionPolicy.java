package com.example.swlerim.memory;

import java.util.*;

public final class BackendSelectionPolicy {
    private final SessionRegistry registry;

    public BackendSelectionPolicy(SessionRegistry registry) {
        this.registry = Objects.requireNonNull(registry, "registry");
    }

    public String chooseBackend(List<String> backends) {
        if (backends == null || backends.isEmpty()) {
            throw new IllegalArgumentException("Backends cannot be empty");
        }

        String selected = null;
        int minLoad = Integer.MAX_VALUE;

        for (String backend : backends) {
            int current = registry.backendLoadSnapshot().getOrDefault(backend, 0);
            if (current < minLoad) {
                minLoad = current;
                selected = backend;
            }
        }

        return Objects.requireNonNull(selected, "selected backend");
    }
}
