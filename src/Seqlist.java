public class Seqlist<T> extends Object{
    protected Object[] elements; //object类型的数组
    protected int n; //标识当前顺序表长度的量
    public Seqlist(int length){  //根据给定长度生成空顺序表
        this.elements = new Object[length]; //初始化elements数组
        this.n = 0; //初始化长度为0
    }
    public Seqlist(){ //生成默认长度的顺序表
        this(64);
    }
    public Seqlist(T[] values){ //根据数组生成顺序表
        this.elements = new Object[values.length]; //初始化空elements数组
        for (int i = 0; i < values.length; i++)
            this.elements[i] = values[i]; //将给定数组中的元素赋值给elements
        this.n = values.length; //更新顺序表长度
    }
    public Seqlist(Seqlist<T> values){ //复制顺序表
        this.n = values.n; //更新当前顺序表长度
        this.elements = new Object[values.elements.length]; //初始化空elements数组
        for (int i = 0; i < this.n; i++){
            this.elements[i] = values.elements[i]; //深拷贝
        }
    }
    public int size(){ //返回顺序表长度
        return this.n;
    }
    public boolean isEmpty(){ //判断是否非空
        return this.n == 0;
    }
    public T get(int i){ //返回指定位置的元素
        if (i >= 0 && i < this.n) //判断合法性
            return (T)elements[i]; //返回元素(需要手动更改类型)
        return null; //非法输入返回null
    }
    public void set(int i, T x){ //设定某个位置的元素
        if (i >= 0 && i < this.n && x != null) //判断合法性
            this.elements[i] = x; //更新指定位置的值
        else if (x == null)
            throw new NullPointerException("x = null"); //非法抛出异常
        else
            throw new NullPointerException("not available index");
    }
    public String tostring(){ //字符串输出
        String str = this.getClass().getName() + "("; //初始化输出字符串
        if (this.n > 0){ //判断为非空时输出顺序表内的元素
            str += elements[0].toString(); //更新输出字符串
            for (int i = 1; i < this.n; i++){ //对于剩下的元素更新字符串
                if (i != this.n - 1)
                    str += elements[i].toString() + ", ";
                else
                    str += elements[i].toString(); //区别对待最后一个元素
            }
        }
        return str + ")"; //返回字符串
    }
    public int insert(int i, T x){ //指定位置插入元素
        if (x == null) //判断合法性
            throw new NullPointerException("x == null"); //抛出异常
        if (i < 0)
            i = 0;
        else if(i > n) //增加robust性
            i = n;
        Object[] copy = this.elements; //做当前elements数组的拷贝
        if (this.n == this.elements.length){ //如果当前的elements1数组已经满了
            this.elements = new Object[copy.length * 2]; //新建一个两倍长的elements数组
            for(int j = 0; j < i; j++){ //将指定位置前的元素导入新的elements数组
                this.elements[j] = copy[j];
            }
        }
        for (int j = i; j < this.n; j++){ //将指定位置后的元素到导入新的数组(注意如果elements数组未满的话无需更新前i个元素)
            this.elements[j + 1] = copy[j];
        }
        this.elements[i] = x; //指定位置插入给定元素
        this.n += 1; //更新数组长度
        return i; //返回插入位置
    }
    public int insert(T x){ //尾部插入
        return this.insert(this.n, x);
        // return this.insert(Integer.MAX_VALUE, x); 也可以
    }
    public T remove(int i){
        if (i < 0)
            i = 0;
        else if (i > n) //增加robust性
            i = n;
        T element = (T) elements[i];
        if (i != this.n){ //若不是最后一个元素(意味着此元素有后继元素需要移动位置)
            for (int j = i; j < this.n; j++){
                elements[j] = elements[j + 1]; //后继元素移动位置
            }
        }
        this.n -= 1; //更新顺序表长度
        return element; //返回被删除元素
    }
    public void clear(){ //清空当前顺序表(并没有删除元素, 只是无法索引)
        this.n = 0;
    }
    public int search(T key){ //根据值搜索
        for (int i = 0; i < this.n; i++){ //遍历顺序表(不是遍历elements数组)
            if (elements[i].equals(key)) //equals方法判断是否相等
                return i; //返回索引值
        }
        return -1; //搜索不到返回-1
    }
    public boolean equals(Object obj){ //检测两个顺序表是否相等(内部元素是否相等)
        if (this == obj) //如果是引用同一个内存地址
            return true;
        if (obj instanceof Seqlist<?>){ //如果obj是顺序表的一个实例
            Seqlist<T> list = (Seqlist<T>) obj; //手动类型转换
            if (this.n == list.n){ //如果长度相同
                for (int i = 0; i < this.n; i++){ //遍历每一个元素检测是否相等
                    if (elements[i] != list.elements[i])
                        return false;
                }
                return true;
            }
            return false;
        }
        return false;
    }
}