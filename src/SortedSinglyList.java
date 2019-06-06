public class SortedSinglyList<T extends Comparable<? super T>> extends Singlylist<T>{
    public SortedSinglyList(){ //初始化空排序单链表
        super();
    }
    public SortedSinglyList(T[] values){ //根据数组生成排序单链表
        super(); //生成空的
        for (int i = 0; i < values.length; i++){ //遍历数组将值插入排序单链表中
            this.insert(values[i]);
        }
    }
    public SortedSinglyList(Singlylist<T> list){ //根据单链表生成排序单链表
        super(); //生成空的
        for (Node<T> p = list.head.next; p != null; p = p.next){ //对于给定的单链表中的值
            this.insert(p.data); //将其插入
        }
    }

    public Node<T> insert(T x) { //覆盖父类的插入方法
        Node<T> front = this.head; //前节点
        Node<T> p = this.head.next; //后节点
        while (p != null){ //遍历比较x与当前链表内的元素的大小
            if (x.compareTo(p.data) > 0){
                front = p;
                p = p.next;
            }else
                break;
        }
        //此时front元素应该指向比x小的元素,p应该指向比x大的元素
        //如果此时为空链表, p应该为null
        front.next = new Node<T>(x, p); //插入data为x节点的元素
        return front.next; //返回新建的节点
    }
//    public Node<T> insert(T x){
//        Node<T> p = this.head;
//        if (!this.isEmpty()){
//            while (x.compareTo(p.next.data) > 0 && p.next != null){
//                p = p.next;
//            }
//            p.next = new Node<T>(x, p.next);
//            return p.next;
//        }else
//            this.head.next = new Node<T>(x, null);
//        return this.head.next;
//    }
    public Node<T> search(T key){ //根据值搜索
        Node<T> p = this.head.next; //初始化节点
        if (! this.isEmpty()){ //判断非空
            while (p != null && key.compareTo(p.data) >= 0){ //遍历比较x与当前链表内的元素的大小
                if (key.compareTo(p.data) == 0) //判断是否等值
                    return p; //返回值
                p = p.next;//否则更新指针
            }
        }
        return null; //否则返回null
    }
    public T remove(T key){ //根据值删除
        Node<T> w = this.head; //前驱节点
        Node<T> p = this.head.next; //当前节点
        if (!this.isEmpty()){
            while (p != null && key.compareTo(p.data) > 0){ //遍历比较x与当前链表内的元素的大小
                if (key.compareTo(p.data) == 0) //判断等值
                    break; //跳出循环
                w = p;
                p = p.next; //否则更新w,p的值
            }
            w.next = p.next; //删除p
            return p.data; //返回p的data值
        }
        return null; //否则返回null
    }
}
