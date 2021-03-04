package xyz.byronhawksmith;

import xyz.byronhawksmith.graph.Tree;
import xyz.byronhawksmith.json.GraphJsonParser;
import xyz.byronhawksmith.pathfinder.TreePathFinder;

public class App {

        public static void main(String[] args) {
                GraphJsonParser parser = new GraphJsonParser();

                Tree tree = new Tree(parser.generateGraphFromJsonFile(
                                "/artificial-intelligence/src/main/java/xyz/byronhawksmith/resources/graph.json"));

                TreePathFinder pathFinder = new TreePathFinder(tree);

                String result = pathFinder.breadthFirstSearch("Bucharest", "Arad");

                System.out.println(result);
        }
}
