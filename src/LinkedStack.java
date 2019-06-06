public class LinkedStack<T> implements Stack<T>{ //��ʽջ
    private Singlylist<T> list; // ��������Ϊ�洢��ʽ
    public LinkedStack(){
        this.list = new Singlylist<T>();
    } // �½���ջ
    public boolean isEmpty(){
        return this.list.isEmpty(); //�ж�ջ�Ŀ�ʹ�õ�����ķ���
    }
    public void push(T x){  //��ֹÿ�γ�ջ��ʱ����Ҫ��Ѱ���һ��Ԫ�ص�λ��
        this.list.insert(0, x); //ջ������
    }
    public T peek(){
        return this.list.get(0);
    }
    public T pop(){
        return this.list.remove(0);
    }
    public String toString(){
        return list.toString();
    }
}
