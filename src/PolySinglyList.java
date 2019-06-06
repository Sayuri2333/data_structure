public class PolySinglyList< T extends Comparable<? super T> & Addible<T>> extends SortedSinglyList<T>{ // �洢�����������
    public PolySinglyList(){
        super();
    } //�����ձ�
    public PolySinglyList(T[] terms){
        super(terms);
    } // ���ݸ������������ɱ�
    public PolySinglyList(PolySinglyList<T> list){
        super(list);
    } // �����±�
    public void addAll(PolySinglyList<T> list){ // ��֮�����ӷ���
        if (!list.isEmpty() & !this.isEmpty()){ //��������Ϊ�յ�ǰ����
            Node<T> p = this.head;
            Node<T> q = list.head; //��ʼ������ָ���λ��
            while (true){ // ѭ��
                if (p.next != null && q.next != null){ //������˳���û�������ǰ����
                    if (p.next.data.compareTo(q.next.data) == 0){ // �������ָ��ָ�����Ŀָ����ͬ
                        p.next.data.add(q.next.data); //�ɼ�
                        if (p.next.data.removable()){ // ���Ƿ�Ϊ0,Ϊ0ɾ��
                            p.next = p.next.next;
                            q = q.next;
                            continue;
                        }
                    }else if (p.next.data.compareTo(q.next.data) > 0){ //����Ǳ����ָ����Ƚϴ�
                        p.next = new Node<T>(q.next.data,p.next); // ��q������뱾��
                        p = p.next; // �ƶ�����ָ��(����Ҫ�ƶ�����,��ֹ�ող����������һ��ѭ���б��Ƚ�)
                    }else if (p.next.data.compareTo(q.next.data) < 0){ // ����Ǹ������ָ����Ƚϴ�
                        p = p.next; //����ָ���ƶ�
                        continue; //�����·��ĸ�����ָ���ƶ�
                    }
                }else if (p.next == null){ // ���������Ŀ�������
                    for (q = q; q != null; q = q.next){ //���ڸ�����ʣ�µ���Ŀ
                        p.next = new Node<T>(q.data, null); //���临�Ʋ����뱾�����
                        p = p.next;
                    }
                    break; //����ѭ��
                }else if (q.next == null){ // ���������������
                    break; //ֱ�ӵ���ѭ��
                }
                p = p.next;
                q = q.next; //��������ָ���λ��
            }
        }else if (!list.isEmpty()){ // �������Ϊ��,������ǿ�
            Node<T> p = this.head;
            for (Node<T> q = list.head.next; q != null; q = q.next){ // ���������������Ŀ����һ�ݹ���
                p.next = new Node<T>(q.data, null);
                p = p.next;
            }
        }
    }
}
