public class LinkedListDeque<T> {
    private int size;
    private Node first;
    private  class Node {
        T item;
        Node before;
        Node after;
        public Node(T t , Node next, Node upxt){
            item = t;
            before  = upxt;
            after = next;
        }
    }
    public LinkedListDeque(){
        Node sentinel = new Node(null,null,null);
        sentinel.item = null;
        sentinel.after = sentinel;
        sentinel.before = sentinel;
        first = sentinel;
        size = 0;

    }
    public  LinkedListDeque (T t){
        Node sentinel = new Node(null,null,null);
        sentinel.item = null;
        sentinel.after = sentinel;
        sentinel.before = sentinel;
        first = sentinel;
        Node newnode = new Node(t,sentinel,sentinel) ;
        newnode.item = t;
        sentinel.after = newnode;
        sentinel.before = newnode;
        size += 1;
    }
    public void addFirst(T item){
        Node first_before = first.after;
        first_before.before = new Node(item,first_before,first);
        size += 1;

    }
    public void addLast(T item){
        Node last_now = new Node(item,first,null);
        //last_now up to be filled
        Node last_before = first.after;
        last_before.after = last_now;
        last_now.before = last_before;
        size += 1;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return  size;
    }
    public void printDeque(){
        if (size ==0){
            System.out.print("empty list");
        }
        else {
            Node p = first.after;
            for (int i = 0; i < size; i += 1) {
                System.out.print(p.item);
                p = p.after;
            }
        }
    }
    public T removeFirst(){
        if (size == 0){
            return null;
        }
        Node returnnode = first.after;
        if (size ==1){
            first.after = first;
            first.before = first;

        }
        else {
            first.after.after.before = first;
            first.after = first.after.after;


        }
        size -= 1;
        return returnnode.item;

    }
    public T removeLast(){
        if (size == 0){
            return null;
        }
        Node re = first.before;
        if (size ==1){
            first.after = first;
            first.before = first;
        }
        else {
            first.before.before.after = first;
            first.before = first.before.before;
        }
        size -= 1;
        return  re.item;

    }
    private T getRecursiveHelp(Node start, int index) {
        if (index == 0) {
            return start.item;
        } else {
            return getRecursiveHelp(start.after, index - 1);
        }
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursiveHelp(first.after, index);
    }
    public T get(int index){
        if (index > size-1 || index < 0){
            System.out.print("index error");
            return null;
        }
        else {
            Node p = first.after;
            while (index != 0 ){
                p = p.after;
                index -= 1;
            }
            return p.item;
        }
    }
}
