public class SortedCirDoublyList<T extends Comparable<? super T>> extends CirDoublyList<T> { // ����ѭ��˫����
    public SortedCirDoublyList(){
        super();
    }
    public SortedCirDoublyList(T[] values){
        for (int i = 0; i < values.length; i++){
            this.insert(values[i]);
        }
    }
    public SortedCirDoublyList(CirDoublyList<T> list){
        for (DoubleNode<T> p = list.head.next; p != list.head; p = p.next){
            this.insert(p.data);
        }
    }
    public SortedCirDoublyList(SortedCirDoublyList<T> list){
        DoubleNode<T> q = this.head;
        for (DoubleNode<T> p = list.head.next; p != list.head; p = p.next){
            q.next = new DoubleNode<T>(p.data, q, this.head);
            q = q.next;
        }
    }
    public void set(int i, T x){
        throw new UnsupportedOperationException("set(int i, T x)");
    }
    public DoubleNode<T> insert(T x){ //�޸Ĳ��뷽��
        if (this.isEmpty() || x.compareTo(this.head.prev.data) > 0){ // �������Ϊ�ջ��ߵ�ǰ����Ԫ�ر�����Ԫ�ػ�Ҫ��
            return super.insert(x); //ֱ��β������
        }else{ //����
            for (DoubleNode<T> front = this.head; front != this.head.prev; front = front.next){ // ���������е�Ԫ��
                if (x.compareTo(front.next.data) <= 0){ // �������ֵ��Ƚ�,���������ֵ��С
                    DoubleNode<T> p = new DoubleNode<T>(x, front, front.next); // �½��ڵ�
                    front.next.prev = p;
                    front.next = p; //���½��Ľڵ�������ȥ
                    return p; //���ؽڵ�
                }
            }
        }
        return null; //���򷵻�null
    }
}
