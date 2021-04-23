package xyz.byronhawksmith.pathfinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import xyz.byronhawksmith.graph.DirectedGraph;
import xyz.byronhawksmith.graph.Tree;
import xyz.byronhawksmith.graphComponents.VertexPathWrapper;

public class TreePathFinder extends PathFinder {

    private Tree tree;

    public enum SearchType {
        BREADTH_FIRST_SEARCH, UNIFORM_COST_SEARCH
    }

    public TreePathFinder(Tree tree) {
        super();
        this.setTree(tree);
    }

    public TreePathFinder(Tree tree, List<VertexPathWrapper> explored, List<VertexPathWrapper> frontier) {
        super(explored, frontier);
        this.setTree(tree);
    }

    public Tree getTree() {
        return tree;
    }

    private void setTree(Tree tree) {
        this.tree = tree;
    }

    public PathData treeSearch(String goalVertexName, String startVertexName, SearchType searchType) {
        if (searchType == null) {
            throw new NullPointerException("The provided search type was null.");
        }

        Path searchHistory = new Path();
        Path path = null;
        Queue<VertexPathWrapper> q = new LinkedList<>();

        VertexPathWrapper startVertexPathWrapper = new VertexPathWrapper(startVertexName, new Path());
        startVertexPathWrapper.getPath().addVertexNameToPath(startVertexName);

        this.explored.add(startVertexPathWrapper);
        q.add(startVertexPathWrapper);

        while (!q.isEmpty()) {
            VertexPathWrapper currentVertexPathWrapper = q.poll();

            if (currentVertexPathWrapper.getVertexName().equals(goalVertexName)) {
                path = currentVertexPathWrapper.getPath();
                break;
            }

            /* Update searchHistory before looking at successors */
            searchHistory.addVertexNameToPath(currentVertexPathWrapper.getVertexName());

            /* Get successors */
            List<String> successors = null;

            if (searchType.equals(SearchType.BREADTH_FIRST_SEARCH)) {
                successors = tree.getVertexSuccessorNames(currentVertexPathWrapper.getVertexName(),
                        Arrays.asList(DirectedGraph.Option.ALPHABETIC));
            } else if (searchType.equals(SearchType.UNIFORM_COST_SEARCH)) {
                successors = tree.getVertexSuccessorNames(currentVertexPathWrapper.getVertexName(),
                        Arrays.asList(DirectedGraph.Option.ORDER_BY_COST));
            }

            for (String successorVertexName : successors) {
                if (!containsVertexName(explored, successorVertexName)) {
                    Path newPath = new Path(currentVertexPathWrapper.getPath());
                    newPath.addVertexNameToPath(successorVertexName);
                    VertexPathWrapper newVertexPathWrapper = new VertexPathWrapper(successorVertexName, newPath);
                    explored.add(newVertexPathWrapper);
                    q.add(newVertexPathWrapper);
                }
            }
        }

        resetInternalVariables();
        return new PathData(path, searchHistory);
    }

    /* https://en.wikipedia.org/wiki/Breadth-first_search */
    // public PathData breadthFirstSearch(String goalVertexName, String
    // startVertexName) {

    // }

    /* AKA: Best First Search */
    // public PathData uniformCostSearch(String goalVertexName, String
    // startVertexName) {

    // }

    private boolean containsVertexName(final List<VertexPathWrapper> list, final String vertexName) {
        return list.stream().anyMatch(o -> o.getVertexName().equals(vertexName));
    }

    private void resetInternalVariables() {
        setExplored(new ArrayList<VertexPathWrapper>());
        setFrontier(new ArrayList<VertexPathWrapper>());
    }

}
