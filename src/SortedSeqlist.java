public class SortedSeqlist<T extends Comparable<? super T>> extends Seqlist<T> { //T���ͱ�����ʵ����Comparable�ӿڵ�
    public SortedSeqlist(){ //�����µ�����˳���
        super();
    }
    public SortedSeqlist(int length){ //���ݳ��������µ�����˳���
        super(length);
    }
    public SortedSeqlist(T[] values){ //���ݸ��������������µ�����˳���
        super(values.length);
        for (int i = 0; i < values.length; i++){ //��������������
            this.insert(values[i]); //����
        }
    }
    public int insert(T x){ //����
        int i = 0; //����λ�õı��
        if (this.isEmpty() || x.compareTo(this.get(this.size() - 1)) > 0) //�ձ����Ϊ�����������ֵʱ���������β��
            i = this.n;
        else
            while (i < this.n && x.compareTo(this.get(i)) > 0) //����˳������ʺϵĲ���λ��
                i++;
        super.insert(i, x); //ʹ�ø���Ĳ��뷽��
        return i;
    }
    public void set(int i, T x){ //�ϳ��̳е����÷���
        throw new java.lang.UnsupportedOperationException("set(int i, T x)");
    }
    public int insert(int i, T x){ //�ϳ��̳еĲ��뷽��
        throw new java.lang.UnsupportedOperationException("insert(int i, T x)");
    }
    public int search(T key){ //��������
        for (int i = 0; i < this.n && key.compareTo(this.get(i)) >= 0; i++){
            if (key.compareTo(this.get(i)) == 0) //�Ƚϸ�����ֵ�����Ԫ�ص�ֵ�Ĵ�С
                return i;
        }
        return -1;
    }
    public T remove(T key){ //ɾ������˳����е�Ԫ��
        if (this.search(key) != -1){ //���˳����������Ԫ�صĻ�
            T temp = super.remove(this.search(key)); // ���ø����remove��������ɾ��
            return temp; //���ر�ɾ����ֵ
        }
        return null; //���Ҳ����򷵻�null
    }
}
