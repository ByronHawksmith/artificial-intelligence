package xyz.byronhawksmith.graphComponents;

import xyz.byronhawksmith.pathfinder.Path;

public class VertexPathWrapper implements Comparable<VertexPathWrapper> {
    private String vertexName;
    private Path path;

    public VertexPathWrapper(String vertex, Path path) {
        this.vertexName = vertex;
        this.path = path;
    }

    public String getVertexName() {
        return this.vertexName;
    }

    public Path getPath() {
        return this.path;
    }

    @Override
    public int compareTo(VertexPathWrapper o) {
        return getPath().compareTo(o.getPath());
    }
}
