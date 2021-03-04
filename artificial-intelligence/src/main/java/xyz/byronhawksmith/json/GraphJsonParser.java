package xyz.byronhawksmith.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import xyz.byronhawksmith.graph.DirectedGraph;

public class GraphJsonParser {

    private DirectedGraph graph;

    public GraphJsonParser() {
        graph = new DirectedGraph();
    }

    public DirectedGraph generateGraphFromJsonFile(String path) {
        Reader reader = initFileReader(path);
        JSONObject jsonObject = new JSONObject(new JSONTokener(reader));
        JSONArray jsonArray = jsonObject.getJSONArray("graph");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject relationJson = jsonArray.getJSONObject(i);
            graph.addRelation(relationJson.getString("origin"), relationJson.getString("destination"));
        }

        return graph;
    }

    public Reader initFileReader(String path) {
        try {
            Reader reader;
            String filePath = new File("").getAbsolutePath();
            reader = new FileReader(filePath.concat(path));
            return reader;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

}
