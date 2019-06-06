import java.util.Arrays;

public class BinaryTree<T> {  //��������
    public BinaryNode<T> root; //���������ڵ�
    public BinaryTree(){ //�������
        this.root = null;
    }
    public boolean isEmpty(){ //�ж��Ƿ�Ϊ����
        return this.root == null;
    }
    public BinaryNode<T> insert(T x){ // ���ڵ������
        return this.root = new BinaryNode<>(x, this.root, null);
    }
    public BinaryNode<T> insert(BinaryNode<T> parent, T x, boolean leftChild){ // �м����,true����Ϊ������, false����Ϊ�Ҳ���
        if (x == null) //�Ϸ����б�
            return null;
        if (leftChild) //����������
            return parent.left = new BinaryNode<>(x, parent.left, null); // ��parent�����ӽ������Ϊ��ǰ�½ڵ�
        return parent.right = new BinaryNode<>(x, parent.right, null); //����parent���Һ��ӽ������Ϊ��ǰ�½ڵ�
    }
    public void remove(BinaryNode<T> parent, boolean leftChild){ // ɾ��������
        if (leftChild) //ɾ�����ӽڵ��ϵ���
            parent.left = null;
        else //ɾ���Һ��ӽڵ��ϵ���
            parent.right = null;
    }
    public void clear(){ //�����
        this.root = null;
    }
    public void preorder(){ //�ȸ�����
        preorder(this.root); //�Ӹ��ڵ㿪ʼ�ȸ�����
        System.out.println();
    }
    public void preorder(BinaryNode<T> p){ // �Ӹ����ڵ㿪ʼ�ȸ�����
        if (p != null){ //ֹͣ����p==null
            System.out.print(p.data.toString() + ""); // �����ǰ���������ַ���
            preorder(p.left); //�������������ַ���
            preorder(p.right); //����ҽ��������ַ���
        }
    }
    public String toString(){ //�ȸ��������ַ�����ʾ����
        return toString(this.root);
    }
    private String toString(BinaryNode<T> p){ //���ݸ����ڵ㿪ʼ�ȸ�����
        if (p == null) //ֹͣ����Ϊp==null
            return "^";//����^
        return p.data.toString() + toString(p.left) + toString(p.right); // ֹͣ����֮ǰ���ص�ǰ�ڵ�������ַ��������ӽ��������ַ������Һ��ӽڵ�������ַ���
    }
    public void inorder(){ // �и�����
        inorder(this.root); // �����и���������,�Ӹ��ڵ㿪ʼ����
        System.out.println();
    }
    public void inorder(BinaryNode<T> p){ // �ӵ�ǰ��㿪ʼ�и�����
        if (p != null){ //ֹͣ����ͬ��
            inorder(p.left); //�ȷ��ص�ǰ���ӽڵ�������ַ���
            System.out.print(p.data.toString() + " "); // ���ص�ǰ���������ַ���
            inorder(p.right); // �����Һ��ӽڵ�������ַ���
        }
    }
    public void postorder(){ //�������
        postorder(this.root); //���ú����������, �Ը��ڵ���Ϊ���
    }
    public void postorder(BinaryNode<T> p){ //�Ը����ڵ���Ϊ�����������ǰ��
        if (p != null){ //ֹͣ����ͬ��
            postorder(p.left); //�ȱ�����ߵ�
            postorder(p.right); //�ٱ����ұߵ�
            System.out.print(p.data.toString() + " "); //�������м��
        }
    }
    public int size(){ //size����
        return this.size(this.root); //����size����,�Ը��ڵ���Ϊ���
    }
    private int size(BinaryNode<T> p){ //�Ե�ǰ�����Ϊ���ڵ����size
        if (p != null){ //ֹͣ����ͬ��
            return 1 + size(p.left) + size(p.right); //���㱾��size + ������size + �Һ�����size
        }else
            return 0; // ����ֹͣ����ʱ����0
    }
    public int height(){ //height����
        return this.height(this.root); // ���ø�����height����
    }
    private int height(BinaryNode<T> p){ // �����ڵ���Ϊ���ڵ�������height
        if (p != null){ //ֹͣ����ͬ��
            int height_left = height(p.left); // ��ǰ�������ĸ߶�
            int height_right = height(p.right); //��ǰ�Һ������ĸ߶�
            if (height_left >= height_right) // �Ƚ�ѡ��ϴ�ĸ߶�
                return height_left + 1;
            else
                return height_right + 1; //����1��Ϊ�Լ��ĸ߶�
        }else
            return 0; //����ֹͣ����ʱ����0
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
    public void printGenlist(){ //����������Ĺ����ı�ʾ
        System.out.println("�������Ĺ����ı�ʾΪ: ");
        printGenlist(this.root);
        System.out.println();
    }
    public void printGenlist(BinaryNode<T> p){  //���ݸ����Ľڵ���������
        if (p == null){ //�ݹ��ֹͣ����
            System.out.print("^");
        }else { //����������ݵĽڵ�Ļ�
            System.out.print(p.data.toString() + ""); //�ȸ������Ĺ����
            if (!p.isLeaf()){  //�������ڵ㲻��Ҷ�ӽ��Ļ�
                System.out.print("("); //���������Ż���ֵ�����
                printGenlist(p.left); //�ݹ�������ӽ��
                System.out.print(", ");
                printGenlist(p.right);  //�ݹ�����Һ��ӽڵ�
                System.out.print(")");
            }
        }
    }
    public void preorderTraverse(){  //�ǵݹ�ʵ���ȸ��������㷨
        System.out.print("�ȸ�����(�ǵݹ�): ");
        LinkedStack<BinaryNode<T>> stack = new LinkedStack<>(); //�½�ջ���ڴ洢�ڵ�
        BinaryNode<T> p = this.root; //�Ӹ��ڵ㿪ʼ����
        while(p != null || !stack.isEmpty()){ // ���б���ѭ��
            // ��������ڵ�, ��ת������ڵ�, �ɴ�һֱ����, ֱ��Ҷ�ӽڵ�β��, Ȼ��ͨ��ջ����ת���ҽڵ�
            if (p != null){ // ���p���ǿյĻ�
                System.out.print(p.data + ""); // ���p������
                stack.push(p); //��p����ѹ��ջ��
                p = p.left; //ת�������ӽڵ�
            }else{ // ���p�ǿյĻ�(Ҷ�ӽڵ��β��)
                System.out.print("^ "); //�����
                p = stack.pop(); //������һ��ѹ��ջ�Ľڵ�
                p = p.right; //��ת�����Һ��ӽڵ�
            }
        }
        System.out.println();
    }
    public void levelorder(){  //��α����ķ���
        System.out.print("��α���: ");
        LinkedQueue<BinaryNode<T>> que = new LinkedQueue<>(); //ʹ�ö���������ʵ��
        BinaryNode<T> p = this.root;  // �Ӹ��ڵ㿪ʼ����
        while(p != null){ // �ڶ��п��˶���pΪnull��ʱ�����
            System.out.print(p.data + ""); //�����ǰ�ڵ������
            if (p.left != null) //����ǰ�ڵ������, �Һ��Ӱ�˳����ӽ������
                que.add(p.left);
            if (p.right != null)
                que.add(p.right);
            p = que.poll(); //ȡ�ö����е���һ���ڵ��ֵ
        }
        System.out.println(); //�ڶ������нڵ�������֮����
    }
    public BinaryTree(T prelist[], T inlist[]){ //�����ȸ�˳�����и�˳�����������
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
    public BinaryTree<T> create(T inlist[], T postlist[]){ //���ݺ��˳�����и�˳�����������
        this.root = createTreeByPostAndIn(postlist, inlist);
        return this;
    }
    public BinaryNode<T> createTreeByPostAndIn(T[] postlist, T[] inlist){ //����ʱʹ�õĵݹ���÷���
        if (postlist.length == 0 || inlist.length == 0) //�ݹ�ֹͣ����(��Ϊ0)
            return null;
        T value = postlist[postlist.length - 1]; //ȡ�õ�ǰ���˳����е����һ��(Ҳ���ǵ�ǰ�ı��еĸ��ڵ�)
        BinaryNode<T> node = new BinaryNode<>(); //��ʼ����ǰ��ĸ��ڵ�
        node.data = value; //���ø��ڵ��ֵ
        if (postlist.length == 1) //�������Ϊ1,��֤���������һ���ڵ�,ֱ�ӷ���
            return node;
        int i = 0; //�������и�˳������ҵ���Ӧ���ڵ��λ��
        while (i < inlist.length){
            if (value == inlist[i])
                break;
            i++;
        } //��ʱi��ֵ���ǵ�ǰ��ĸ��ڵ����и�˳����еĶ�Ӧ����ֵ
        //��������λ�ý���ǰ�������з�Ϊ��������������,���ݹ������������������ڵ�
        node.right = createTreeByPostAndIn(Arrays.copyOfRange(postlist, i,postlist.length - 1), Arrays.copyOfRange(inlist, i + 1, inlist.length));
        node.left = createTreeByPostAndIn(Arrays.copyOfRange(postlist, 0, i), Arrays.copyOfRange(inlist, 0, i));
        return node; //���ظ��ڵ�

    }
    public BinaryNode<T> createTreeByPreAndIn(T[] prelist, T[] inlist) { //�����ȸ�˳�����и�˳�����������
        if (prelist.length == 0 || inlist.length == 0) //�������Ϊ0���ؿ�(��Ϊ�����������������в��ԳƵ����,���Ե��зֱ�Ϊ0ʱҲҪ���ǵ�)
            return null;
        T value = prelist[0]; //�����ȸ�˳����õ�ǰ���ڵ��ֵ
        BinaryNode<T> node = new BinaryNode<>(); //��ʼ�����ڵ�
        node.data = value; //��ֵ���ڵ�
        if (prelist.length == 1) //����ǰ��ĳ���Ϊ1, ��ֱ�ӷ��ص�ǰ�ڵ�
            return node;
        int i = 0; //�������и�˳������ҵ���Ӧ���ڵ��λ��
        while (i < inlist.length){
            if (value == inlist[i])
                break;
            i++;
        }//��ʱi��ֵ���ǵ�ǰ��ĸ��ڵ����и�˳����еĶ�Ӧ����ֵ
        //��������λ�ý���ǰ�������з�Ϊ��������������,���ݹ������������������ڵ�
        node.left = createTreeByPreAndIn(Arrays.copyOfRange(prelist, 1, 1 + i), Arrays.copyOfRange(inlist, 0, i));
        node.right = createTreeByPreAndIn(Arrays.copyOfRange(prelist, i + 1, prelist.length), Arrays.copyOfRange(inlist, i + 1, inlist.length));
        return node;
    }

    public void leaf(){ //�������Ҷ�ӽ���ֵ
        System.out.print("Ҷ�ӽ��Ϊ: ");
        this.leafString(this.root); //���õݹ鷽��
        System.out.println();
    }
    public void leafString(BinaryNode<T> p){ //�ݹ���Ҷ�ӽڵ��ֵ
        if (p != null){ //�����ǰ�ڵ㲻��null(�Խڵ����ʽ����)���ж��Ƿ���Ҷ�ӽڵ�
            if (p.isLeaf()) //���ýڵ�����ķ����ж��Ƿ�ΪҶ�ӽ��
                System.out.print(p.data + " "); //����, ���������ֵ
            leafString(p.left); //��������������
            leafString(p.right);// ��������������
        }
    }
    public int leafCount(){ //�������Ҷ�ӽڵ������
        leafCountnum = 0; //��ʼ������ֵ(ÿ�ε����������ʱ����Ҫ��ʼ��,�Է���һ�ε��õ�Ӱ��)
        leafCount(this.root); //�ݹ������Ҷ�ӽ������
        return leafCountnum;
    }
    private int leafCountnum = 0; //��ʼ����Ҷ�ӽ������ֵ
    public void leafCount(BinaryNode<T> p){ //�ݹ�������Ҷ������
        if (p != null){ //�ڵ�ǿ�ʱ���������ж�
            if (p.isLeaf()) //�ж��Ƿ���Ҷ�ӽڵ�
                leafCountnum++; //�ǵĻ�count+1
            leafCount(p.left); //�������ӽڵ�ݹ����
            leafCount(p.right); //�����Һ��ӽڵ�ݹ����
        }
    }
    public void replaceAll(T key, T x){ //�滻����Ϊkeyֵ�Ľڵ��ֵΪx
        replaceAll(this.root, key, x); //�ݹ���õķ���
    }
    public void replaceAll(BinaryNode<T> p, T key, T x){ //�ݹ�����޸Ľڵ��ֵ
        if (p != null){ //�ǿյ�ʱ������޸�ֵ
            if (p.data == key) //�ж�ֵ�Ƿ�����ƥ��Ҫ��
                p.data = x; //�޸�ֵ
            replaceAll(p.left, key, x); //�������ӵݹ����
            replaceAll(p.right, key, x); //�����Һ��ӵݹ����
        }
    }
    public BinaryNode<T> search(T key){ //��������
        //ʹ��ջ����Ϊ����ÿһ��ѭ����Ҫ���ؽڵ�, ���ʹ�õݹ���ò�������з���ֵ����������
        LinkedStack<BinaryNode<T>> stack = new LinkedStack<>();
        BinaryNode<T> p = this.root; //�ӵ�ǰ���ڵ㿪ʼ����
        while (p != null || !stack.isEmpty()){ //����ڵ㻹û�б������
            if (p != null){ //����ڵ㲻Ϊ��(�����ж�������)
                if (p.data == key) //�ж�������
                    return p; //���ʱ���ش˽ڵ�
                stack.push(p); //�����ʱѹ��ջ��, ���Ž��б���
                p = p.left; //�����ȸ�����˳����б���
            }else {
                p = stack.pop(); //�����Ѿ��������, �л����Һ���
                p = p.right;
            }
        }
        return null; //������ǿ����Ļ�ֱ�ӷ��ؿ�
    }
    public BinaryNode<T> parents(BinaryNode<T> node){ //������ڵ�ĸ�ĸ�ڵ�
        LinkedStack<BinaryNode<T>> stack = new LinkedStack<>(); //ʹ��ջ���б���(��Ϊջ��洢��ǰ�ڵ�ĸ�ĸ�ڵ�)
        BinaryNode<T> p = this.root; //�Ӹ��ڵ㿪ʼ����
        while (p != null || !stack.isEmpty()){ //������
            if (p != null){ //�����ǰ�ڵ�ǿ�
                if (p.left == node || p.right == node) //�����ж��亢�ӽڵ��Ƿ�Ϊ�����Ľڵ�
                    return p; //�ǵĻ�ֱ�ӷ��ص�ǰ�ڵ�
                stack.push(p); //���ǵĻ�ѹ��ջ�м�������
                p = p.left;
            }else {
                p = stack.pop();
                p = p.right;
            }
        }
        return null; //����������û���ҵ��ͷ���null
    }
    public int level(T key){ //�������ֵ�ڵ�ǰ���еĲ��
        return level(this.root, key); //�ݹ���÷���
    }
    public int level(BinaryNode<T> p,T key){ //�ݹ���÷���
        if (p == null) // �ݹ����ֹͣ����, ����-1��ʾû�ҵ�
            return -1;
        if (p.data.equals(key)) //�뵱ǰ�ڵ���бȽ�
            return 1;
        int depth = level(p.left, key); //�ݹ����Ѱ��
        if (depth > 0) //���depth����0, ֤���ҵ���
            return depth + 1; //����depth + 1(��������)��ֵ
        depth = level(p.right, key); //ͬ��
        if (depth > 0)
            return depth + 1;
        return -1; //��û�ҵ�����-1
    }
    public String toGenlist(){ //�޸ĵĹ������,ʹ���䷵�ع�����ַ���
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
    public boolean equals(Object obj){ // �ж������������Ƿ����
        if (obj == this) //�������ͬһ���ڴ��ַ
            return true;
        else if (obj instanceof BinaryTree<?>){ // ���������obj�ǵ�ǰ���һ��ʵ��
            if (this.root.data.equals(((BinaryTree) obj).root.data)){ //�ж��������ĸ��ڵ��Ƿ���ͬ(ͬʱ�ж����������Ƿ���ͬ)
                String str_1 = this.toGenlist(); //��������ת��Ϊ�����֮��Թ��������ж�(��Ϊ���������һһ��Ӧ)
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
    public BinaryNode<T> search(BinaryTree<T> pattern){ //���ݸ���������������ǰ��(Ĭ��pattern�е�Ҷ�ڵ�Ҳ�����ڵ�ǰ����ΪҶ�ڵ�)
        BinaryNode<T> p = searchRoot(pattern.root, this.root); //������ǰ���Ƿ��нڵ���pattern�ĸ��ڵ�ƥ��
        if (p != null){ //p��Ϊ�յĻ���ʾ�ҵ��˽ڵ�
            if (matchTree(p, pattern.root)){ //����true˵��ƥ��ɹ�
                return p; //�����ҵ��ĸ��ڵ�
            }
            return null; //��ƥ�䷵�ؿ�
        }
        else
            return p; //�Ҳ������ؿ�
    }
    public BinaryNode<T> searchRoot(BinaryNode<T> p , BinaryNode<T> q){ //�������ڵ�
        BinaryNode<T> m = null;
        if (p != null && q != null){ //�жϸ����������ڵ�����ֵ�Ƿ����
            if (p.data == q.data)
                return q; //��ȷ���q(qΪ��ǰ���еĽڵ�)
        }
        if (q != null){ // �ڱ�����������֮ǰ
            m = searchRoot(p, q.left); //�������ӽڵ�
            if (m == null){ //������û�еĻ�
                m = searchRoot(p, q.right); //����������
            }
        }
        return m; //����ֵ
    }
    public boolean matchTree(BinaryNode<T> p, BinaryNode<T> q){ //�ж��Ƿ���patternƥ��
        boolean temp = (p == null && q == null) || (q != null && p != null && (p.data.equals(q.data))); //���жϵ�ǰ�ڵ��Ƿ����
        if (!temp) //����ȷ���false
            return false;
        if (p != null && q != null){ //p�ڵ��q�ڵ㶼����ͬ���ƶ�֪������������pattern
            return temp && (matchTree(p.left, q.left) && matchTree(p.right, q.right));
        }else
            return true; //ȫ��һֱ����true
    }
    public void removeAll(BinaryTree<T> pattern){ //ɾ��������pattern��ͬ������
        BinaryNode<T> p = search(pattern); //Ѱ����ͬ�������ĸ��ڵ�
        while (p != null){ //ֱ���Ҳ���Ϊֹ
            BinaryNode<T> parent = parents(p); //ȡ�õ�ǰ�ڵ�ĸ�ĸ�ڵ�
            if (parent.left.data.equals(p.data)) //�жϵ�ǰ�ڵ������ӻ����Һ���
                parent.left = null;
            else
                parent.right = null; //ɾ���Ե�ǰ�ڵ�Ϊ���ڵ������
            p = search(pattern); //����һ������
        }
    }
}
