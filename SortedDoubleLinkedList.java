package sample;

import java.util.ListIterator;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * @author Amanda Yoresh
 */


public class SortedDoubleLinkedList extends BasicDoubleLinkedList{

    protected Comparator<T> comp = null;

    public SortedDoubleLinkedList(Comparator<T> comp){
        this.comp = comp;
    }

    public SortedDoubleLinkedList<T> add(T temp){
        Node n = new Node(temp);

        if(head == null){
            head = tail = n;
        }
        else if(comp.compare(head.x, temp) > 0){
            head.previous = n;
            n.next = head;
            head = n;
        }
        else if(comp.compare(tail.x, temp) < 0){
            tail.next = n;
            n.previous = tail;
            tail = n;
        }
        else{
            Node c = head;
            while(c!=null){
                if(comp.compare(c.x, temp) <= 0){
                    Node y = c;
                    Node z = c.next;
                    z.previous = n;
                    y.next = n;
                    n.next = z;
                    n.previous = y;
                }
                c=c.next;
            }
        }
        size += 1;
        return this;
    }

    @Override
    public SortedDoubleLinkedList<T> remove(T tar, Comparator<T> comp){
        super.remove(tar, comp);
        return this;
    }

    @Override
    public BasicDoubleLinkedList addToEnd(Object toAdd) {
        throw new UnsupportedOperationException("Invalid operation for sorted list");
    }

    @Override
    public BasicDoubleLinkedList addToFront(Object temp) {
        throw new UnsupportedOperationException("Invalid operation for sorted list");
    }

    @Override
    public ListIterator iterator() throws UnsupportedOperationException, NoSuchElementException {
        return super.iterator();
    }

    public void display(){
        Node z = head;
        if(head == null){
            System.out.println("Empty List");
            return;
        }
        System.out.println("Nodes of DLL: ");
        while(z != null){
            System.out.println(z.x + "");
            z = z.next;
        }
        System.out.println("");
    }


}
