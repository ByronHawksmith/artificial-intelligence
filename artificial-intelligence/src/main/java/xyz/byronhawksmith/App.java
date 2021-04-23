package xyz.byronhawksmith;

import xyz.byronhawksmith.graph.Tree;
import xyz.byronhawksmith.json.GraphJsonParser;
import xyz.byronhawksmith.pathfinder.PathData;
import xyz.byronhawksmith.pathfinder.TreePathFinder;

public class App {

        public static void main(String[] args) {

                Tree tree = GraphJsonParser.generateTreeFromJsonFile(
                                "/artificial-intelligence/src/main/java/xyz/byronhawksmith/resources/graph.json");

                TreePathFinder pathFinder = new TreePathFinder(tree);

                PathData result = pathFinder.treeSearch("Bucharest", "Arad",
                                TreePathFinder.SearchType.BREADTH_FIRST_SEARCH);
                // PathData result = pathFinder.treeSearch("Bucharest", "Arad",
                //                 TreePathFinder.SearchType.UNIFORM_COST_SEARCH);

                System.out.println(result.toString());
        }
}
