package data_structures.implementation;

import java.util.ArrayList;

import data_structures.Sorted;

public class CoarseGrainedTree<T extends Comparable<T>> implements Sorted<T> {
    private static class TreeNode<T> {
        public T data;
        public TreeNode<T> left = null;
        public TreeNode<T> right = null;

        public TreeNode(T data) {
            this.data = data;
        }
    }

    private TreeNode<T> root;

    public synchronized void add(T t) {
        root = addOrReplaceNode(root, t);
    }

    private TreeNode<T> addOrReplaceNode(TreeNode<T> node, T t) {
        if (node == null) {
            return new TreeNode<>(t);
        } else if (t.compareTo(node.data) < 0) {
            node.left = addOrReplaceNode(node.left, t);
        } else {
            node.right = addOrReplaceNode(node.right, t);
        }

        return node;
    }

    public synchronized void remove(T t) {
        root = removeOrReplaceNode(root, t);
    }

    private TreeNode<T> removeOrReplaceNode(TreeNode<T> node, T t) {
        if (node != null) {
            if (t.compareTo(node.data) == 0) {
                if (node.left == null) {
                    return node.right;
                } else if (node.right == null) {
                    return node.left;
                } else {
                    TreeNode<T> parent = null;
                    TreeNode<T> nodeToMove = node.left;
                    while (nodeToMove.right != null) {
                        parent = nodeToMove;
                        nodeToMove = nodeToMove.right;
                    }

                    if (parent != null) {
                        parent.right = nodeToMove.left;
                        nodeToMove.left = node.left;
                    }

                    nodeToMove.right = node.right;

                    return nodeToMove;
                }
            } else if (t.compareTo(node.data) < 0) {
                node.left = removeOrReplaceNode(node.left, t);
            } else {
                node.right = removeOrReplaceNode(node.right, t);
            }
        }

        return node;
    }

    public ArrayList<T> toArrayList() {
        ArrayList<T> list = new ArrayList<>();
        addNodeToArrayList(root, list);
        return list;
    }

    private void addNodeToArrayList(TreeNode<T> node, ArrayList<T> list) {
        if (node != null) {
            addNodeToArrayList(node.left, list);
            list.add(node.data);
            addNodeToArrayList(node.right, list);
        }
    }
}
