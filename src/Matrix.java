public class Matrix{  //������
    protected int rows, columns; //�ƶ�������
    protected int[][] elements; //�洢Ԫ��
    public Matrix(int m, int n){ //ͨ���������������������
        this.elements = new int[m][n];
        this.rows = m;
        this.columns = n;
    }
    public Matrix(int n){ //������������ȵľ���
        this(n, n);
    }
    public Matrix(int m, int n, int[][] values){ //�����������Լ�ֵ�������
        this(m, n);
        for (int i = 0; i < values.length && i < m; i++){
            for (int j = 0; j < values[i].length && j < n; j++){
                this.elements[i][j] = values[i][j];
            }
        }
    }
    public int getRows(){ //��������
        return this.rows;
    }
    public int getColumns(){ //��������
        return this.columns;
    }
    public int get(int i, int j){ //���ظ�����������λ�õ�ֵ
        if (i >= 0 && i < this.getRows() && j >= 0 && j < this.getColumns()){ //�Ϸ����ж�
            return this.elements[i][j];
        }else
            throw new IndexOutOfBoundsException(); //�׳��±�Խ���쳣
    }
    public void set(int i, int j, int x){ //���ø���λ�õ�ֵ
        if (i >= 0 && i < this.getRows() && j >= 0 && j < this.getColumns()) //�Ϸ����ж�
            this.elements[i][j] = x;
        else
            throw new IndexOutOfBoundsException();
    }
    public String toString(){ //��������������ַ���
        String str = "����" +this.getClass().getName() + "(" + this.rows + "x" + this.columns + ") : \n";
        for (int i = 0; i < this.rows; i++){
            for (int j = 0; i < this.columns; j++){
                str += String.format("%6d", this.elements[i][j]);  // "%6d"��ʾʮ��������ռ6��
            }
            str += "\n"; //ÿ�������Ϻ���
        }
        return str;
    }
    public void setRowsColumns(int m, int n){ //���ݸ����Ĳ������о�������
        if (m > 0 && n > 0){//�Ϸ��Լ���
            if (m > this.elements.length || n > this.elements[0].length){ //��������Ĳ������ڵ�ǰ����Ĳ���
                int[][] source = this.elements; //�����������Seqlist����
                this.elements = new int[m * 2][n * 2];
                for (int i = 0; i < this.rows; i++)
                    for (int j = 0; j < this.columns; j++)
                        this.elements[i][j] = source[i][j];
            }
            this.columns = m; //��������Ĵ�С
            this.rows = n;
        }else
            throw new IllegalArgumentException();
    }
}