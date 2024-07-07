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
        if (size == 0){
            Node node_new = new Node(item,null,null)  ;
            first.after = node_new;
            first.before = node_new;
            node_new.before = first;
            node_new.after = first;
            size += 1;

        }else {
            Node first_before = first.after;
            Node node_new = new Node(item,null,null) ;
            first_before.before = node_new;
            first.after = node_new;
            node_new.after = first_before;
            node_new.before = first;
            size += 1;
        }
    }
    public void addLast(T item){
        Node last_new = new Node(item,null,null);
        //last_now up to be filled
        Node last_before = first.before;
        last_before.after = last_new;
        first.before = last_new;
        last_new.before = last_before;
        last_new.after = first;
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
        T return_item = returnnode.item;
        if (size ==1){
            first.after = first;
            first.before = first;

        }
        else {
            first.after.after.before = first;
            first.after = first.after.after;


        }
        size -= 1;
        return return_item;

    }
    public T removeLast(){
        if (size == 0){
            return null;
        }
        Node re = first.before;
        T return_value = re.item;
        if (size ==1){
            first.after = first;
            first.before = first;
        }
        else {
            first.before.before.after = first;
            first.before = first.before.before;
        }
        size -= 1;
        return  return_value;

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
