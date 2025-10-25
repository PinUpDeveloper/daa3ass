# Minimum Spanning Tree (MST) Algorithms — Kruskal vs Prim

## 📘 Project Overview
This project implements and compares two classical Minimum Spanning Tree (MST) algorithms: **Prim’s** and **Kruskal’s**.  
The program reads input graph data from a JSON file, constructs MSTs using both algorithms, and outputs comparative performance metrics and costs in JSON and text formats.

The project is developed in **Java 17** using **Maven** for dependency management.  
It utilizes the **Jackson** library for JSON parsing and writing.

---

## 📂 Project Structure
```
mst-assignment/
│
├── pom.xml                      # Maven configuration (dependencies & build settings)
├── src/
│   └── main/
│       ├── java/
│       │   └── org/example/
│       │       ├── Main.java    # Entry point; runs MST comparison and handles file I/O
│       │       ├── Graph.java   # Graph representation
│       │       ├── Edge.java    # Edge data structure
│       │       ├── Prim.java    # Prim's algorithm implementation
│       │       ├── Kruskal.java # Kruskal's algorithm implementation
│       │       └── Result.java  # Result model for JSON output
│       └── resources/
│           ├── input.json       # Input graph data
│           └── output.json      # Output comparison results
│
└── MST_Report.txt               # Academic report summarizing the results
```

---

## ⚙️ How to Run

### 1. Clone or Extract the Project
```bash
git clone <repository-url>
cd mst-assignment
```

### 2. Build with Maven
Ensure Maven is installed and run:
```bash
mvn clean package
```

### 3. Run the Program
```bash
mvn exec:java -Dexec.mainClass="org.example.Main"
```

### 4. Input and Output Files
- Input file: `src/main/resources/input.json`  
- Output file: `src/main/resources/output.json`  
- Text summary: `MST_Report.txt`

---

## 🧩 Example Input
```json
{
  "graphs": [
    {
      "id": 1,
      "nodes": ["A", "B", "C", "D", "E"],
      "edges": [
        {"from": "A", "to": "B", "weight": 4},
        {"from": "A", "to": "C", "weight": 3},
        {"from": "B", "to": "C", "weight": 2},
        {"from": "B", "to": "D", "weight": 5},
        {"from": "C", "to": "D", "weight": 7},
        {"from": "C", "to": "E", "weight": 8},
        {"from": "D", "to": "E", "weight": 6}
      ]
    }
  ]
}
```

---

## 📊 Example Output
```json
[
  {
    "graphId": 1,
    "primTotalCost": 16.0,
    "kruskalTotalCost": 16.0,
    "primTimeMs": 2,
    "kruskalTimeMs": 1
  }
]
```

---

## 🧠 Algorithm Comparison Summary

| Metric | Prim’s Algorithm | Kruskal’s Algorithm |
|--------|------------------|---------------------|
| **Approach** | Grows MST from a starting vertex | Sorts edges and joins sets |
| **Time Complexity** | O(E log V) | O(E log E) |
| **Best for** | Dense graphs | Sparse graphs |
| **Performance (Observed)** | Slightly slower due to priority queue operations | Faster in small graph sets |

---

## 🏁 Conclusion
Both Prim’s and Kruskal’s algorithms yield identical MST costs for connected, weighted, undirected graphs.  
However, **Kruskal’s algorithm** generally performs marginally faster for sparse graphs, while **Prim’s algorithm** is more efficient for dense graphs when implemented with optimized data structures.

---

## 👨‍💻 Author
**Tazhibayev Marsel**  
Course: **Design and Analysis of Algorithms (DAA)**  
Year: 2025