package data_structures.implementation;

import java.util.ArrayList;

import data_structures.Sorted;

public class CoarseGrainedList<T extends Comparable<T>> implements Sorted<T> {
    private static final class Node<T> {
        public T data;
        public Node<T> next = null;

        public Node(T data) {
            this.data = data;
        }
    }

    private Node<T> head = null;

    public synchronized void add(T t) {
        // Find the nodes to insert the new node between
        Node<T> previousNode = null;
        Node<T> nextNode = head;
        while (nextNode != null && t.compareTo(nextNode.data) > 0) {
            previousNode = nextNode;
            nextNode = nextNode.next;
        }

        // Insert the new node
        Node<T> newNode = new Node<>(t);
        newNode.next = nextNode;
        if (previousNode == null) {
            head = newNode;
        } else {
            previousNode.next = newNode;
        }
    }

    public synchronized void remove(T t) {
        // Find the node to be removed
        Node<T> previousNode = null;
        Node<T> currentNode = head;
        while (currentNode != null && t.compareTo(currentNode.data) > 0) {
            previousNode = currentNode;
            currentNode = currentNode.next;
        }

        // If we found it, remove it
        if (currentNode != null && t.compareTo(currentNode.data) == 0) {
            if (previousNode == null) {
                head = currentNode.next;
            } else {
                previousNode.next = currentNode.next;
            }
        }
    }

    public ArrayList<T> toArrayList() {
        ArrayList<T> list = new ArrayList<>();

        for (Node<T> curr = head; curr != null; curr = curr.next) {
            list.add(curr.data);
        }

        return list;
    }
}
