public class AdjListGraph<T> extends AbstractGraph<T> { //图的邻接表表示的实现
    protected LinkedMatrix adjlist; // 图的邻接表
    public AdjListGraph(int length){ // 构造空图,顶点数和边数都为0,length指定顶点顺序表的容量和邻接表的容量
        super(length); // 构造容量为length的顺序表来存储点
        this.adjlist = new LinkedMatrix(length); //构造length规模的矩阵
    }
    public AdjListGraph(){ // 构造默认大小的空图
        this(10);
    }
    public AdjListGraph(T[] vertices){ //根据给定的顶点集合构造图
        this(vertices.length); //先初始化大小
        for (int i = 0; i < vertices.length; i++) //然后插入顶点
            this.insertVertex(vertices[i]);
    }
    public AdjListGraph(T[] vertices, Triple[] edges){ // 根据给定的顶点和边的组合来初始化图
        this(vertices); //根据点初始化图
        for (int j = 0; j < edges.length; j++) //插入边
            this.insertEdge(edges[j]);
    }
    public String toString(){ // 输出描述字符串
        return super.toString() + "出边表: \n" + this.adjlist.toString(); //调用父类的描述字符串加上本类的
    }
    public void insertEdge(int i, int j, int weight){ //插入边
        if (i != j){ //如果不是环
            if (weight < 0 || weight > MAX_WEIGHT) //规范化权值的大小
                weight = 0; //权值等于0则表示没有边
            this.adjlist.set(i, j, weight); //设置矩阵当前位置的值(等于0的时候不表示)
        }else throw new IllegalArgumentException();
    }
    public void insertEdge(Triple tri){ //更具给定的三元组来插入边
        insertEdge(tri.row, tri.column, tri.values);
    }
    public int insertVertex(T x) //根据给定的元素来插入点
    {
        int i = this.vertexlist.insert(x); //先更新点列表中的点(返回当前点的个数)
        if (i >= this.adjlist.getRows()) // 如果点的个数比矩阵的行数要多(需要扩充的情况下)
            this.adjlist.setRowsColumns(i+1, i+1); //重新设置矩阵的行列数
        return i; //返回当前点的个数
    }
    public void removeEdge(int i, int j){ //删除给定两点之间的边
        if (i != j) //如果不是环
            this.adjlist.set(new Triple(i, j, 0)); //设置当前位置的权重为0(也就是不表示)
    }
    public void removeEdge(Triple tri){ // 根据给定的三元组删除边
        this.removeEdge(tri.row, tri.column); // 根据三元组的行列数删除边(不考虑weight)
    }
    public void removeVertex(int i){ // 删除点(首先删除点列表中的点, 然后删除点在边矩阵中的行列, 最后对于后面的行列整体上上移一位)
        int n = this.vertexCount(); //获得当前点的数量
        if (i >= 0 && i < n){ // 判断合法性
            SortedSinglyList<Triple> link = this.adjlist.rowlist.get(i); // 获得当前点的邻接边列表
            for (Node<Triple> p = link.head.next; p != null; p = p.next) // 对于当前点的各个邻接边(这个循环是为了删除邻接边的)
                this.removeEdge(p.data.toSymmetry()); //删除其对称位置的Triple
            n--; //更新当前矩阵的点数
            this.adjlist.rowlist.remove(i); //通过Seqlist的删除方法直接删除这一行的所有边
            this.adjlist.setRowsColumns(n, n); //更新矩阵的大小
            for(int j = 0; j < n; j++){ //对于矩阵中的每一行(这个循环用来调整行列数)
                link = this.adjlist.rowlist.get(j); //获得当前行
                for (Node<Triple> p = link.head.next; p != null; p = p.next){ //检查其中的每一个元素
                    if (p.data.row > i) //如果行列树大于给定的点的指针
                        p.data.row--; //行列数减一(在使用二维数组的时候不需要考虑这个,但是需要将整体进行移动)
                    if (p.data.column > i)
                        p.data.column--;
                }
            }
            this.vertexlist.remove(i); //在点列表中删除给定点
        }else
            throw new IndexOutOfBoundsException();
    }
    public int weight(int i, int j){ //返回给定位置的权重
        if (i == j){
            return 0; //环是不存在的
        }
        int weight = this.adjlist.get(i, j); //获得给定位置的权重(找不到当前位置的节点的话返回0)
        return weight != 0 ? weight : MAX_WEIGHT; //非零正常返回,为0的话返回无穷
    }
    // 下面这个方法必须在存在vi和vj之间的边的时候才能使用
    protected int next(int i, int j){ // 返回vi在vj后的后继邻接顶点的序号,如果j = -1, 返回第一个, 如果不存在返回-1
        int n = this.vertexCount(); //获得当前点的个数
        if (i >= 0 && i < n && j >= -1 && j < n && i != j){ //如果i和j都在范围之内
            SortedSinglyList<Triple> link = this.adjlist.rowlist.get(i); // 获得当前vi邻接边列表
            Node<Triple> find = link.head.next; // 获得邻接边列表的第一个元素
            if (j == -1) //判断j的值
                return find != null ? find.data.column : -1; //检查邻接边列表的第一个元素是否为空,非空则输出
            find = link.search(new Triple(i, j, 0)); //否则,使用SortedSinglyList的搜索方法来搜索是否存在给定位置的边
            if (find != null){ //如果有
                find = find.next; //找下一个
                if (find != null)  //如果有下一个
                    return find.data.column; //返回列数
            }
        }
        return -1; //否则返回-1
    }
}
