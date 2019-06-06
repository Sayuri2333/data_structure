public class CirDoublyList<T> {
    public DoubleNode<T> head; //新建头部节点
    public CirDoublyList(){ //新建空链表
        this.head = new DoubleNode<T>();
        this.head.prev = this.head;
        this.head.next = this.head;
    }
    public boolean isEmpty(){
        return this.head.next == this.head;
    } // 判断链表是否为空（自环）
    public DoubleNode<T> insert(int i, T x){ // 在给定位置插入元素
        DoubleNode<T> front = this.head; // 初始化指针
        if (x == null)
            throw new NullPointerException("X = null");
        for (int j = 0; front.next != this.head && j < i; j++){ // 将指针移动到给定位置的前面紧邻的位置
            front = front.next;
        }
        DoubleNode<T> p = new DoubleNode<T>(x, front, front.next); // 新建节点并连接上链表
        front.next.prev = p;
        front.next = p; // 修改链表中原有元素的连接
        return p; //返回新建的节点
    }
    public DoubleNode<T> insert(T x){ // 尾部插入
        if (x == null)
            throw new NullPointerException();
        DoubleNode<T> p = new DoubleNode<T>(x, head.prev, head); // 新建节点并连接上链表
        head.prev.next = p;
        head.prev = p; // 修改链表中原来的节点的连接
        return p; // 返回新节点
    }
    public String toPreviousString(){ // 倒序返回当前链表中所有元素的描述字符串
        if (this.head.prev == this.head) // 如果当前链表为空
            return this.getClass().getName();
        String str = this.getClass().getName() + "("; // 如果不为空，返回内部元素的描述字符串
        for (DoubleNode<T> p = this.head.prev; p != this.head; p = p.prev){ // 从头部节点开始向前遍历
            str += " " + p.data.toString(); // 每次更新描述字符串
            if (p != this.head.next) // 如果不是第一个元素
                str += ",";
        }
        return str + ")"; // 返回总描述字符串
    }
    public T removeLast(){ // 删除最后一个元素
        if (this.head.next == this.head) // 如果是空链表
            return null; // 返回null
        DoubleNode<T> p = this.head.prev; // 直接取最后一个节点
        this.head.prev = p.prev; // 修改头结点的prev位置
        p.prev.next = p.next; // 修改倒数第二个元素next位置
        return p.data; //返回删除节点的数据
    }
    public T get(int i){ // 返回给定位置的元素值
        DoubleNode<T> front = this.head; // 初始化指针的位置
        if (i < 0) // 处理非法输入
            i = 0;
        for (int j = 0; j < i + 1; j++){ // 调整指针的位置
            if (front.next != this.head)
                front = front.next; // 移动指针
        }
        return front.data; // 返回当前指针处的数据值
    }
    public T remove(int i){ // 根据给定的位置删除元素
        DoubleNode<T> p = this.head; // 新建指针
        if (i < 0) // 处理非法输入
            i = 0;
        for (int j = 0; j <= i; j++){ // 调整指针位置
            if (p.next != this.head)
                p = p.next; // 移动指针
        }
        T data = p.data; // 存储当前指针所指位置的值
        p.prev.next = p.next;
        p.next.prev = p.prev;
        p.prev = null;
        p.next = null; // 调整链表的顺序
        return data; //返回值
    }
}
