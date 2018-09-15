package data_structures.implementation;

import java.util.ArrayList;

import data_structures.Sorted;

public class CoarseGrainedTree<T extends Comparable<T>> implements Sorted<T> {
    TreeNode<T> start;

    public void add(T t) {
        if(start == null){
            start = new TreeNode<T>(t);
        }
        else{
            findPlacement(t, start);
        }
    }

    public void remove(T t) {
        if(start.data.compareTo(t) == 0 && start.left == null && start.right == null){
            start = null;
        }
        else if(start.data.compareTo(t) == 0){
            removeStart();
        }
        else{
            removeNode(start.left, start, t);
        }
    }

    private void removeNode(TreeNode<T> curr, TreeNode<T> prev, T t){
        if(curr.data.compareTo(t) == 0){
            if(curr.left == null && curr.right == null){
                if(prev.left == curr){
                    prev.left = null;
                    removed = true;
                }
                else{
                    prev.right = null;
                    removed = true;
                }
            }
            else if(curr.left == null){
                if(prev.left == curr){
                    prev.left = curr.right;
                    removed = true;
                }
                else{
                    prev.right = curr.right;
                    removed = true;
                }
            }
            else if(curr.right == null){
                if(prev.left == curr){
                    prev.left = curr.left;
                    removed = true;
                }
                else{
                    prev.right = curr.left;
                    removed = true;
                }
            }
        }
        else{
            if(curr.left != null){
                removed = removeNode(curr.left, curr, t);
            }
            if(!removed && curr.right != null){
                removed = removeNode(curr.right, curr, t);
            }
        }
        return removed;
    }

    public ArrayList<T> toArrayList() {
        return arrayList(start, new ArrayList<T>());
    }

    private ArrayList<T> arrayList(TreeNode<T> curr, ArrayList<T> ret){
        if(curr.left != null) {
            ret = arrayList(curr.left, ret);
        }
        ret.add(curr.data);
        if(curr.right != null) {
            ret = arrayList(curr.right, ret);
        }
        return ret;
    }

    private void findPlacement(T t, TreeNode<T> curr){
        if(t.compareTo(curr.data) <= 0 && curr.left == null){
            curr.left = new TreeNode<T>(t);
        }
        else if(t.compareTo(curr.data) >= 0 && curr.right == null){
            curr.right = new TreeNode<T>(t);
        }
        else if(t.compareTo(curr.data) <= 0){
            findPlacement(t, curr.left);
        }
        else{
            findPlacement(t, curr.right);
            
        }
    }
}
