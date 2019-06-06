public  abstract class AbstractGraph<T> {  //抽象图类表示顶点集合
    protected static final int MAX_WEIGHT = 99999; // 初始化权重为最大权重
    protected Seqlist<T> vertexlist;  //顶点顺序表,存储顶点,T表示元素类型
    public AbstractGraph(int length){ //构造空图顶点数为0, 指定长度
        this.vertexlist = new Seqlist<T>(length);
    }
    public AbstractGraph(){ //以默认长度构造空图
        this(10);
    }
    public int vertexCount(){ //返回图中的顶点数
        return this.vertexlist.size(); //返回表中的元素数量
    }
    public String toString(){ //描述字符串
        return "顶点集合: " +this.vertexlist.toString() + "\n"; //调用顺序表的描述字符串
    }
    public T getVertex(int i){ //返回第i个节点的值
        return this.vertexlist.get(i); //返回顺序表中的第i个位置的值
    }
    public void setVertex(int i, T x){ //设置第i个顶点的值
        this.vertexlist.set(i, x); //调用顺序表中的设置方法
    }
    public abstract int insertVertex(T x); //插入顶点
    public abstract void removeVertex(int j); //删除顶点
    public abstract int weight(int i, int j); //返回权重
    protected abstract int next(int i, int j);  //返回vi在vj后的后继邻接顶点序号 ；若j=-1，返回vi的第一个邻接顶点序号；若不存在后继邻接顶点，返回-1。
    public void DFSTraverse(int i){ // 深度优先遍历方法
        boolean[] visited = new boolean[this.vertexCount()]; //新建对于各个顶点的是否访问过的标记数组
        int j = i; //从给定顶点开始进行深度遍历
        do { //先进行循环再进行判断
            if (!visited[j]){ //如果当前的顶点未被访问过
                System.out.print("{");
                this.depthfs(j, visited); //从当前节点开始进行一次深度优先遍历
                System.out.print("}");
            }
            j = (j + 1) % this.vertexCount(); //更新当前顶点位置到下一个顶点
        }while (j != i); //如果当前顶点与开始的顶点不同
        System.out.println();
    }
    private void depthfs(int i, boolean[] visited){ //根据给定的顶点进行一次深度优先遍历
        System.out.print(this.getVertex(i) + " "); //输出当前顶点的值
        visited[i] = true; //设置其访问标记
        for (int j = this.next(i, -1); j != -1; j = this.next(i, j)) //对于其下一个顶点继续进行深度优先遍历(使用for循环的原因是能够遍历到叶子节点后退回来进行下一个分支的遍历)
            if (!visited[j]) //如果未被访问过
                depthfs(j, visited); //则对其使用深度优先遍历
    }
    public void BFSTraverse(int i){ //根据给定的顶点进行广度优先遍历
        boolean[] visited = new boolean[this.vertexCount()]; //新建标记数组
        int j = i; //设置当前顶点为初始顶点
        do {
            if (!visited[j]){ //如果当前顶点未被访问
                System.out.print("{");
                breadthfs(j, visited); //进行一次广度优先遍历
                System.out.print("}");
            }
            j = (j + 1) % this.vertexCount(); //对下一个顶点进行广度优先遍历(对于非连通图能够遍历所有的顶点)
        }while (j != i); //若当前顶点与初始顶点不同
        System.out.println();
    }
    private void breadthfs(int i, boolean[] visited){
        System.out.print(this.getVertex(i) + " "); //输出当前顶点的值
        visited[i] = true; //设置其标记
        LinkedQueue<Integer> que = new LinkedQueue<>(); //新建栈来按顺序存储访问过的点(栈的作用是在访问过所有后继顶点后存储后继顶点从而能够继续访问)
        que.add(i); //将当前顶点入栈
        while (!que.isEmpty()){ //如果栈不为空(栈为空的话这次广度优先遍历就完成了)
            i = que.poll(); //弹出一个顶点
            for (int j = next(i, -1); j != 1; j = next(i, j)){ //对于当前顶点的所有后继顶点
                if (!visited[j]){ //如果当前确定的节点未被访问
                    System.out.print(this.getVertex(j) + " "); //输出当前确定顶点的值
                    visited[j] = true; //设置标记点
                    que.add(j);//将当前的顶点入栈
                }
            }
        }
    }
    public void minSpanTree(){
        Triple[] mst = new Triple[vertexCount() - 1]; //初始化边的集合
        for (int i = 0; i < mst.length; i++){ //初始化各条边
            mst[i] = new Triple(0, i + 1, this.weight(0, i + 1)); //使用v0到各个顶点的权重来初始化
        }
        for (int i = 0; i < mst.length; i++){
            System.out.print("mst边集合: ");
            for (int j = 0; j < mst.length; j++){
                System.out.print(mst[j].toString() + ", ");
            }
            System.out.println();
            int min = i;
            for (int j = i + 1; j < mst.length; j++){
                if (mst[j].values < mst[min].values)
                    min = j;
            }
            Triple edge = mst[min];
            if (min != i){
                mst[min] = mst[i];
                mst[i] = edge;
            }
            int tv = edge.column;
            for (int j = i + 1; j < mst.length; j++){
                int v = mst[j].column;
                int weight = this.weight(tv, v);
                if (weight < mst[j].values)
                    mst[j] = new Triple(tv, v, weight);
            }
        }
        System.out.print("\n最小生成树的边的集合: ");
        int mincost = 0;
        for (int i = 0; i < mst.length; i++){
            System.out.print(mst[i] + " ");
            mincost += mst[i].values;
        }
        System.out.println(", 最小代价为: " + mincost);
    }
    public void shortestPath(int i){ // 计算给定顶点与其他顶点之间的最短路径
        int n = this.vertexCount(); // 使用n存储顶点个数
        boolean[] vset = new boolean[n]; // 使用布尔数组存储点的标记情况(标记过的点代表已经确定最短路径)
        vset[i] = true; // 设置当前位置为true(不需要检查这个位置)
        int[] dist = new int[n]; //初始化到各个点的距离的数组
        int[] path = new int[n]; // 初始化路径中上一个点的索引的数组
        // 初始化赋值dist和path两个数组
        for (int j = 0; j < n; j++){ //遍历所有的点
            dist[j] = this.weight(i, j); //使用当前点到各个点的权重来填充数组
            path[j] = (j != i && dist[j] < MAX_WEIGHT) ? i : -1; //根据当前点到各个点是否可达来判断path中的取值
        }
        // 确定dist以及path中的值
        for (int j = (i + 1) % n; j != i; j = (j + 1) % n){ //从当前点的下一个点开始遍历
            int mindist = MAX_WEIGHT; //初始化最小距离
            int min = 0; //初始化其对应的点
            for (int k = 0; k < n; k++){ //遍历dist数组得到长度最短的边以及对应的点
                if (!vset[k] && dist[k] < mindist){
                    mindist = dist[k];
                    min = k;
                }
            }
            if (mindist == MAX_WEIGHT) //非连通图必须
                break;
            vset[min] = true; //将得到的点加入已经确定的最短路径的点中
            for (int k = 0; k < n; k++){ //根据新加入的这个点来更新dist以及path
                if (!vset[k] && this.weight(min, k) < MAX_WEIGHT && dist[min] + this.weight(min, k) < dist[k]){
                    dist[k] = dist[min] + this.weight(min, k);
                    path[k] = min;
                }
            }
        }
        System.out.print(this.getVertex(i) + "的单源最短路径：");
        for (int j = 0; j < n; j++){ //输出最短路径
            if (j != i){
                Singlylist<T> pathlink = new Singlylist<>(); //新建单链表存储点集
                pathlink.insert(0, this.getVertex(j));//将终点添加进去
                for (int k = path[j]; k != i && k != j && k != -1; k = path[k]) //迭代k
                    pathlink.insert(0, this.getVertex(k)); //将位于k位置的点添加进去
                pathlink.insert(0, this.getVertex(i)); //添加起点
                System.out.print(pathlink.toString() + "长度" + (dist[j] == MAX_WEIGHT ? "∞" : dist[j]) + ", "); //输出
            }
        }
        System.out.println();
    }
    public void shortestPath(){ //每一对顶点之间的最短路径
        int n = this.vertexCount(); // 图的顶点数量
        Matrix path = new Matrix(n); // 路径前一点以及长度矩阵 初值全部为0
        Matrix dist = new Matrix(n);
        for (int i = 0; i < n; i++){ // 初始化两个矩阵
            for (int j = 0; j < n; j++){ //遍历矩阵
                int w = this.weight(i, j); //长度矩阵的对应位置填入当前两点之间的权重
                dist.set(i, j, w);
                path.set(i, j, (i != j && w < MAX_WEIGHT ? i : -1)); //如果这两点可达,则路径前一点矩阵填入起始点,否则填入-1表示不可达
            }
        }
        System.out.println("dist" + dist.toString() + "path" + path.toString() + "路径矩阵: "); //输出描述
        printPathAll(path);
        for (int k = 0; k < n; k++){ // 把k点作为路径上的一个中间顶点
            System.out.println("\n以"+this.getVertex(k)+"作为中间顶点，替换路径如下：");
            for (int i = 0; i < n; i++){ //根据给定的k看vi和vj之间的距离能否更短
                if (i != k){ //横轴排除k行
                    for (int j = 0; j < n; j++){
                        if (j != k && j != i){ //纵轴排除k列和i行
                            System.out.print(toPath(path,i,j)+"路径长度"+dist.get(i,j)+"，替换为"+
                                    toPath(path,i,k)+","+toPath(path,k,j)+"路径长度"+(dist.get(i,k)+dist.get(k,j))+"？");
                        }
                        if (j != k && j != i && dist.get(i, j) > dist.get(i, k) + dist.get(k, j)){ //如果通过k点的路径长度更加小则替换
                            dist.set(i, j, dist.get(i, k) + dist.get(k, j));
                            path.set(i, j, path.get(k, j)); // 注意这里不能直接写path.set(i, j, k); 应该要考虑k和j两点之间的最短路径不是直接可达的情况
                            System.out.println("是，d"+i+j+"="+dist.get(i,j)+"，p"+i+j+"="+path.get(i,j));
                        }else {
                            System.out.println("否");
                        }
                    }
                }
            }
            System.out.println("dist"+dist.toString()+"path"+path.toString()+"路径矩阵：");
            printPathAll(path);
        }
        System.out.println("\n每对顶点间的最短路径如下：");
        for (int i=0; i<n; i++)
        {
            for (int j=0; j<n; j++)
                if (i!=j)
                    System.out.print(toPath(path,i,j)+"长度"+(dist.get(i,j)==MAX_WEIGHT ? "∞" : dist.get(i,j))+"，");
            System.out.println();
        }
    }
    private String toPath(Matrix path, int i, int j){
        Singlylist<T> pathlink = new Singlylist<T>();
        pathlink.insert(0, this.getVertex(j));
        for (int k = path.get(i, j); k != i && k != j && k!= -1; k = path.get(i, k))
            pathlink.insert(0, this.getVertex(i));
        pathlink.insert(0, this.getVertex(i));
        return pathlink.toString();
    }
    private void printPathAll(Matrix path){
        for (int i=0; i<path.getRows(); i++)
        {
            for (int j=0; j<path.getRows(); j++)
                System.out.print(toPath(path,i,j)+" ");
            System.out.println();
        }
    }
}
