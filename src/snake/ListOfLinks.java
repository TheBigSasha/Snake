package snake;

/**
 * Singly linked list with tail
 * @param <E>
 */
public class ListOfLinks<E> {
    private Node<E> head;  //The number of Objects in this structure:
                        //Head + All other nodes
                        //+ Each payload
                        //+ 1 ListOfLinks
    private Node<E> tail;
    private int size;

    public ListOfLinks(){
        head = null;
        tail = null;
        size = 0;
    }

    public void add(E s){
        if(head == null){
            head = new Node<E>(s);     //Change head to be the new node
            tail = head;
        }else{
            Node<E> added = new Node<E>(s);
            added.next = head;
            head = added;
            /*
            This is kinda like when in an ArrayList, we replace our array with the new bigger one
             */
        }
        size++;//We only count nodes when the count changes! Better!
    }

    public void addLast(E s){
        if(head == null){
            head = new Node<E>(s);     //Change head to be the new node
            tail = head;
        }else{
            Node<E> added = new Node<E>(s);
            tail.next = added;
            tail = added;
            /*
            This is kinda like when in an ArrayList, we replace our array with the new bigger one
             */
        }
        size++;//We only count nodes when the count changes! Better!
    }

    public E getFirst(){
        if(head == null) return null;
        return head.payload;
    }

    public E get(int i){
        if(i > size || i < 0){
            return null;
        }else{
            Node<E> cur = head;
            int counter = 0;
            while(counter < i){
                counter++;
                cur = cur.next;
            }
            return cur.payload;
        }
    }

    public void removeFirst(){
        if(head.next == null){
            head = null;
            tail = null;
        }
        if(head != null) {
            Node temp = head.next;
            head.next = null;
            head = temp;
        }
        size--;
    }

    public E getLast(){
        return tail.payload;
    }
    public int size(){
        //We don't wanna care how long our list is when we do stuff?

        return size;
    }

    public static void main(String args[]){
        ListOfLinks<String> l = new ListOfLinks<String>();
        l.add("Hello");
        l.add("World");
        l.add("It's a me!");
        System.out.println(l.get(0));
        System.out.println(l.get(2));
        int num = l.size;
        for(int i = 0; i< num; i++){
            System.out.println(l.get(0));
            l.removeFirst();
        }


    }

    private class Node<E> {
        public E payload;
        public Node<E> next;   //This is the core of how a linked list works, the reference to next

        public Node(E payload) {
            this.payload = payload;
        }
    }
}
//THis right now is a working linked list!