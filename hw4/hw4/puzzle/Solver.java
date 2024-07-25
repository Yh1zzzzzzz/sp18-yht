package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.List;

public class Solver {
    private class Node implements Comparable<Node> {
        private WorldState ws;
        private int moveCounter;
        private Node parent;
        int estMove = -1;


        @Override
        public int compareTo(Node o) {
            if (this.estMove < 0) {
                this.estMove = this.ws.estimatedDistanceToGoal();
            }
            if (o.estMove < 0) {
                o.estMove = o.ws.estimatedDistanceToGoal();
            }
            return this.estMove + this.moveCounter - o.estMove - o.moveCounter;
        }
    }


    private MinPQ<Node> NodePQ = new MinPQ<>();
    private List<WorldState> bestWay = new ArrayList<>() ;

    private int move = 0;


    public Solver(WorldState initial) {
        Node newNode = createNode(initial);
        NodePQ.insert(newNode);


        while (true) {
            Node n = NodePQ.delMin();
            addneibortoPQ(n);
            initial = n.ws;
            if (initial.isGoal()) {
                move = n.moveCounter;
                construct(n);
                break;
            }
        }

    }
    public int moves() {
        return move ;
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
        if (s.ws.neighbors() == null) {
            return;
        }
        for (WorldState a : s.ws.neighbors()) {
            int m = s.moveCounter + 1;
            Node temp = new Node();
            WorldState tws = a;
            temp.moveCounter = m;
            temp.parent = s;
            temp.ws = tws;
            if (s.parent == null || !tws.equals(s.parent.ws)) {
                NodePQ.insert(temp);
            }
        }
    }

    private void construct(Node node) {
        while (node != null) {
            bestWay.addFirst(node.ws);
            node = node.parent;
        }
    }

}
