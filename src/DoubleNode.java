public class DoubleNode<T> { //双链表节点类
    public T data; //节点内存储的数据
    public DoubleNode<T> prev, next; // 本节点的前后位置的节点
    public DoubleNode(T data, DoubleNode<T> prev, DoubleNode<T> next){ // 新建节点
        this.data = data;
        this.prev = prev;
        this.next = next;
    }
    public DoubleNode(){
        this(null, null, null);
    } // 新建空节点
    public String toString(){
        return this.data.toString();
    } // 返回描述字符串
}
