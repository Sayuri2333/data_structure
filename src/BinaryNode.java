public class BinaryNode<T> {  //二叉树的二叉链表类, T指定节点的元素类型
    public T data; //数据域, 存储数据元素
    public BinaryNode<T> left, right; //地址域, 分别只指向左右结点
    public BinaryNode(T data, BinaryNode<T> left, BinaryNode<T> right){  // 构造节点, data指定元素, left, right分别指向左孩子和右孩子结点
        this.data = data;
        this.left = left;
        this.right = right;
    }
    public BinaryNode(T data){ //构造元素为data的叶子结点
        this(data, null, null);
    }
    public String toString(){ //返回结点数据域的描述字符串
        return this.data.toString();
    }
    public boolean isLeaf(){ //判断是否是叶子结点
        return this.left == null && this.right == null;
    }
    public BinaryNode(){
        this.data = null;
        this.left = null;
        this.right = null;
    }
}
