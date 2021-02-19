package snake;

public class Queue<E> extends  ListOfLinks<E>{
    public E dequeue(){
        E s = getFirst();
        removeFirst();
        return s;
    }

    public void enqueue( E s){
        addLast(s);
    }

    public E peek(){
        return getFirst();
    }

    public static void main(String args[]){
        String[] test = new String[]{"1 Hello","2 World","3 Stack","4 Queue"};
        Queue<String> q = new Queue<String>();
        Stack<String> s = new Stack<String>();
        System.out.println("STACK     QUEUE");

        for(String str : test){
            q.enqueue(str);
            s.push(str);
            System.out.println(str + "     " +str );

        }
        while(q.size() != 0 && s.size()!=0){
            System.out.println(s.pop() + "     " + q.dequeue());
        }
    }
}
