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

}
