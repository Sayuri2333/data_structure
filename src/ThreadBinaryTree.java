public class ThreadBinaryTree<T> {  //������������
    public ThreadNode<T> root; //���ڵ�
    public ThreadBinaryTree(){ //�����յ�����������
        this.root = null;
    }
    public ThreadBinaryTree(T[] prelist){ // �����ȸ����������ڵ�
        this.root = create(prelist);
        inorderThread(this.root); //�и�������
    }
    private int i = 0; //�ж��ȸ�������ı��λ��
    private ThreadNode<T> create(T[] prelist){ // ����������(��δ������)
        ThreadNode<T> p = null; //�½��սڵ�
        if (i < prelist.length){ //�ݹ���ý�����
            T element = prelist[i]; //ȡ�õ�ǰλ�õ�Ԫ��
            i++; //���±��λ��
            if (element != null){ // ����ǿ�
                p = new ThreadNode<>(element); //���������½��ڵ�
                p.left = create(prelist); //���ӽڵ�ݹ齨��
                p.right = create(prelist); //�Һ��ӽڵ�ݹ齨��
            }
        }
        return p; //���ؽ����Ľڵ�
    }
    public boolean isEmpty(){ //�ж��Ƿ�Ϊ��
        return this.root == null;
    }
    private ThreadNode<T> front = null; //��ʼ��ǰ����ǽڵ�
    private void inorderThread(ThreadNode<T> p){ //��������ǰ��
        if (p != null){ //�ǿ�ʱ
            inorderThread(p.left); // �и�����˳�����������������ӽڵ�
            if (p.left == null){ // �������λ��Ϊ��
                p.ltag = true; //�����������
                p.left = front; //���ӽڵ�ָ��ǰ���ڵ�(����ͬ��������ϵ)
            }
            if (p.right == null) // ����Һ���λ��Ϊ��
                p.rtag = true; //�����������
            if (front != null && front.rtag) //���front���Һ��ӽڵ�Ϊ��
                front.right = p; //���ú�̽ڵ�
            front = p; //����front��λ��(p��λ�ò���Ҫ����, ��Ϊÿ�ε������������ʱ���Ѿ����º���p��λ��)
            inorderThread(p.right); //��p��������ʹ��������
        }
    }
    public ThreadNode<T> inNext(ThreadNode<T> p){ //��ǰ�ڵ�ĺ�̽ڵ�
        if (p.rtag) //�����̽ڵ��־������
            return  p.right; //ֱ�ӷ��غ�̽ڵ�
        p = p.right; //���û�б�����, ��ѭ����(�Һ��ӵ������µĽڵ�Ϊ��̽ڵ�)
        while (!p.rtag) // ����Ѱ�������µĽڵ�(Ҷ�ڵ�)(��Ϊ�����������������)
            p = p.left;
        return p; //���صõ���p
    }
}
