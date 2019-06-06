public class Singlylist<T> extends Object { //�½�������
    public Node<T> head; //�½�ͷ���
    public Singlylist(){
        this.head = new Node<T>();
    } //�½�������
    public Singlylist(T[] values){ //���������½�����
        this(); //�½�������
        Node<T> rear = this.head; //�½�һ��ָ��ָ�������β��
        for (int i = 0; i < values.length; i++){ //�������鲢��ÿһ��Ԫ�ؽ�������ĸ�ֵ
            rear.next = new Node<T>(values[i], null); //�����һ���ڵ��nextλ������Ϊ��һ���ڵ���ڴ��ַ
            rear = rear.next; //�ƶ�rearָ���λ��
            // rear = new Node<T>(values[i], rear); Ӧ��Ҳ����
        }
    }
    public boolean isEmpty(){
        return this.head.next == null;
    } //�ж������Ƿ�ǿ�(�ж�ͷ����Ƿ��зǿյ�next)
    public T get(int i){ //��ȡ�������ض�λ�õ�Ԫ��
        Node<T> p = this.head; //�½�ָ��
        for (int j = 0; j < i + 1 && p != null; j++){ //��������ĸ���λ��Ѱ��
            p = p.next; //����ָ���λ��
        }
        if (i>= 0 && p != null){ //���i�Ϸ���ָ�벻���������ĵ�λ��
            return p.data; //���ؽڵ��dataֵ
        }else return null; //���򷵻�null
    }
    public void set(int i, T x){ //���������ض�λ�õ�ֵ
        Node<T> p =this.head; //�½�ָ��
        for (int j = 0; j < i + 1 && p != null; j++){ //Ѱ�Ҹ�����λ��
            p = p.next; //����ָ���λ��
        }
        if (i >= 0 && x != null && p != null){ //���λ�úϷ�,ֵ�Ϸ�,ָ�벻��ָ����������λ��
            p.data = x; //����ֵ
        }else {
            throw new NullPointerException("x = i"); //�����׳��쳣(��Ϊ�˷���û�з���ֵ,��֪���Ƿ��Ѿ����óɹ�)
        }
    }
    public int size(){ //��������ĳ���
        Node<T> p = this.head.next; // �½�ָ��ָ������ĵ�һ��Ԫ��
        int i = 0; //��ʼ��������
        while (p != null){ //���ָ�뵱ǰָ���λ�ò������һλ
            p = p.next; //����ָ��λ��
            i++; //ͬ������������
        }
        return i; //����������
    }
    public String toString(){ //����ַ���
        String str = this.getClass().getName() + "("; //��ʼ���ַ���
        for (Node<T> p = this.head.next; p != null; p = p.next){ //������������
            str += p.data.toString(); //�������ڵ�ÿһ��Ԫ��ִ���ַ����ĸ���
            if (p.next != null) //����������һ��Ԫ��
                str += ", "; //���붺��
        }
        return str + ")"; //���ظ�����ϵ��ַ���
    }
    public Node<T> insert(int i, T x){ //�������
        Node<T> p = this.head; //�½�ָ��ָ��ͷ��
        for (int j = 0; j < i && p.next != null; j++){ //Ѱ�Ҹ�����λ�õ�ǰһ��λ��
            p = p.next; //����ָ��λ��
        }
        if (x != null){ //�жϲ���ֵ�Ƿ�Ϸ�(�����жϲ���λ���Ƿ����������,��Ϊʹ��p.next!= null������³����)
            p.next = new Node<T>(x, p.next); //�½������������ݵĽڵ㲢���ӵ�������(��ֵ������ҵ���)
        }
        return p.next; // ���ؽڵ�
    }
    public Node<T> insert(T x){ //β������
        if (x != null) //�жϺϷ���
            return this.insert(Integer.MAX_VALUE, x);
        return null;
    }
    public T remove(int i){ //����λ��ɾ������Ԫ��
        Node<T> front = this.head; //�½�ָ��ָ��ͷ�ڵ�
        for (int j = 0; j < i && front.next.next != null; j++){ //Ѱ�Ҹ�����λ��ǰ���λ��
            front = front.next; //����ָ��λ��
        }
        T key = front.next.data; //
        front.next = front.next.next;
        return key;
    }
    public void clear(){
        this.head.next = null;
    } //�������(��ͷ����next����Ϊnull)
    public Node<T> search(T key){ //����ֵ��Ѱ�ҽڵ�
        for (Node<T> p = this.head.next; p != null; p = p.next){ //�����������ÿһ���ڵ�
            if (p.data.equals(key)) //���dataֵ����key
                return p; //��������ڵ�
        }
        return null; //���򷵻�null
    }
    public boolean contains(T key){ //�ж������Ƿ���ĳ��ֵ
        for (Node<T> p = this.head.next; p != null; p = p.next){ //�����������ÿһ���ڵ�
            if (p.data.equals(key)) //�����dataֵ
                return true; //���Ϸ���true
        }
        return false; //�����ϲ����Ϸ���false
    }
    public Node<T> insertDifferent(T x){ //
        if (!this.contains(x)){
            return this.insert(x);
        }
        return null;
    }
    public T remove(T key){ //����ֵɾ��Ԫ��
        int i = 0;
        for (Node<T> p = this.head.next; p != null; p = p.next){ //�����������ÿһ���ڵ�
            if (p.data == key){ //�ж��Ƿ����
                return this.remove(i); //����remove����ɾ��
            }
            i++;
        }
        return null; //�Ҳ�������null
    }
//    public Singlylist<T> remove(int begin, int end){ //ɾ����begin��end�е�����Ԫ�ز�����
//        Singlylist<T> list = new Singlylist<T>(); //�½�������
//        if (begin <= end && begin >= 0){ //���1begin��end�Ϸ�
//            for (int i = begin; i <= end; i++){ //���ڴ�begin��end�е�ÿ��Ԫ��
//                T key = this.remove(begin); //ʹ��remove�����õ���ɾ��Ԫ��
//                list.insert(key); //ʹ�ò��뷽����Ԫ�ز���list��
//            }
//            return list; //����������
//        }
//        return null; //���򷵻�null
//    }
    public Singlylist<T> remove(int begin, int end){ //ɾ����begin��end1�е�����Ԫ�ز�����
        Singlylist<T> list = new Singlylist<>(); //�½�������
        if (begin < end && begin >= 0){ //����Ϸ�
            int b = -1; //�����еĵ�ǰλ��ָ��
            Node<T> p = this.head; //��ʼ��ǰָ��
            Node<T> q; //��ʼ����ָ��
            if (! this.isEmpty()){ //�������Ϊ��
                // ����һ�����ҵ�begin��end��Ӧ��λ��
                for (q = this.head; q.next != null; q = q.next){ //���ڵ�ǰ�����е�ÿһ��Ԫ��
                    if (b == begin - 1) //���Ϊbegin��ǰ��Ԫ��
                        p = q; //��ֵǰָ��
                    if (b == end) //���Ϊendλ�õ�Ԫ��
                        break; //ֹͣѭ��ʹ�ú�ָ��ָ����
                    b++; //ѭ��β�����µ�ǰλ��
                }
                //��ʱ�õ���pָ��beginλ�õ�ǰ��Ԫ��, qָ��endԪ��
                list.head.next = p.next; //listͷ���������beginԪ��
                p.next = q.next; //begin��ǰ��Ԫ��ָ��q����һ��Ԫ��(���Ұ���ɾ��)
                q.next = null; //��list�е����һ��Ԫ�ص�next����Ϊnull
                return list;//����list
            }
            return null;
        }
        return null;//���򷵻�null
    }
    public Singlylist<T> difference(Singlylist<T> tlist){
        Singlylist<T> list = new Singlylist<T>();
        for (Node<T> p = this.head; p != null; p = p.next){
            if (!tlist.contains(p.data)){
                list.insert(p.data);
            }
        }
        return list;
    }
    public Singlylist<T> intersection(Singlylist<T> tlist){
        Singlylist<T> list = new Singlylist<>();
        for (Node<T> p = this.head; p != null; p = p.next){
            if (tlist.contains(p.data))
                list.insert(p.data);
        }
        return list;
    }
    public void removeAll(Singlylist<T> pattern){
        int i = 0;
        int j = 0;
        int length = pattern.size();
        boolean x = false;
        Node<T> q = pattern.head.next;
        for (Node<T> p = this.head.next; p != null; p = p.next){
            if (p.data == pattern.head.next.data){
                x = true;
                q = q.next;
                continue;
            }
            if (j == length - 1){
                this.remove(i - j, i);
                i = i - j;
                j = 0;
                x = false;
                q = pattern.head.next;
            }else if (j < length && x){
                x = (p.data == q.data) ? true: false;
                q = q.next;
                j++;
            }else if (!x){
                j = 0;
            }
            i++;
        }
    }
    public static double average(Singlylist<Integer> list){
        int total = 0;
        for (Node<Integer> p = list.head.next; p != null; p = p.next){
            total += p.data;
        }
        return (double)total / list.size();
    }
    public static double averageExceptMaxMin(Singlylist<Integer> list){
        int total = 0;
        int i = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (Node<Integer> p = list.head.next; p != null; p = p.next){
            if (p.data >= max)
                max = p.data;
            if (p.data <= min)
                min = p.data;
            total += p.data;
            i++;
        }
        return (double)(total - max - min) / (i - 2);
    }
}
