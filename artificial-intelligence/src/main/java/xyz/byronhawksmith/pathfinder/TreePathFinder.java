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
    public PathData breadthFirstSearch(String goalVertexName, String startVertexName) {
        PathData pathData = new PathData();
        Queue<String> q = new LinkedList<>();
        this.explored.add(startVertexName);
        q.add(startVertexName);

        while (!q.isEmpty()) {
            String vertexName = q.poll();

            if (vertexName.equals(goalVertexName)) {
                pathData = backtrackFromDestinationToOrigin(goalVertexName, startVertexName, pathData);
                break;
            }

            // Update searchHistory before looking at successors
            pathData.addVertexNameToSearchHistory(vertexName);

            for (String edgeName : tree.getVertexSuccessorNames(vertexName, true)) {
                if (!explored.contains(edgeName)) {
                    explored.add(edgeName);
                    q.add(edgeName);
                }
            }
        }

        resetInternalVariables();
        return pathData;
    }

    public PathData backtrackFromDestinationToOrigin(String goalVertexName, String startVertexName, PathData pathData) {
        String currentVertexName = goalVertexName;

        while (!currentVertexName.equals(startVertexName)) {
            pathData.addVertexNameToPath(currentVertexName);
            currentVertexName = tree.getVertexPredecessorNames(currentVertexName).get(0);
        }

        pathData.addVertexNameToPath(currentVertexName);

        return pathData;
    }

    private void resetInternalVariables() {
        setExplored(new ArrayList<String>());
        setFrontier(new ArrayList<String>());
    }

}
