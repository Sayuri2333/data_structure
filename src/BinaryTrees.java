public class BinaryTrees {  //���ݹ������������
    private static int i = 0; //��ʼ������ֵ(��ΪҪ�ݹ��������ֻ������������)
    public static BinaryTree<String> createByGenlist(String genlist){
        BinaryTree<String> bitree = new BinaryTree<>();
        i = 0; // ÿ���½�����ʱ�򶼳�ʼ��һ������ֵ
        if (genlist.length() > 0){ //�Ϸ����ж�
            bitree.root = create(genlist);
        }
        return bitree;
    }
    private static BinaryNode<String> create(String genlist){ //���ݸ����ַ�������������
        BinaryNode<String> p = null; // ��ʼ���ڵ�
        char ch = genlist.charAt(i); // ȡ�õ�ǰ�����µ��ַ�
        if (ch == '^'){ //����ǿ�
            i++; //����ֵ����
            return null; //���ؿ�(�ݹ���ֹ����)
        }
        // ������һ���ܹ���þ��в������ȵ���Ч�ַ���
        int end = i; //�����ǰ�ַ����ǿյĻ�, ����һ��β������
        while(end < genlist.length() && ch != '(' && ch != ',' && ch != ')'){ // �����ǰ����ָ�������Ч�ַ�
            end++; //β����������
            ch = genlist.charAt(end); //�����һ��λ�õ��ַ�
        }
        String str = genlist.substring(i, end); //ȡ�õ�ǰ������Ч���ַ���(��ʱend��λ���Ѿ���������Ч�ַ���)
        i = end; //����i��ֵ
        p = new BinaryNode<String>(str); //������Ч�ַ��������½ڵ�
        if (genlist.charAt(i) == '('){ // �����ǰλ��Ϊ(�Ļ�(����������һ������)
            i++; // ��������
            p.left = create(genlist); // ��ڵ����
            i++;  // ��������
            p.right = create(genlist); //�ҽڵ����
            i++; //��������(Ҫ��ס��һ��ѭ������ʼ��������������Ա���ѭ����һ��Ҫ�ǵø���)
        }
        return p; //����p�ڵ�
    }
    public static void main(String[] args){
        String genlist = "AA(BB(DD(^,G),^),C(E,F(H,^)))";
        BinaryTree<String> bitree = BinaryTrees.createByGenlist(genlist);
        bitree.printGenlist();
    }
}
