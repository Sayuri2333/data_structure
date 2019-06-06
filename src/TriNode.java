public class TriNode<T> { //三叉链表节点类
    public T data; //存储数据的数据域
    public TriNode<T> parent, left, right; //指向父母和子节点
    public TriNode(T data, TriNode<T> parent, TriNode<T> left, TriNode<T> right){ //构造三叉链表节点
        this.right = right;
        this.left = left;
        this.parent = parent;
        this.data = data;
    }
    public TriNode(T data){ //根据给定的数据构造三叉链表节点
        this(data, null, null, null);
    }
    public TriNode(){ //构造空节点
        this(null);
    }
    public String toString(){ //返回描述字符串
        return this.data.toString();
    }
    public boolean isLeaf(){ //根据是否有孩子节点判断是否为叶子结点
        return this.left == null && this.right == null;
    }
}
