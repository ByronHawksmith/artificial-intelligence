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
                PathData result;

                String destination = "Bucharest";
                String origin = "Arad";

                result = pathFinder.breadthFirstSearch(destination, origin);
                System.out.println(result.toString());

                result = pathFinder.uniformCostSearch(destination, origin);
                System.out.println(result.toString());

                result = pathFinder.depthFirstSearch(destination, origin);
                System.out.println(result.toString());

                result = pathFinder.iterativeDeepeningDepthFirstSearch(destination, origin);
                System.out.println(result.toString());
        }
}
