package xyz.byronhawksmith.graphComponents;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o == this)
            return true;
        if (!(o instanceof VertexPathWrapper)) {
            return false;
        }
        VertexPathWrapper vertexPathWrapper = (VertexPathWrapper) o;
        return Objects.equals(vertexName, vertexPathWrapper.vertexName) && Objects.equals(path, vertexPathWrapper.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertexName, path);
    }

    @Override
    public int compareTo(VertexPathWrapper o) {
        return getPath().compareTo(o.getPath());
    }
}
