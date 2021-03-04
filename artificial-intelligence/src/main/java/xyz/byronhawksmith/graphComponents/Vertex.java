package xyz.byronhawksmith.graphComponents;

import java.util.ArrayList;
import java.util.List;

public class Vertex { // node

    private String name;
    private List<String> incomingEdgeNames;
    private List<String> outgoingEdgeNames;

    public Vertex(String name, List<String> incomingEdgeNames, List<String> outgoingEdgeNames) {
        this.setName(name);
        this.setIncomingEdgeNames(incomingEdgeNames);
        this.setOutgoingEdgeNames(outgoingEdgeNames);
    }

    public Vertex(String name) {
        this.setName(name);
        this.incomingEdgeNames = new ArrayList<>();
        this.outgoingEdgeNames = new ArrayList<>();
    }

    public List<String> getOutgoingEdgeNames() {
        return outgoingEdgeNames;
    }

    private void setOutgoingEdgeNames(List<String> outgoingEdgeNames) {
        this.outgoingEdgeNames = outgoingEdgeNames;
    }

    public List<String> getIncomingEdgeNames() {
        return incomingEdgeNames;
    }

    private void setIncomingEdgeNames(List<String> incomingEdgeNames) {
        this.incomingEdgeNames = incomingEdgeNames;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public void addOutgoingEdgeName(String name) {
        outgoingEdgeNames.add(name);
    }

    public void addIncomingEdgeName(String name) {
        incomingEdgeNames.add(name);
    }
    
}
