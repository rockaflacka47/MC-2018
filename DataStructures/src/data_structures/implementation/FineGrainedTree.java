package data_structures.implementation;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import data_structures.Sorted;

public class FineGrainedTree<T extends Comparable<T>> implements Sorted<T> {
    private static final class Node<T> {
        public T data;
        public Node<T> left = null;
        public Node<T> right = null;
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

    private Node<T> root = null;
    private final Lock treeLock = new ReentrantLock();

    public void add(T t) {
        treeLock.lock();

        // Find the leaf to insert the new node at
        Node<T> parentNode = null;
        Node<T> currentNode = root;
        while (currentNode != null) {
            currentNode.lock();
            Node.unlockNullable(parentNode, treeLock);

            parentNode = currentNode;
            if (t.compareTo(currentNode.data) < 0) {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }

        // Insert the new node. We do this here rather than in the middle of the
        // loop in the name of preserving the structure used in remove
        Node<T> newNode = new Node<>(t);
        if (parentNode == null) {
            root = newNode;
        } else if (t.compareTo(parentNode.data) < 0) {
            parentNode.left = newNode;
        } else {
            parentNode.right = newNode;
        }

        Node.unlockNullable(parentNode, treeLock);
    }

    public void remove(T t) {
        treeLock.lock();

        // Find the node to be removed
        Node<T> parentNode = null;
        Node<T> currentNode = root;
        while (currentNode != null && t.compareTo(currentNode.data) != 0) {
            currentNode.lock();
            Node.unlockNullable(parentNode, treeLock);

            parentNode = currentNode;
            if (t.compareTo(currentNode.data) < 0) {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }

        // If we found it, remove it
        if (currentNode != null) {
            assert t.compareTo(currentNode.data) == 0;

            // Compute the modified subtree to replace the current node
            Node<T> modifiedSubtree;
            if (currentNode.left == null) {
                modifiedSubtree = currentNode.right;
            } else if (currentNode.right == null) {
                modifiedSubtree = currentNode.left;
            } else {
                // Find the righmost node of the left subtree
                Node<T> substitutionParent = null;
                Node<T> nodeToSubstitute = currentNode.left;
                nodeToSubstitute.lock();
                while (nodeToSubstitute.right != null) {
                    if (substitutionParent != null) {
                        substitutionParent.unlock();
                    }

                    nodeToSubstitute.right.lock();
                    substitutionParent = nodeToSubstitute;
                    nodeToSubstitute = nodeToSubstitute.right;
                }

                // Substitute the data to be removed with that of nodeToSubstitute
                currentNode.data = nodeToSubstitute.data;

                // Remove nodeToSubstitute from the tree
                if (substitutionParent != null) {
                    assert nodeToSubstitute != currentNode.left;
                    substitutionParent.right = nodeToSubstitute.left;
                    substitutionParent.unlock();
                } else {
                    assert nodeToSubstitute == currentNode.left;
                    currentNode.left = nodeToSubstitute.left;
                }

                modifiedSubtree = currentNode;
            }

            // Replace the current node with the modified subtree
            if (parentNode == null) {
                root = modifiedSubtree;
            } else if (currentNode == parentNode.left) {
                parentNode.left = modifiedSubtree;
            } else {
                assert currentNode == parentNode.right;
                parentNode.right = modifiedSubtree;
            }
        }

        Node.unlockNullable(parentNode, treeLock);
    }

    public ArrayList<T> toArrayList() {
        ArrayList<T> list = new ArrayList<>();
        addSubtreeToArrayList(root, list);
        return list;
    }

    private static <T> void addSubtreeToArrayList(Node<T> node, ArrayList<T> list) {
        if (node != null) {
            addSubtreeToArrayList(node.left, list);
            list.add(node.data);
            addSubtreeToArrayList(node.right, list);
        }
    }
}
