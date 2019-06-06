public class SortedCirDoublyList<T extends Comparable<? super T>> extends CirDoublyList<T> { // 排序循环双链表
    public SortedCirDoublyList(){
        super();
    }
    public SortedCirDoublyList(T[] values){
        for (int i = 0; i < values.length; i++){
            this.insert(values[i]);
        }
    }
    public SortedCirDoublyList(CirDoublyList<T> list){
        for (DoubleNode<T> p = list.head.next; p != list.head; p = p.next){
            this.insert(p.data);
        }
    }
    public SortedCirDoublyList(SortedCirDoublyList<T> list){
        DoubleNode<T> q = this.head;
        for (DoubleNode<T> p = list.head.next; p != list.head; p = p.next){
            q.next = new DoubleNode<T>(p.data, q, this.head);
            q = q.next;
        }
    }
    public void set(int i, T x){
        throw new UnsupportedOperationException("set(int i, T x)");
    }
    public DoubleNode<T> insert(T x){ //修改插入方法
        if (this.isEmpty() || x.compareTo(this.head.prev.data) > 0){ // 如果数组为空或者当前给定元素比最大的元素还要大
            return super.insert(x); //直接尾部插入
        }else{ //否则
            for (DoubleNode<T> front = this.head; front != this.head.prev; front = front.next){ // 遍历链表中的元素
                if (x.compareTo(front.next.data) <= 0){ // 与给定的值相比较,如果给定的值更小
                    DoubleNode<T> p = new DoubleNode<T>(x, front, front.next); // 新建节点
                    front.next.prev = p;
                    front.next = p; //将新建的节点连接上去
                    return p; //返回节点
                }
            }
        }
        return null; //否则返回null
    }
}
