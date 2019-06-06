public class LinkedMatrix { // 链表存储的矩阵类
    int rows, columns; //存储行列数
    public Seqlist<SortedSinglyList<Triple>> rowlist; //构造存储矩阵的数据结构(顺序表套链表)
    public LinkedMatrix(int m, int n){ //根据给定的信息构造矩阵
        if (m > 0 && n > 0){ //判断合法性
            this.rows = m;
            this.columns = n; //设定矩阵的行列值
            this.rowlist = new Seqlist<>(); // 初始化矩阵
            for (int i = 0; i < m; i++){
                this.rowlist.insert(new SortedSinglyList<Triple>()); //初始化矩阵的行数
            }
        }else
            throw new IllegalArgumentException();
    }
    public LinkedMatrix(int m){ //初始化行列数相等的矩阵
        this(m, m);
    }
    public LinkedMatrix(int m, int n, Triple[] tris){ //根据给定的信息构造矩阵并设定矩阵的值
        this(m, n); //初始化矩阵
        for (int i = 0; i < tris.length; i++){
            this.set(tris[i]); //使用set方法将tri三元组添加进矩阵中
        }
    }
    public int getRows(){ //返回行数
        return this.rows;
    }
    public int getColumns(){ //返回列数
        return this.columns;
    }
    public int get(int i, int j){  //返回矩阵给定位置的值
        if (i >= 0 && i < this.rows && j >= 0 && j < this.columns){ //判断合法性
            Node<Triple> find  = this.rowlist.get(i).search(new Triple(i, j, 0)); //在给定行的SortedSinglyList中寻找是否有给出的triple(triple的equals方法只比较行列数)
            return (find != null) ? find.data.values : 0; //如果找到了就返回找到的值 否则返回0
        }else throw new IndexOutOfBoundsException();
    }
    public void set(int i, int j, int x){ //设置给定位置的值
        if (i >= 0 && i < this.rows && j >= 0 && j < this.columns){ //判断合法性
            SortedSinglyList<Triple> link = this.rowlist.get(i); //获得给定行的SortedSinglyList
            if (x == 0) //如果给定的值为0
                link.remove(new Triple(i, j, 0)); //删除这个位置的Triple
            else {
                Triple tri = new Triple(i, j, x); //不为0的话先新建一个Triple
                Node<Triple> find = link.search(tri); //找一找是否有这个位置的节点
                if (find != null) //如果有
                    find.data.values = x; //那就设置这个位置的值
                else link.insert(tri); //否则插入一个新的节点
            }
        }else throw new IndexOutOfBoundsException();
    }
    public void set(Triple tri){ //根据给定的三元组设置这个位置的值
        this.set(tri.row, tri.column, tri.values);
    }
    public String toString(){ //返回描述字符串
        String str = ""; //初始化字符串
        for (int i = 0; i < this.rowlist.size(); i++){ //每一行的元素都返回描述字符串
            str += i + " -> " + this.rowlist.get(i).toString() + "\n";
        }
        return str; //返回总的字符串
    }
    public void printMatrix(){ //按照矩阵的格式进行打印
        System.out.println("矩阵" + this.getClass().getName() + "(" + rows + "x" + columns + ") : "); //初始化打印头部
        for (int i = 0; i < this.rows; i++){ //对于矩阵中的每一行打印其中的元素
            Node<Triple> p = this.rowlist.get(i).head.next; //获得给出的每一行的头部元素
            for (int j = 0; j < this.columns; j++){ //对于这行中的每一列
                if (p != null && j == p.data.column){ //如果这个位置有不为0的元素,则有节点
                    System.out.print(String.format("%4d", p.data.values)); //输出元素的值
                    p = p .next; //节点位置更新
                }else System.out.print(String.format("%4d", 0)); //如果没有节点的话证明值为0
            }
            System.out.println(); //每输出一行之后换行
        }
    }
    public boolean equals(Object obj){ //判断是否相等
        if (this == obj) //指针相等
            return true;
        if (!(obj instanceof LinkedMatrix)) //如果不是这个类的一个实例
            return false;
        LinkedMatrix mat = (LinkedMatrix)obj;
        return this.rows == mat.rows && this.columns == mat.columns && this.rowlist.equals(mat.rowlist); //比较行列数以及rowlist是否相等
    }
    public void setRowsColumns(int m, int n){ //设置矩阵的行列数
        if (m > 0 && n > 0){
            if (m > this.rows){ //如果给定的行数比当前的行数要大
                for (int i = this.rows; i < m; i++){ //对于增加的每一行
                    this.rowlist.insert(new SortedSinglyList<>()); //插入一个SortedSinglyList在末尾
                }
            }
            this.rows = m;
            this.columns = n; //修改行列数
        }else throw new IllegalArgumentException();
    }
}
