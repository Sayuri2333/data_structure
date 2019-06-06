public class TriNode<T> { //��������ڵ���
    public T data; //�洢���ݵ�������
    public TriNode<T> parent, left, right; //ָ��ĸ���ӽڵ�
    public TriNode(T data, TriNode<T> parent, TriNode<T> left, TriNode<T> right){ //������������ڵ�
        this.right = right;
        this.left = left;
        this.parent = parent;
        this.data = data;
    }
    public TriNode(T data){ //���ݸ��������ݹ�����������ڵ�
        this(data, null, null, null);
    }
    public TriNode(){ //����սڵ�
        this(null);
    }
    public String toString(){ //���������ַ���
        return this.data.toString();
    }
    public boolean isLeaf(){ //�����Ƿ��к��ӽڵ��ж��Ƿ�ΪҶ�ӽ��
        return this.left == null && this.right == null;
    }
}
