import java.lang.IndexOutOfBoundsException;
import java.lang.IllegalArgumentException;
import java.util.Iterator;

/**
 * Pathfinder uses A* search to find a near optimal path
 * between to locations with given terrain.
 */

public class Pathfinder {

    private Coord start;
    private Coord end;
    private float heuristic;
    private boolean pathFound;
    private Stack<Coord> s;
    private Terrain terrain;
    private boolean[][] board;
    private int searchSize;

    /**
     * PFNode will be the key for MinPQ (used in computePath())
     */
    private class PFNode implements Comparable<PFNode> {
        Coord loc;
        PFNode fromNode;
        float runningCost;
        // loc: the location of the PFNode
        // fromNode: how did we get here? (linked list back to start)
        public PFNode(Coord loc, PFNode fromNode) {
            this.loc = loc;
            this.fromNode = fromNode;
            if(fromNode == null){
                runningCost = 0;
            }
            else{
                if(loc.getJ() >= 0 && loc.getJ() < terrain.getN() && loc.getI() >= 0 && loc.getI() < terrain.getN()){
                    runningCost = fromNode.getCost(0) + terrain.computeTravelCost(loc, fromNode.loc);
                    searchSize = (int)runningCost;
                }
            }
        }

        // compares this with that, used to find minimum cost PFNode
        public int compareTo(PFNode that) {
            if(this.getCost(0) < that.getCost(0)) return -1;
            else if (this.getCost(0) > that.getCost(0)) return 1;
            return 0;
        }

        // returns the cost to travel from starting point to this
        // via the fromNode chain
        public float getCost(float heuristic) {
            return runningCost;
        }

        // returns if this PFNode is not marked invalid
        public boolean isValid() {
            return false;
        }

        // marks the PFNode as invalid
        public void invalidate() {
        }

        // returns if the PFNode is marked as used
        public boolean isUsed() {
            return board[loc.getJ()][loc.getI()];
        }

        // marks the PFNode as used
        public void use() { }

        // returns an Iterable of PFNodes that surround this
        public Iterable<PFNode> neighbors() {
            Stack<PFNode> s = new Stack<>();
            s.push(new PFNode(new Coord(loc.getI() + 1, loc.getJ()), this));
            s.push(new PFNode(new Coord(loc.getI() - 1, loc.getJ()), this));
            s.push(new PFNode(new Coord(loc.getI() , loc.getJ() + 1), this));
            s.push(new PFNode(new Coord(loc.getI(), loc.getJ() - 1), this));
            return s;
        }
    }

    public Pathfinder(Terrain terrain) {
        this.terrain = terrain;
    }

    public void setPathStart(Coord loc) {
        start = loc;
    }

    public Coord getPathStart() {
        return start;
    }

    public void setPathEnd(Coord loc) {
        end = loc;
    }

    public Coord getPathEnd() {
        return end;
    }

    public void setHeuristic(float v)
    {
        heuristic = v;
    }

    public float getHeuristic() {
        return heuristic;
    }

    public void resetPath() {
    }

    public void computePath() {



        //Initialize the board and then print the current state of it
        board = new boolean[terrain.getN()][terrain.getN()];
        for(int i = 0; i < terrain.getN(); i++){
            for(int j = 0; j < terrain.getN(); j++){
                board[i][j] = false;
                //System.out.print(board[i][j] + " ");
            }
            //System.out.println();
        }



        s = new Stack<>();
        MinPQ<PFNode> pfQueue = new MinPQ<>();
        PFNode begin = new PFNode(start, null);

        pfQueue.insert(begin);


        while(!pfQueue.min().loc.equals(end)){
            begin = pfQueue.delMin();
            if(board[begin.loc.getJ()][begin.loc.getI()]){
                continue;
            }
            board[begin.loc.getJ()][begin.loc.getI()] = true;
            for(PFNode p : begin.neighbors()){
                //Only add the neighbors to the queue if they're in range of the board
                if(p.loc.getJ() >= 0 && p.loc.getJ() < terrain.getN() && p.loc.getI() >= 0 && p.loc.getI() < terrain.getN()){
                    pfQueue.insert(p);
                }
            }
            if(pfQueue.min().loc.equals(end)){
                break;
            }

        }












        PFNode cur = pfQueue.min();
        while(cur.fromNode != null){
            s.push(cur.loc);
            cur = cur.fromNode;
        }
        s.push(start);



        pathFound = true;
    }

    public boolean foundPath() {
        return pathFound;
    }

    public float getPathCost() {
        return searchSize;
    }

    public int getSearchSize() {
        return searchSize;
    }

    public Iterable<Coord> getPathSolution() {

        //Return a stack of coordinates for the visualizer
        return s;
    }

    public boolean wasSearched(Coord loc) {
        return false;
                //board[loc.getJ()][loc.getI()];
    }
}
