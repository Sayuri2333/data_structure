public class SortedSinglyList<T extends Comparable<? super T>> extends Singlylist<T>{
    public SortedSinglyList(){ //��ʼ������������
        super();
    }
    public SortedSinglyList(T[] values){ //��������������������
        super(); //���ɿյ�
        for (int i = 0; i < values.length; i++){ //�������齫ֵ��������������
            this.insert(values[i]);
        }
    }
    public SortedSinglyList(Singlylist<T> list){ //���ݵ�����������������
        super(); //���ɿյ�
        for (Node<T> p = list.head.next; p != null; p = p.next){ //���ڸ����ĵ������е�ֵ
            this.insert(p.data); //�������
        }
    }

    public Node<T> insert(T x) { //���Ǹ���Ĳ��뷽��
        Node<T> front = this.head; //ǰ�ڵ�
        Node<T> p = this.head.next; //��ڵ�
        while (p != null){ //�����Ƚ�x�뵱ǰ�����ڵ�Ԫ�صĴ�С
            if (x.compareTo(p.data) > 0){
                front = p;
                p = p.next;
            }else
                break;
        }
        //��ʱfrontԪ��Ӧ��ָ���xС��Ԫ��,pӦ��ָ���x���Ԫ��
        //�����ʱΪ������, pӦ��Ϊnull
        front.next = new Node<T>(x, p); //����dataΪx�ڵ��Ԫ��
        return front.next; //�����½��Ľڵ�
    }
//    public Node<T> insert(T x){
//        Node<T> p = this.head;
//        if (!this.isEmpty()){
//            while (x.compareTo(p.next.data) > 0 && p.next != null){
//                p = p.next;
//            }
//            p.next = new Node<T>(x, p.next);
//            return p.next;
//        }else
//            this.head.next = new Node<T>(x, null);
//        return this.head.next;
//    }
    public Node<T> search(T key){ //����ֵ����
        Node<T> p = this.head.next; //��ʼ���ڵ�
        if (! this.isEmpty()){ //�жϷǿ�
            while (p != null && key.compareTo(p.data) >= 0){ //�����Ƚ�x�뵱ǰ�����ڵ�Ԫ�صĴ�С
                if (key.compareTo(p.data) == 0) //�ж��Ƿ��ֵ
                    return p; //����ֵ
                p = p.next;//�������ָ��
            }
        }
        return null; //���򷵻�null
    }
    public T remove(T key){ //����ֵɾ��
        Node<T> w = this.head; //ǰ���ڵ�
        Node<T> p = this.head.next; //��ǰ�ڵ�
        if (!this.isEmpty()){
            while (p != null && key.compareTo(p.data) > 0){ //�����Ƚ�x�뵱ǰ�����ڵ�Ԫ�صĴ�С
                if (key.compareTo(p.data) == 0) //�жϵ�ֵ
                    break; //����ѭ��
                w = p;
                p = p.next; //�������w,p��ֵ
            }
            w.next = p.next; //ɾ��p
            return p.data; //����p��dataֵ
        }
        return null; //���򷵻�null
    }
}
