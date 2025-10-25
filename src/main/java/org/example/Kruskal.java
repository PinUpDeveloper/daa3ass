package org.example;

import java.util.*;

public class Kruskal {

    static class DSU {
        int[] parent, rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if (x != parent[x]) parent[x] = find(parent[x]);
            return parent[x];
        }

        boolean union(int a, int b) {
            int pa = find(a), pb = find(b);
            if (pa == pb) return false;
            if (rank[pa] < rank[pb]) parent[pa] = pb;
            else if (rank[pa] > rank[pb]) parent[pb] = pa;
            else {
                parent[pb] = pa;
                rank[pa]++;
            }
            return true;
        }
    }

    public static Map<String, Object> run(Graph graph) {
        List<Edge> edges = new ArrayList<>(graph.getEdges());
        edges.sort(Comparator.comparingDouble(Edge::getWeight));

        Map<String, Integer> nodeIndex = new HashMap<>();
        int idx = 0;
        for (String node : graph.getNodes()) nodeIndex.put(node, idx++);

        DSU dsu = new DSU(graph.getNodes().size());
        List<Edge> mstEdges = new ArrayList<>();
        double totalCost = 0;

        for (Edge e : edges) {
            int u = nodeIndex.get(e.getFrom());
            int v = nodeIndex.get(e.getTo());
            if (dsu.union(u, v)) {
                mstEdges.add(e);
                totalCost += e.getWeight();
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("edges", mstEdges);
        result.put("totalCost", totalCost);
        return result;
    }
}
