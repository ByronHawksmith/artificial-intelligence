package xyz.byronhawksmith.pathfinder;

public class PathData {
    Path path;
    Path searchHistory;

    public PathData(Path path, Path searchHistory) {
        this.path = path;
        this.searchHistory = searchHistory;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Backtracked Path: ");
        sb.append(path.toString());

        if (searchHistory != null) {
            sb.append("\n");
            sb.append("Search History: ");
            sb.append(searchHistory.toString());
        }

        sb.append("\n");

        return sb.toString();
    }
}
