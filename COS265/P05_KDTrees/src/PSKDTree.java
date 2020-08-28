import java.util.Iterator;

/**
 * PSKDTree is a Point collection that provides nearest neighbor searching using
 * 2d tree
 */
public class PSKDTree<Value> implements PointSearch<Value> {
    private Point min;
    PointSearch<Value> kdTree;
    private class Node {
        Point p;
        Value v;
        Node left, right;
        Partition.Direction dir;
    }
    double xMin;
    double xMax;
    double yMin;
    double yMax;
    int treeSize = 0;
    Node root;
    // constructor makes empty kD-tree
    public PSKDTree() { }

    // add the given Point to kD-tree
    public void put(Point p, Value v) {
        if ( treeSize == 0 ) {
            root = new Node();
            root.p = p;
            root.v = v;
            root.dir = Partition.Direction.LEFTRIGHT;
        }
        else {
            root = putX(root, p, v);
        }
        treeSize ++;
        if ( treeSize == 1 ) {
            xMin = p.x();
            xMax = p.x();
            yMin = p.y();
            yMax = p.y();
        }
        else {
            if ( p.x() <= xMin ) {
                xMin = p.x();
            }
            else if ( p.x() >= xMax ) {
                xMax = p.x();
            }
            if ( p.y() <= yMin ) {
                yMin = p.y();
            }
            else if ( p.y() >= yMax ) {
                yMax = p.y();
            }
        }
    }
    private Node putX( Node node, Point p, Value v) {
        if( node == null ) {
            Node alt = new Node();
            alt.p = p;
            alt.v = v;
            alt.dir = Partition.Direction.DOWNUP;
            return alt;
        }
        if ( p.x() < node.p.x() ) {
            node.left = putY( node.left,p,v );
        }
        else if ( p.x() > node.p.x() ) {
            node.right = putY( node.right,p,v );
        }
        else if ( p.x() == node.p.x() ) {
            if ( p.y() == node.p.y() ) node.v = v;
            else node.left = putY( node.left,p,v );
        }
        return node;
    }
    private Node putY( Node node, Point p, Value v) {
        if( node == null ) {
            Node alt = new Node();
            alt.p = p;
            alt.v = v;
            alt.dir = Partition.Direction.LEFTRIGHT;
            return alt;
        }
        if ( p.y() < node.p.y() ) {
            node.left = putX( node.left,p,v );
        }
        else if ( p.y() > node.p.y() ) {
            node.right = putX( node.right,p,v );
        }
        else if ( p.y() == node.p.y() ) {
            if ( p.x() == node.p.x() ) node.v = v;
            else node.left = putX( node.left,p,v );
        }
        return node;
    }

    public Value get(Point p) {
        if ( p == root.p ) {
            return root.v;
        }
        Node check = root;
        while ( p != check.p ) {
            if ( check.dir == Partition.Direction.LEFTRIGHT ) {
                if ( p.x() > check.p.x() ) {
                    check = check.right;
                }
                else check = check.left;
            }
            else if ( check.dir == Partition.Direction.DOWNUP ) {
                if ( p.y() > check.p.y() ) {
                    check = check.right;
                }
                else check = check.left;
            }
            else if ( check == null ) {
                break;
            }
            else {
                throw new RuntimeException("Node in KDTree is improperly defined!");
            }
        }
        if ( check == null ) return null;
        return check.v;
    }

    public boolean contains(Point p) {
        if(get(p) == null) return false;
        return true;
    }

    public Value getNearest(Point p) {
        return null;
    }

    // return an iterable of all points in collection
    public Iterable<Point> points() {

        Queue<Point> q = new Queue<Point>();
        inorder(root,q);
        return q;
    }

    private void inorder(Node node, Queue<Point> q){
        if(node == null) return;
        inorder(node.left, q);
        q.enqueue(node.p);
        inorder(node.right,q);
    }

    // return an iterable of all partitions that make up the kD-tree
    public Iterable<Partition> partitions() {
        return null;
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

    // return the min and max for all Points in collection.
    // The min-max pair will form a bounding box for all Points.
    // if kD-tree is empty, return null.
    public Point min() { return new Point(xMin,yMin); }
    public Point max() { return new Point(xMax,yMax); }

    // return the number of Points in kD-tree
    public int size() { return treeSize; }

    // return whether the kD-tree is empty
    public boolean isEmpty() {
        if( treeSize == 0 ) return true;
        return false;
    }

    // place your timing code or unit testing here
    public static void main(String[] args) {

    }

}
