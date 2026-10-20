package com.example.swlerim.memory;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class BackendNode {
    private final String id;
    private final int maxPlayers;
    private final int currentPlayers;

    public BackendNode(String id, int currentPlayers, int maxPlayers) {
        this.id = Objects.requireNonNull(id, "id");
        this.currentPlayers = currentPlayers;
        this.maxPlayers = maxPlayers;
    }

    public String id() {
        return id;
    }

    public int currentPlayers() {
        return currentPlayers;
    }

    public int maxPlayers() {
        return maxPlayers;
    }

    public boolean isFull() {
        return currentPlayers >= maxPlayers;
    }

    public int remainingCapacity() {
        return Math.max(0, maxPlayers - currentPlayers);
    }

    public static BackendNode selectLeastLoaded(List<BackendNode> nodes) {
        return nodes.stream()
                .filter(node -> !node.isFull())
                .min(Comparator.comparingInt(BackendNode::remainingCapacity))
                .orElseThrow(() -> new IllegalStateException("No backend has available capacity"));
    }
}
