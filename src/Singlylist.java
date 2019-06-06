public class Singlylist<T> extends Object { //新建链表类
    public Node<T> head; //新建头结点
    public Singlylist(){
        this.head = new Node<T>();
    } //新建空链表
    public Singlylist(T[] values){ //根据数组新建链表
        this(); //新建空链表
        Node<T> rear = this.head; //新建一个指针指向链表的尾部
        for (int i = 0; i < values.length; i++){ //遍历数组并对每一个元素进行链表的赋值
            rear.next = new Node<T>(values[i], null); //将最后一个节点的next位置设置为下一个节点的内存地址
            rear = rear.next; //移动rear指针的位置
            // rear = new Node<T>(values[i], rear); 应该也可以
        }
    }
    public boolean isEmpty(){
        return this.head.next == null;
    } //判断链表是否非空(判断头结点是否有非空的next)
    public T get(int i){ //获取链表中特定位置的元素
        Node<T> p = this.head; //新建指针
        for (int j = 0; j < i + 1 && p != null; j++){ //对于输入的给定位置寻找
            p = p.next; //更新指针的位置
        }
        if (i>= 0 && p != null){ //如果i合法且指针不是链表最后的的位置
            return p.data; //返回节点的data值
        }else return null; //否则返回null
    }
    public void set(int i, T x){ //设置链表特定位置的值
        Node<T> p =this.head; //新建指针
        for (int j = 0; j < i + 1 && p != null; j++){ //寻找给定的位置
            p = p.next; //更新指针的位置
        }
        if (i >= 0 && x != null && p != null){ //如果位置合法,值合法,指针不是指向链表最后的位置
            p.data = x; //设置值
        }else {
            throw new NullPointerException("x = i"); //否则抛出异常(因为此方法没有返回值,不知道是否已经设置成功)
        }
    }
    public int size(){ //返回链表的长度
        Node<T> p = this.head.next; // 新建指针指向链表的第一个元素
        int i = 0; //初始化链表长度
        while (p != null){ //如果指针当前指向的位置不是最后一位
            p = p.next; //更新指针位置
            i++; //同步更新链表长度
        }
        return i; //返回链表长度
    }
    public String toString(){ //输出字符串
        String str = this.getClass().getName() + "("; //初始化字符串
        for (Node<T> p = this.head.next; p != null; p = p.next){ //遍历整个链表
            str += p.data.toString(); //对链表内的每一个元素执行字符串的更新
            if (p.next != null) //如果不是最后一个元素
                str += ", "; //加入逗号
        }
        return str + ")"; //返回更新完毕的字符串
    }
    public Node<T> insert(int i, T x){ //插入操作
        Node<T> p = this.head; //新建指针指向头部
        for (int j = 0; j < i && p.next != null; j++){ //寻找给定的位置的前一个位置
            p = p.next; //更新指针位置
        }
        if (x != null){ //判断插入值是否合法(不用判断插入位置是否大于链表长度,因为使用p.next!= null可以有鲁棒性)
            p.next = new Node<T>(x, p.next); //新建包含给定数据的节点并连接到链表上(赋值运算从右到左)
        }
        return p.next; // 返回节点
    }
    public Node<T> insert(T x){ //尾部插入
        if (x != null) //判断合法性
            return this.insert(Integer.MAX_VALUE, x);
        return null;
    }
    public T remove(int i){ //根据位置删除链表元素
        Node<T> front = this.head; //新建指针指向头节点
        for (int j = 0; j < i && front.next.next != null; j++){ //寻找给定的位置前面的位置
            front = front.next; //更新指针位置
        }
        T key = front.next.data; //
        front.next = front.next.next;
        return key;
    }
    public void clear(){
        this.head.next = null;
    } //清空链表(将头结点的next设置为null)
    public Node<T> search(T key){ //根据值来寻找节点
        for (Node<T> p = this.head.next; p != null; p = p.next){ //遍历链表对于每一个节点
            if (p.data.equals(key)) //如果data值等于key
                return p; //返回这个节点
        }
        return null; //否则返回null
    }
    public boolean contains(T key){ //判断链表是否含有某个值
        for (Node<T> p = this.head.next; p != null; p = p.next){ //遍历链表对于每一个节点
            if (p.data.equals(key)) //检测其data值
                return true; //符合返回true
        }
        return false; //检测完毕不符合返回false
    }
    public Node<T> insertDifferent(T x){ //
        if (!this.contains(x)){
            return this.insert(x);
        }
        return null;
    }
    public T remove(T key){ //根据值删除元素
        int i = 0;
        for (Node<T> p = this.head.next; p != null; p = p.next){ //遍历链表对于每一个节点
            if (p.data == key){ //判断是否符合
                return this.remove(i); //调用remove方法删除
            }
            i++;
        }
        return null; //找不到返回null
    }
//    public Singlylist<T> remove(int begin, int end){ //删除从begin到end中的所有元素并返回
//        Singlylist<T> list = new Singlylist<T>(); //新建空链表
//        if (begin <= end && begin >= 0){ //如果1begin和end合法
//            for (int i = begin; i <= end; i++){ //对于从begin到end中的每个元素
//                T key = this.remove(begin); //使用remove方法得到并删除元素
//                list.insert(key); //使用插入方法将元素插入list中
//            }
//            return list; //返回新链表
//        }
//        return null; //否则返回null
//    }
    public Singlylist<T> remove(int begin, int end){ //删除从begin到end1中的所有元素并返回
        Singlylist<T> list = new Singlylist<>(); //新建空链表
        if (begin < end && begin >= 0){ //如果合法
            int b = -1; //链表中的当前位置指针
            Node<T> p = this.head; //初始化前指针
            Node<T> q; //初始化后指针
            if (! this.isEmpty()){ //如果链表不为空
                // 遍历一次来找到begin和end对应的位置
                for (q = this.head; q.next != null; q = q.next){ //对于当前链表中的每一个元素
                    if (b == begin - 1) //如果为begin的前驱元素
                        p = q; //赋值前指针
                    if (b == end) //如果为end位置的元素
                        break; //停止循环使得后指针指向它
                    b++; //循环尾部更新当前位置
                }
                //此时得到的p指向begin位置的前驱元素, q指向end元素
                list.head.next = p.next; //list头结点连接上begin元素
                p.next = q.next; //begin的前驱元素指向q的下一个元素(左右包含删除)
                q.next = null; //将list中的最后一个元素的next设置为null
                return list;//返回list
            }
            return null;
        }
        return null;//否则返回null
    }
    public Singlylist<T> difference(Singlylist<T> tlist){
        Singlylist<T> list = new Singlylist<T>();
        for (Node<T> p = this.head; p != null; p = p.next){
            if (!tlist.contains(p.data)){
                list.insert(p.data);
            }
        }
        return list;
    }
    public Singlylist<T> intersection(Singlylist<T> tlist){
        Singlylist<T> list = new Singlylist<>();
        for (Node<T> p = this.head; p != null; p = p.next){
            if (tlist.contains(p.data))
                list.insert(p.data);
        }
        return list;
    }
    public void removeAll(Singlylist<T> pattern){
        int i = 0;
        int j = 0;
        int length = pattern.size();
        boolean x = false;
        Node<T> q = pattern.head.next;
        for (Node<T> p = this.head.next; p != null; p = p.next){
            if (p.data == pattern.head.next.data){
                x = true;
                q = q.next;
                continue;
            }
            if (j == length - 1){
                this.remove(i - j, i);
                i = i - j;
                j = 0;
                x = false;
                q = pattern.head.next;
            }else if (j < length && x){
                x = (p.data == q.data) ? true: false;
                q = q.next;
                j++;
            }else if (!x){
                j = 0;
            }
            i++;
        }
    }
    public static double average(Singlylist<Integer> list){
        int total = 0;
        for (Node<Integer> p = list.head.next; p != null; p = p.next){
            total += p.data;
        }
        return (double)total / list.size();
    }
    public static double averageExceptMaxMin(Singlylist<Integer> list){
        int total = 0;
        int i = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (Node<Integer> p = list.head.next; p != null; p = p.next){
            if (p.data >= max)
                max = p.data;
            if (p.data <= min)
                min = p.data;
            total += p.data;
            i++;
        }
        return (double)(total - max - min) / (i - 2);
    }
}
