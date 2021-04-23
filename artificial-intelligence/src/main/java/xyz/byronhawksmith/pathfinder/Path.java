package xyz.byronhawksmith.pathfinder;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private List<String> path;

    public Path() {
        setPath(new ArrayList<String>());
    }

    /* Copy Constructor */
    public Path(Path p) {
        setPath(new ArrayList<String>());
        for (String s : p.getPath()) {
            addVertexNameToPath(s);
        }
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

    public void reversePath() {
        java.util.Collections.reverse(path);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (String vertex : path) {
            sb.append(vertex);
            sb.append(" ");
        }

        return sb.toString();
    }
}
