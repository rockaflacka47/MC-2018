package data_structures.implementation;

import java.util.ArrayList;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import data_structures.Sorted;

public class FineGrainedList<T extends Comparable<T>> implements Sorted<T> {

 
    private Node<T> start;
    private final Lock lock = new ReentrantLock();

    public void add(T t){
        //start.lock();
        System.out.println(t);
        Node<T> newNode = new Node<T>(t);
        if(start == null){
            start = newNode;
        }
        else if(newNode.data.compareTo(start.data) < 0) {
            newNode.next = start;
            start = newNode;   
        }
        else if(start.next == null){
            start.next = newNode;
        }
        else {
           addAndSort(newNode);
        }

        System.out.println(this.toArrayList());
        System.out.println("\n\n");
    }

    public void remove(T t) {
        if(start == null){
            return;
        }
        else if(start.data.compareTo(t) == 0 && start.next == null){
            start = null;
        }
       else if(start.data.compareTo(t) == 0){
            start = start.next;     
       }
       else{
           Node<T> curr = start.next;
           Node<T> prev = start;
           
           while(curr != null){
               if(curr.data.compareTo(t) == 0){
                       prev.next = curr.next;
                       return;
               }
                prev = prev.next;
                curr = curr.next;
               }
           }
       }

    public ArrayList<T> toArrayList() {
        if(start == null) return new ArrayList<T>();

        ArrayList<T> list = new ArrayList<>();
        Node<T> curr = start;
        while(curr != null){
            list.add(curr.data);
            curr = curr.next;
        }
        return list;      
    }

    private void addAndSort(Node<T> newNode){
        Node<T> prev = start;
        Node<T> curr = start.next;
        
        while(curr != null){
            if(newNode.data.compareTo(curr.data)< 0){
                newNode.next = curr;
                prev.next = newNode;
                return;
            }
            if(curr.next == null){
                curr.next = newNode;
                break;
            }
                prev = curr;
                curr = curr.next;
            
        }
    }
}