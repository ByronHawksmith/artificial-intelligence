package xyz.byronhawksmith.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import xyz.byronhawksmith.graph.Tree;

public class GraphJsonParser {

    private GraphJsonParser() {
    }

    public static Tree generateTreeFromJsonFile(String path) {
        Tree tree = new Tree();
        Reader reader = initFileReader(path);
        JSONObject jsonObject = new JSONObject(new JSONTokener(reader));
        JSONArray jsonArray = jsonObject.getJSONArray("graph");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject relationJson = jsonArray.getJSONObject(i);

            tree.addRelation(relationJson.getString("origin"), relationJson.getString("destination"));

            if (relationJson.getBoolean("bidirectional")) {
                tree.addRelation(relationJson.getString("destination"), relationJson.getString("origin"));
            }
        }

        return tree;
    }

    public static Reader initFileReader(String path) {
        try {
            Reader reader;
            String filePath = new File("").getAbsolutePath();
            reader = new FileReader(filePath.concat(path));
            return reader;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

}
