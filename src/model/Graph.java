package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dan Joel Canqui Aviles
 */
public class Graph {

    private static final String SOURCE = "^[sS]\\d*$";

    private static final String TARGET = "^[tT]\\d*$";

    private final Map<String, Vertex> vertices;

    private Vertex source;

    private Vertex target;

    public Graph(String adjancencyList) {
        vertices = new HashMap<>();
        parserGraph(adjancencyList);
        buildSourceOfGraph();
        buildTargetOfGraph();
    }

    public Vertex getSource() {
        return source;
    }

    public Vertex getTarget() {
        return target;
    }

    public List<Vertex> getVertices() {
        return new ArrayList<>(vertices.values());
    }

    private void buildSourceOfGraph() {
        List<Vertex> sources = search(SOURCE);
        int numberOfSources = sources.size();
        if (numberOfSources == 1) {
            source = sources.get(0);
        } else if (sources.size() > 1) {
            source = new Vertex("S");
            for (Vertex current : sources) {
                source.addEdge(new Edge(current, 1000));
            }
        }
    }

    private void buildTargetOfGraph() {
        List<Vertex> targets = search(TARGET);
        int numberOfSources = targets.size();
        if (numberOfSources == 1) {
            target = targets.get(0);
        } else if (targets.size() > 1) {
            target = new Vertex("T");
            for (Vertex current : targets) {
                current.addEdge(new Edge(target, 1000));
            }
        }
    }

    private List<Vertex> search(String pattern) {
        List<Vertex> result = new ArrayList<>();
        List<Vertex> allVertex = getVertices();
        for (Vertex current : allVertex) {
            if (current.getId().matches(pattern)) {
                result.add(current);
            }
        }
        return result;
    }

    private void parserGraph(String adjacencyList) {
        String[] lines, edges;
        lines = adjacencyList.split("\n");
        for (String line : lines) {
            edges = line.split("->");
            addEdges(edges);
        }
    }

    private void addEdges(String[] edges) {
        Vertex sourceVertex, targetVertex;
        String[] edge;
        int capacity;

        sourceVertex = getVertex(edges[0]);
        for (int i = 1; i < edges.length; i++) {
            edge = edges[i].split(",");
            targetVertex = getVertex(edge[0]);
            capacity = Integer.parseInt(edge[1]);
            sourceVertex.addEdge(new Edge(targetVertex, capacity));
        }
    }

    private Vertex getVertex(String id) {
        if (!vertices.containsKey(id)) {
            vertices.put(id, new Vertex(id));
        }
        return vertices.get(id);
    }
}
