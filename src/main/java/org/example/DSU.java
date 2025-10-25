package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DSU {
    private final Map<String, String> parent = new HashMap<>();

    public DSU(List<String> nodes) {
        for (String node : nodes) {
            parent.put(node, node);
        }
    }

    public String find(String x) {
        if (!parent.get(x).equals(x)) {
            parent.put(x, find(parent.get(x)));
        }
        return parent.get(x);
    }

    public void union(String a, String b) {
        String pa = find(a);
        String pb = find(b);
        if (!pa.equals(pb)) {
            parent.put(pa, pb);
        }
    }
}
