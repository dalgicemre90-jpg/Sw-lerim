package com.example.swlerim;

import com.example.swlerim.memory.BackendNode;
import com.example.swlerim.memory.MemoryPolicyConfig;
import com.example.swlerim.memory.PlayerSessionStore;
import com.example.swlerim.memory.ProxyRouter;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class App {
    public static void main(String[] args) {
        MemoryPolicyConfig config = new MemoryPolicyConfig(
                300_000L,
                60_000L,
                30_000L,
                3,
                100
        );

        PlayerSessionStore store = new PlayerSessionStore(config);

        BackendNode nodeA = new BackendNode("paper-1", 2, 4);
        BackendNode nodeB = new BackendNode("paper-2", 2, 4);
        BackendNode nodeC = new BackendNode("paper-3", 2, 4);

        ProxyRouter router = new ProxyRouter(List.of(nodeA, nodeB, nodeC));

        UUID alice = UUID.randomUUID();
        UUID bob = UUID.randomUUID();
        UUID charlie = UUID.randomUUID();

        store.register(alice, "alice", "paper-1");
        store.register(bob, "bob", "paper-2");
        store.register(charlie, "charlie", "paper-3");

        System.out.println("Active session count: " + store.activeCount());
        System.out.println("Router choice: " + router.selectBackendFor(alice));

        store.touch(alice, 0L);
        store.touch(bob, 0L);
        store.touch(charlie, 0L);

        store.unregister(bob);
        System.out.println("After logout, active session count: " + store.activeCount());

        Map<String, Integer> distribution = router.distributePlayers(List.of(alice, bob, charlie));
        System.out.println("Distribution by backend: " + distribution);

        System.out.println("Sw-lerim runtime memory-first architecture initialized.");
        System.out.println("JDK: " + Runtime.version());
    }
}
