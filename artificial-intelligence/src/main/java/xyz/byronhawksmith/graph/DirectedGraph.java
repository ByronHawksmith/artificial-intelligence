package xyz.byronhawksmith.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xyz.byronhawksmith.graphComponents.Edge;
import xyz.byronhawksmith.graphComponents.Vertex;

public class DirectedGraph {

    protected Map<String, Vertex> vertexMap;
    protected Map<String, Edge> edgeMap;

    public enum Option {
        DEFAULT,
        ALPHABETIC,
        ORDER_BY_COST
    }

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

    public List<String> getVertexSuccessorNames(String name, List<Option> options) {
        List<String> outgoingEdgeNames = vertexMap.get(name).getOutgoingEdgeNames();
        List<String> vertexSuccessorNames = new ArrayList<>();

        if (options.contains(Option.ALPHABETIC)) {
            java.util.Collections.sort(outgoingEdgeNames);
        }

        List<Edge> outgoingEdges = new ArrayList<>();

        for (String edgeName : outgoingEdgeNames) {
            outgoingEdges.add(getEdge(edgeName));
        }

        /* https://howtodoinjava.com/java/sort/collections-sort/ */
        if (options.contains(Option.ORDER_BY_COST)) {
            Comparator<Edge> compareByCost = new Comparator<Edge>() {
                @Override
                public int compare(Edge e1, Edge e2) {

                    Integer e1Weight = Integer.valueOf(e1.getWeight());
                    Integer e2Weight = Integer.valueOf(e2.getWeight());

                    return e1Weight.compareTo(e2Weight);
                }
            };

            java.util.Collections.sort(outgoingEdges, compareByCost);
        }

        for (Edge edge : outgoingEdges) {
            vertexSuccessorNames.add(edge.getDestinationVertexName());
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
