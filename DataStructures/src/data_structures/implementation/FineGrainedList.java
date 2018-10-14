package data_structures.implementation;

import java.util.ArrayList;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import data_structures.Sorted;

public class FineGrainedList<T extends Comparable<T>> implements Sorted<T> {
    private static final class Node<T> {
        public T data;
        public Node<T> next = null;
        private final Lock lock = new ReentrantLock();

        public Node(T data) {
            this.data = data;
        }

        public void lock() {
            lock.lock();
        }

        public void unlock() {
            lock.unlock();
        }

        public static <T> void unlockNullable(Node<T> node, Lock lock) {
            if (node == null) {
                lock.unlock();
            } else {
                node.unlock();
            }
        }
    }

    private Node<T> head = null;
    private final Lock listLock = new ReentrantLock();

    public void add(T t) {
        listLock.lock();

        // Find the nodes to insert the new node between
        Node<T> previousNode = null;
        Node<T> nextNode = head;
        while (nextNode != null && t.compareTo(nextNode.data) > 0) {
            nextNode.lock();
            Node.unlockNullable(previousNode, listLock);

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

        Node.unlockNullable(previousNode, listLock);
    }

    public void remove(T t) {
        listLock.lock();

        // Find the node to be removed
        Node<T> previousNode = null;
        Node<T> currentNode = head;
        while (currentNode != null && t.compareTo(currentNode.data) > 0) {
            currentNode.lock();
            Node.unlockNullable(previousNode, listLock);

            previousNode = currentNode;
            currentNode = currentNode.next;
        }

        // If we found it, remove it
        if (currentNode != null && t.compareTo(currentNode.data) == 0) {
            currentNode.lock();

            // Replace the current node with the modified subtree
            if (previousNode == null) {
                head = currentNode.next;
            } else {
                previousNode.next = currentNode.next;
            }
        }

        Node.unlockNullable(previousNode, listLock);
    }

    public ArrayList<T> toArrayList() {
        ArrayList<T> list = new ArrayList<>();

        for (Node<T> curr = head; curr != null; curr = curr.next) {
            list.add(curr.data);
        }

        return list;
    }
}