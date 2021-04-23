package xyz.byronhawksmith.pathfinder;

import java.util.ArrayList;
import java.util.List;

import xyz.byronhawksmith.graphComponents.VertexPathWrapper;

public class PathFinder {

    /*
     * A sequence of nodes (v1, v2, ..., vn-1, vn) is a path iff, for all 1 <= i < n
     * (Vi, Vi+1) is an element of Edges.
     */
    /* A path contains a cycle iff the same node is visited more than once. */

    protected List<VertexPathWrapper> explored;
    protected List<VertexPathWrapper> frontier;

    public PathFinder() {
        setExplored(new ArrayList<VertexPathWrapper>());
        setFrontier(new ArrayList<VertexPathWrapper>());
    }

    public PathFinder(List<VertexPathWrapper> explored, List<VertexPathWrapper> frontier) {
        setExplored(explored);
        setFrontier(frontier);
    }

    public List<VertexPathWrapper> getExplored() {
        return explored;
    }

    public void setExplored(List<VertexPathWrapper> explored) {
        this.explored = explored;
    }

    public List<VertexPathWrapper> getFrontier() {
        return frontier;
    }

    public void setFrontier(List<VertexPathWrapper> frontier) {
        this.frontier = frontier;
    }

}
