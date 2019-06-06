public class PriorityQueue<T extends Comparable<? super T>> implements Queue<T>{
    private SortedCirDoublyList<T> list; // 使用排序循环双链表作为存储空间
    private boolean asc; // 指定升序或者降序
    public PriorityQueue(boolean asc){ // 根据指定的升降序来生成优先队列
        this.list = new SortedCirDoublyList<T>();
        this.asc = asc;
    }
    public PriorityQueue(){
        this(true);
    } //默认使用升序
    public boolean isEmpty(){
        return this.list.isEmpty();
    } // 返回是否非空
    public boolean add(T x){
        return this.list.insert(x) != null;
    } // 插入队列
    public T peek(){
        return this.asc ? this.list.get(0) : this.list.head.prev.data;
    } //升序时返回队头的元素,降序时返回队尾的元素
    public T poll(){
        return this.asc ? this.list.remove(0) : this.list.removeLast();
    } // 同上
    public String toString(){ // 返回队列中所有元素的描述字符串
        return this.getClass().getName() + " " + (this.asc ? this.list.toString() : this.list.toPreviousString());
    }
}
