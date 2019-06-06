public class DoubleNode<T> { //˫����ڵ���
    public T data; //�ڵ��ڴ洢������
    public DoubleNode<T> prev, next; // ���ڵ��ǰ��λ�õĽڵ�
    public DoubleNode(T data, DoubleNode<T> prev, DoubleNode<T> next){ // �½��ڵ�
        this.data = data;
        this.prev = prev;
        this.next = next;
    }
    public DoubleNode(){
        this(null, null, null);
    } // �½��սڵ�
    public String toString(){
        return this.data.toString();
    } // ���������ַ���
}
