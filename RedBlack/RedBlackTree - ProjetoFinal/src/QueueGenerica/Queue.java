package QueueGenerica;

public class Queue<T>{
    
    private QueueNode<T> head;

    public boolean isEmpty(){
        return this.head == null;
    }

    public void enqueue(T value){
        QueueNode<T> newNode = new QueueNode<>(value);
        if(isEmpty()){
            head = newNode;
        } else{
            QueueNode<T> aux = head;
            while(aux.getNext() != null){
                aux = aux.getNext();
            }
            aux.setNext(newNode);
        }
    }

    public T dequeue(){
        if(isEmpty()){
            return null;
        } else{
            T info = head.getInfo();
            head = head.getNext();
            return info;
        }
    }
}

