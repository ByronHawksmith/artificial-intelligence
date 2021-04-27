package xyz.byronhawksmith.pathfinder;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import xyz.byronhawksmith.graph.DirectedGraph;
import xyz.byronhawksmith.graph.Tree;
import xyz.byronhawksmith.graphComponents.VertexPathWrapper;

public class TreePathFinder {

    private Tree tree;

    public TreePathFinder(Tree tree) {
        super();
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
        Set<VertexPathWrapper> explored = new HashSet<>();
        Queue<VertexPathWrapper> frontier = new LinkedList<>();
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

            /*
             * NOTE: THIS IS OPTIONAL: Update searchHistory before looking at successors
             */
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
        /*
         * TODO: Give hashmap the size of num vertices in the graph
         */
        Map<String, VertexPathWrapper> explored = new HashMap<>();
        Queue<VertexPathWrapper> frontier = new PriorityQueue<>(
                (VertexPathWrapper vp1, VertexPathWrapper vp2) -> vp1.compareTo(vp2));
        VertexPathWrapper startVertexPathWrapper;
        VertexPathWrapper currentVertexPathWrapper;
        VertexPathWrapper newVertexPathWrapper;
        VertexPathWrapper oldVertexPathWrapper;
        List<String> successors;
        Path newPath;

        /* Create start vertex pathfinding object */
        startVertexPathWrapper = new VertexPathWrapper(startVertexName, new Path(Arrays.asList(startVertexName), 0));

        /* Add start vertex to the explored set and to the frontier */
        explored.put(startVertexName, startVertexPathWrapper);
        frontier.add(startVertexPathWrapper);

        while (!frontier.isEmpty()) {
            currentVertexPathWrapper = frontier.poll();

            if (currentVertexPathWrapper.getVertexName().equals(goalVertexName)) {
                path = currentVertexPathWrapper.getPath();
                break;
            }

            /*
             * NOTE: THIS IS OPTIONAL: Update searchHistory before looking at successors
             */
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
                oldVertexPathWrapper = explored.get(successorVertexName);

                if (oldVertexPathWrapper != null) {
                    if (oldVertexPathWrapper.getPath().getWeight() >= newVertexPathWrapper.getPath().getWeight()) {
                        explored.put(successorVertexName, newVertexPathWrapper);
                        frontier.add(newVertexPathWrapper);
                        frontier.remove(oldVertexPathWrapper);
                    }
                } else {
                    explored.put(successorVertexName, newVertexPathWrapper);
                    frontier.add(newVertexPathWrapper);
                }
            }
        }

        return new PathData(path, searchHistory);
    }

    public PathData depthFirstSearch(String goalVertexName, String startVertexName) {
        /* Initialise variables */
        Path searchHistory = new Path();
        Path path = null;
        Deque<VertexPathWrapper> frontier = new ArrayDeque<>(); /* Being used as a stack */
        VertexPathWrapper startVertexPathWrapper;
        VertexPathWrapper currentVertexPathWrapper;
        VertexPathWrapper newVertexPathWrapper;
        List<String> successors;
        Path newPath;

        /* Create start vertex pathfinding object */
        startVertexPathWrapper = new VertexPathWrapper(startVertexName, new Path(Arrays.asList(startVertexName)));

        /* Add start vertex to the frontier */
        frontier.add(startVertexPathWrapper);

        while (!frontier.isEmpty()) {
            currentVertexPathWrapper = frontier.pop();

            if (currentVertexPathWrapper.getVertexName().equals(goalVertexName)) {
                path = currentVertexPathWrapper.getPath();
                break;
            }

            /*
             * NOTE: THIS IS OPTIONAL: Update searchHistory before looking at successors
             */
            searchHistory.addVertexNameToPathList(currentVertexPathWrapper.getVertexName());

            /* Get successors */
            successors = tree.getVertexSuccessorNames(currentVertexPathWrapper.getVertexName(),
                    Arrays.asList(DirectedGraph.Option.REVERSE_ALPHABETIC));

            for (String successorVertexName : successors) {
                if (!currentVertexPathWrapper.getPath().getPathList().contains(successorVertexName)) {
                    newPath = new Path(currentVertexPathWrapper.getPath());
                    newPath.addVertexNameToPathList(successorVertexName);
                    newVertexPathWrapper = new VertexPathWrapper(successorVertexName, newPath);

                    frontier.addFirst(newVertexPathWrapper);
                }
            }
        }

        return new PathData(path, searchHistory);
    }

    private boolean containsVertexName(final Collection<VertexPathWrapper> list, final String vertexName) {
        return list.stream().anyMatch(o -> o.getVertexName().equals(vertexName));
    }

    private VertexPathWrapper getVertexPathWrapper(final Collection<VertexPathWrapper> list, final String vertexName) {
        return list.stream().filter(o -> o.getVertexName().equals(vertexName)).findFirst().orElse(null);
    }
}
