package xyz.byronhawksmith;

public class Edge {

    private String name;
    private String originVertexName; // origin, predecessor, parent
    private String destinationVertexName; // destination, successor, child

    public Edge(String name, String originVertexName, String destinationVertexName) {
        this.setName(name);
        this.setOriginVertexName(originVertexName);
        this.setDestinationVertexName(destinationVertexName);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getOriginVertexName() {
        return originVertexName;
    }

    private void setOriginVertexName(String originVertexName) {
        this.originVertexName = originVertexName;
    }

    public String getDestinationVertexName() {
        return destinationVertexName;
    }

    private void setDestinationVertexName(String destinationVertexName) {
        this.destinationVertexName = destinationVertexName;
    }
    
}
