import java.io.ObjectInputStream;

public class HashSet<T>{ // ɢ�б�洢
    private Singlylist<T>[] table; //ʹ�õ���������洢
    private int count = 0; // ����Ԫ������
    private static final float LOAD_FACTOR = 0.75f; //װ������
    public HashSet(int length){ //���ݸ������ȴ���ɢ�б�
        if (length < 10) //�޶���С����
            length = 10;
        this.table = new Singlylist[length]; // ��ʼ������������
        for (int i = 0; i < this.table.length; i++) // ��ʼ�����������������ÿһ��ֵ
            this.table[i] = new Singlylist<T>(); //��ʼ��Ϊ�յ�����
    }
    public HashSet(){ //Ĭ�ϳ��ȴ���ɢ�б�
        this(16);
    }
    private int hash(T x){ //���ݸ�����Ԫ�����ɢ�е�ַ
        int key = Math.abs(x.hashCode()); //��õ�ǰԪ�ص�Hashֵ
        return key % this.table.length; //����ȡ�෨
    }
    public T search(T key){ //���ݸ�����Ԫ������ɢ�б�
        Node<T> find = this.table[this.hash(key)].search(key); //���ݸ���Ԫ�ص�ɢ�е�ַ�ҵ���Ӧ������,����search������ֵ
        return find == null ? null : find.data; //����ֵ
    }
    public boolean add(T x){ //���ݸ�����Ԫ�����ֵ
        if (this.count > this.table.length * LOAD_FACTOR){ //�������������װ������
            this.printAll(); //�����ǰɢ�б����Ϣ
            //System.out.print("\n���"+x+"��");
            Singlylist<T>[] temp = this.table; //ʹ��temp�����洢��ǰɢ�б�
            this.table = new Singlylist[this.table.length * 2]; //�½�һ����������ɢ�б�
            for (int i = 0; i < this.table.length; i++)
                this.table[i] = new Singlylist<T>(); //��ʼ���µ�ɢ�б�
            this.count = 0; //��ʼ��countֵ
            for (int i = 0; i < temp.length; i++) //����ԭ����ɢ�б��е�ÿһ��������
                for (Node<T> p = temp[i].head.next; p != null; p = p.next) // ���ڵ������е�ÿһ���ڵ�
                    this.add(p.data); //����ֵ�������ɢ�б���
        }
        boolean insert = this.table[this.hash(x)].insertDifferent(x) != null; // ���ݸ���ֵ���ɢ�е�ַ,�����������
        if (insert) //�������ɹ�
            this.count++; //��������1
        return insert; //���ز���ɹ����
    }
    public T remove(T key){ //����ֵɾ��Ԫ��
        T x = this.table[this.hash(key)].remove(key); // ���ݸ�����ֵ�ҵ�ɢ�е�ַ,����remove����ɾ��
        if (x != null) //ɾ���ɹ��Ļ�
            this.count --; //������һ
        return x;
    }
    public HashSet(T[] values){ //���ݸ������б��½�ɢ�б�
        this((int)(values.length / HashSet.LOAD_FACTOR)); //�½��ʺϳ��ȵĿ�ɢ�б�
        this.addAll(values); //��values�е�����ֵ��ӽ�ɢ�б���
    }
    public int size(){ //���ص�ǰ����
        return count;
    }
    public boolean isEmpty(){ //�ж�ɢ�б��Ƿ�Ϊ��
        return this.size() == 0;
    }
    public boolean contains(T key){ //�ж�ɢ�б����Ƿ���ĳ��Ԫ��
        return this.search(key) != null;
    }
    private void addAll(T[] values){ //��values�е�����ֵ��ӽ�ɢ�б���
        for (T value : values) {
            this.add(value); //����ÿ��ֵ����add����
        }
    }
    public void clear(){ //���ɢ�б�
        for (Singlylist p : this.table)
            p.clear(); //����ÿ�������������շ���
    }
    public String toString(){ //���������ַ���
        String str = this.getClass().getName() + "(";
        boolean first = true;
        for (Singlylist<T> aTable : this.table) {
            for (Node<T> p = aTable.head.next; p != null; p = p.next){
                if (!first)
                    str += ",";
                first = false;
                str += p.data.toString();
            }
        }
        return str + ")";
    }
    public void printAll(){
        System.out.println("ɢ�б�����="+this.table.length+"��"+this.count+"��Ԫ��"+
                "��hash(key)=key % "+this.table.length+"��"+this.toString());
        for (int i = 0; i < this.table.length; i++){
            System.out.println("table["+i+"]="+this.table[i].toString());
        }
        System.out.print("ASL�ɹ�=(");
        int asl = 0;
        for (int i = 0; i < this.table.length; i++){
            int j = 1;
            for (Node<T> p = this.table[i].head.next; p != null; p = p.next, j++){
                System.out.print((asl==0 ? "" : "+")+j);
                asl += j;
            }
        }
        if (count == 0)
            System.out.println(") = 0\n");
        else
            System.out.println(")/"+count+" ="+asl+"/"+count+" ="+((asl+0.0)/count)+"\n");
    }
    public Object[] toArray(){ //���ذ�����������Ԫ�ص�����
        Object[] values = new Object[this.size()]; // ��ʼ��һ�����ڴ洢������
        int j = 0; // ��ʼ��ָ���ֵ
        for (int i = 0; i < this.table.length; i++) //����ɢ�б�
            for (Node<T> p = this.table[i].head.next; p != null; p = p.next) //���ڵ�������ÿһ��Ԫ��
                values[j++] = p.data; //����洢��������
        return values; //��������
    }
}
