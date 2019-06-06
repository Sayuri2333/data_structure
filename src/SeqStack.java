public class SeqStack<T> implements Stack<T> { // ʵ��˳��ջ
    private Seqlist<T> list; //ʹ��˳���������ջ
    public SeqStack(int length){
        this.list = new Seqlist<T>(length);
    } // �½�һ���������ȵ�˳��ջ
    public SeqStack(){
        this(64);
    } //�½�Ĭ�ϳ��ȵ�˳��ջ
    public boolean isEmpty(){
        return this.list.isEmpty();
    } // �ж�˳��ջ�Ƿ�ǿ�(����˳���ķ���)
    public void push(T x){
        this.list.insert(x);
    } // ��Ԫ�ز���ջ��(β�����벻��Ҫ�ƶ�Ԫ��λ��)
    public T peek(){ //�õ���ǰջ�����Ԫ�ص�ֵ
        return this.list.get(list.size() - 1); //����˳������һλԪ�ص�ֵ
    }
    public T pop(){
        return this.list.remove(list.size() - 1);
    } // ջ����
}
