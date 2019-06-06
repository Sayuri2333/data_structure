import java.io.Serializable;
public class LinkedQueue<T> implements Queue<T>, Serializable{
    private Node<T> front, rear; //设置两个指针分别指向前后
    public LinkedQueue(){
        this.front = this.rear = null; //初始化指针的位置
    }
    public boolean isEmpty(){
        return this.front == null && this.rear == null; //前后指针都为空的时候为空
    }
    public boolean add(T x){
        if (x == null) //空对象排除
            return false;
        Node<T> q = new Node<T>(x, null); //根据数据新建节点
        if (this.front == null) //空队插入
            this.front = q;
        else this.rear.next = q; //尾部插入,将当前尾部的next赋值为q从而连接起链表
        this.rear = q; //将尾指针指向尾部
        return true;
    }
    public T peek(){
        return this.isEmpty()? null: this.front.data; //返回栈顶值
    }
    public T poll(){
        if (isEmpty()) //空链表返回空
            return null;
        T x = this.front.data; //队列顶返回值
        this.front = this.front.next; //front指针前移
        if (this.front == null) //如果front指针为null时证明已经为空队列,则改变rear指针的值
            this.rear = null;
        return x;
    }
    public int size(){ //求队列的长度
        int i = 0; //初始化计数器
        Node<T> p; // 初始化指针的位置
        if (! this.isEmpty()){ // 如果队列非空
            for (p = this.front; p != null; p = p.next) // 遍历整个队列
                i++; //增加计数
            return i; //返回长度
        }
        return 0; //空队列返回0
    }
}