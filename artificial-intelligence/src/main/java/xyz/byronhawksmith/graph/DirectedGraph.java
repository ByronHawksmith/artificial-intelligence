package xyz.byronhawksmith.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xyz.byronhawksmith.Edge;
import xyz.byronhawksmith.Vertex;

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
        List<String> outoingEdgeNames = vertexMap.get(name).getOutgoingEdgeNames();
        List<String> vertexSuccessorNames = new ArrayList<>();

        for (String edgeName : outoingEdgeNames) {
            vertexSuccessorNames.add(getEdge(edgeName).getDestinationVertexName());
        }

        return vertexSuccessorNames;
    }

    public boolean containsVertex(String name) {
        return vertexMap.containsKey(name);
    }

    protected Edge addEdgeToGraph(String originName, String destinationName) {
        Edge e = new Edge(originName.concat(destinationName), originName, destinationName);
        edgeMap.put(e.getName(), e);
        return e;
    }

    public boolean containsEdge(String name) {
        return edgeMap.containsKey(name);
    }

}
