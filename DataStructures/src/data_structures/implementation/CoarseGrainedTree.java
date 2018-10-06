package data_structures.implementation;

import java.util.ArrayList;

import data_structures.Sorted;

public class CoarseGrainedTree<T extends Comparable<T>> implements Sorted<T> {
    private static class Node<T> {
        public T data;
        public Node<T> left = null;
        public Node<T> right = null;

        public Node(T data) {
            this.data = data;
        }
    }

    private Node<T> root = null;

    public synchronized void add(T t) {
        root = addToSubtree(root, t);
    }

    private static <T extends Comparable<T>> Node<T> addToSubtree(Node<T> node, T t) {
        if (node == null) {
            return new Node<>(t);
        } else if (t.compareTo(node.data) < 0) {
            node.left = addToSubtree(node.left, t);
        } else {
            node.right = addToSubtree(node.right, t);
        }

        return node;
    }

    public synchronized void remove(T t) {
        root = removeFromSubtree(root, t);
    }

    private static <T extends Comparable<T>> Node<T> removeFromSubtree(Node<T> node, T t) {
        if (node != null) {
            if (t.compareTo(node.data) == 0) {
                if (node.left == null) {
                    return node.right;
                } else if (node.right == null) {
                    return node.left;
                } else {
                    Node<T> parent = null;
                    Node<T> nodeToSubstitute = node.left;
                    while (nodeToSubstitute.right != null) {
                        parent = nodeToSubstitute;
                        nodeToSubstitute = nodeToSubstitute.right;
                    }

                    node.data = nodeToSubstitute.data;

                    if (parent != null) {
                        assert nodeToSubstitute != node.left;
                        parent.right = nodeToSubstitute.left;
                    } else {
                        assert nodeToSubstitute == node.left;
                        node.left = nodeToSubstitute.left;
                    }

                    return nodeToSubstitute;
                }
            } else if (t.compareTo(node.data) < 0) {
                node.left = removeFromSubtree(node.left, t);
            } else {
                node.right = removeFromSubtree(node.right, t);
            }
        }

        return node;
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
