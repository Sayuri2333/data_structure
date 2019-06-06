public class Matrix{  //矩阵类
    protected int rows, columns; //制定行列数
    protected int[][] elements; //存储元素
    public Matrix(int m, int n){ //通过给定的行列数构造矩阵
        this.elements = new int[m][n];
        this.rows = m;
        this.columns = n;
    }
    public Matrix(int n){ //构造行列数相等的矩阵
        this(n, n);
    }
    public Matrix(int m, int n, int[][] values){ //给定行列数以及值构造矩阵
        this(m, n);
        for (int i = 0; i < values.length && i < m; i++){
            for (int j = 0; j < values[i].length && j < n; j++){
                this.elements[i][j] = values[i][j];
            }
        }
    }
    public int getRows(){ //返回行数
        return this.rows;
    }
    public int getColumns(){ //返回列数
        return this.columns;
    }
    public int get(int i, int j){ //返回给定行列数的位置的值
        if (i >= 0 && i < this.getRows() && j >= 0 && j < this.getColumns()){ //合法性判断
            return this.elements[i][j];
        }else
            throw new IndexOutOfBoundsException(); //抛出下标越界异常
    }
    public void set(int i, int j, int x){ //设置给定位置的值
        if (i >= 0 && i < this.getRows() && j >= 0 && j < this.getColumns()) //合法性判断
            this.elements[i][j] = x;
        else
            throw new IndexOutOfBoundsException();
    }
    public String toString(){ //返回数组的描述字符串
        String str = "矩阵" +this.getClass().getName() + "(" + this.rows + "x" + this.columns + ") : \n";
        for (int i = 0; i < this.rows; i++){
            for (int j = 0; i < this.columns; j++){
                str += String.format("%6d", this.elements[i][j]);  // "%6d"表示十进制整数占6列
            }
            str += "\n"; //每行输出完毕后换行
        }
        return str;
    }
    public void setRowsColumns(int m, int n){ //根据给定的参数进行矩阵扩容
        if (m > 0 && n > 0){//合法性检验
            if (m > this.elements.length || n > this.elements[0].length){ //如果给定的参数大于当前矩阵的参数
                int[][] source = this.elements; //基本步骤近似Seqlist扩容
                this.elements = new int[m * 2][n * 2];
                for (int i = 0; i < this.rows; i++)
                    for (int j = 0; j < this.columns; j++)
                        this.elements[i][j] = source[i][j];
            }
            this.columns = m; //更新数组的大小
            this.rows = n;
        }else
            throw new IllegalArgumentException();
    }
}