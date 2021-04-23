package xyz.byronhawksmith.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xyz.byronhawksmith.graphComponents.Edge;
import xyz.byronhawksmith.graphComponents.Vertex;

public class DirectedGraph {

    protected Map<String, Vertex> vertexMap;
    protected Map<String, Edge> edgeMap;

    public DirectedGraph() {
        this.vertexMap = new HashMap<>();
        this.edgeMap = new HashMap<>();
    }

    public DirectedGraph(Map<String, Vertex> vertexMap, Map<String, Edge> edgeMap) {
        this.vertexMap = vertexMap;
        this.edgeMap = edgeMap;
    }

    private Edge getEdge(String name) {
        return edgeMap.get(name);
    }

    private Vertex getVertex(String name) {
        return vertexMap.get(name);
    }

    public List<String> getVertexSuccessorNames(String name) {
        List<String> outgoingEdgeNames = vertexMap.get(name).getOutgoingEdgeNames();
        List<String> vertexSuccessorNames = new ArrayList<>();

        for (String edgeName : outgoingEdgeNames) {
            vertexSuccessorNames.add(getEdge(edgeName).getDestinationVertexName());
        }

        return vertexSuccessorNames;
    }

    public List<String> getVertexSuccessorNames(String name, boolean isAlphabetic) {
        if (!isAlphabetic) {
            return getVertexSuccessorNames(name);
        }

        List<String> outgoingEdgeNames = vertexMap.get(name).getOutgoingEdgeNames();
        List<String> vertexSuccessorNames = new ArrayList<>();

        java.util.Collections.sort(outgoingEdgeNames);

        for (String edgeName : outgoingEdgeNames) {
            vertexSuccessorNames.add(getEdge(edgeName).getDestinationVertexName());
        }

        return vertexSuccessorNames;
    }

    public List<String> getVertexPredecessorNames(String name) {
        List<String> incomingEdgeNames = vertexMap.get(name).getIncomingEdgeNames();
        List<String> vertexPredecessorNames = new ArrayList<>();

        for (String edgeName : incomingEdgeNames) {
            vertexPredecessorNames.add(getEdge(edgeName).getOriginVertexName());
        }

        return vertexPredecessorNames;
    }

    public boolean containsVertex(String name) {
        return vertexMap.containsKey(name);
    }

    protected Edge addEdgeToGraph(String originName, String destinationName) {
        Edge e = new Edge(originName.concat(destinationName), originName, destinationName);
        edgeMap.put(e.getName(), e);
        return e;
    }

    protected Edge addWeightedEdgeToGraph(String originName, String destinationName, int weight) {
        Edge e = new Edge(originName.concat(destinationName), weight, originName, destinationName);
        edgeMap.put(e.getName(), e);
        return e;
    }

    public boolean containsEdge(String name) {
        return edgeMap.containsKey(name);
    }

}
