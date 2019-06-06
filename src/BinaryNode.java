public class BinaryNode<T> {  //�������Ķ���������, Tָ���ڵ��Ԫ������
    public T data; //������, �洢����Ԫ��
    public BinaryNode<T> left, right; //��ַ��, �ֱ�ָֻ�����ҽ��
    public BinaryNode(T data, BinaryNode<T> left, BinaryNode<T> right){  // ����ڵ�, dataָ��Ԫ��, left, right�ֱ�ָ�����Ӻ��Һ��ӽ��
        this.data = data;
        this.left = left;
        this.right = right;
    }
    public BinaryNode(T data){ //����Ԫ��Ϊdata��Ҷ�ӽ��
        this(data, null, null);
    }
    public String toString(){ //���ؽ��������������ַ���
        return this.data.toString();
    }
    public boolean isLeaf(){ //�ж��Ƿ���Ҷ�ӽ��
        return this.left == null && this.right == null;
    }
    public BinaryNode(){
        this.data = null;
        this.left = null;
        this.right = null;
    }
}
