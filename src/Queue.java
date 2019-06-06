public interface Queue<T> { //队列接口
    public abstract boolean isEmpty(); // 是否为空
    public abstract boolean add(T x); // 排队
    public abstract T peek(); // 返回队列第一个元素
    public abstract T poll(); //弹出队列第一个元素
}
