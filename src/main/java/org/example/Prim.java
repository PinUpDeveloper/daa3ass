package org.example;

import java.util.*;

public class Prim {

    public static Map<String, Object> run(Graph graph) {
        Map<String, List<Edge>> adj = new HashMap<>();
        for (String node : graph.getNodes()) adj.put(node, new ArrayList<>());
        for (Edge e : graph.getEdges()) {
            adj.get(e.getFrom()).add(e);
            adj.get(e.getTo()).add(new Edge(e.getTo(), e.getFrom(), e.getWeight()));
        }

        Set<String> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingDouble(Edge::getWeight));
        List<Edge> mstEdges = new ArrayList<>();
        double totalCost = 0;

        String start = graph.getNodes().get(0);
        visited.add(start);
        pq.addAll(adj.get(start));

        while (!pq.isEmpty() && visited.size() < graph.getNodes().size()) {
            Edge e = pq.poll();
            if (visited.contains(e.getTo())) continue;

            visited.add(e.getTo());
            mstEdges.add(e);
            totalCost += e.getWeight();

            for (Edge next : adj.get(e.getTo())) {
                if (!visited.contains(next.getTo())) pq.add(next);
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("edges", mstEdges);
        result.put("totalCost", totalCost);
        return result;
    }
}
