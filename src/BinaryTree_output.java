public class BinaryTree_output
{
    public static void main(String args[])
    {                                                           //图6.15所示二叉树标明空子树的先根遍历序列
        String[] prelist = {"A","B","D",null,"G",null,null,null,"C","E",null,null,"F","H"};
        String[] pattern_1 = {"D", null, "G"};
        BinaryTree<String> patree_1 = new BinaryTree<>(pattern_1);
        System.out.println("pattern_1的广义表表示为: ");
        patree_1.printGenlist();
        String[] postlist = {"G", "D", "B", "E", "H", "F", "C", "A"};
        String[] inlist = {"D", "G", "B", "A", "E", "C", "H", "F"};
//        System.out.println("inlist为: {\"D\", \"G\", \"B\", \"A\", \"E\", \"C\", \"H\", \"F\"}");
//        System.out.println("postlist为: {\"D\", \"G\", \"B\", \"A\", \"E\", \"C\", \"H\", \"F\"}");
        BinaryTree<String> bitree = new BinaryTree<>();
        bitree.create(inlist, postlist);
        System.out.println("-------删除模式前的原广义表-------");
        bitree.printGenlist();
        bitree.removeAll(patree_1);
        System.out.println("-------删除模式后的广义表-------");
        bitree.printGenlist();
    }
}