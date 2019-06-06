public class Triple implements Comparable<Triple>, Addible<Triple> {
    public int row; //����
    public int column; // ����
    public int values; //ֵ
    public Triple(int row, int column, int value){ //���ݸ���ֵ�½���Ԫ��
        if (row >= 0 && column >= 0){
            this.row = row;
            this.column = column;
            this.values = value;
        }else
            throw new IllegalArgumentException("���в�Ϊ����");
    }
    public Triple(Triple tri){ //���ݸ�����Ԫ���½���Ԫ��
        this(tri.row, tri.column, tri.values);
    }
    public String toString(){ //��������ַ���
        return "(" + row + ", " + column + ", " + values + ")";
    }
    public Triple toSymmetry(){ //����һ����Ԫ��
        return new Triple(this.column, this.row, this.values);
    }
    public boolean equals(Object obj){ //�ж��Ƿ����
        if (this == obj)
            return true;
        else if(!(obj instanceof Triple))
            return false;
        Triple tri = (Triple)obj;
        return this.row == tri.row && this.column == tri.column && this.values == tri.values;
    }
    public int compareTo(Triple tri){ //�Ƚ���Ԫ��, λ�ڱ�����Ϸ������󷽵ķ���1(ֻ�Ƚ�������,���Ƚ�ֵ)
        if (this.row == tri.row && this.column == tri.column)
            return 0;
        return (this.row < tri.row || this.row == tri.row && this.column < tri.column) ? -1: 1;

    }
    public void add(Triple term){ //���λ�õ���Ԫ�����
        if (this.compareTo(term) == 0)
            this.values += term.values;
        else
            throw new IllegalArgumentException("��������ͬ�޷����");
    }
    public boolean removable(){ //�ж��Ƿ�����Ƴ�(ϡ������ѹ��)
        return this.values == 0;
    }
}
