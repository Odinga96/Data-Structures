package linkedlist;

import java.util.Objects;

/**
 * @author : Odinga David
 * @since : 12/21/21, Tue
 */
public class SinglyLinkedList<E> {

    private int size=0;

    private Node<E> head=null;
    private Node<E> tail=null;


    private static class Node<E> {
       private E data;
       private Node<E> next;

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node<?> node)) return false;
            return data.equals(node.data) && Objects.equals(next, node.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(data, next);
        }
    }


    // Return the size of this linked list
    public int size() {
        return size;
    }

    // Is this linked list empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // Add an element to the tail of the linked list, O(1)
    public void add(E elem) {
        addLast(elem);
    }

    public void addLast(E elem) {
        if (isEmpty()){
            head= tail = new Node<E>(elem, null);
        }else {
            tail.next = new Node<E>(elem, null);
            tail = tail.next;
        }

        size++;
    }

    public void addFirst(E elem){
        if (isEmpty()){
            head= tail = new Node<>(elem, null);
        }else {
            head = new Node<>(elem, head);
        }

        size++;
    }


    // Check the value of the first node if it exists, O(1)
    public E peekFirst() {
        if (isEmpty()) throw new RuntimeException("Empty list");
        return head.data;
    }


    // Check the value of the last node if it exists, O(1)
    public E peekLast() {
        if (isEmpty()) throw new RuntimeException("Empty list");
        return tail.data;
    }


    // Remove the first value at the head of the linked list, O(1)
    public E removeFirst() {
        // Can't remove data from an empty list
        if (isEmpty()) throw new RuntimeException("Empty list");

        // Extract the data at the head and move
        // the head pointer forwards one node
        E data = head.data;
        head = head.next;
        --size;

        // If the list is empty set the tail to null
        if (isEmpty()) tail = null;

        // Return the data that was at the first node we just removed
        return data;
    }

    // Remove the last value at the tail of the linked list, O(1)
    public E removeLast() {
        // Can't remove data from an empty list
        if (isEmpty()) throw new RuntimeException("Empty list");

        // Extract the data at the tail and move
        // the tail pointer backwards one node
        E data = tail.data;

        if (size() ==1){
            tail=null;
        }else {

            Node<E> node = head;
            while (node != null) {

                if (node.next.next == null) {
                    tail=null;
                    tail = node;
                    break;
                }

                node = node.next;
            }
        }

        --size;

        // If the list is now empty set the head to null
        if (isEmpty()) head = null;

            // Do a memory clean of the node that was just removed
        else tail.next = null;

        // Return the data that was in the last node we just removed
        return data;
    }


    // Remove an arbitrary node from the linked list, O(1)
    private E remove(Node<E> node) {
        // If the node to remove is somewhere either at the tail handle those independently
        if (node.next == null) return removeLast();

        // Make the pointers of adjacent nodes skip over 'node'

        Node<E> curr = head;
        Node<E> prev=curr;

        while(!curr.equals(node)){
            prev=curr;
            curr=curr.next;
        }

        prev.next=curr.next;

        // Temporarily store the data we want to return
        E data = node.data;

        // Memory cleanup
        node.data = null;

        --size;

        // Return the data in the node we just removed
        return data;
    }
}
