package assignment.graph;

import java.util.*;

/**
 * Created by Koo Lee on 12/5/2014.
 */
public class Dijkstra {

    private Map<Node, Set<Edge>> edgeMap = new HashMap<Node, Set<Edge>>();
    private Map<Node, Status> scoreMap = new HashMap<Node, Status>();
    private Set<Edge> lastEdges = new LinkedHashSet<Edge>();

    private int complexity = 0;

    public List<Status> sp(Edge[] edges, Node from, Node to) {
        for (Edge edge : edges) {
            put(edge.from, edge);
            put(edge.to, edge);
            lastEdges.add(edge);
        }

        scoreMap.get(from).score = 0;
        Node recent = from;

        while (true) {
            if (lastEdges.size() == 0) break;

            Node candidate = null;
            int score = Integer.MAX_VALUE;
            for (Edge edge : edgeMap.get(recent)) {
                if (lastEdges.contains(edge)) {
                    complexity += 1;

                    Node now;
                    Node before;
                    if (edge.from.equals(recent)) {
                        now = edge.to;
                        before = edge.from;
                    } else {
                        now = edge.from;
                        before = edge.to;
                    }

                    Status status = scoreMap.get(now);
                    status.node = now;
                    status.link = before;

                    int sumScore = edge.weight;
                    if (before != null)
                        sumScore += scoreMap.get(status.link).score;

                    if (status.score > sumScore) {
                        status.setScore(sumScore);
                    }

                    if (score > status.score) {
                        candidate = now;
                        score = status.score;
                    }

                    lastEdges.remove(edge);
                }
            }
            if (candidate != null) {
                recent = candidate;
            } else {
                break;
            }
        }

        List<Status> result = new ArrayList<Status>();
        Status status = scoreMap.get(to);
        while ((status.link) != null) {
            result.add(status);
            status = scoreMap.get(status.link);
        }

        return result;
    }

    private void put(Node node, Edge edge) {
        if (!edgeMap.containsKey(node)) {
            edgeMap.put(node, new LinkedHashSet<Edge>());
            scoreMap.put(node, new Status(Integer.MAX_VALUE));
        }

        edgeMap.get(node).add(edge);
    }

    public static void main(String[] args) {
        Dijkstra dijkstra = new Dijkstra();
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        Node e = new Node("e");

        Edge[] initialNodes = {
                new Edge(b, c, 4),
                new Edge(a, b, 3),
                new Edge(b, d, 2),
                new Edge(d, c, 5),
                new Edge(c, e, 6),
                new Edge(a, d, 7),
                new Edge(d, e, 4)};

        List<Status> statuses = dijkstra.sp(initialNodes, a, e);

        for (Status status : statuses) {
            System.out.println(status);
        }

        System.out.println(dijkstra.complexity);
    }
}
