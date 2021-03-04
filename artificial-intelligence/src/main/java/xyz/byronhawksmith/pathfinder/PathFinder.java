package xyz.byronhawksmith.pathfinder;

import java.util.ArrayList;
import java.util.List;

public class PathFinder {
    
    /* A sequence of nodes (v1, v2, ..., vn-1, vn) is a path iff, for all 1 <= i < n (Vi, Vi+1) is an element of Edges. */
    /* A path contains a cycle iff the same node is visited more than once. */

    protected List<String> explored;
    protected List<String> frontier;

    public PathFinder() {
        setExplored(new ArrayList<String>());
        setFrontier(new ArrayList<String>());
    }

    public PathFinder(List<String> explored, List<String> frontier) {
        setExplored(explored);
        setFrontier(frontier);
    }

    public List<String> getExplored() {
        return explored;
    }

    public void setExplored(List<String> explored) {
        this.explored = explored;
    }

    public List<String> getFrontier() {
        return frontier;
    }

    public void setFrontier(List<String> frontier) {
        this.frontier = frontier;
    }

}
