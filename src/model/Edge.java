package model;

/**
 *
 * @author Dan Joel Canqui Aviles
 */
public class Edge {

    private final Vertex destiny;

    private final int capacity;

    private int flow;

    public Edge(Vertex destiny, int capacity) {
        this.destiny = destiny;
        this.capacity = capacity;
        
    }

    public Vertex getDestiny() {
        return destiny;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    public boolean isEnabled() {
        return flow < capacity;
    }
}
