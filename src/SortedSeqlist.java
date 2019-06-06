public class SortedSeqlist<T extends Comparable<? super T>> extends Seqlist<T> { //T类型必须是实现了Comparable接口的
    public SortedSeqlist(){ //生成新的排序顺序表
        super();
    }
    public SortedSeqlist(int length){ //根据长度生成新的排序顺序表
        super(length);
    }
    public SortedSeqlist(T[] values){ //根据给定的数组生成新的排序顺序表
        super(values.length);
        for (int i = 0; i < values.length; i++){ //遍历给定的数组
            this.insert(values[i]); //插入
        }
    }
    public int insert(T x){ //插入
        int i = 0; //插入位置的标记
        if (this.isEmpty() || x.compareTo(this.get(this.size() - 1)) > 0) //空表或者为整个表中最大值时将标记移至尾部
            i = this.n;
        else
            while (i < this.n && x.compareTo(this.get(i)) > 0) //遍历顺序表在适合的插入位置
                i++;
        super.insert(i, x); //使用父类的插入方法
        return i;
    }
    public void set(int i, T x){ //废除继承的设置方法
        throw new java.lang.UnsupportedOperationException("set(int i, T x)");
    }
    public int insert(int i, T x){ //废除继承的插入方法
        throw new java.lang.UnsupportedOperationException("insert(int i, T x)");
    }
    public int search(T key){ //遍历搜索
        for (int i = 0; i < this.n && key.compareTo(this.get(i)) >= 0; i++){
            if (key.compareTo(this.get(i)) == 0) //比较给出的值与各个元素的值的大小
                return i;
        }
        return -1;
    }
    public T remove(T key){ //删除排序顺序表中的元素
        if (this.search(key) != -1){ //如果顺序表内有这个元素的话
            T temp = super.remove(this.search(key)); // 调用父类的remove方法进行删除
            return temp; //返回被删除的值
        }
        return null; //查找不到则返回null
    }
}
