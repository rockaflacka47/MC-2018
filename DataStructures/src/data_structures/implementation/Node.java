package data_structures.implementation;

public class Node<T>{
    public T data;
    public Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }

    public Node(Node<T> next, T data){
        this.data = data;
        this.next = next;
    }
}