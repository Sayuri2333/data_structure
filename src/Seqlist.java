public class Seqlist<T> extends Object{
    protected Object[] elements; //object���͵�����
    protected int n; //��ʶ��ǰ˳����ȵ���
    public Seqlist(int length){  //���ݸ����������ɿ�˳���
        this.elements = new Object[length]; //��ʼ��elements����
        this.n = 0; //��ʼ������Ϊ0
    }
    public Seqlist(){ //����Ĭ�ϳ��ȵ�˳���
        this(64);
    }
    public Seqlist(T[] values){ //������������˳���
        this.elements = new Object[values.length]; //��ʼ����elements����
        for (int i = 0; i < values.length; i++)
            this.elements[i] = values[i]; //�����������е�Ԫ�ظ�ֵ��elements
        this.n = values.length; //����˳�����
    }
    public Seqlist(Seqlist<T> values){ //����˳���
        this.n = values.n; //���µ�ǰ˳�����
        this.elements = new Object[values.elements.length]; //��ʼ����elements����
        for (int i = 0; i < this.n; i++){
            this.elements[i] = values.elements[i]; //���
        }
    }
    public int size(){ //����˳�����
        return this.n;
    }
    public boolean isEmpty(){ //�ж��Ƿ�ǿ�
        return this.n == 0;
    }
    public T get(int i){ //����ָ��λ�õ�Ԫ��
        if (i >= 0 && i < this.n) //�жϺϷ���
            return (T)elements[i]; //����Ԫ��(��Ҫ�ֶ���������)
        return null; //�Ƿ����뷵��null
    }
    public void set(int i, T x){ //�趨ĳ��λ�õ�Ԫ��
        if (i >= 0 && i < this.n && x != null) //�жϺϷ���
            this.elements[i] = x; //����ָ��λ�õ�ֵ
        else if (x == null)
            throw new NullPointerException("x = null"); //�Ƿ��׳��쳣
        else
            throw new NullPointerException("not available index");
    }
    public String tostring(){ //�ַ������
        String str = this.getClass().getName() + "("; //��ʼ������ַ���
        if (this.n > 0){ //�ж�Ϊ�ǿ�ʱ���˳����ڵ�Ԫ��
            str += elements[0].toString(); //��������ַ���
            for (int i = 1; i < this.n; i++){ //����ʣ�µ�Ԫ�ظ����ַ���
                if (i != this.n - 1)
                    str += elements[i].toString() + ", ";
                else
                    str += elements[i].toString(); //����Դ����һ��Ԫ��
            }
        }
        return str + ")"; //�����ַ���
    }
    public int insert(int i, T x){ //ָ��λ�ò���Ԫ��
        if (x == null) //�жϺϷ���
            throw new NullPointerException("x == null"); //�׳��쳣
        if (i < 0)
            i = 0;
        else if(i > n) //����robust��
            i = n;
        Object[] copy = this.elements; //����ǰelements����Ŀ���
        if (this.n == this.elements.length){ //�����ǰ��elements1�����Ѿ�����
            this.elements = new Object[copy.length * 2]; //�½�һ����������elements����
            for(int j = 0; j < i; j++){ //��ָ��λ��ǰ��Ԫ�ص����µ�elements����
                this.elements[j] = copy[j];
            }
        }
        for (int j = i; j < this.n; j++){ //��ָ��λ�ú��Ԫ�ص������µ�����(ע�����elements����δ���Ļ��������ǰi��Ԫ��)
            this.elements[j + 1] = copy[j];
        }
        this.elements[i] = x; //ָ��λ�ò������Ԫ��
        this.n += 1; //�������鳤��
        return i; //���ز���λ��
    }
    public int insert(T x){ //β������
        return this.insert(this.n, x);
        // return this.insert(Integer.MAX_VALUE, x); Ҳ����
    }
    public T remove(int i){
        if (i < 0)
            i = 0;
        else if (i > n) //����robust��
            i = n;
        T element = (T) elements[i];
        if (i != this.n){ //���������һ��Ԫ��(��ζ�Ŵ�Ԫ���к��Ԫ����Ҫ�ƶ�λ��)
            for (int j = i; j < this.n; j++){
                elements[j] = elements[j + 1]; //���Ԫ���ƶ�λ��
            }
        }
        this.n -= 1; //����˳�����
        return element; //���ر�ɾ��Ԫ��
    }
    public void clear(){ //��յ�ǰ˳���(��û��ɾ��Ԫ��, ֻ���޷�����)
        this.n = 0;
    }
    public int search(T key){ //����ֵ����
        for (int i = 0; i < this.n; i++){ //����˳���(���Ǳ���elements����)
            if (elements[i].equals(key)) //equals�����ж��Ƿ����
                return i; //��������ֵ
        }
        return -1; //������������-1
    }
    public boolean equals(Object obj){ //�������˳����Ƿ����(�ڲ�Ԫ���Ƿ����)
        if (this == obj) //���������ͬһ���ڴ��ַ
            return true;
        if (obj instanceof Seqlist<?>){ //���obj��˳����һ��ʵ��
            Seqlist<T> list = (Seqlist<T>) obj; //�ֶ�����ת��
            if (this.n == list.n){ //���������ͬ
                for (int i = 0; i < this.n; i++){ //����ÿһ��Ԫ�ؼ���Ƿ����
                    if (elements[i] != list.elements[i])
                        return false;
                }
                return true;
            }
            return false;
        }
        return false;
    }
}