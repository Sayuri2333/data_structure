import java.util.Arrays;

public class BinaryTree<T> {  //二叉树类
    public BinaryNode<T> root; //二叉树根节点
    public BinaryTree(){ //构造空树
        this.root = null;
    }
    public boolean isEmpty(){ //判断是否为空树
        return this.root == null;
    }
    public BinaryNode<T> insert(T x){ // 根节点左插入
        return this.root = new BinaryNode<>(x, this.root, null);
    }
    public BinaryNode<T> insert(BinaryNode<T> parent, T x, boolean leftChild){ // 中间插入,true代表为做插入, false代表为右插入
        if (x == null) //合法性判别
            return null;
        if (leftChild) //如果是左插入
            return parent.left = new BinaryNode<>(x, parent.left, null); // 将parent的左孩子结点设置为当前新节点
        return parent.right = new BinaryNode<>(x, parent.right, null); //否则将parent的右孩子结点设置为当前新节点
    }
    public void remove(BinaryNode<T> parent, boolean leftChild){ // 删除部分树
        if (leftChild) //删除左孩子节点上的树
            parent.left = null;
        else //删除右孩子节点上的树
            parent.right = null;
    }
    public void clear(){ //清空树
        this.root = null;
    }
    public void preorder(){ //先根遍历
        preorder(this.root); //从根节点开始先根遍历
        System.out.println();
    }
    public void preorder(BinaryNode<T> p){ // 从给定节点开始先根遍历
        if (p != null){ //停止条件p==null
            System.out.print(p.data.toString() + ""); // 输出当前结点的描述字符串
            preorder(p.left); //输出左结点的描述字符串
            preorder(p.right); //输出右结点的描述字符串
        }
    }
    public String toString(){ //先根遍历的字符串表示方法
        return toString(this.root);
    }
    private String toString(BinaryNode<T> p){ //根据给定节点开始先根遍历
        if (p == null) //停止条件为p==null
            return "^";//返回^
        return p.data.toString() + toString(p.left) + toString(p.right); // 停止条件之前返回当前节点的描述字符串和左孩子结点的描述字符串和右孩子节点的描述字符串
    }
    public void inorder(){ // 中根遍历
        inorder(this.root); // 调用中根遍历方法,从根节点开始遍历
        System.out.println();
    }
    public void inorder(BinaryNode<T> p){ // 从当前结点开始中根遍历
        if (p != null){ //停止条件同上
            inorder(p.left); //先返回当前左孩子节点的描述字符串
            System.out.print(p.data.toString() + " "); // 返回当前结点的描述字符串
            inorder(p.right); // 返回右孩子节点的描述字符串
        }
    }
    public void postorder(){ //后根遍历
        postorder(this.root); //调用后根遍历方法, 以根节点作为起点
    }
    public void postorder(BinaryNode<T> p){ //以给定节点作为起点后根遍历当前树
        if (p != null){ //停止条件同上
            postorder(p.left); //先遍历左边的
            postorder(p.right); //再遍历右边的
            System.out.print(p.data.toString() + " "); //最后输出中间的
        }
    }
    public int size(){ //size方法
        return this.size(this.root); //调用size方法,以根节点作为起点
    }
    private int size(BinaryNode<T> p){ //以当前结点作为根节点计算size
        if (p != null){ //停止条件同上
            return 1 + size(p.left) + size(p.right); //计算本身size + 左孩子树size + 右孩子树size
        }else
            return 0; // 触发停止条件时返回0
    }
    public int height(){ //height方法
        return this.height(this.root); // 调用给定的height方法
    }
    private int height(BinaryNode<T> p){ // 给定节点作为根节点来计算height
        if (p != null){ //停止条件同上
            int height_left = height(p.left); // 当前左孩子树的高度
            int height_right = height(p.right); //当前右孩子树的高度
            if (height_left >= height_right) // 比较选择较大的高度
                return height_left + 1;
            else
                return height_right + 1; //加上1作为自己的高度
        }else
            return 0; //触发停止条件时返回0
    }
    public BinaryTree(T[] prelist){
        this.root = create(prelist);
    }
    private int i = 0;
    private BinaryNode<T> create(T[] prelist){
        BinaryNode<T> p = null;
        if (i < prelist.length){
            T element = prelist[i];
            i++;
            if (element != null){
                p = new BinaryNode<T>(element);
                p.left = create(prelist);
                p.right = create(prelist);
            }
        }
        return p;
    }
    public void printGenlist(){ //输出二叉树的广义表的表示
        System.out.println("二叉树的广义表的表示为: ");
        printGenlist(this.root);
        System.out.println();
    }
    public void printGenlist(BinaryNode<T> p){  //根据给定的节点输出广义表
        if (p == null){ //递归的停止条件
            System.out.print("^");
        }else { //如果是有数据的节点的话
            System.out.print(p.data.toString() + ""); //先根遍历的广义表
            if (!p.isLeaf()){  //如果这个节点不是叶子结点的话
                System.out.print("("); //产生子树才会出现的括号
                printGenlist(p.left); //递归调用左孩子结点
                System.out.print(", ");
                printGenlist(p.right);  //递归调用右孩子节点
                System.out.print(")");
            }
        }
    }
    public void preorderTraverse(){  //非递归实现先根遍历的算法
        System.out.print("先根遍历(非递归): ");
        LinkedStack<BinaryNode<T>> stack = new LinkedStack<>(); //新建栈用于存储节点
        BinaryNode<T> p = this.root; //从根节点开始遍历
        while(p != null || !stack.isEmpty()){ // 进行遍历循环
            // 先输出根节点, 在转向其左节点, 由此一直深入, 直到叶子节点尾部, 然后通过栈依次转向右节点
            if (p != null){ // 如果p不是空的话
                System.out.print(p.data + ""); // 输出p的数据
                stack.push(p); //将p绩点压入栈中
                p = p.left; //转向其左孩子节点
            }else{ // 如果p是空的话(叶子节点的尾部)
                System.out.print("^ "); //输出空
                p = stack.pop(); //弹出上一个压入栈的节点
                p = p.right; //并转向其右孩子节点
            }
        }
        System.out.println();
    }
    public void levelorder(){  //层次遍历的方法
        System.out.print("层次遍历: ");
        LinkedQueue<BinaryNode<T>> que = new LinkedQueue<>(); //使用队列来进行实现
        BinaryNode<T> p = this.root;  // 从根节点开始遍历
        while(p != null){ // 在队列空了而且p为null的时候结束
            System.out.print(p.data + ""); //输出当前节点的数据
            if (p.left != null) //将当前节点的左孩子, 右孩子按顺序添加进入队列
                que.add(p.left);
            if (p.right != null)
                que.add(p.right);
            p = que.poll(); //取得队列中的下一个节点的值
        }
        System.out.println(); //在对于所有节点遍历完成之后换行
    }
    public BinaryTree(T prelist[], T inlist[]){ //根据先根顺序表和中根顺序表构建二叉树
        this.root = createTreeByPreAndIn(prelist, inlist);
    }
//    private BinaryNode<T> createTreeByPreAndIn(T[] prelist, T[] inlist, int start, int end, int length){
//        if (prelist == null || prelist.length == 0 || inlist == null || inlist.length == 0)
//            return null;
//        T value = prelist[start];
//         BinaryNode<T> root = new BinaryNode<>();
//         root.data = value;
//         if (length == 1)
//             return root;
//         int i = 0;
//         while(i < length){
//             if (value == inlist[end - i])
//                 break;
//             i++;
//         }
//         root.left = createTreeByPreAndIn(prelist, inlist, start + 1, end - i - 1, length - i - 1);
//         root.right = createTreeByPreAndIn(prelist, inlist, start + length - i, end, i);
//         return root;
//    }
    public BinaryTree<T> create(T inlist[], T postlist[]){ //根据后根顺序表和中根顺序表构建二叉树
        this.root = createTreeByPostAndIn(postlist, inlist);
        return this;
    }
    public BinaryNode<T> createTreeByPostAndIn(T[] postlist, T[] inlist){ //构建时使用的递归调用方法
        if (postlist.length == 0 || inlist.length == 0) //递归停止条件(表长为0)
            return null;
        T value = postlist[postlist.length - 1]; //取得当前后根顺序表中的最后一个(也就是当前的表中的根节点)
        BinaryNode<T> node = new BinaryNode<>(); //初始化当前表的根节点
        node.data = value; //设置根节点的值
        if (postlist.length == 1) //如果表长度为1,则证明这是最后一个节点,直接返回
            return node;
        int i = 0; //用于在中根顺序表中找到对应根节点的位置
        while (i < inlist.length){
            if (value == inlist[i])
                break;
            i++;
        } //此时i的值就是当前表的根节点在中根顺序表中的对应索引值
        //根据索引位置将当前两个表切分为左子树和右子树,并递归调用这个方法来建立节点
        node.right = createTreeByPostAndIn(Arrays.copyOfRange(postlist, i,postlist.length - 1), Arrays.copyOfRange(inlist, i + 1, inlist.length));
        node.left = createTreeByPostAndIn(Arrays.copyOfRange(postlist, 0, i), Arrays.copyOfRange(inlist, 0, i));
        return node; //返回根节点

    }
    public BinaryNode<T> createTreeByPreAndIn(T[] prelist, T[] inlist) { //根据先根顺序表和中根顺序表建立二叉树
        if (prelist.length == 0 || inlist.length == 0) //如果长度为0返回空(因为二叉树左右子树会有不对称的情况,所以当切分表长为0时也要考虑到)
            return null;
        T value = prelist[0]; //根据先根顺序表获得当前根节点的值
        BinaryNode<T> node = new BinaryNode<>(); //初始化根节点
        node.data = value; //赋值根节点
        if (prelist.length == 1) //若当前表的长度为1, 则直接返回当前节点
            return node;
        int i = 0; //用于在中根顺序表中找到对应根节点的位置
        while (i < inlist.length){
            if (value == inlist[i])
                break;
            i++;
        }//此时i的值就是当前表的根节点在中根顺序表中的对应索引值
        //根据索引位置将当前两个表切分为左子树和右子树,并递归调用这个方法来建立节点
        node.left = createTreeByPreAndIn(Arrays.copyOfRange(prelist, 1, 1 + i), Arrays.copyOfRange(inlist, 0, i));
        node.right = createTreeByPreAndIn(Arrays.copyOfRange(prelist, i + 1, prelist.length), Arrays.copyOfRange(inlist, i + 1, inlist.length));
        return node;
    }

    public void leaf(){ //输出所有叶子结点的值
        System.out.print("叶子结点为: ");
        this.leafString(this.root); //调用递归方法
        System.out.println();
    }
    public void leafString(BinaryNode<T> p){ //递归求叶子节点的值
        if (p != null){ //如果当前节点不是null(以节点的形式存在)则判断是否是叶子节点
            if (p.isLeaf()) //调用节点自身的方法判断是否为叶子结点
                System.out.print(p.data + " "); //若是, 则输出数据值
            leafString(p.left); //对其左子树调用
            leafString(p.right);// 对其右子树调用
        }
    }
    public int leafCount(){ //输出所有叶子节点的数量
        leafCountnum = 0; //初始化数量值(每次调用这个方法时都需要初始化,以防上一次调用的影响)
        leafCount(this.root); //递归调用求叶子结点数量
        return leafCountnum;
    }
    private int leafCountnum = 0; //初始化的叶子结点数量值
    public void leafCount(BinaryNode<T> p){ //递归调用输出叶子数量
        if (p != null){ //节点非空时可以用来判断
            if (p.isLeaf()) //判断是否是叶子节点
                leafCountnum++; //是的话count+1
            leafCount(p.left); //对于左孩子节点递归调用
            leafCount(p.right); //对于右孩子节点递归调用
        }
    }
    public void replaceAll(T key, T x){ //替换所有为key值的节点的值为x
        replaceAll(this.root, key, x); //递归调用的方法
    }
    public void replaceAll(BinaryNode<T> p, T key, T x){ //递归调用修改节点的值
        if (p != null){ //非空的时候可以修改值
            if (p.data == key) //判断值是否满足匹配要求
                p.data = x; //修改值
            replaceAll(p.left, key, x); //对于左孩子递归调用
            replaceAll(p.right, key, x); //对于右孩子递归调用
        }
    }
    public BinaryNode<T> search(T key){ //搜索方法
        //使用栈是因为不是每一次循环都要返回节点, 如果使用递归调用不方便进行返回值的设置区分
        LinkedStack<BinaryNode<T>> stack = new LinkedStack<>();
        BinaryNode<T> p = this.root; //从当前根节点开始遍历
        while (p != null || !stack.isEmpty()){ //如果节点还没有遍历完成
            if (p != null){ //如果节点不为空(可以判断数据域)
                if (p.data == key) //判断数据域
                    return p; //相等时返回此节点
                stack.push(p); //不相等时压入栈中, 接着进行遍历
                p = p.left; //按照先根遍历顺序进行遍历
            }else {
                p = stack.pop(); //左孩子已经遍历完成, 切换到右孩子
                p = p.right;
            }
        }
        return null; //本身就是空树的话直接返回空
    }
    public BinaryNode<T> parents(BinaryNode<T> node){ //求给定节点的父母节点
        LinkedStack<BinaryNode<T>> stack = new LinkedStack<>(); //使用栈进行遍历(因为栈会存储当前节点的父母节点)
        BinaryNode<T> p = this.root; //从根节点开始遍历
        while (p != null || !stack.isEmpty()){ //遍历树
            if (p != null){ //如果当前节点非空
                if (p.left == node || p.right == node) //可以判断其孩子节点是否为给定的节点
                    return p; //是的话直接返回当前节点
                stack.push(p); //不是的话压入栈中继续遍历
                p = p.left;
            }else {
                p = stack.pop();
                p = p.right;
            }
        }
        return null; //如果遍历完成没有找到就返回null
    }
    public int level(T key){ //求给定的值在当前树中的层次
        return level(this.root, key); //递归调用方法
    }
    public int level(BinaryNode<T> p,T key){ //递归调用方法
        if (p == null) // 递归调用停止条件, 返回-1表示没找到
            return -1;
        if (p.data.equals(key)) //与当前节点进行比较
            return 1;
        int depth = level(p.left, key); //递归调用寻找
        if (depth > 0) //如果depth大于0, 证明找到了
            return depth + 1; //返回depth + 1(包括本层)的值
        depth = level(p.right, key); //同上
        if (depth > 0)
            return depth + 1;
        return -1; //都没找到返回-1
    }
    public String toGenlist(){ //修改的广义表方法,使得其返回广义表字符串
        return toGenlist(this.root);
    }
    public String toGenlist(BinaryNode<T> p){
        String str = "";
        if (p == null)
            str += "^";
        else {
            str += p.data.toString() + "";
            if (!p.isLeaf()){
                str += "(";
                str += toGenlist(p.left);
                str += ", ";
                toGenlist(p.right);
                str += ")";
            }
        }
        return str;
    }
    public boolean equals(Object obj){ // 判断两个二叉树是否相等
        if (obj == this) //如果引用同一个内存地址
            return true;
        else if (obj instanceof BinaryTree<?>){ // 如果给定的obj是当前类的一个实例
            if (this.root.data.equals(((BinaryTree) obj).root.data)){ //判断两个树的根节点是否相同(同时判断数据类型是否相同)
                String str_1 = this.toGenlist(); //将两个树转换为广义表之后对广义表进行判断(因为广义表与树一一对应)
                String str_2 = ((BinaryTree) obj).toGenlist();
                if (str_1.equals(str_2))
                    return true;
                else
                    return false;
            }
            return false;
        }else
            return false;
    }
    public BinaryNode<T> search(BinaryTree<T> pattern){ //根据给定的子树搜索当前树(默认pattern中的叶节点也必须在当前树中为叶节点)
        BinaryNode<T> p = searchRoot(pattern.root, this.root); //搜索当前树是否有节点与pattern的根节点匹配
        if (p != null){ //p不为空的话表示找到了节点
            if (matchTree(p, pattern.root)){ //返回true说明匹配成功
                return p; //返回找到的根节点
            }
            return null; //不匹配返回空
        }
        else
            return p; //找不到返回空
    }
    public BinaryNode<T> searchRoot(BinaryNode<T> p , BinaryNode<T> q){ //搜索根节点
        BinaryNode<T> m = null;
        if (p != null && q != null){ //判断给定的两个节点数据值是否相等
            if (p.data == q.data)
                return q; //相等返回q(q为当前树中的节点)
        }
        if (q != null){ // 在遍历完整个树之前
            m = searchRoot(p, q.left); //遍历左孩子节点
            if (m == null){ //左子树没有的话
                m = searchRoot(p, q.right); //遍历右子树
            }
        }
        return m; //返回值
    }
    public boolean matchTree(BinaryNode<T> p, BinaryNode<T> q){ //判断是否与pattern匹配
        boolean temp = (p == null && q == null) || (q != null && p != null && (p.data.equals(q.data))); //先判断当前节点是否相等
        if (!temp) //不相等返回false
            return false;
        if (p != null && q != null){ //p节点和q节点都进行同步移动知道遍历完整个pattern
            return temp && (matchTree(p.left, q.left) && matchTree(p.right, q.right));
        }else
            return true; //全部一直返回true
    }
    public void removeAll(BinaryTree<T> pattern){ //删除所有与pattern相同的子树
        BinaryNode<T> p = search(pattern); //寻找相同的子树的根节点
        while (p != null){ //直到找不到为止
            BinaryNode<T> parent = parents(p); //取得当前节点的父母节点
            if (parent.left.data.equals(p.data)) //判断当前节点是左孩子还是右孩子
                parent.left = null;
            else
                parent.right = null; //删除以当前节点为根节点的子树
            p = search(pattern); //找下一个子树
        }
    }
}
