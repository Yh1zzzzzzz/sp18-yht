package lab11.graphs;
import java.util.LinkedList;
import java.util.Queue;
/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    int s;
    int t;
    boolean found;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        found = false;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs(int t) {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        Queue<Integer> q = new LinkedList<Integer>();
        if (found) {
            return;
        }
        if (t == s) {
            found = true;
        }
        marked[s] = true;
        announce();
        q.add(s);
        while (!q.isEmpty()) {
            int temp = q.peek();
            if (temp == t) {
                return;
            }
            q.remove();
            for (int w : maze.adj(temp)) {
                if (!marked[w]) {
                q.add(w);
                distTo[w] = distTo[temp] + 1;
                edgeTo[w] = temp;
                marked[w] = true;
                announce();
                }
            }
        }

    }


    @Override
    public void solve() {
        bfs(t);
    }
}

