public class TriElement { // 二叉树的三叉链表节点类
    int data; //数据域
    int parent, left, right; //制定父母节点, 左孩子节点和右孩子节点
    public TriElement(int data, int parent, int left, int right){ //根据给定的数据生成三叉节点
        this.data = data;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }
    public TriElement(int data){ //根据数据内容初始化节点
        this(data, -1, -1, -1); //父母节点, 左孩子节点和右孩子节点默认为1
    }
    public String toString(){ //描述字符串
        return "(" + this.data + ", " + this.parent + ", " + this.left + ", " + this.right + ")";
    }
    public boolean isLeaf(){ //判断是否为叶子结点
        return this.left == -1 && this.right == -1;
    }
}
