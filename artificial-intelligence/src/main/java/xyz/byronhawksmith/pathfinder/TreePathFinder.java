package xyz.byronhawksmith.pathfinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import xyz.byronhawksmith.graph.DirectedGraph;
import xyz.byronhawksmith.graph.Tree;
import xyz.byronhawksmith.graphComponents.VertexPathWrapper;

public class TreePathFinder extends PathFinder {

    private Tree tree;

    public TreePathFinder(Tree tree) {
        super();
        this.setTree(tree);
    }

    public TreePathFinder(Tree tree, List<VertexPathWrapper> explored, Queue<VertexPathWrapper> frontier) {
        super(explored, frontier);
        this.setTree(tree);
    }

    public Tree getTree() {
        return tree;
    }

    private void setTree(Tree tree) {
        this.tree = tree;
    }

    public PathData breadthFirstSearch(String goalVertexName, String startVertexName) {
        /* Initialise variables */
        Path searchHistory = new Path();
        Path path = null;
        explored = new ArrayList<>();
        frontier = new LinkedList<>();
        VertexPathWrapper startVertexPathWrapper;
        VertexPathWrapper currentVertexPathWrapper;
        VertexPathWrapper newVertexPathWrapper;
        List<String> successors;
        Path newPath;

        /* Create start vertex pathfinding object */
        startVertexPathWrapper = new VertexPathWrapper(startVertexName, new Path(Arrays.asList(startVertexName)));

        /* Add start vertex to the explored set and to the frontier */
        explored.add(startVertexPathWrapper);
        frontier.add(startVertexPathWrapper);

        while (!frontier.isEmpty()) {
            currentVertexPathWrapper = frontier.poll();

            if (currentVertexPathWrapper.getVertexName().equals(goalVertexName)) {
                path = currentVertexPathWrapper.getPath();
                break;
            }

            /* Update searchHistory before looking at successors */
            searchHistory.addVertexNameToPathList(currentVertexPathWrapper.getVertexName());

            /* Get successors */
            successors = tree.getVertexSuccessorNames(currentVertexPathWrapper.getVertexName(),
                    Arrays.asList(DirectedGraph.Option.ALPHABETIC));

            for (String successorVertexName : successors) {
                if (!containsVertexName(explored, successorVertexName)) {
                    newPath = new Path(currentVertexPathWrapper.getPath());
                    newPath.addVertexNameToPathList(successorVertexName);
                    newVertexPathWrapper = new VertexPathWrapper(successorVertexName, newPath);

                    explored.add(newVertexPathWrapper);
                    frontier.add(newVertexPathWrapper);
                }
            }
        }

        return new PathData(path, searchHistory);
    }

    public PathData uniformCostSearch(String goalVertexName, String startVertexName) {
        /* Initialise variables */
        Path searchHistory = new Path();
        Path path = null;
        explored = new ArrayList<>();
        frontier = new PriorityQueue<>((VertexPathWrapper vp1, VertexPathWrapper vp2) -> vp1.compareTo(vp2));
        VertexPathWrapper startVertexPathWrapper;
        VertexPathWrapper currentVertexPathWrapper;
        VertexPathWrapper newVertexPathWrapper;
        VertexPathWrapper oldVertexPathWrapper;
        List<String> successors;
        Path newPath;

        /* Create start vertex pathfinding object */
        startVertexPathWrapper = new VertexPathWrapper(startVertexName, new Path(Arrays.asList(startVertexName), 0));

        /* Add start vertex to the explored set and to the frontier */
        explored.add(startVertexPathWrapper);
        frontier.add(startVertexPathWrapper);

        while (!frontier.isEmpty()) {
            currentVertexPathWrapper = frontier.poll();

            if (currentVertexPathWrapper.getVertexName().equals(goalVertexName)) {
                path = currentVertexPathWrapper.getPath();
                break;
            }

            /* Update searchHistory before looking at successors */
            searchHistory.addVertexNameToPathList(currentVertexPathWrapper.getVertexName());

            /* Get successors */
            successors = tree.getVertexSuccessorNames(currentVertexPathWrapper.getVertexName(),
                    Arrays.asList(DirectedGraph.Option.ALPHABETIC));

            for (String successorVertexName : successors) {
                newPath = new Path(currentVertexPathWrapper.getPath());
                newPath.addVertexNameToPathList(successorVertexName);
                newPath.addEdgeWeightToPathList(
                        tree.getEdgeWeight(currentVertexPathWrapper.getVertexName(), successorVertexName));
                newVertexPathWrapper = new VertexPathWrapper(successorVertexName, newPath);

                /*
                 * If the path weight of the old path to the successor vertex (which is in the
                 * explored set) is greater than or equal to the newly calculated alternative
                 * path weight to the successor vertex; replace the old path with the new path
                 * thus guaranteeing we always have the shortest path to a given vertex in our
                 * frontier.
                 */
                oldVertexPathWrapper = getVertexFromList(explored, successorVertexName);

                if (oldVertexPathWrapper != null) {
                    if (oldVertexPathWrapper.getPath().getWeight() >= newVertexPathWrapper.getPath().getWeight()) {
                        explored.add(newVertexPathWrapper);
                        frontier.add(newVertexPathWrapper);
                    }
                } else {
                    explored.add(newVertexPathWrapper);
                    frontier.add(newVertexPathWrapper);
                }
            }
        }

        return new PathData(path, searchHistory);
    }

    private boolean containsVertexName(final List<VertexPathWrapper> list, final String vertexName) {
        return list.stream().anyMatch(o -> o.getVertexName().equals(vertexName));
    }

    private VertexPathWrapper getVertexFromList(final List<VertexPathWrapper> list, final String vertexName) {
        return list.stream().filter(o -> o.getVertexName().equals(vertexName)).findFirst().orElse(null);
    }
}
