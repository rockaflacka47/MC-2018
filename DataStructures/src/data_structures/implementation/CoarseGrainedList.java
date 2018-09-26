package data_structures.implementation;

import java.util.ArrayList;
import data_structures.*;
import java.util.*;

public class CoarseGrainedList<T extends Comparable<T>> implements Sorted<T> {

    private Node<T> start;
    private Node<T> end;
    private int length = 0;

    public synchronized void add(T t){
        Node<T> newNode = new Node<T>(t);
        if(start == null && end == null){
            start = newNode;
            end = newNode;
            length ++;
        }
        else if(newNode.data.compareTo(start.data) < 0) {
            newNode.next = start;
            start = newNode;
            length++;
        }
        else if(newNode.data.compareTo(end.data) >= 0){
            end.next = newNode;
            end = newNode;
            length++;
        }
        else if(start == end){
            start.next = newNode;
            end = newNode;
            length++;
        }
        else {
           addAndSort(newNode);
           length++;
        }
    }

    public synchronized void remove(T t) {
        if(start == null){
            return;
        }
        else if(start.data.compareTo(t) == 0 && end.data.compareTo(t) == 0){
            start = null;
            end = null;
            length --;
        }
       else if(start.data.compareTo(t) == 0){
            start = start.next;
            length--;
       }
       else{
           Node<T> curr = start.next;
           Node<T> prev = start;
           for(int i = 1; i < length; i ++){
               if(curr.data.compareTo(t) == 0){
                   if(curr.next != null){
                       prev.next = curr.next;
                       length--;
                       return;
                   }
                   else{
                       prev.next = null;
                       end = prev;
                       length--;
                       return;
                   }
               }
               if(curr.next != null){
                prev = prev.next;
                curr = curr.next;
               }
           }
       }
    }

    public ArrayList<T> toArrayList() {
        if(start == null) return new ArrayList<T>();

        ArrayList<T> list = new ArrayList<>();
        Node<T> curr = start;
        for(int i = 0; i < length; i++){
            list.add(curr.data);
            curr = curr.next;
        }
        return list;
        
    }

    private void addAndSort(Node<T> newNode){
        Node<T> prev = start;
        Node<T> curr = start.next;
        
        for(int i = 1; i < length; i++){
            if(newNode.data.compareTo(curr.data)< 0){
                newNode.next = curr;
                prev.next = newNode;
                return;
            }
                prev = curr;
                curr = curr.next;
            
        }
    }
}
