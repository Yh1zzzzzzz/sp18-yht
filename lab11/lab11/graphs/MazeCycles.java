package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    int s;
    int t;
    boolean targetFound;
    private Maze maze;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        int N =m.N();
        s = m.xyTo1D(1, 1);
        t = m.xyTo1D(N, N);
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        dfs(s);
    }
    private void dfs(int v) {
        marked[v] = true;
        for (int w : maze.adj(v)) {
            if (marked[w] && edgeTo[v] != w) {
                edgeTo[w] = v;
                announce();
                return;
            }
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(w);

            }
        }
    }
    // Helper methods go here
}

