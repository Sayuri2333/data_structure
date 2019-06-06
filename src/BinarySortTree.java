public class BinarySortTree<T extends Comparable<? super T>> { //二叉排序树
    public TriNode<T> root; //根节点
    private int count = 0; //计数器
    public BinarySortTree(){ //构造空的二叉排序树
        this.root = null;
    }
    public BinarySortTree(T[] values){ //根据给定的少数族构造二叉排序树
        this();
        this.addAll(values);
    }
    public boolean isEmpty(){ //判断是否为空
        return this.root == null;
    }
    public TriNode<T> searchNode(T key){ //根据给定的数据搜索对应的节点
        TriNode<T> p = this.root; //初始化指针
        while (p != null && key.compareTo(p.data) != 0){ //判断给出的元素与指针所指的元素的大小关系
            if (key.compareTo(p.data) < 0) //根据这个来给出下一步的去向
                p = p.left;
            else
                p = p.right;
        }
        return p != null ? p : null; //返回值
    }
    public T search(T key){ //根据给定的值搜索树,查看是否含有这个值
        TriNode<T> find = this.searchNode(key); //调用searchNode方法获得节点
        return find != null ? find.data : null; //获得节点的数据值
    }
    public boolean add(T x){ //根据数据添加节点
        if (this.root == null) //如果当前树为空
            this.root = new TriNode<>(x); //将数据设置成根节点
        else {
            TriNode<T> p = this.root, parent = null; //新建p指针和parent指针
            while (p != null){ //寻找插入节点的位置
                if (x.compareTo(p.data) == 0) //如果发现一样的节点就返回false表示节点已经存在
                    return false;
                parent = p; //parent指针移动到p指针位置
                if (x.compareTo(p.data) < 0) //根据大小关系根性p指针的位置
                    p = p.left;
                else
                    p = p.right;
            }
            if (x.compareTo(parent.data) < 0) //此时p指针所指的位置即插入位置,parent即插入新节点的父母节点
                parent.left = new TriNode<>(x, parent, null, null); //根据大小关系插入节点
            else
                parent.right = new TriNode<>(x, parent, null, null);
        }
        count++; //更新计数器的值
        return true; //返回插入成功
    }
    public String toString(){ //返回描述字符串(中根遍历)
        String str = "[";
        TriNode<T> p = this.first(this.root); //寻找当前中根遍历次序下的第一个节点
        while (p != null){
            str += p.data.toString() + " "; //输出
            p = this.next(p); //切换到下一个节点
        }
        return str + "]"; //返回字符串
    }
    public void inorder(){
        System.out.println(this.toString());
    }
    public TriNode<T> first(TriNode<T> p){ //返回以p为根节点的树中的中根遍历次序下的第一个节点
        if (p != null) //合法性判断
            while (p.left != null) //以p为根节点的最左子树节点
                p = p.left;
        return p; //返回
    }
    public TriNode<T> next(TriNode<T> p){ //p节点的后继节点(应该是右子树上的第一个节点)
        if (p != null){ //合法性判断
            if (p.right != null) //有右子树
                return this.first(p.right); //找右子树上的第一个节点
            while (p.parent != null){ //如果没有右子树,有父母节点
                if (p.parent.left == p) //如果p是父母节点的左孩子
                    return p.parent; //返回父母节点
                p = p.parent; //否则继续寻找父母节点
            }
        }
        return null; //找不到返回空
    }
    public boolean contains(T key){
        return this.searchNode(key) != null;
    }
    public void addAll(T[] values){
        for (int i = 0; i < values.length; i++)
            this.add(values[i]);
    }
    public void clear(){
        this.root = null;
    }
    public int size(){
        return count;
    }
    public T remove(T key){ // 根据给定的值删除节点
        TriNode<T> p = this.searchNode(key); //获得需要被删除的节点
        if (p != null && p.left != null && p.right != null){ //如果这个节点为二度节点(有左右孩子)
            TriNode<T> insucc = this.first(p.right); //找到中根遍历次序下此节点的后继节点
            T temp = p.data;
            p.data = insucc.data;
            insucc.data = temp; //交换当前节点和后继节点的值(交换的原因是最后要返回正确的删除值)
            p = insucc; //将要删除的节点变为当前节点的后继节点(其必然是一度节点或者是叶子结点)
        }
        if (p != null && p == this.root){ //如果要删除的是根节点(则根节点一定为叶子结点或者是一度节点)
            if (this.root.left != null) //有左孩子的话(就没有右孩子)
                this.root = p.left; //根节点设置为左孩子
            else
                this.root = p.right; //否则设置为右孩子
            if (this.root != null) //判断设置后的根节点是否为空(也即是原来的根节点是否是叶子结点)
                this.root.parent = null; //不为空设置parent域为null
            return p.data; //返回删除节点的值
        }
        if (p != null && p == p.parent.left){ //如果要删除的节点是父母的左孩子
            if(p.left != null){ //如果当前节点有左孩子
                p.parent.left = p.left;
                p.left.parent = p.parent; //将当前节点的父母节点的左孩子设置为当前节点的左孩子
            }else { //当前节点有右孩子或者是叶子节点
                p.parent.left = p.right; //将当前节点的父母节点的左孩子设置为当前节点的右孩子
                if (p.right != null) //如果当前节点的右孩子不为空
                    p.right.parent = p.parent; //设置其parent域
            }
        }
        if (p != null && p == p.parent.right){ //如果要删除的节点是父母的右孩子
            if (p.left != null){ //如果当前节点有左孩子
                p.parent.right = p.left;
                p.left.parent = p.parent; //将当前节点的父母节点的左孩子设置为当前节点的左孩子
            }else{ //等等同上
                p.parent.right = p.right;
                if (p.right != null)
                    p.right.parent = p.parent;
            }
        }
        return p != null ? p.data : null; //返回删除节点的值
    }
    public T removeRoot(){
        return this.remove(this.root.data);
    }
}
