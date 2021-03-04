package xyz.byronhawksmith.pathfinder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import xyz.byronhawksmith.graph.Tree;

public class TreePathFinder extends PathFinder {

    private Tree tree;

    public TreePathFinder(Tree tree) {
        super();
        this.setTree(tree);
    }

    public TreePathFinder(Tree tree, List<String> explored, List<String> frontier) {
        super(explored, frontier);
        this.setTree(tree);
    }

    public Tree getTree() {
        return tree;
    }

    private void setTree(Tree tree) {
        this.tree = tree;
    }

    // https://en.wikipedia.org/wiki/Breadth-first_search
    public String breadthFirstSearch(String goalVertexName, String startVertexName) {
        String result = "";
        Queue<String> q = new LinkedList<>();
        this.explored.add(startVertexName);
        q.add(startVertexName);

        while (!q.isEmpty()) {
            String v = q.poll();
            if (v.equals(goalVertexName)) {
                result = v;
                break;
            }
            for (String edgeName : tree.getVertexSuccessorNames(v)) {
                if (!explored.contains(edgeName)) {
                    explored.add(edgeName);
                    q.add(edgeName);
                }
            }
        }

        resetInternalVariables();
        return result;
    }

    private void resetInternalVariables() {
        setExplored(new ArrayList<String>());
        setFrontier(new ArrayList<String>());
    }

}
