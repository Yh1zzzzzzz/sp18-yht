public class LinkedListDeque<T> implements Deque<T> {
    private int size;
    private Node first;
    private  class Node {
        T item;
        Node before;
        Node after;
        public Node(T t, Node next, Node upxt) {
            item = t;
            before  = upxt;
            after = next;
        }
    }
    public LinkedListDeque() {
        Node sentinel = new Node(null, null, null);
        sentinel.item = null;
        sentinel.after = sentinel;
        sentinel.before = sentinel;
        first = sentinel;
        size = 0;

    }
    @Override
    public void addFirst(T item) {
        if (size == 0) {
            Node nodeNew = new Node(item, null, null);
            first.after = nodeNew;
            first.before = nodeNew;
            nodeNew.before = first;
            nodeNew.after = first;
            size += 1;

        } else {
            Node firstBefore = first.after;
            Node nodeNew = new Node(item, null, null);
            firstBefore.before = nodeNew;
            first.after = nodeNew;
            nodeNew.after = firstBefore;
            nodeNew.before = first;
            size += 1;
        }
    }
    @Override
    public void addLast(T item) {
        Node lastNew = new Node(item, null, null);
        //last_now up to be filled
        Node lastBefore = first.before;
        lastBefore.after = lastNew;
        first.before = lastNew;
        lastNew.before = lastBefore;
        lastNew.after = first;
        size += 1;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    @Override
    public int size() {
        return  size;
    }
    @Override
    public void printDeque() {
        if (size == 0) {
            System.out.print("empty list");
        } else {
            Node p = first.after;
            for (int i = 0; i < size; i += 1) {
                System.out.print(p.item);
                p = p.after;
            }
        }
    }
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node returnnode = first.after;
        T returnItem = returnnode.item;
        if (size == 1) {
            first.after = first;
            first.before = first;

        } else {
            first.after.after.before = first;
            first.after = first.after.after;


        }
        size -= 1;
        return returnItem;

    }
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node re = first.before;
        T returnValue = re.item;
        if (size == 1) {
            first.after = first;
            first.before = first;
        } else {
            first.before.before.after = first;
            first.before = first.before.before;
        }
        size -= 1;
        return returnValue;

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
    @Override
    public T get(int index) {
        if (index > size - 1 || index < 0) {
            System.out.print("index error");
            return null;
        } else {
            Node p = first.after;
            while (index != 0) {
                p = p.after;
                index -= 1;
            }
            return p.item;
        }
    }
}
