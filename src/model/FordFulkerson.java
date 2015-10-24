package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Dan Joel Canqui Aviles
 */
public class FordFulkerson {

    private final Graph graph;

    private int maxFlow;

    public FordFulkerson(Graph graph) {
        this.graph = graph;
    }

    public int getMaxFlow() {
        return maxFlow;
    }

    public void begin() {
        buildSimplePath();
        while (graph.getTarget().getPrevious() != null) {
            maxFlow += calculateFlow();
            buildSimplePath();
        }
    }

    private void buildSimplePath() {
        restartGraph();
        Stack<Vertex> stack = new Stack<>();
        Vertex current = graph.getSource();
        Vertex target = graph.getTarget();
        stack.push(current);
        while (!current.equals(target) & !stack.empty()) {
            current = stack.pop();
            visitVertex(current, stack);
        }
    }

    private void visitVertex(Vertex current, Stack<Vertex> stack) {
        List<Edge> edges;
        Vertex destiny;
        if (!current.isVisited()) {
            current.setVisited(Boolean.TRUE);
            edges = current.getEdges();
            for (Edge edge : edges) {
                if (edge.isEnabled()) {
                    destiny = edge.getDestiny();
                    if (destiny.getPrevious() == null) {
                        destiny.setPrevious(current);
                        stack.push(destiny);
                    }
                }
            }
        }
    }

    private void restartGraph() {
        graph.getSource().restart();
        graph.getTarget().restart();
        List<Vertex> vertices = graph.getVertices();
        for (Vertex node : vertices) {
            node.restart();
        }
    }

    private int calculateFlow() {
        Edge edge;
        int result = Integer.MAX_VALUE;
        Vertex current = graph.getTarget();
        List<Edge> edges = new ArrayList<>();
        while (current.getPrevious() != null) {
            edge = findEdge(current.getPrevious(), current);
            result = Math.min(result, edge.getCapacity() - edge.getFlow());
            current = current.getPrevious();
            edges.add(edge);
        }
        for (Edge newEdge : edges) {
            newEdge.setFlow(result + newEdge.getFlow());
        }
        return result;
    }

    private Edge findEdge(Vertex source, Vertex target) {
        for (Edge edge : source.getEdges()) {
            if (edge.getDestiny().equals(target)) {
                return edge;
            }
        }
        return null;
    }
}
