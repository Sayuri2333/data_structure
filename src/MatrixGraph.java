public class MatrixGraph<T> extends AbstractGraph<T>{  //�ڽӾ����ʾ�Ĵ�Ȩͼ��
    protected Matrix matrix; //����洢Ȩֵ(�����������洢Ԫ�ص�Seqlist)
    public MatrixGraph(int length){ //���ݸ�����Ԫ�س������ɿ�ͼ
        super(length); //���ø���Ĺ��췽��������Seqlist
        this.matrix = new Matrix(length); //���ɾ���
    }
    public MatrixGraph(){ //Ĭ��ֵ����
        this(10);
    }
    public MatrixGraph(T[] vertices){ //��vertices���㼯�Ϲ���ͼ
        this(vertices.length);
        for (int i = 0; i < vertices.length; i++){
            this.insertVertex(vertices[i]); //�̳еĸ����insertVertex���췽��
        }
    }
    public String toString(){ //��������ַ���
        String str = super.toString() + "�ڽӾ���: \n";
        int n = this.vertexCount();
        for (int  i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (this.matrix.get(i, j) == MAX_WEIGHT)
                    str += "      ��"; //�����Ϊʲô����ֱ��ʹ�þ���������ַ�����ԭ��
                else str += String.format("%6d", this.matrix.get(i, j));
            }
            str += "\n";
        }
        return str;
    }
    public void insertEdge(int i, int j, int weight){ //�����<vi, vj>, ȨֵΪweight
        if (i != j){ //���ܲ���ػ�
            if (weight <= 0 || weight > MAX_WEIGHT) //�淶��weightֵ
                weight = MAX_WEIGHT;
            this.matrix.set(i, j, weight); //���ݸ���λ�ò���weightֵ(ԭ���ķ��������쳣����)
        }else throw new IllegalArgumentException(); //����ػ����쳣����
    }
    public void insertEdge(Triple edge){ //���ݸ�������Ԫ�����Ȩ��(��)
        this.insertEdge(edge.row, edge.column, edge.values);
    }
    public int insertVertex(T x){ //����Ԫ��Ϊx�Ķ���(ͬʱҪ���±ߵľ���)
        int i = this.vertexlist.insert(x); //�Ƚ������
        if (i >= this.matrix.getRows()) //����ڽӾ������������
            this.matrix.setRowsColumns(i+1, i+1); //��������
        for (int j = 0; j < i; j++){ //��ʼ����i����Ԫ��Ϊ����
            this.matrix.set(i, j, MAX_WEIGHT);
            this.matrix.set(j, i , MAX_WEIGHT);
        }
        return i; //���ض������
    }
    public void removeEdge(int i, int j){ //ɾ����, ����Ȩֵ
        if (i != j) //�Ϸ����ж�
            this.matrix.set(i, j, MAX_WEIGHT); //����ȨֵΪ����
    }
    public void removeVertex(int i){ //ɾ������vi�Լ������й����ı�
        int n = this.vertexCount(); //ԭ���Ķ�����Ŀ
        if (i >= 0 && i < n){
            this.vertexlist.remove(i); //ɾ�������Ķ���
            for (int j = i + 1; j < n; j++) //��λ ����i��֮���������,ÿ�е�����Ԫ������1λ
                for (int k = 0; k < n; k++)
                    this.matrix.set(j-1, k, this.matrix.get(j, k));
            for(int k = i + 1; k < n; k++) //��λ����i��֮���������,ÿ�е�����Ԫ������1λ
                for (int j = 0; j < n; j++)
                    this.matrix.set(j, k - 1, this.matrix.get(j, k));
            this.matrix.setRowsColumns(n-1, n-1); //�ڽӾ�����С
        }else throw new IndexOutOfBoundsException();
    }
    public int weight(int i, int j){ //����Ȩֵ
        return this.matrix.get(i, j);
    }
    protected int next(int i, int j){ //����vi��vj֮��(������vj)�ĺ���ڽӶ������
        int n = this.vertexCount();
        if (i >= 0 && i < n && j >= -1 && j < n && i != j)
            for (int k = j + 1; k < n; k++) // ���ڸ�����vj������ж���
                if (this.matrix.get(i, k) > 0 && this.matrix.get(i, k) < MAX_WEIGHT) //ȨֵΪ�����Ҳ�Ϊ�������ʾ�б�
                    return k;
        return -1;
    }
    public void removeVertex(T vertex){ //ɾ������
        int i = this.vertexlist.search(vertex); //����һ��λ��
        this.removeVertex(i); //ɾ��
    }
}