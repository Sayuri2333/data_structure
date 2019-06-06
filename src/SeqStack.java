public class SeqStack<T> implements Stack<T> { // 实现顺序栈
    private Seqlist<T> list; //使用顺序表来构造栈
    public SeqStack(int length){
        this.list = new Seqlist<T>(length);
    } // 新建一个给定长度的顺序栈
    public SeqStack(){
        this(64);
    } //新建默认长度的顺序栈
    public boolean isEmpty(){
        return this.list.isEmpty();
    } // 判断顺序栈是否非空(调用顺序表的方法)
    public void push(T x){
        this.list.insert(x);
    } // 将元素插入栈中(尾部插入不需要移动元素位置)
    public T peek(){ //得到当前栈中最顶部元素的值
        return this.list.get(list.size() - 1); //返回顺序表最后一位元素的值
    }
    public T pop(){
        return this.list.remove(list.size() - 1);
    } // 栈弹出
}
