package org.example;

import java.util.List;

public class Result {
    private List<Edge> edges;
    private double totalCost;
    private int operations;

    public Result(List<Edge> edges, double totalCost, int operations) {
        this.edges = edges;
        this.totalCost = totalCost;
        this.operations = operations;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public int getOperations() {
        return operations;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public void setOperations(int operations) {
        this.operations = operations;
    }
}
