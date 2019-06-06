public class MatrixGraph<T> extends AbstractGraph<T>{  //邻接矩阵表示的带权图类
    protected Matrix matrix; //矩阵存储权值(父类有用来存储元素的Seqlist)
    public MatrixGraph(int length){ //根据给定的元素长度生成空图
        super(length); //调用父类的构造方法来生成Seqlist
        this.matrix = new Matrix(length); //生成矩阵
    }
    public MatrixGraph(){ //默认值生成
        this(10);
    }
    public MatrixGraph(T[] vertices){ //以vertices顶点集合构造图
        this(vertices.length);
        for (int i = 0; i < vertices.length; i++){
            this.insertVertex(vertices[i]); //继承的父类的insertVertex构造方法
        }
    }
    public String toString(){ //输出描述字符串
        String str = super.toString() + "邻接矩阵: \n";
        int n = this.vertexCount();
        for (int  i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (this.matrix.get(i, j) == MAX_WEIGHT)
                    str += "      ∞"; //这就是为什么不能直接使用矩阵的描述字符串的原因
                else str += String.format("%6d", this.matrix.get(i, j));
            }
            str += "\n";
        }
        return str;
    }
    public void insertEdge(int i, int j, int weight){ //插入边<vi, vj>, 权值为weight
        if (i != j){ //不能插入回环
            if (weight <= 0 || weight > MAX_WEIGHT) //规范化weight值
                weight = MAX_WEIGHT;
            this.matrix.set(i, j, weight); //根据给定位置插入weight值(原来的方法中有异常处理)
        }else throw new IllegalArgumentException(); //插入回环的异常处理
    }
    public void insertEdge(Triple edge){ //根据给定的三元组插入权重(边)
        this.insertEdge(edge.row, edge.column, edge.values);
    }
    public int insertVertex(T x){ //插入元素为x的顶点(同时要更新边的矩阵)
        int i = this.vertexlist.insert(x); //先将点插入
        if (i >= this.matrix.getRows()) //如果邻接矩阵的容量不够
            this.matrix.setRowsColumns(i+1, i+1); //矩阵扩容
        for (int j = 0; j < i; j++){ //初始化第i行列元素为无穷
            this.matrix.set(i, j, MAX_WEIGHT);
            this.matrix.set(j, i , MAX_WEIGHT);
        }
        return i; //返回顶点序号
    }
    public void removeEdge(int i, int j){ //删除边, 忽略权值
        if (i != j) //合法性判断
            this.matrix.set(i, j, MAX_WEIGHT); //设置权值为无穷
    }
    public void removeVertex(int i){ //删除顶点vi以及其所有关联的边
        int n = this.vertexCount(); //原来的顶点数目
        if (i >= 0 && i < n){
            this.vertexlist.remove(i); //删除给定的顶点
            for (int j = i + 1; j < n; j++) //补位 对于i行之后的所有行,每行的所有元素上移1位
                for (int k = 0; k < n; k++)
                    this.matrix.set(j-1, k, this.matrix.get(j, k));
            for(int k = i + 1; k < n; k++) //补位对于i列之后的所有列,每列的所有元素左移1位
                for (int j = 0; j < n; j++)
                    this.matrix.set(j, k - 1, this.matrix.get(j, k));
            this.matrix.setRowsColumns(n-1, n-1); //邻接矩阵缩小
        }else throw new IndexOutOfBoundsException();
    }
    public int weight(int i, int j){ //设置权值
        return this.matrix.get(i, j);
    }
    protected int next(int i, int j){ //返回vi在vj之后(不包括vj)的后继邻接顶点序号
        int n = this.vertexCount();
        if (i >= 0 && i < n && j >= -1 && j < n && i != j)
            for (int k = j + 1; k < n; k++) // 对于给定的vj后的所有顶点
                if (this.matrix.get(i, k) > 0 && this.matrix.get(i, k) < MAX_WEIGHT) //权值为正数且不为无穷则表示有边
                    return k;
        return -1;
    }
    public void removeVertex(T vertex){ //删除顶点
        int i = this.vertexlist.search(vertex); //搜索一下位置
        this.removeVertex(i); //删除
    }
}