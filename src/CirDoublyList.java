public class CirDoublyList<T> {
    public DoubleNode<T> head; //�½�ͷ���ڵ�
    public CirDoublyList(){ //�½�������
        this.head = new DoubleNode<T>();
        this.head.prev = this.head;
        this.head.next = this.head;
    }
    public boolean isEmpty(){
        return this.head.next == this.head;
    } // �ж������Ƿ�Ϊ�գ��Ի���
    public DoubleNode<T> insert(int i, T x){ // �ڸ���λ�ò���Ԫ��
        DoubleNode<T> front = this.head; // ��ʼ��ָ��
        if (x == null)
            throw new NullPointerException("X = null");
        for (int j = 0; front.next != this.head && j < i; j++){ // ��ָ���ƶ�������λ�õ�ǰ����ڵ�λ��
            front = front.next;
        }
        DoubleNode<T> p = new DoubleNode<T>(x, front, front.next); // �½��ڵ㲢����������
        front.next.prev = p;
        front.next = p; // �޸�������ԭ��Ԫ�ص�����
        return p; //�����½��Ľڵ�
    }
    public DoubleNode<T> insert(T x){ // β������
        if (x == null)
            throw new NullPointerException();
        DoubleNode<T> p = new DoubleNode<T>(x, head.prev, head); // �½��ڵ㲢����������
        head.prev.next = p;
        head.prev = p; // �޸�������ԭ���Ľڵ������
        return p; // �����½ڵ�
    }
    public String toPreviousString(){ // ���򷵻ص�ǰ����������Ԫ�ص������ַ���
        if (this.head.prev == this.head) // �����ǰ����Ϊ��
            return this.getClass().getName();
        String str = this.getClass().getName() + "("; // �����Ϊ�գ������ڲ�Ԫ�ص������ַ���
        for (DoubleNode<T> p = this.head.prev; p != this.head; p = p.prev){ // ��ͷ���ڵ㿪ʼ��ǰ����
            str += " " + p.data.toString(); // ÿ�θ��������ַ���
            if (p != this.head.next) // ������ǵ�һ��Ԫ��
                str += ",";
        }
        return str + ")"; // �����������ַ���
    }
    public T removeLast(){ // ɾ�����һ��Ԫ��
        if (this.head.next == this.head) // ����ǿ�����
            return null; // ����null
        DoubleNode<T> p = this.head.prev; // ֱ��ȡ���һ���ڵ�
        this.head.prev = p.prev; // �޸�ͷ����prevλ��
        p.prev.next = p.next; // �޸ĵ����ڶ���Ԫ��nextλ��
        return p.data; //����ɾ���ڵ������
    }
    public T get(int i){ // ���ظ���λ�õ�Ԫ��ֵ
        DoubleNode<T> front = this.head; // ��ʼ��ָ���λ��
        if (i < 0) // ����Ƿ�����
            i = 0;
        for (int j = 0; j < i + 1; j++){ // ����ָ���λ��
            if (front.next != this.head)
                front = front.next; // �ƶ�ָ��
        }
        return front.data; // ���ص�ǰָ�봦������ֵ
    }
    public T remove(int i){ // ���ݸ�����λ��ɾ��Ԫ��
        DoubleNode<T> p = this.head; // �½�ָ��
        if (i < 0) // ����Ƿ�����
            i = 0;
        for (int j = 0; j <= i; j++){ // ����ָ��λ��
            if (p.next != this.head)
                p = p.next; // �ƶ�ָ��
        }
        T data = p.data; // �洢��ǰָ����ָλ�õ�ֵ
        p.prev.next = p.next;
        p.next.prev = p.prev;
        p.prev = null;
        p.next = null; // ���������˳��
        return data; //����ֵ
    }
}
