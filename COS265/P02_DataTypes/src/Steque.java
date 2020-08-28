import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Steque<Item> implements Iterable<Item> {
    private Node top,bottom;
    private int N = 1;

    public Steque() {

    }
    private class Node{
        Item item;
        Node next;
    }
    // returns the number of items stored
    public int size() {
        return N;
    }

    // returns true iff steque is empty
    public boolean isEmpty() {
        return top == null;
    }

    // enqueues item to bottom of steque
    public void enqueue(Item item) {
        Node oldbottom = bottom;
        bottom = new Node();
        bottom.item = item;
        bottom.next = null;
        if(isEmpty()){
            top = bottom;
        }
        else{
            oldbottom.next = bottom;
        }

    }

    // pushes item to top of steque
    public void push(Item item) {
        Node oldtop = top;
        top = new Node();
        top.item = item;
        if(isEmpty()){
            bottom = top;
        }
        else {
            top.next = oldtop;
        }
        N++;
    }

    // pops and returns top item
    public Item pop() throws NoSuchElementException {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        Item item = top.item;
        top = top.next;
        N--;
        return item;

    }

    // returns new Iterator<Item> that iterates over items in steque
    @Override
    public Iterator<Item> iterator() {
        return new StequeIterator();
    }

    private class StequeIterator implements Iterator<Item>{
        private Node current = top;

        public boolean hasNext() {return current != null;}

        public void remove() {throw new UnsupportedOperationException();}

        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // perform unit testing here
    public static void main(String[] args) throws NoSuchElementException {
        Steque<String> testSteque = new Steque<>();
        testSteque.push("Hello");
        testSteque.enqueue("Kenobi");
        for(String s : testSteque){
            StdOut.println(s);
        }
    }
}