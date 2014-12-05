package assignment.graph;

/**
 * Created by Koo Lee on 12/5/2014.
 */
public final class Edge implements Comparable<Edge> {
    protected Node from;
    protected Node to;
    protected int weight;

    protected Edge(Node from, Node to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        if (weight > o.weight) {
            return 1;
        } else if (weight < o.weight) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return from.toString() + " " + to.toString() + " " + weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (from == null || to == null) return false;
        if (from.equals(edge.to) && to.equals(edge.to)) return true;

        if (!from.equals(edge.from)) return false;
        if (!to.equals(edge.to)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = from != null ? from.hashCode() : 0;
        result = result + (to != null ? to.hashCode() : 0);
        return result;
    }
}
