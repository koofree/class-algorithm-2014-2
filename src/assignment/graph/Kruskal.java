package assignment.graph;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Koo Lee on 12/5/2014.
 */
public class Kruskal {
    private int complexity = 0;
    private Set<Node> findingNode = new LinkedHashSet<Node>();
    private Set<Edge> findingEdge = new LinkedHashSet<Edge>();

    public Edge[] mst(Edge[] edges, int nodeCount) {

        Arrays.sort(edges);

        for (Edge edge : edges) {
            if (findingNode.size() == nodeCount) {
                break;
            }

            complexity += 1;

            if (findingNode.contains(edge.from)) {
                if (findingNode.contains(edge.to)) {
                    continue;
                } else {
                    findingNode.add(edge.to);
                    findingEdge.add(edge);
                }
            } else {
                if (findingNode.contains(edge.to)) {
                    findingNode.add(edge.from);
                    findingEdge.add(edge);
                } else {
                    findingNode.add(edge.from);
                    findingNode.add(edge.to);
                    findingEdge.add(edge);
                }
            }
        }

        return findingEdge.toArray(new Edge[findingEdge.size()]);
    }

    public int getComplexity() {
        return complexity;
    }

    public void reset() {
        this.complexity = 0;
        this.findingEdge.clear();
        this.findingNode.clear();
    }

    public static void main(String[] args) {
        Kruskal kruskal = new Kruskal();
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        Node e = new Node("e");

        Edge[] initialNodes = {
                new Edge(b, c, 1),
                new Edge(a, b, 5),
                new Edge(b, d, 3),
                new Edge(d, c, 4),
                new Edge(c, e, 6),
                new Edge(a, d, 6),
                new Edge(d, e, 2)};

        Edge[] min = kruskal.mst(initialNodes, 5);
        for (Edge edge : min) {
            System.out.println(edge);
        }

        System.out.println(kruskal.complexity);
    }
}

