package xyz.byronhawksmith.graphComponents;

import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;

import xyz.byronhawksmith.pathfinder.Path;

public class VertexPathWrapper implements Comparable<VertexPathWrapper> {
    protected String vertexName;
    protected Path path;

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
        VertexPathWrapper rhs = (VertexPathWrapper) o;

        return new EqualsBuilder().appendSuper(super.equals(o)).append(vertexName, rhs.vertexName)
                .append(path, rhs.path).isEquals();
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
