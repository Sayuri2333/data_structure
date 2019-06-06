public class SeqQueue<T> implements Queue<T>{ //使用顺序表的方法实现队列
    private Object element[]; //新建存储数组指针
    private int front, rear; // 新建队列头尾指针
    public SeqQueue(int length){ // 根据给定长度新建队列
        if (length < 64){
            length = 64;
        }
        this.element = new Object[length]; // 新建给定长度的数组
        this.front = this.rear = 0; // 初始化头尾指针的位置
    }
    public SeqQueue(){
        this(64);
    } // 建立默认长度的队列
    public boolean isEmpty(){
        return this.front == this.rear;
    } // 判断是否为空(头尾指针是否在一起)
    public boolean add(T x){ // 向队列中添加元素
        if (x == null) // 判断输入是否合法
            return false;
        if (this.front == (this.rear + 1) % this.element.length){ // 判断队列是否已满
            Object[] copy = this.element;
            this.element = new Object[copy.length * 2]; // 新建一个数组用于存储
            int j = 0;
            for (int i = this.front; i != this.rear; i = (i + 1) % this.element.length){ // 将原数组中的队列元素按顺序复制过来
                this.element[j++] = copy[i];
            }
            this.front = 0;
            this.rear = j; // 调整指针位置
        }
        this.element[this.rear] = x; //插入元素
        this.rear = (this.rear + 1) % this.element.length; // 修改尾部指针的位置
        return true;
    }
    public T peek(){
        return this.isEmpty() ? null: (T)this.element[this.front];
    } // 在数组非空的前提下返回头部指针处的值
    public T poll(){ // 弹出队列元素
        if (this.isEmpty()){  // 非空检验
            return null;
        }else {
            T x = (T)this.element[this.front]; // 返回第一个元素的值
            this.front = (this.front + 1) % this.element.length; //头部指针前移
            return x;
        }
    }
}
