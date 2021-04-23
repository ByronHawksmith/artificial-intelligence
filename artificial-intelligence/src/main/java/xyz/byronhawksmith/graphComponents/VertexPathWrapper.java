package xyz.byronhawksmith.graphComponents;

import xyz.byronhawksmith.pathfinder.Path;

public class VertexPathWrapper {
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
}
