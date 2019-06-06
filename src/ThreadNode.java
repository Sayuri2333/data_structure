public class ThreadNode<T> { //线索二叉树的节点类
    public T data; //数据域
    public ThreadNode<T> left, right; // 表明左孩子和右孩子
    public boolean ltag, rtag; // 标明左孩子和右孩子是否是前驱节点和后继节点
    public ThreadNode(T data, ThreadNode<T> left, boolean ltag, ThreadNode<T> right, boolean rtag){ // 给出数据生成一个节点
        this.data = data;
        this.left = left;
        this.ltag = ltag;
        this.rtag = rtag;
        this.right = right;
    }
    public ThreadNode(T data){ //只根据数据域制定节点
        this(data, null, false, null, false);
    }
    public String toString(){ //toString方法
        return this.data.toString();
    }
    public boolean isLeaf(){ //判断是否是叶节点
        return this.left == null && this.right == null;
    }
}
