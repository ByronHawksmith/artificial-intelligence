package xyz.byronhawksmith.graphComponents;

public class Edge {

    private String name;
    private int weight;
    private String originVertexName; // origin, predecessor, parent
    private String destinationVertexName; // destination, successor, child

    public Edge(String name, String originVertexName, String destinationVertexName) {
        this.setName(name);
        this.setWeight(-1);
        this.setOriginVertexName(originVertexName);
        this.setDestinationVertexName(destinationVertexName);
    }

    public Edge(String name, int weight, String originVertexName, String destinationVertexName) {
        this.setName(name);
        this.setWeight(weight);
        this.setOriginVertexName(originVertexName);
        this.setDestinationVertexName(destinationVertexName);
    }

    public int getWeight() {
        return weight;
    }

    private void setWeight(int weight) {
        this.weight = weight;
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
