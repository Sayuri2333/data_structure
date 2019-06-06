public class PriorityQueue<T extends Comparable<? super T>> implements Queue<T>{
    private SortedCirDoublyList<T> list; // ʹ������ѭ��˫������Ϊ�洢�ռ�
    private boolean asc; // ָ��������߽���
    public PriorityQueue(boolean asc){ // ����ָ�������������������ȶ���
        this.list = new SortedCirDoublyList<T>();
        this.asc = asc;
    }
    public PriorityQueue(){
        this(true);
    } //Ĭ��ʹ������
    public boolean isEmpty(){
        return this.list.isEmpty();
    } // �����Ƿ�ǿ�
    public boolean add(T x){
        return this.list.insert(x) != null;
    } // �������
    public T peek(){
        return this.asc ? this.list.get(0) : this.list.head.prev.data;
    } //����ʱ���ض�ͷ��Ԫ��,����ʱ���ض�β��Ԫ��
    public T poll(){
        return this.asc ? this.list.remove(0) : this.list.removeLast();
    } // ͬ��
    public String toString(){ // ���ض���������Ԫ�ص������ַ���
        return this.getClass().getName() + " " + (this.asc ? this.list.toString() : this.list.toPreviousString());
    }
}
