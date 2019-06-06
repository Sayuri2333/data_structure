import java.io.Serializable;
public class Node<T> implements Serializable{ //�½��ڵ���
    public T data; //�ڵ����д洢������
    public Node<T> next; //��һ���ڵ���ڴ��ַ
    public Node(T data, Node<T> next){ //�½��ڵ�
        this.data = data;
        this.next = next;
    }
    public Node(){
        this(null, null);
    } //�½��սڵ�
    public String toString(){
        return this.data.toString();
    } //�ڵ�ı�������
}
