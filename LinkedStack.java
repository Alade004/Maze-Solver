
import java.util.ArrayList;

/**
* Linked stack that implements Stack
*
*/
public class LinkedStack<T> implements Stack <T> {

     Node top = null;
    
    /**
    * Private Node class
    *
    */    
    private class Node {
        private T    data; 
        private Node next; 
    
        private Node(T dataPortion) {
            this(dataPortion, null);
        } // end constructor

        private Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        } // end constructor
    } // end Node

    
    /**
     * Adds an item to the top of this stack.
     * @param item The item to add.
     */

    public void push(T item) {
        Node add = new Node(item, top);
        top = add;

    }
    
    /**
     * Removes and returns the item from the top of this stack.
     * @return the item at the top of the stack. Returns null if empty.
     */
    public T pop() {
        if (top != null) {
            T data = top.data;
            top = top.next;
            return data;


        }
            return null;
        }
    
    
    /**
     * Returns the item on top of the stack, without removing it.
     * @return the item at the top of the stack. Returns null if empty.
     */
    public T peek() {
        if (top != null) {
            return top.data;

        }
        return null;
        }
    
    
    /** 
     * Returns whether the stack is empty. 
     * @return true if the stack is empty; false otherwise
     */
    public boolean isEmpty() {
        return top == null;
    }
    
    /** 
     * Removes all items from the stack. 
     */
    public void clear() {
        top = null;
    }
}

