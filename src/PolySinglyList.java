public class PolySinglyList< T extends Comparable<? super T> & Addible<T>> extends SortedSinglyList<T>{ // 存储二次项的链表
    public PolySinglyList(){
        super();
    } //制作空表
    public PolySinglyList(T[] terms){
        super(terms);
    } // 根据给定的数组生成表
    public PolySinglyList(PolySinglyList<T> list){
        super(list);
    } // 复制新表
    public void addAll(PolySinglyList<T> list){ // 表之间的相加方法
        if (!list.isEmpty() & !this.isEmpty()){ //两个表都不为空的前提下
            Node<T> p = this.head;
            Node<T> q = list.head; //初始化两个指针的位置
            while (true){ // 循环
                if (p.next != null && q.next != null){ //在两个顺序表都没有用完的前提下
                    if (p.next.data.compareTo(q.next.data) == 0){ // 如果两个指针指向的项目指数相同
                        p.next.data.add(q.next.data); //可加
                        if (p.next.data.removable()){ // 和是否为0,为0删除
                            p.next = p.next.next;
                            q = q.next;
                            continue;
                        }
                    }else if (p.next.data.compareTo(q.next.data) > 0){ //如果是本表的指数项比较大
                        p.next = new Node<T>(q.next.data,p.next); // 将q的项插入本表
                        p = p.next; // 移动本表指针(这里要移动两次,防止刚刚插入的项在下一次循环中被比较)
                    }else if (p.next.data.compareTo(q.next.data) < 0){ // 如果是给定表的指数项比较大
                        p = p.next; //本表指针移动
                        continue; //跳过下方的给定表指针移动
                    }
                }else if (p.next == null){ // 如果本表项目遍历完成
                    for (q = q; q != null; q = q.next){ //对于给定表剩下的项目
                        p.next = new Node<T>(q.data, null); //将其复制并插入本表后面
                        p = p.next;
                    }
                    break; //跳出循环
                }else if (q.next == null){ // 如果给定表遍历完成
                    break; //直接调出循环
                }
                p = p.next;
                q = q.next; //更新两个指针的位置
            }
        }else if (!list.isEmpty()){ // 如果本表为空,给定表非空
            Node<T> p = this.head;
            for (Node<T> q = list.head.next; q != null; q = q.next){ // 将给定表的所有项目复制一份过来
                p.next = new Node<T>(q.data, null);
                p = p.next;
            }
        }
    }
}
