package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Solver {
    private class Node implements Comparable<Node> {
        private WorldState ws;
        private int moveCounter;
        private Node parent;


        @Override
        public int compareTo(Node o) {
            return this.ws.estimatedDistanceToGoal() - o.ws.estimatedDistanceToGoal();
        }
    }




    private MinPQ<Node> NodePQ = new MinPQ<>();
    private List<WorldState> bestWay ;
    private Set<WorldState> parents;
    private int move = 0;




    public Solver(WorldState initial) {
        Node newNode = createNode(initial);
        NodePQ.insert(newNode);
        /*
        *
        * 1.将邻居全部送入PQ
        * 3.将自己移除PQ
        * 2.将自己添加到最佳队列、将自己添加到家长队列
        * 4.更新initial
        * */


        while (!initial.isGoal()) {
            Node n = NodePQ.delMin();
            addneibortoPQ(n);
            bestWay.addLast(n.ws);
            parents.add(n.ws);
            initial = n.ws;
            move += 1;
        }

    }
    public int moves() {
        return move - 1;
    }
    public Iterable<WorldState> solution() {
        return bestWay;
    }
    private Node createNode(WorldState initial) {
        Node f = new Node();
        f.moveCounter = 0;
        f.parent = null;
        f.ws = initial;
        return f;
    }
    private void addneibortoPQ(Node s) {
        int m = s.moveCounter + 1;
        Iterator<WorldState> it = s.ws.neighbors().iterator();
        while (it.hasNext()) {
            Node temp = new Node();
            WorldState tws = it.next();
            temp.moveCounter = m;
            temp.parent = s;
            temp.ws = tws;
            if (!parents.contains(tws)) {
                NodePQ.insert(temp);
            }
        }


    }

}
