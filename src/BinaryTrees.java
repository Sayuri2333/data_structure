public class BinaryTrees {  //根据广义表创建二叉树
    private static int i = 0; //初始化索引值(因为要递归调用所以只能设置在外面)
    public static BinaryTree<String> createByGenlist(String genlist){
        BinaryTree<String> bitree = new BinaryTree<>();
        i = 0; // 每次新建树的时候都初始化一次索引值
        if (genlist.length() > 0){ //合法性判断
            bitree.root = create(genlist);
        }
        return bitree;
    }
    private static BinaryNode<String> create(String genlist){ //根据给定字符串创建二叉树
        BinaryNode<String> p = null; // 初始化节点
        char ch = genlist.charAt(i); // 取得当前索引下的字符
        if (ch == '^'){ //如果是空
            i++; //索引值更新
            return null; //返回空(递归终止条件)
        }
        // 下面这一段能够获得具有不定长度的有效字符串
        int end = i; //如果当前字符不是空的话, 设置一个尾部索引
        while(end < genlist.length() && ch != '(' && ch != ',' && ch != ')'){ // 如果当前索引指向的是有效字符
            end++; //尾部索引更新
            ch = genlist.charAt(end); //检测下一个位置的字符
        }
        String str = genlist.substring(i, end); //取得当前整个有效的字符串(此时end的位置已经不属于有效字符了)
        i = end; //更新i的值
        p = new BinaryNode<String>(str); //根据有效字符串创建新节点
        if (genlist.charAt(i) == '('){ // 如果当前位置为(的话(表明后面是一个子树)
            i++; // 索引更新
            p.left = create(genlist); // 左节点更新
            i++;  // 索引更新
            p.right = create(genlist); //右节点更新
            i++; //索引更新(要记住下一个循环的起始不会更新索引所以本次循环中一定要记得更新)
        }
        return p; //返回p节点
    }
    public static void main(String[] args){
        String genlist = "AA(BB(DD(^,G),^),C(E,F(H,^)))";
        BinaryTree<String> bitree = BinaryTrees.createByGenlist(genlist);
        bitree.printGenlist();
    }
}
