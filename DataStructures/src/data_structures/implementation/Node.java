package data_structures.implementation;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Node<T>{
    public T data;
    public Node<T> next;
    private final Lock lock = new ReentrantLock();

    public Node(T data) {
        this.data = data;
        this.next = null;
    }

    public Node(Node<T> next, T data){
        this.data = data;
        this.next = next;
    }

    public void lock() {
        lock.lock();
    }

    public boolean isLocked(){
        return lock.tryLock();
    }

    public void unlock() {
        lock.unlock();
    }
}