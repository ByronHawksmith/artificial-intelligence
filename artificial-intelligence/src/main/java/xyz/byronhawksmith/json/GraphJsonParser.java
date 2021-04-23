package xyz.byronhawksmith.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

            String originName = relationJson.getString("origin");
            String destinationName = relationJson.getString("destination");
            int weightOne = relationJson.getInt("weightOne");
            int weightTwo = relationJson.getInt("weightTwo");
            boolean bidirectional = relationJson.getBoolean("bidirectional");

            tree.addWeightedRelation(originName, destinationName, weightOne);

            if (bidirectional) {
                tree.addWeightedRelation(destinationName, originName, weightTwo);
            }
        }

        /* Close reader resource when no longer needed */
        closeReaderResource(reader);

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

    private static void closeReaderResource(Reader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
