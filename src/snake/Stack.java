package snake;

public class Stack<E> extends  ListOfLinks<E>{
    public void push(E s){
        add(s);
    }

    public E peek(){
        return getFirst();
    }

    public E pop(){
        E s = getFirst();
        removeFirst();
        return s;
    }
}
