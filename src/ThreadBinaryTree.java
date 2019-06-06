public class ThreadBinaryTree<T> {  //线索二叉树类
    public ThreadNode<T> root; //根节点
    public ThreadBinaryTree(){ //建立空的线索二叉树
        this.root = null;
    }
    public ThreadBinaryTree(T[] prelist){ // 根据先根遍历表建立节点
        this.root = create(prelist);
        inorderThread(this.root); //中根线索化
    }
    private int i = 0; //判断先根遍历表的标记位置
    private ThreadNode<T> create(T[] prelist){ // 建立线索树(还未线索化)
        ThreadNode<T> p = null; //新建空节点
        if (i < prelist.length){ //递归调用结束点
            T element = prelist[i]; //取得当前位置的元素
            i++; //更新标记位置
            if (element != null){ // 如果非空
                p = new ThreadNode<>(element); //根据数据新建节点
                p.left = create(prelist); //左孩子节点递归建立
                p.right = create(prelist); //右孩子节点递归建立
            }
        }
        return p; //返回建立的节点
    }
    public boolean isEmpty(){ //判断是否为空
        return this.root == null;
    }
    private ThreadNode<T> front = null; //初始化前驱标记节点
    private void inorderThread(ThreadNode<T> p){ //线索化当前树
        if (p != null){ //非空时
            inorderThread(p.left); // 中根遍历顺序所以先线索化左孩子节点
            if (p.left == null){ // 如果左孩子位置为空
                p.ltag = true; //设置线索标记
                p.left = front; //左孩子节点指向前驱节点(检测后同步建立联系)
            }
            if (p.right == null) // 如果右孩子位置为空
                p.rtag = true; //设置线索标记
            if (front != null && front.rtag) //如果front的右孩子节点为空
                front.right = p; //设置后继节点
            front = p; //更新front的位置(p的位置不需要更新, 因为每次调用这个方法的时候已经更新好了p的位置)
            inorderThread(p.right); //对p的右子树使用线索化
        }
    }
    public ThreadNode<T> inNext(ThreadNode<T> p){ //求当前节点的后继节点
        if (p.rtag) //如果后继节点标志被设置
            return  p.right; //直接返回后继节点
        p = p.right; //如果没有被设置, 遵循规律(右孩子的最左下的节点为后继节点)
        while (!p.rtag) // 遍历寻找最左下的节点(叶节点)(因为它设置了右线索标记)
            p = p.left;
        return p; //返回得到的p
    }
}
