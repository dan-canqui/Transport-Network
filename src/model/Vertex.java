package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dan Joel Canqui Aviles
 */
public class Vertex implements Comparable<Vertex>{

    private final List<Edge> edges;

    private final String id;

    private boolean visited;

    private Vertex previous;


    public Vertex(String id) {
        edges = new ArrayList<>();
        this.id = id;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public String getId() {
        return id;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Vertex getPrevious() {
        return previous;
    }

    public void setPrevious(Vertex previous) {
        this.previous = previous;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public void restart() {
        visited = Boolean.FALSE;
        previous = null;
    }

    @Override
    public int compareTo(Vertex vertex) {
        return id.compareTo(vertex.id);
    }
}
