import java.io.ObjectInputStream;

public class HashSet<T>{ // 散列表存储
    private Singlylist<T>[] table; //使用单链表数组存储
    private int count = 0; // 计数元素数量
    private static final float LOAD_FACTOR = 0.75f; //装填因子
    public HashSet(int length){ //根据给定长度创建散列表
        if (length < 10) //限定最小长度
            length = 10;
        this.table = new Singlylist[length]; // 初始化单链表数组
        for (int i = 0; i < this.table.length; i++) // 初始化单链表数组里面的每一个值
            this.table[i] = new Singlylist<T>(); //初始化为空单链表
    }
    public HashSet(){ //默认长度创建散列表
        this(16);
    }
    private int hash(T x){ //根据给定的元素求出散列地址
        int key = Math.abs(x.hashCode()); //获得当前元素的Hash值
        return key % this.table.length; //除留取余法
    }
    public T search(T key){ //根据给定的元素搜索散列表
        Node<T> find = this.table[this.hash(key)].search(key); //根据给定元素的散列地址找到对应单链表,调用search方法求值
        return find == null ? null : find.data; //返回值
    }
    public boolean add(T x){ //根据给定的元素添加值
        if (this.count > this.table.length * LOAD_FACTOR){ //如果承载量超过装填因子
            this.printAll(); //输出当前散列表的信息
            //System.out.print("\n添加"+x+"，");
            Singlylist<T>[] temp = this.table; //使用temp变量存储当前散列表
            this.table = new Singlylist[this.table.length * 2]; //新建一个两倍长空散列表
            for (int i = 0; i < this.table.length; i++)
                this.table[i] = new Singlylist<T>(); //初始化新的散列表
            this.count = 0; //初始化count值
            for (int i = 0; i < temp.length; i++) //对于原来的散列表中的每一个单链表
                for (Node<T> p = temp[i].head.next; p != null; p = p.next) // 对于单链表中的每一个节点
                    this.add(p.data); //将其值添加至新散列表中
        }
        boolean insert = this.table[this.hash(x)].insertDifferent(x) != null; // 根据给定值求出散列地址,进行相异插入
        if (insert) //如果插入成功
            this.count++; //计数自增1
        return insert; //返回插入成功情况
    }
    public T remove(T key){ //根据值删除元素
        T x = this.table[this.hash(key)].remove(key); // 根据给定的值找到散列地址,调用remove方法删除
        if (x != null) //删除成功的话
            this.count --; //计数减一
        return x;
    }
    public HashSet(T[] values){ //根据给定的列表新建散列表
        this((int)(values.length / HashSet.LOAD_FACTOR)); //新建适合长度的空散列表
        this.addAll(values); //将values中的所有值添加进散列表中
    }
    public int size(){ //返回当前计数
        return count;
    }
    public boolean isEmpty(){ //判断散列表是否为空
        return this.size() == 0;
    }
    public boolean contains(T key){ //判断散列表中是否含有某个元素
        return this.search(key) != null;
    }
    private void addAll(T[] values){ //将values中的所有值添加进散列表中
        for (T value : values) {
            this.add(value); //对于每个值调用add方法
        }
    }
    public void clear(){ //清空散列表
        for (Singlylist p : this.table)
            p.clear(); //对于每个单链表调用清空方法
    }
    public String toString(){ //返回描述字符串
        String str = this.getClass().getName() + "(";
        boolean first = true;
        for (Singlylist<T> aTable : this.table) {
            for (Node<T> p = aTable.head.next; p != null; p = p.next){
                if (!first)
                    str += ",";
                first = false;
                str += p.data.toString();
            }
        }
        return str + ")";
    }
    public void printAll(){
        System.out.println("散列表：容量="+this.table.length+"，"+this.count+"个元素"+
                "，hash(key)=key % "+this.table.length+"，"+this.toString());
        for (int i = 0; i < this.table.length; i++){
            System.out.println("table["+i+"]="+this.table[i].toString());
        }
        System.out.print("ASL成功=(");
        int asl = 0;
        for (int i = 0; i < this.table.length; i++){
            int j = 1;
            for (Node<T> p = this.table[i].head.next; p != null; p = p.next, j++){
                System.out.print((asl==0 ? "" : "+")+j);
                asl += j;
            }
        }
        if (count == 0)
            System.out.println(") = 0\n");
        else
            System.out.println(")/"+count+" ="+asl+"/"+count+" ="+((asl+0.0)/count)+"\n");
    }
    public Object[] toArray(){ //返回包含集合所有元素的数组
        Object[] values = new Object[this.size()]; // 初始化一个用于存储的数组
        int j = 0; // 初始化指针的值
        for (int i = 0; i < this.table.length; i++) //遍历散列表
            for (Node<T> p = this.table[i].head.next; p != null; p = p.next) //对于单链表中每一个元素
                values[j++] = p.data; //将其存储到数组中
        return values; //返回数组
    }
}
