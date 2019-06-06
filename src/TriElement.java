public class TriElement { // ����������������ڵ���
    int data; //������
    int parent, left, right; //�ƶ���ĸ�ڵ�, ���ӽڵ���Һ��ӽڵ�
    public TriElement(int data, int parent, int left, int right){ //���ݸ�����������������ڵ�
        this.data = data;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }
    public TriElement(int data){ //�����������ݳ�ʼ���ڵ�
        this(data, -1, -1, -1); //��ĸ�ڵ�, ���ӽڵ���Һ��ӽڵ�Ĭ��Ϊ1
    }
    public String toString(){ //�����ַ���
        return "(" + this.data + ", " + this.parent + ", " + this.left + ", " + this.right + ")";
    }
    public boolean isLeaf(){ //�ж��Ƿ�ΪҶ�ӽ��
        return this.left == -1 && this.right == -1;
    }
}
