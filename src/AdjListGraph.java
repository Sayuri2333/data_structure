public class AdjListGraph<T> extends AbstractGraph<T> { //ͼ���ڽӱ��ʾ��ʵ��
    protected LinkedMatrix adjlist; // ͼ���ڽӱ�
    public AdjListGraph(int length){ // �����ͼ,�������ͱ�����Ϊ0,lengthָ������˳�����������ڽӱ������
        super(length); // ��������Ϊlength��˳������洢��
        this.adjlist = new LinkedMatrix(length); //����length��ģ�ľ���
    }
    public AdjListGraph(){ // ����Ĭ�ϴ�С�Ŀ�ͼ
        this(10);
    }
    public AdjListGraph(T[] vertices){ //���ݸ����Ķ��㼯�Ϲ���ͼ
        this(vertices.length); //�ȳ�ʼ����С
        for (int i = 0; i < vertices.length; i++) //Ȼ����붥��
            this.insertVertex(vertices[i]);
    }
    public AdjListGraph(T[] vertices, Triple[] edges){ // ���ݸ����Ķ���ͱߵ��������ʼ��ͼ
        this(vertices); //���ݵ��ʼ��ͼ
        for (int j = 0; j < edges.length; j++) //�����
            this.insertEdge(edges[j]);
    }
    public String toString(){ // ��������ַ���
        return super.toString() + "���߱�: \n" + this.adjlist.toString(); //���ø���������ַ������ϱ����
    }
    public void insertEdge(int i, int j, int weight){ //�����
        if (i != j){ //������ǻ�
            if (weight < 0 || weight > MAX_WEIGHT) //�淶��Ȩֵ�Ĵ�С
                weight = 0; //Ȩֵ����0���ʾû�б�
            this.adjlist.set(i, j, weight); //���þ���ǰλ�õ�ֵ(����0��ʱ�򲻱�ʾ)
        }else throw new IllegalArgumentException();
    }
    public void insertEdge(Triple tri){ //���߸�������Ԫ���������
        insertEdge(tri.row, tri.column, tri.values);
    }
    public int insertVertex(T x) //���ݸ�����Ԫ���������
    {
        int i = this.vertexlist.insert(x); //�ȸ��µ��б��еĵ�(���ص�ǰ��ĸ���)
        if (i >= this.adjlist.getRows()) // �����ĸ����Ⱦ��������Ҫ��(��Ҫ����������)
            this.adjlist.setRowsColumns(i+1, i+1); //�������þ����������
        return i; //���ص�ǰ��ĸ���
    }
    public void removeEdge(int i, int j){ //ɾ����������֮��ı�
        if (i != j) //������ǻ�
            this.adjlist.set(new Triple(i, j, 0)); //���õ�ǰλ�õ�Ȩ��Ϊ0(Ҳ���ǲ���ʾ)
    }
    public void removeEdge(Triple tri){ // ���ݸ�������Ԫ��ɾ����
        this.removeEdge(tri.row, tri.column); // ������Ԫ���������ɾ����(������weight)
    }
    public void removeVertex(int i){ // ɾ����(����ɾ�����б��еĵ�, Ȼ��ɾ�����ڱ߾����е�����, �����ں������������������һλ)
        int n = this.vertexCount(); //��õ�ǰ�������
        if (i >= 0 && i < n){ // �жϺϷ���
            SortedSinglyList<Triple> link = this.adjlist.rowlist.get(i); // ��õ�ǰ����ڽӱ��б�
            for (Node<Triple> p = link.head.next; p != null; p = p.next) // ���ڵ�ǰ��ĸ����ڽӱ�(���ѭ����Ϊ��ɾ���ڽӱߵ�)
                this.removeEdge(p.data.toSymmetry()); //ɾ����Գ�λ�õ�Triple
            n--; //���µ�ǰ����ĵ���
            this.adjlist.rowlist.remove(i); //ͨ��Seqlist��ɾ������ֱ��ɾ����һ�е����б�
            this.adjlist.setRowsColumns(n, n); //���¾���Ĵ�С
            for(int j = 0; j < n; j++){ //���ھ����е�ÿһ��(���ѭ����������������)
                link = this.adjlist.rowlist.get(j); //��õ�ǰ��
                for (Node<Triple> p = link.head.next; p != null; p = p.next){ //������е�ÿһ��Ԫ��
                    if (p.data.row > i) //������������ڸ����ĵ��ָ��
                        p.data.row--; //��������һ(��ʹ�ö�ά�����ʱ����Ҫ�������,������Ҫ����������ƶ�)
                    if (p.data.column > i)
                        p.data.column--;
                }
            }
            this.vertexlist.remove(i); //�ڵ��б���ɾ��������
        }else
            throw new IndexOutOfBoundsException();
    }
    public int weight(int i, int j){ //���ظ���λ�õ�Ȩ��
        if (i == j){
            return 0; //���ǲ����ڵ�
        }
        int weight = this.adjlist.get(i, j); //��ø���λ�õ�Ȩ��(�Ҳ�����ǰλ�õĽڵ�Ļ�����0)
        return weight != 0 ? weight : MAX_WEIGHT; //������������,Ϊ0�Ļ���������
    }
    // ����������������ڴ���vi��vj֮��ıߵ�ʱ�����ʹ��
    protected int next(int i, int j){ // ����vi��vj��ĺ���ڽӶ�������,���j = -1, ���ص�һ��, ��������ڷ���-1
        int n = this.vertexCount(); //��õ�ǰ��ĸ���
        if (i >= 0 && i < n && j >= -1 && j < n && i != j){ //���i��j���ڷ�Χ֮��
            SortedSinglyList<Triple> link = this.adjlist.rowlist.get(i); // ��õ�ǰvi�ڽӱ��б�
            Node<Triple> find = link.head.next; // ����ڽӱ��б�ĵ�һ��Ԫ��
            if (j == -1) //�ж�j��ֵ
                return find != null ? find.data.column : -1; //����ڽӱ��б�ĵ�һ��Ԫ���Ƿ�Ϊ��,�ǿ������
            find = link.search(new Triple(i, j, 0)); //����,ʹ��SortedSinglyList�����������������Ƿ���ڸ���λ�õı�
            if (find != null){ //�����
                find = find.next; //����һ��
                if (find != null)  //�������һ��
                    return find.data.column; //��������
            }
        }
        return -1; //���򷵻�-1
    }
}
