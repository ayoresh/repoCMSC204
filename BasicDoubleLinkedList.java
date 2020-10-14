package sample;



import java.util.*;


/**
 * @author Amanda Yoresh
 */
public class BasicDoubleLinkedList<T> {


    protected Node head, tail;
    protected int size = 0;


    public BasicDoubleLinkedList(){
        head = null;
        tail = null;
    }

    public BasicDoubleLinkedList<T> addToEnd(T toAdd){

        if ((tail == null) && (head == null)){
            tail = new Node(toAdd);
            head = tail;
        }
        else{
            Node temp = new Node(toAdd);
            tail.next  = temp;
            temp.previous = tail;
            tail = temp;
        }
        size += 1;
        return this;
    }

    public BasicDoubleLinkedList<T> addToFront(T temp){
        if(head == null){
            head = new Node(temp);
            tail = head;
        }

        else{
            Node n = new Node(temp);
            head.previous = n;
            n.next = head;
            head = n;
        }
        size += 1;
        return  this;


    }
    public T getFirst(){
        return head.x;
    }

    public T getLast(){
        return tail.x;
    }
    public int getSize(){
        return size;
    }
    public ListIterator<T> iterator() throws UnsupportedOperationException, NoSuchElementException{
        iter temp = new iter();
        return temp;
    }

    public BasicDoubleLinkedList<T> remove(T tar, Comparator comp){

        if(head == null){
            return this;
        }

        if(comp.compare(head.x, tar) == 0){
            head = null;
            tail = null;
            size -= 1;
            return this;
        }
        else{
            Node c = head;
            while(c.next != null){
                if(comp.compare(c.next.x, tar) == 0){
                    c.next = c.next.next;
                    if(c.next == null){
                        tail = c;
                        size -= 1;
                        return this;
                    }
                    else{
                        c.next.previous = c;
                        size -= 1;
                        return this;
                    }
                }
                c = c.next;
            }
        }

        return this;

    }

    public T retrieveFirstElement(){
        T toReturn;
        if(head == null){
            toReturn = null;
            return toReturn;
        }

        toReturn = head.x;
        head = head.next;
        head.previous = null;
        return toReturn;
    }

    public T retrieveLastElement(){
        T toReturn;
        if(tail == null){
            toReturn = null;
            return toReturn;
        }

        toReturn = tail.x;
        tail = tail.previous;
        tail.next = null;
        return toReturn;
    }



protected class Node {

    protected T x;
    protected Node next, previous;

    Node(T el){
        this.x = el;
        this.next = null;
        this.previous = null;
    }

    Node (Node p, T el, Node n){
        this.x = el;
        this.next = n;
        this.previous = p;
    }

}
    protected class iter implements ListIterator<T>{
        Node current;
        int size;

        public iter(){
            current = new Node(null, null, head);
        }

        @Override
        public boolean hasNext(){
            boolean toReturn = (current.next != null);
            return  toReturn;
        }

        @Override
        public boolean hasPrevious(){
            boolean toReturn = (current.previous != null);
            return toReturn;
        }

        @Override
        public T next(){
            T toReturn;

            if(!hasNext()){
                throw new NoSuchElementException("There is no next element.");
            }
            current.previous = current.next;
            current.next = current.previous.next;
            toReturn = current.previous.x;


            return toReturn;
        }

        @Override
        public T previous(){
            T toreturn;
            if(!hasPrevious()){
                throw new NoSuchElementException("There is no previous element.");
            }

            current.next = current.previous;
            current.previous = current.next.previous;
            toreturn = current.next.x;

            return toreturn;
        }

        @Override
        public void add(T arg) throws UnsupportedOperationException{
            throw new UnsupportedOperationException("Unsupported operation");
        }
        @Override
        public void remove() throws UnsupportedOperationException{
            throw new UnsupportedOperationException("Unsupported operation");
        }
        @Override
        public int nextIndex() throws UnsupportedOperationException{
            throw new UnsupportedOperationException("Unsupported operation");
        }
        @Override
        public void set(T arg) throws UnsupportedOperationException{
            throw new UnsupportedOperationException("Unsupported operation");
        }
        public int previousIndex() throws UnsupportedOperationException{
            throw new UnsupportedOperationException("Unsupported operation");
        }
    }

    public ArrayList<T> toArrayList(){
        ArrayList<T> toReturn = new ArrayList<>();
        Node n = head;

        while(n != null){
            toReturn.add(n.x);
            n = n.next;
        }
        return toReturn;
    }
}