import java.io.Serializable;
public class Node<T> implements Serializable{ //新建节点类
    public T data; //节点类中存储的数据
    public Node<T> next; //下一个节点的内存地址
    public Node(T data, Node<T> next){ //新建节点
        this.data = data;
        this.next = next;
    }
    public Node(){
        this(null, null);
    } //新建空节点
    public String toString(){
        return this.data.toString();
    } //节点的表述方法
}
