import java.util.Arrays;

public class Array9 {
    // 插入排序的算法:
    // 将第一个元素标记为已排序
    //
    //遍历每个没有排序过的元素
    //
    //  “提取” 位于未排序区的第一个元素 X
    //
    //  i = 最后排序过元素的指数 到 0 的遍历
    //
    //    如果现在排序过的元素 > 提取的元素
    //
    //      将排序过的元素向右移一格
    //
    //    否则：插入提取的元素
    public static void insertSort(int[] keys){ // 直接插入排序
        System.out.println("直接插入排序（升序）");
        for (int i = 1; i < keys.length; i++){ //n-1次扫描,依次向前插入n-1个数
            int temp = keys[i], j; //使用temp存储当前需要进行排序的元素
            for (j = i-1; j >= 0 && temp < keys[j]; j--){ //从最后排序过的元素到0号位置的元素进行遍历,如果当前排序过的元素大于现在进行排序的元素
                keys[j + 1] = keys[j]; //将排序过的元素右移一格
            }
            keys[j + 1] = temp; //否则插入需要排序的元素(注意插入的位置)
            System.out.print("第"+i+"趟 temp="+temp+"\t");
            Array1.print(keys);
        }
    }
    public static <T extends Comparable<? super T>> void insertSort(T[] value){ //对象数组的插入排序
        System.out.println("直接插入排序（升序）");
        for (int i = 1; i < value.length; i++){
            T temp = value[i];
            int j;
            for (j = i - 1; j > 0 && temp.compareTo(value[j]) < 0; j--){
                value[j + 1] = value[j];
            }
            value[j + 1] = temp;
            System.out.print("第"+i+"趟: ");
            Array1.print(value);
        }
    }
    public static void shellSort(int[] keys){ //希尔排序
        System.out.println("希尔排序（升序）");
        for (int delta = keys.length / 2; delta > 0; delta /= 2){ //若干趟，控制增量每趟减半(直到增量为1)
            for (int i = delta; i < keys.length; i++){ //根据delta所设置的分组,从第一组的第二个元素开始插入排序,每一个组同步进行排序
                int temp = keys[i], j;
                for (j = i - delta; j >= 0 && temp < keys[j]; j -= delta)
                    keys[j + delta] = keys[j];
                keys[j + delta] = temp;
            }
            System.out.print("delta = " + delta + " ");
            Array1.print(keys);
        }
//        //应该也可以这么写(这样是不同步的排序每个组的元素,排序完这个组再进行下一个组的排序)
//        for(int delta = keys.length / 2; delta > 0; delta /= 2){ //若干次,直到delta的值为1
//            int group = keys.length / delta + 1; //根据delta的值计算组的数量
//            for (int temp = 1; temp <= group; temp++){ // 对于每一个组进行插入排序
//                for (int i = delta + (temp - 1); i < keys.length; i += delta){ //根据组的长度决定扫描的次数
//                    int change = keys[i], j; //使用change存储的那个钱需要进行排序的元素
//                    for (j = i - delta; j >= 0 && change < keys[j]; j -= delta) // 从最后排序过的元素到第一个元素进行比那里,如果当前排序过的元素大于现在进行排序的元素
//                        keys[j + delta] = keys[j]; //右移一个排序过的元素
//                    keys[j + delta] = change; // 否则插入需要进行排序的元素(注意插入的位置)
//                }
//            }
//        }
    }
    // 冒泡排序算法:
    //do
    //
    //  交换变量 = false
    //
    //  从 i = 1 到 最后一个没有排序过元素的指数
    //
    //    如果 左边元素 > 右边元素
    //
    //      交换（左边元素，右边元素）
    //
    //      交换旗帜变量 = true
    //
    //while 交换旗帜变量
    private static void swap(int[] keys, int i, int j){ //交换指定数组中指定两个元素的位置
        if (i < keys.length && j < keys.length){
            int temp = keys[j];
            keys[j] = keys[i];
            keys[i] = temp;
        }
    }
    public static void bubbleSort(int[] keys){ //冒泡排序(升序)
        bubbleSort(keys, true);
    }
    public static void bubbleSort(int[] keys, boolean asc){ //冒泡排序
        System.out.println("冒泡排序（"+(asc?"升":"降")+"序）");
        boolean exchange = true; //初始化交换变量
        for (int i = 1; i < keys.length && exchange; i++){ //总共这么多趟排序(最后一个元素绝对是最小的所以不用排序)
            exchange = false; //先设置交换变量为false
            for (int j = 0; j < keys.length - i; j++){ //进行一趟比较,遍历从第一个元素到最后一个没有排序过的元素(遍历无序区)
                if (asc ? keys[j] > keys[j + 1] : keys[j] < keys[j + 1]){ //向零元素比较
                        swap(keys, i, j); //若不满足序向则交换
                        exchange = true; //设置交换变量为true表示进行过交换
                }
            }
            System.out.print("第"+i+"趟，下标0～"+(keys.length-i)+"，");
            Array1.print(keys);
        }
    }
    // 快速排序算法:
    //每个（未排序）的部分
    //
    //将第一个元素设为轴心点
    //
    //  存储指数 = 轴心点指数 +1
    //
    //  从 i=轴心点指数 +1 到 最右指数 的遍历 (整理好小部分区剩下的部分就是大部分区)
    //
    //    如果 元素[i] < 元素[轴心点]
    //
    //      交换(i, 存储指数);
    //
    //      存储指数++
    //
    //  交换(轴心点, 存储指数 - 1)
    public static void quickSort(int[] keys){
        System.out.println("快速排序（升序）");
        quickSort(keys, 0, keys.length - 1);
    }
    private static void quickSort(int[] keys, int begin, int end){
        if (begin >= 0 && begin < keys.length && end >= 0 && end < keys.length && begin < end){
            int i = begin, j = end;
            int vot = keys[i];
            while (i != j){
                while (i < j && keys[j] >= vot)
                    j--;
                if (i < j)
                    keys[i++] = keys[j];
                while (i < j && keys[i] <= vot)
                    i++;
                if (i < j)
                    keys[j--] = keys[i];
            }
            keys[i] = vot;
            System.out.print("下标"+begin+"～"+end+"， vot="+vot+"，  ");
            Array1.print(keys);
            quickSort(keys, begin, j - 1);
            quickSort(keys, i + 1, end);
        }
    }
    //应该也可以这么写
    private static void quickSort(int[] keys, int begin, int end){
        if (begin >= 0 && begin < keys.length && end >= 0 && end < keys.length && begin < end){
            int center = keys[begin], i; //获得轴心点的值
            int small = begin + 1; //初始化小部分区的存储区域指针(指针处不包括)
            for (i = begin + 1; i <= end; i++){ //遍历除轴心点之外的数组
                if (keys[i] < center) //如果当前元素比轴心点要小
                    swap(keys, i, small++); //交换当前元素和存储区域指针处的元素
            }
            swap(keys, begin, small - 1); //交换小部分区最后一个元素和轴心点的位置
            //递归
            quickSort(keys, begin, small - 2);
            quickSort(keys, small, end);
        }
    }
    // 选择排序算法:
    //重复（元素个数-1）次
    //
    //  把第一个没有排序过的元素设置为最小值
    //
    //  遍历每个没有排序过的元素
    //
    //    如果元素 < 现在的最小值
    //
    //      将此元素设置成为新的最小值
    //
    //  将最小值和第一个没有排序过的位置交换
    public static void selectSort(int[] keys){ //选择排序
        System.out.println("直接选择排序（升序）");
        for (int i = 0; i < keys.length - 1; i++){ //遍历第一个元素到倒数第二个元素寻找最小值
            int min = i;
            for (int j = i + 1; j < keys.length; j++){
                if (keys[j] < keys[min])
                    min = j;
            } //寻找到最小值的下标
            System.out.print("第"+(i+1)+"趟，下标"+i+"～"+(keys.length-1)+"，min="+min+"，");
            if (min != i) //交换最小值和当前未排序区的第一个元素的位置
                swap(keys, i, min);
            Array1.print(keys);
        }
    }
    //堆排序
    public static void heapSort(int[] keys)
    {
        heapSort(keys,true);
    }
    public static void heapSort(int[] keys, boolean asc) //堆排序,根据asc决定是最小堆还是最大堆
    {
        for (int i=keys.length/2-1; i>=0; i--) //创建最小/大堆
            sift(keys, i, keys.length-1, !asc);
        System.out.print("最"+((!asc)?"小":"大")+"堆：");
        Array1.print(keys);
        System.out.println("非递归算法，最小堆？ "+isHeap(keys,true)+"，最大堆？ "+isHeap(keys,false));
        System.out.print("堆排序（"+((!asc)?"降":"升")+"序）：");
        for (int i=keys.length-1; i>0; i--)
        {
            swap(keys, 0, i);
            sift(keys, 0, i-1, !asc);
        }
        Array1.print(keys);
    }
    //将keys数组中以parent为根的子树调整成最小/大堆，子序列范围为parent～end。
    private static void sift(int[] keys, int parent, int end, boolean minheap)
    {
        int child=2*parent+1;
        int value=keys[parent];
        while (child<=end)
        {
            if (child<end && (minheap ? keys[child]>keys[child+1] : keys[child]<keys[child+1]))
                child++;
            if (minheap ? value>keys[child] : value<keys[child])
            {   keys[parent] = keys[child];
                parent = child;
                child = 2*parent+1;
            }
            else break;
        }
        keys[parent] = value;
    }
    public static boolean isHeap(int[] value, boolean minheap)
    {
        if (value.length==0)
            return false;
        for (int i=value.length/2-1; i>=0; i--)
        {
            int left=2*i+1;
            if (minheap ? (value[i]>value[left] || left+1<value.length && value[i]>value[left+1])
                    : (value[i]<value[left] || left+1<value.length && value[i]<value[left+1]))
                return false;
        }
        return true;
    }
    // 归并排序算法:
    //将每个元素拆分成大小为1的部分
    //
    //递归的合并相邻的拆分项
    //
    //  i = 左侧开始项指数 到 右侧最后项指数 的遍历（两端包括）
    //
    //    如果左侧首值 <= 右侧首值
    //
    //      拷贝左侧首项的值
    //
    //    否则： 拷贝右侧部分首值
    //
    //将元素拷贝进原来的数组中
    public static void mergeSort(int[] X){
        System.out.println("归并排序（升序）");
        int[] Y = new int[X.length];
        int n=1;
        while (n<X.length)
        {
            mergepass(X, Y, n);
            n*=2;
            if (n<X.length)
            {
                mergepass(Y, X, n);
                n*=2;
            }
        }
    }
    //一趟归并，将X中若干相邻子序列两两归并到Y中，子序列长度为n
    private static void mergepass(int[] X, int[] Y, int n)
    {
        System.out.print("子序列长度n="+n+"  ");
        for (int i=0;  i<X.length;  i+=2*n) //遍历所有的子序列对
            merge(X, Y, i, i+n, n); //此方法仅仅处理两个相邻的子序列
        Array1.print(Y);
    }
    //将X中分别以begin1、begin2开始的两个相邻子序列归并（升序）到Y中，子序列长度为n
    private static void merge(int[] X, int[] Y, int begin1, int begin2, int n)
    {
        int i=begin1, j=begin2, k=begin1;
        while (i<begin1+n && j<begin2+n && j<X.length) //将两个相邻的子序列归并到Y中
            if (X[i]<X[j]) //将较小的值先复制到Y中
                Y[k++]=X[i++];
            else
                Y[k++]=X[j++];
            //这个时候起码有一个序列已经插入完毕
        while (i<begin1+n && i<X.length) //将另一个序列的剩下部分直接插入
            Y[k++]=X[i++];
        while (j<begin2+n && j<X.length)
            Y[k++]=X[j++];
    }
}
