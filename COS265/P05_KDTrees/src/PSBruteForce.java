import java.util.Iterator;

/**
 * PSBruteForce is a Point collection that provides brute force
 * nearest neighbor searching using red-black tree.
 */
public class PSBruteForce<Value> implements PointSearch<Value> {
    RedBlackBST<Point, Value> rbTree;
    private Point min;
    private Point kmin[];
    private double minX;
    private double minY;
    private double maxX;
    private double maxY;

    // constructor makes empty collection
    public PSBruteForce() {
        rbTree = new RedBlackBST<>();
    }

    // add the given Point to KDTree
    public void put(Point p, Value v) {
        rbTree.put(p, v);

        if(size() == 1) {
            minX = p.x();
            minY = p.y();
            maxX = p.x();
            maxY = p.y();
        }
        else{
            if(p.y() <= minY){
                minY = p.y();
            }
            else if(p.y() >= maxY){
                maxY = p.y();
            }
            if(p.x() <= minX){
                minX = p.x();
            }
            else if(p.x() >= maxX){
                maxX = p.x();
            }
        }
    }

    public Value get(Point p) {
        return rbTree.get(p);
    }

    public boolean contains(Point p) {
        return rbTree.contains(p);
    }

    // return an iterable of all points in collection
    public Iterable<Point> points() {
        return rbTree.keys();
    }

    // return the Point that is closest to the given Point
    public Point nearest(Point p) {

        for(Point q : points()){
            min = q;
            break;
        }
        for(Point q : points()){
            if(q.dist(p) < min.dist(p)){
                min = q;
            }
        }
        return min;
    }

    // return the Value associated to the Point that is closest to the given Point
    public Value getNearest(Point p) {
        return get(nearest(p));
    }

    // return the min and max for all Points in collection.
    // The min-max pair will form a bounding box for all Points.
    // if KDTree is empty, return null.
    public Point min() {
        if(rbTree.isEmpty()){
            return null;

        }
        return new Point(minX, minY);
    }
    public Point max() {
        if(rbTree.isEmpty()){
            return null;

        }
        return new Point(maxX, maxY);
    }

    // return the k nearest Points to the given Point
    public Iterable<Point> nearest(Point p, int k) {


        MaxPQ<PointDist> points = new MaxPQ<>();
        for(Point q : points()){
            points.insert(new PointDist(q, q.dist(p)));
            if(points.size() > k){
                points.delMax();
            }
        }

        Stack<Point> s = new Stack<>();

        for (PointDist point: points) {
            s.push(point.p());
        }



        return s;
    }

    public Iterable<Partition> partitions() { return null; }

    // return the number of Points in KDTree
    public int size() { return rbTree.size(); }

    // return whether the KDTree is empty
    public boolean isEmpty() {
        if(rbTree.isEmpty()){
            return true;

        }
        return false;
    }

    // place your timing code or unit testing here
    public static void main(String[] args) {
    }
}
