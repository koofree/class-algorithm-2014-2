package assignment.graph;

/**
 * Created by Koo Lee on 12/5/2014.
 */
public final class Status {
    protected int score;
    protected Node node;
    protected Node link;

    public Status(int score) {
        this.score = score;
    }

    protected void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return link + " " + node + " " + score;
    }
}
