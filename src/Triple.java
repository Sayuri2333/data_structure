public class Triple implements Comparable<Triple>, Addible<Triple> {
    public int row; //行数
    public int column; // 列数
    public int values; //值
    public Triple(int row, int column, int value){ //根据给定值新建三元组
        if (row >= 0 && column >= 0){
            this.row = row;
            this.column = column;
            this.values = value;
        }else
            throw new IllegalArgumentException("行列不为负数");
    }
    public Triple(Triple tri){ //根据给定三元组新建三元组
        this(tri.row, tri.column, tri.values);
    }
    public String toString(){ //输出描述字符串
        return "(" + row + ", " + column + ", " + values + ")";
    }
    public Triple toSymmetry(){ //复制一个三元组
        return new Triple(this.column, this.row, this.values);
    }
    public boolean equals(Object obj){ //判断是否相等
        if (this == obj)
            return true;
        else if(!(obj instanceof Triple))
            return false;
        Triple tri = (Triple)obj;
        return this.row == tri.row && this.column == tri.column && this.values == tri.values;
    }
    public int compareTo(Triple tri){ //比较三元组, 位于本组的上方或者左方的返回1(只比较行列数,不比较值)
        if (this.row == tri.row && this.column == tri.column)
            return 0;
        return (this.row < tri.row || this.row == tri.row && this.column < tri.column) ? -1: 1;

    }
    public void add(Triple term){ //相等位置的三元组相加
        if (this.compareTo(term) == 0)
            this.values += term.values;
        else
            throw new IllegalArgumentException("行列数不同无法相加");
    }
    public boolean removable(){ //判断是否可以移除(稀疏矩阵的压缩)
        return this.values == 0;
    }
}
