public class ThreadNode<T> { //�����������Ľڵ���
    public T data; //������
    public ThreadNode<T> left, right; // �������Ӻ��Һ���
    public boolean ltag, rtag; // �������Ӻ��Һ����Ƿ���ǰ���ڵ�ͺ�̽ڵ�
    public ThreadNode(T data, ThreadNode<T> left, boolean ltag, ThreadNode<T> right, boolean rtag){ // ������������һ���ڵ�
        this.data = data;
        this.left = left;
        this.ltag = ltag;
        this.rtag = rtag;
        this.right = right;
    }
    public ThreadNode(T data){ //ֻ�����������ƶ��ڵ�
        this(data, null, false, null, false);
    }
    public String toString(){ //toString����
        return this.data.toString();
    }
    public boolean isLeaf(){ //�ж��Ƿ���Ҷ�ڵ�
        return this.left == null && this.right == null;
    }
}
