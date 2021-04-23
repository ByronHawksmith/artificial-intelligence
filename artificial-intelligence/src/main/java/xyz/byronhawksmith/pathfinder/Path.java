package xyz.byronhawksmith.pathfinder;

import java.util.ArrayList;
import java.util.List;

public class Path implements Comparable<Path> {
    private List<String> pathList;
    private int weight;

    public Path() {
        setPathList(new ArrayList<String>());
        weight = -1;
    }

    public Path(List<String> pathList) {
        this.pathList = pathList;
        weight = -1;
    }

    public Path(List<String> pathList, int weight) {
        this.pathList = pathList;
        this.weight = weight;
    }

    /* Copy Constructor */
    public Path(Path p) {
        setPathList(new ArrayList<String>());
        for (String s : p.getPathList()) {
            addVertexNameToPathList(s);
        }
        weight = p.getWeight();
    }

    public List<String> getPathList() {
        return pathList;
    }

    private void setPathList(List<String> path) {
        this.pathList = path;
    }

    public void addVertexNameToPathList(String vertexName) {
        pathList.add(vertexName);
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (String vertex : pathList) {
            sb.append(vertex);
            sb.append(" ");
        }

        if (weight != -1) {
            sb.append("\nTotal Weight: ");
            sb.append(getWeight());
        }

        return sb.toString();
    }

    @Override
    public int compareTo(Path o) {
        Integer thisWeight = Integer.valueOf(getWeight());
        Integer oWeight = Integer.valueOf(o.getWeight());

        return thisWeight.compareTo(oWeight);
    }

}