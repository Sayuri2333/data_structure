public class BinaryTree_output
{
    public static void main(String args[])
    {                                                           //ͼ6.15��ʾ�������������������ȸ���������
        String[] prelist = {"A","B","D",null,"G",null,null,null,"C","E",null,null,"F","H"};
        String[] pattern_1 = {"D", null, "G"};
        BinaryTree<String> patree_1 = new BinaryTree<>(pattern_1);
        System.out.println("pattern_1�Ĺ�����ʾΪ: ");
        patree_1.printGenlist();
        String[] postlist = {"G", "D", "B", "E", "H", "F", "C", "A"};
        String[] inlist = {"D", "G", "B", "A", "E", "C", "H", "F"};
//        System.out.println("inlistΪ: {\"D\", \"G\", \"B\", \"A\", \"E\", \"C\", \"H\", \"F\"}");
//        System.out.println("postlistΪ: {\"D\", \"G\", \"B\", \"A\", \"E\", \"C\", \"H\", \"F\"}");
        BinaryTree<String> bitree = new BinaryTree<>();
        bitree.create(inlist, postlist);
        System.out.println("-------ɾ��ģʽǰ��ԭ�����-------");
        bitree.printGenlist();
        bitree.removeAll(patree_1);
        System.out.println("-------ɾ��ģʽ��Ĺ����-------");
        bitree.printGenlist();
    }
}