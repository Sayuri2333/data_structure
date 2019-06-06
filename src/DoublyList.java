public class DoublyList<T> {
    public DoubleNode<T> head;
    public DoublyList(){
        this.head = new DoubleNode<T>();
        this.head.prev = null;
        this.head.next = null;
    }
    public DoublyList(T[] values){
        this();
        DoubleNode<T> rear = this.head;
        for (int i = 0; i < values.length; i++){
            rear.next = new DoubleNode<T>(values[i], rear, null);
            rear = rear.next;
        }
    }
    public boolean isEmpty(){
        return this.head.next == this.head || this.head.next == null;
    }
    public DoubleNode<T> insert(int i, T x){
        if (x == null)
            throw new NullPointerException("x == null");
        DoubleNode<T> front = this.head;
        for (int j = 0; front.next != null && j < i; j++){
            front = front.next;
        }
        DoubleNode<T> q = new DoubleNode<T>(x, front, front.next);
        if (front.next != null){
            front.next.prev = q;
        }
        front.next = q;
        return q;
    }
    public DoubleNode<T> insert(T x){
        if (x == null)
            throw new NullPointerException("x == null");
        return this.insert(Integer.MAX_VALUE, x);
    }
    public DoublyList<T> remove(int begin, int end){
        DoublyList<T> list = new DoublyList<T>();
        DoubleNode<T> p = null;
        DoubleNode<T> q = this.head;
        if (begin <= end && begin >= 0){
            for (int i = 0; q.next != null && i <= end; i++){
                q = q.next;
                if (i == begin)
                    p = q;
            }
            list.head.next = p.prev.next;
            p.prev.next = q;
            list.head.next.prev = list.head;
            q.prev.next = null;
            q.prev = p;
            return list;
        }
        return null;
    }
    public boolean contains(T key){
        for (DoubleNode<T> p = this.head; p != null; p = p.next){
            if (p.data == key)
                return true;
        }
        return false;
    }
    public String toString(){
        String str = this.getClass().getName() + "(";
        for (DoubleNode<T> p = this.head.next; p != null; p = p.next){
            str += p.data.toString();
            if (p.next != null)
                str += ", ";
        }
        return str + ")";
    }
    public DoublyList<T> difference(DoublyList<T> tlist){
        DoublyList<T> list = new DoublyList<T>();
        for (DoubleNode<T> p = this.head; p != null; p = p.next){
            if (!tlist.contains(p.data) && !list.contains(p.data)){
                list.insert(p.data);
            }
        }
        return list;
    }
    public DoublyList<T> intersection(DoublyList<T> tlist){
        DoublyList<T> list = new DoublyList<T>();
        for (DoubleNode<T> p = this.head; p != null; p = p.next){
            if (tlist.contains(p.data))
                list.insert(p.data);
        }
        return list;
    }
    public int size(){
        DoubleNode<T> p = this.head.next;
        int i = 0;
        while (p != null){
            p = p.next;
            i++;
        }
        return i;
    }

    public void removeAll(DoublyList<T> pattern){
        int i = 0; //记录p指针的当前位置
        int j = 0; //记录q指针的当前位置
        int length = pattern.size(); //记录pattern的长度
        boolean x = false; //判断是否开始识别
        DoubleNode<T> q = pattern.head.next; //初始化q指针的位置
        for (DoubleNode<T> p = this.head.next; p != null; p = p.next){ //遍历本链表
            if (p.data == pattern.head.next.data && x == false){ //
                x = true;
                q = q.next;
                i++;
                j++;
                continue;
            }
            if (j == length){
                this.remove(i - j, i);
                i = i - j;
                j = 0;
                x = false;
                q = pattern.head.next;
                continue;
            }else if (j < length && x){
                x = (p.data == q.data) ? true: false;
                q = q.next;
                j++;
                if (x == false){
                    q = pattern.head.next;
                    j = 0;
                    p = p.prev;
                    continue;
                }
            }
            i++;
        }
    }
    public static double average(DoublyList<Integer> list){
        int total = 0;
        for (DoubleNode<Integer> p = list.head.next; p != null; p = p.next){
            total += p.data;
        }
        return (double)total / list.size();
    }
    public static double averageExceptMaxMin(DoublyList<Integer> list){
        if (list.size() > 2){
            int total = 0;
            int i = 0;
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (DoubleNode<Integer> p = list.head.next; p != null; p = p.next){
                if (p.data >= max)
                    max = p.data;
                if (p.data <= min)
                    min = p.data;
                total += p.data;
                i++;
            }
            return (double)(total - max - min) / (i - 2);
        }
        return 0;
    }
}
