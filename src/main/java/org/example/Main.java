package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.InputStream;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            // читаем input.json из ресурсов
            InputStream inputStream = Main.class.getResourceAsStream("/input.json");
            if (inputStream == null) {
                throw new RuntimeException("❌ Файл input.json не найден в resources/");
            }

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(inputStream);

            List<Map<String, Object>> outputResults = new ArrayList<>();

            for (JsonNode graphNode : root.get("graphs")) {
                int id = graphNode.get("id").asInt();
                List<String> nodes = new ArrayList<>();
                for (JsonNode n : graphNode.get("nodes")) nodes.add(n.asText());

                List<Edge> edges = new ArrayList<>();
                for (JsonNode e : graphNode.get("edges")) {
                    String from = e.get("from").asText();
                    String to = e.get("to").asText();
                    double weight = e.get("weight").asDouble();
                    edges.add(new Edge(from, to, weight));
                }

                Graph graph = new Graph(id, nodes, edges);

                // запускаем алгоритмы
                long startPrim = System.nanoTime();
                Map<String, Object> primResult = Prim.run(graph);
                long primTime = (System.nanoTime() - startPrim) / 1_000_000;

                long startKruskal = System.nanoTime();
                Map<String, Object> kruskalResult = Kruskal.run(graph);
                long kruskalTime = (System.nanoTime() - startKruskal) / 1_000_000;

                // вывод в консоль
                System.out.println("──────────────────────────────");
                System.out.println("Graph ID: " + id);
                System.out.println("Prim MST cost: " + primResult.get("totalCost") + " (" + primTime + " ms)");
                System.out.println("Kruskal MST cost: " + kruskalResult.get("totalCost") + " (" + kruskalTime + " ms)");
                System.out.println("Prim edges:");
                for (Edge e : (List<Edge>) primResult.get("edges")) {
                    System.out.println("  " + e.getFrom() + " - " + e.getTo() + " : " + e.getWeight());
                }
                System.out.println("Kruskal edges:");
                for (Edge e : (List<Edge>) kruskalResult.get("edges")) {
                    System.out.println("  " + e.getFrom() + " - " + e.getTo() + " : " + e.getWeight());
                }

                // добавляем в JSON вывод
                Map<String, Object> graphOutput = new LinkedHashMap<>();
                graphOutput.put("graphId", id);
                graphOutput.put("primTotalCost", primResult.get("totalCost"));
                graphOutput.put("kruskalTotalCost", kruskalResult.get("totalCost"));
                graphOutput.put("primTimeMs", primTime);
                graphOutput.put("kruskalTimeMs", kruskalTime);
                outputResults.add(graphOutput);
            }

            // записываем результаты в output.json
            File outFile = new File("src/main/resources/output.json");
            mapper.writerWithDefaultPrettyPrinter().writeValue(outFile, outputResults);
            System.out.println("✅ Результаты сохранены в " + outFile.getAbsolutePath());

        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
