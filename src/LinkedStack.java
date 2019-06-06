public class LinkedStack<T> implements Stack<T>{ //链式栈
    private Singlylist<T> list; // 单链表作为存储方式
    public LinkedStack(){
        this.list = new Singlylist<T>();
    } // 新建空栈
    public boolean isEmpty(){
        return this.list.isEmpty(); //判断栈的空使用单链表的方法
    }
    public void push(T x){  //防止每次出栈的时候需要找寻最后一个元素的位置
        this.list.insert(0, x); //栈顶插入
    }
    public T peek(){
        return this.list.get(0);
    }
    public T pop(){
        return this.list.remove(0);
    }
    public String toString(){
        return list.toString();
    }
}
