package xyz.byronhawksmith.pathfinder;

import java.util.ArrayList;
import java.util.List;

public class PathData {

    private List<String> path;
    private List<String> searchHistory;

    public PathData() {
        setPath(new ArrayList<String>());
        setSearchHistory(new ArrayList<String>());
    }

    public List<String> getSearchHistory() {
        return searchHistory;
    }

    public void addVertexNameToSearchHistory(String vertexName) {
        searchHistory.add(vertexName);
    }

    private void setSearchHistory(List<String> searchHistory) {
        this.searchHistory = searchHistory;
    }

    public List<String> getPath() {
        return path;
    }

    public void addVertexNameToPath(String vertexName) {
        path.add(vertexName);
    }

    private void setPath(List<String> path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Backtracked Path: ".concat(path.toString()).concat("\nSearch History: ")
                .concat(searchHistory.toString());
    }

}
