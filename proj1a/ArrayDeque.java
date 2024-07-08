public class ArrayDeque <T> {
    private int size;
    private int next_first;
    private int next_last;
    private T[] Arry;
    private int lenth;
    public ArrayDeque(){
        Arry = (T[]) new Object[8];
        size = 0;
        next_first = 0;
        next_last = 0;
    }
    public void resize(int n){
            T[] newad = (T[]) new Object[n];
            System.arraycopy(Arry,0,newad,0,size);
            Arry = newad;
    }
    public void addFirst(T item){
        if (size == 0){
            this.Arry[0] = item;
            size += 1;
        }else {
            if (size == get_lenth()){
                resize(2*size);
            }
            Right_Move();
            Arry[0] = item;
            size += 1;
        }

    }
    public int get_lenth(){
        return Arry.length;
    }
    public void addLast(T item){
        if (size == get_lenth()){
            resize(2*size);
        }
        Arry[size] = item;
        size += 1;
    }
    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }
    public T removeFirst(){
        if (size == 0 ){
            return null;
        }
        T to_return = Arry[0];
        reform();
        size -= 1;
        if (get_lenth() >= 4 * size){
            resize(size * 2);
        }
        return to_return;
    }
    public T removeLast(){
        if (size == 0){
            return null;
        }
         T n = Arry[size-1];
        Arry[size-1] = null;
        size -= 1;
        if (get_lenth() >= 4 * size){
            resize(size * 2);
        }
        return n;
    }
    /*
    * 将第一个删去？
    * */
    public void reform(){
        T[] newad = (T[]) new Object[size];
        System.arraycopy(this.Arry,1,newad,0,size-1);
        Arry = newad;
    }
    /*
     * 将第一个右移一位
     * */
    public void Right_Move(){
        T[] newad = (T[]) new Object[size+1];
        System.arraycopy(Arry,0,newad,1,size);
        Arry = newad;
    }
    public T get(int index){
        return Arry[index];
    }
    public void printDeque(){
        if (size == 0){
            System.out.print("empty ArrayDeque");
        }else {
            for (int i =0;i<size;i++) {
                System.out.print(Arry[i]+" ");
            }
        }
    }
}
