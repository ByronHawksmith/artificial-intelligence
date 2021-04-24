package xyz.byronhawksmith.pathfinder;

import java.util.List;
import java.util.Queue;

import xyz.byronhawksmith.graphComponents.VertexPathWrapper;

public class PathFinder {

    /*
     * A sequence of nodes (v1, v2, ..., vn-1, vn) is a path iff, for all 1 <= i < n
     * (Vi, Vi+1) is an element of Edges.
     */
    /* A path contains a cycle iff the same node is visited more than once. */

    protected List<VertexPathWrapper> explored;
    protected Queue<VertexPathWrapper> frontier;

    public PathFinder() {
    }

    public PathFinder(List<VertexPathWrapper> explored, Queue<VertexPathWrapper> frontier) {
        setExplored(explored);
        setFrontier(frontier);
    }

    public List<VertexPathWrapper> getExplored() {
        return explored;
    }

    public void setExplored(List<VertexPathWrapper> explored) {
        this.explored = explored;
    }

    public Queue<VertexPathWrapper> getFrontier() {
        return frontier;
    }

    public void setFrontier(Queue<VertexPathWrapper> frontier) {
        this.frontier = frontier;
    }

}
