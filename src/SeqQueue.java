public class SeqQueue<T> implements Queue<T>{ //ʹ��˳���ķ���ʵ�ֶ���
    private Object element[]; //�½��洢����ָ��
    private int front, rear; // �½�����ͷβָ��
    public SeqQueue(int length){ // ���ݸ��������½�����
        if (length < 64){
            length = 64;
        }
        this.element = new Object[length]; // �½��������ȵ�����
        this.front = this.rear = 0; // ��ʼ��ͷβָ���λ��
    }
    public SeqQueue(){
        this(64);
    } // ����Ĭ�ϳ��ȵĶ���
    public boolean isEmpty(){
        return this.front == this.rear;
    } // �ж��Ƿ�Ϊ��(ͷβָ���Ƿ���һ��)
    public boolean add(T x){ // ����������Ԫ��
        if (x == null) // �ж������Ƿ�Ϸ�
            return false;
        if (this.front == (this.rear + 1) % this.element.length){ // �ж϶����Ƿ�����
            Object[] copy = this.element;
            this.element = new Object[copy.length * 2]; // �½�һ���������ڴ洢
            int j = 0;
            for (int i = this.front; i != this.rear; i = (i + 1) % this.element.length){ // ��ԭ�����еĶ���Ԫ�ذ�˳���ƹ���
                this.element[j++] = copy[i];
            }
            this.front = 0;
            this.rear = j; // ����ָ��λ��
        }
        this.element[this.rear] = x; //����Ԫ��
        this.rear = (this.rear + 1) % this.element.length; // �޸�β��ָ���λ��
        return true;
    }
    public T peek(){
        return this.isEmpty() ? null: (T)this.element[this.front];
    } // ������ǿյ�ǰ���·���ͷ��ָ�봦��ֵ
    public T poll(){ // ��������Ԫ��
        if (this.isEmpty()){  // �ǿռ���
            return null;
        }else {
            T x = (T)this.element[this.front]; // ���ص�һ��Ԫ�ص�ֵ
            this.front = (this.front + 1) % this.element.length; //ͷ��ָ��ǰ��
            return x;
        }
    }
}
