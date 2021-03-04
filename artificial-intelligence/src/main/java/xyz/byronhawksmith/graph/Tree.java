package xyz.byronhawksmith.graph;

import java.util.Map;

import xyz.byronhawksmith.Edge;
import xyz.byronhawksmith.Vertex;

public class Tree extends DirectedGraph {

    // A graph is a tree iff
    // a) there is a unique undirected path between any two nodes and
    // b) each node has at most one predecessor

    // A node R is a root of a graph iff R has no predecessors.
    // A node L is a leaf iff L has no successors

    public Tree(Map<String, Vertex> vertexMap, Map<String, Edge> edgeMap) {
        super(vertexMap, edgeMap);
    }

    public Tree(DirectedGraph d) {
        super(d.vertexMap, d.edgeMap);
    }

    public Tree() {
        super();
    }

    public void addRelation(String originName, String destinationName) {
        Edge e = addEdgeToGraph(originName, destinationName);

        if (containsVertex(originName)) {
            vertexMap.get(originName).addOutgoingEdgeName(e.getName());
        } else {
            Vertex v1 = new Vertex(originName);
            v1.addOutgoingEdgeName(e.getName());
            vertexMap.put(originName, v1);
        }

        if (containsVertex(destinationName)) {
            vertexMap.get(destinationName).addIncomingEdgeName(e.getName());
        } else {
            Vertex v2 = new Vertex(destinationName);
            v2.addIncomingEdgeName(e.getName());
            vertexMap.put(destinationName, v2);
        }

    }

}
