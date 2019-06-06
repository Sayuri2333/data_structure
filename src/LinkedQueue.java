import java.io.Serializable;
public class LinkedQueue<T> implements Queue<T>, Serializable{
    private Node<T> front, rear; //��������ָ��ֱ�ָ��ǰ��
    public LinkedQueue(){
        this.front = this.rear = null; //��ʼ��ָ���λ��
    }
    public boolean isEmpty(){
        return this.front == null && this.rear == null; //ǰ��ָ�붼Ϊ�յ�ʱ��Ϊ��
    }
    public boolean add(T x){
        if (x == null) //�ն����ų�
            return false;
        Node<T> q = new Node<T>(x, null); //���������½��ڵ�
        if (this.front == null) //�նӲ���
            this.front = q;
        else this.rear.next = q; //β������,����ǰβ����next��ֵΪq�Ӷ�����������
        this.rear = q; //��βָ��ָ��β��
        return true;
    }
    public T peek(){
        return this.isEmpty()? null: this.front.data; //����ջ��ֵ
    }
    public T poll(){
        if (isEmpty()) //�������ؿ�
            return null;
        T x = this.front.data; //���ж�����ֵ
        this.front = this.front.next; //frontָ��ǰ��
        if (this.front == null) //���frontָ��Ϊnullʱ֤���Ѿ�Ϊ�ն���,��ı�rearָ���ֵ
            this.rear = null;
        return x;
    }
    public int size(){ //����еĳ���
        int i = 0; //��ʼ��������
        Node<T> p; // ��ʼ��ָ���λ��
        if (! this.isEmpty()){ // ������зǿ�
            for (p = this.front; p != null; p = p.next) // ������������
                i++; //���Ӽ���
            return i; //���س���
        }
        return 0; //�ն��з���0
    }
}