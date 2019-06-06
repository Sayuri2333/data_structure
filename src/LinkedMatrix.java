public class LinkedMatrix { // ����洢�ľ�����
    int rows, columns; //�洢������
    public Seqlist<SortedSinglyList<Triple>> rowlist; //����洢��������ݽṹ(˳���������)
    public LinkedMatrix(int m, int n){ //���ݸ�������Ϣ�������
        if (m > 0 && n > 0){ //�жϺϷ���
            this.rows = m;
            this.columns = n; //�趨���������ֵ
            this.rowlist = new Seqlist<>(); // ��ʼ������
            for (int i = 0; i < m; i++){
                this.rowlist.insert(new SortedSinglyList<Triple>()); //��ʼ�����������
            }
        }else
            throw new IllegalArgumentException();
    }
    public LinkedMatrix(int m){ //��ʼ����������ȵľ���
        this(m, m);
    }
    public LinkedMatrix(int m, int n, Triple[] tris){ //���ݸ�������Ϣ��������趨�����ֵ
        this(m, n); //��ʼ������
        for (int i = 0; i < tris.length; i++){
            this.set(tris[i]); //ʹ��set������tri��Ԫ����ӽ�������
        }
    }
    public int getRows(){ //��������
        return this.rows;
    }
    public int getColumns(){ //��������
        return this.columns;
    }
    public int get(int i, int j){  //���ؾ������λ�õ�ֵ
        if (i >= 0 && i < this.rows && j >= 0 && j < this.columns){ //�жϺϷ���
            Node<Triple> find  = this.rowlist.get(i).search(new Triple(i, j, 0)); //�ڸ����е�SortedSinglyList��Ѱ���Ƿ��и�����triple(triple��equals����ֻ�Ƚ�������)
            return (find != null) ? find.data.values : 0; //����ҵ��˾ͷ����ҵ���ֵ ���򷵻�0
        }else throw new IndexOutOfBoundsException();
    }
    public void set(int i, int j, int x){ //���ø���λ�õ�ֵ
        if (i >= 0 && i < this.rows && j >= 0 && j < this.columns){ //�жϺϷ���
            SortedSinglyList<Triple> link = this.rowlist.get(i); //��ø����е�SortedSinglyList
            if (x == 0) //���������ֵΪ0
                link.remove(new Triple(i, j, 0)); //ɾ�����λ�õ�Triple
            else {
                Triple tri = new Triple(i, j, x); //��Ϊ0�Ļ����½�һ��Triple
                Node<Triple> find = link.search(tri); //��һ���Ƿ������λ�õĽڵ�
                if (find != null) //�����
                    find.data.values = x; //�Ǿ��������λ�õ�ֵ
                else link.insert(tri); //�������һ���µĽڵ�
            }
        }else throw new IndexOutOfBoundsException();
    }
    public void set(Triple tri){ //���ݸ�������Ԫ���������λ�õ�ֵ
        this.set(tri.row, tri.column, tri.values);
    }
    public String toString(){ //���������ַ���
        String str = ""; //��ʼ���ַ���
        for (int i = 0; i < this.rowlist.size(); i++){ //ÿһ�е�Ԫ�ض����������ַ���
            str += i + " -> " + this.rowlist.get(i).toString() + "\n";
        }
        return str; //�����ܵ��ַ���
    }
    public void printMatrix(){ //���վ���ĸ�ʽ���д�ӡ
        System.out.println("����" + this.getClass().getName() + "(" + rows + "x" + columns + ") : "); //��ʼ����ӡͷ��
        for (int i = 0; i < this.rows; i++){ //���ھ����е�ÿһ�д�ӡ���е�Ԫ��
            Node<Triple> p = this.rowlist.get(i).head.next; //��ø�����ÿһ�е�ͷ��Ԫ��
            for (int j = 0; j < this.columns; j++){ //���������е�ÿһ��
                if (p != null && j == p.data.column){ //������λ���в�Ϊ0��Ԫ��,���нڵ�
                    System.out.print(String.format("%4d", p.data.values)); //���Ԫ�ص�ֵ
                    p = p .next; //�ڵ�λ�ø���
                }else System.out.print(String.format("%4d", 0)); //���û�нڵ�Ļ�֤��ֵΪ0
            }
            System.out.println(); //ÿ���һ��֮����
        }
    }
    public boolean equals(Object obj){ //�ж��Ƿ����
        if (this == obj) //ָ�����
            return true;
        if (!(obj instanceof LinkedMatrix)) //�������������һ��ʵ��
            return false;
        LinkedMatrix mat = (LinkedMatrix)obj;
        return this.rows == mat.rows && this.columns == mat.columns && this.rowlist.equals(mat.rowlist); //�Ƚ��������Լ�rowlist�Ƿ����
    }
    public void setRowsColumns(int m, int n){ //���þ����������
        if (m > 0 && n > 0){
            if (m > this.rows){ //��������������ȵ�ǰ������Ҫ��
                for (int i = this.rows; i < m; i++){ //�������ӵ�ÿһ��
                    this.rowlist.insert(new SortedSinglyList<>()); //����һ��SortedSinglyList��ĩβ
                }
            }
            this.rows = m;
            this.columns = n; //�޸�������
        }else throw new IllegalArgumentException();
    }
}
