public interface Queue<T> { //���нӿ�
    public abstract boolean isEmpty(); // �Ƿ�Ϊ��
    public abstract boolean add(T x); // �Ŷ�
    public abstract T peek(); // ���ض��е�һ��Ԫ��
    public abstract T poll(); //�������е�һ��Ԫ��
}
