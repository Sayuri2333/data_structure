import java.util.Arrays;

public class Array9 {
    // ����������㷨:
    // ����һ��Ԫ�ر��Ϊ������
    //
    //����ÿ��û���������Ԫ��
    //
    //  ����ȡ�� λ��δ�������ĵ�һ��Ԫ�� X
    //
    //  i = ��������Ԫ�ص�ָ�� �� 0 �ı���
    //
    //    ��������������Ԫ�� > ��ȡ��Ԫ��
    //
    //      ���������Ԫ��������һ��
    //
    //    ���򣺲�����ȡ��Ԫ��
    public static void insertSort(int[] keys){ // ֱ�Ӳ�������
        System.out.println("ֱ�Ӳ�����������");
        for (int i = 1; i < keys.length; i++){ //n-1��ɨ��,������ǰ����n-1����
            int temp = keys[i], j; //ʹ��temp�洢��ǰ��Ҫ���������Ԫ��
            for (j = i-1; j >= 0 && temp < keys[j]; j--){ //������������Ԫ�ص�0��λ�õ�Ԫ�ؽ��б���,�����ǰ�������Ԫ�ش������ڽ��������Ԫ��
                keys[j + 1] = keys[j]; //���������Ԫ������һ��
            }
            keys[j + 1] = temp; //���������Ҫ�����Ԫ��(ע������λ��)
            System.out.print("��"+i+"�� temp="+temp+"\t");
            Array1.print(keys);
        }
    }
    public static <T extends Comparable<? super T>> void insertSort(T[] value){ //��������Ĳ�������
        System.out.println("ֱ�Ӳ�����������");
        for (int i = 1; i < value.length; i++){
            T temp = value[i];
            int j;
            for (j = i - 1; j > 0 && temp.compareTo(value[j]) < 0; j--){
                value[j + 1] = value[j];
            }
            value[j + 1] = temp;
            System.out.print("��"+i+"��: ");
            Array1.print(value);
        }
    }
    public static void shellSort(int[] keys){ //ϣ������
        System.out.println("ϣ����������");
        for (int delta = keys.length / 2; delta > 0; delta /= 2){ //�����ˣ���������ÿ�˼���(ֱ������Ϊ1)
            for (int i = delta; i < keys.length; i++){ //����delta�����õķ���,�ӵ�һ��ĵڶ���Ԫ�ؿ�ʼ��������,ÿһ����ͬ����������
                int temp = keys[i], j;
                for (j = i - delta; j >= 0 && temp < keys[j]; j -= delta)
                    keys[j + delta] = keys[j];
                keys[j + delta] = temp;
            }
            System.out.print("delta = " + delta + " ");
            Array1.print(keys);
        }
//        //Ӧ��Ҳ������ôд(�����ǲ�ͬ��������ÿ�����Ԫ��,������������ٽ�����һ���������)
//        for(int delta = keys.length / 2; delta > 0; delta /= 2){ //���ɴ�,ֱ��delta��ֵΪ1
//            int group = keys.length / delta + 1; //����delta��ֵ�����������
//            for (int temp = 1; temp <= group; temp++){ // ����ÿһ������в�������
//                for (int i = delta + (temp - 1); i < keys.length; i += delta){ //������ĳ��Ⱦ���ɨ��Ĵ���
//                    int change = keys[i], j; //ʹ��change�洢���Ǹ�Ǯ��Ҫ���������Ԫ��
//                    for (j = i - delta; j >= 0 && change < keys[j]; j -= delta) // ������������Ԫ�ص���һ��Ԫ�ؽ��б�����,�����ǰ�������Ԫ�ش������ڽ��������Ԫ��
//                        keys[j + delta] = keys[j]; //����һ���������Ԫ��
//                    keys[j + delta] = change; // ���������Ҫ���������Ԫ��(ע������λ��)
//                }
//            }
//        }
    }
    // ð�������㷨:
    //do
    //
    //  �������� = false
    //
    //  �� i = 1 �� ���һ��û�������Ԫ�ص�ָ��
    //
    //    ��� ���Ԫ�� > �ұ�Ԫ��
    //
    //      ���������Ԫ�أ��ұ�Ԫ�أ�
    //
    //      �������ı��� = true
    //
    //while �������ı���
    private static void swap(int[] keys, int i, int j){ //����ָ��������ָ������Ԫ�ص�λ��
        if (i < keys.length && j < keys.length){
            int temp = keys[j];
            keys[j] = keys[i];
            keys[i] = temp;
        }
    }
    public static void bubbleSort(int[] keys){ //ð������(����)
        bubbleSort(keys, true);
    }
    public static void bubbleSort(int[] keys, boolean asc){ //ð������
        System.out.println("ð������"+(asc?"��":"��")+"��");
        boolean exchange = true; //��ʼ����������
        for (int i = 1; i < keys.length && exchange; i++){ //�ܹ���ô��������(���һ��Ԫ�ؾ�������С�����Բ�������)
            exchange = false; //�����ý�������Ϊfalse
            for (int j = 0; j < keys.length - i; j++){ //����һ�˱Ƚ�,�����ӵ�һ��Ԫ�ص����һ��û���������Ԫ��(����������)
                if (asc ? keys[j] > keys[j + 1] : keys[j] < keys[j + 1]){ //����Ԫ�رȽ�
                        swap(keys, i, j); //�������������򽻻�
                        exchange = true; //���ý�������Ϊtrue��ʾ���й�����
                }
            }
            System.out.print("��"+i+"�ˣ��±�0��"+(keys.length-i)+"��");
            Array1.print(keys);
        }
    }
    // ���������㷨:
    //ÿ����δ���򣩵Ĳ���
    //
    //����һ��Ԫ����Ϊ���ĵ�
    //
    //  �洢ָ�� = ���ĵ�ָ�� +1
    //
    //  �� i=���ĵ�ָ�� +1 �� ����ָ�� �ı��� (�����С������ʣ�µĲ��־��Ǵ󲿷���)
    //
    //    ��� Ԫ��[i] < Ԫ��[���ĵ�]
    //
    //      ����(i, �洢ָ��);
    //
    //      �洢ָ��++
    //
    //  ����(���ĵ�, �洢ָ�� - 1)
    public static void quickSort(int[] keys){
        System.out.println("������������");
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
            System.out.print("�±�"+begin+"��"+end+"�� vot="+vot+"��  ");
            Array1.print(keys);
            quickSort(keys, begin, j - 1);
            quickSort(keys, i + 1, end);
        }
    }
    //Ӧ��Ҳ������ôд
    private static void quickSort(int[] keys, int begin, int end){
        if (begin >= 0 && begin < keys.length && end >= 0 && end < keys.length && begin < end){
            int center = keys[begin], i; //������ĵ��ֵ
            int small = begin + 1; //��ʼ��С�������Ĵ洢����ָ��(ָ�봦������)
            for (i = begin + 1; i <= end; i++){ //���������ĵ�֮�������
                if (keys[i] < center) //�����ǰԪ�ر����ĵ�ҪС
                    swap(keys, i, small++); //������ǰԪ�غʹ洢����ָ�봦��Ԫ��
            }
            swap(keys, begin, small - 1); //����С���������һ��Ԫ�غ����ĵ��λ��
            //�ݹ�
            quickSort(keys, begin, small - 2);
            quickSort(keys, small, end);
        }
    }
    // ѡ�������㷨:
    //�ظ���Ԫ�ظ���-1����
    //
    //  �ѵ�һ��û���������Ԫ������Ϊ��Сֵ
    //
    //  ����ÿ��û���������Ԫ��
    //
    //    ���Ԫ�� < ���ڵ���Сֵ
    //
    //      ����Ԫ�����ó�Ϊ�µ���Сֵ
    //
    //  ����Сֵ�͵�һ��û���������λ�ý���
    public static void selectSort(int[] keys){ //ѡ������
        System.out.println("ֱ��ѡ����������");
        for (int i = 0; i < keys.length - 1; i++){ //������һ��Ԫ�ص������ڶ���Ԫ��Ѱ����Сֵ
            int min = i;
            for (int j = i + 1; j < keys.length; j++){
                if (keys[j] < keys[min])
                    min = j;
            } //Ѱ�ҵ���Сֵ���±�
            System.out.print("��"+(i+1)+"�ˣ��±�"+i+"��"+(keys.length-1)+"��min="+min+"��");
            if (min != i) //������Сֵ�͵�ǰδ�������ĵ�һ��Ԫ�ص�λ��
                swap(keys, i, min);
            Array1.print(keys);
        }
    }
    //������
    public static void heapSort(int[] keys)
    {
        heapSort(keys,true);
    }
    public static void heapSort(int[] keys, boolean asc) //������,����asc��������С�ѻ�������
    {
        for (int i=keys.length/2-1; i>=0; i--) //������С/���
            sift(keys, i, keys.length-1, !asc);
        System.out.print("��"+((!asc)?"С":"��")+"�ѣ�");
        Array1.print(keys);
        System.out.println("�ǵݹ��㷨����С�ѣ� "+isHeap(keys,true)+"�����ѣ� "+isHeap(keys,false));
        System.out.print("������"+((!asc)?"��":"��")+"�򣩣�");
        for (int i=keys.length-1; i>0; i--)
        {
            swap(keys, 0, i);
            sift(keys, 0, i-1, !asc);
        }
        Array1.print(keys);
    }
    //��keys��������parentΪ����������������С/��ѣ������з�ΧΪparent��end��
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
    // �鲢�����㷨:
    //��ÿ��Ԫ�ز�ֳɴ�СΪ1�Ĳ���
    //
    //�ݹ�ĺϲ����ڵĲ����
    //
    //  i = ��࿪ʼ��ָ�� �� �Ҳ������ָ�� �ı��������˰�����
    //
    //    ��������ֵ <= �Ҳ���ֵ
    //
    //      ������������ֵ
    //
    //    ���� �����Ҳಿ����ֵ
    //
    //��Ԫ�ؿ�����ԭ����������
    public static void mergeSort(int[] X){
        System.out.println("�鲢��������");
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
    //һ�˹鲢����X���������������������鲢��Y�У������г���Ϊn
    private static void mergepass(int[] X, int[] Y, int n)
    {
        System.out.print("�����г���n="+n+"  ");
        for (int i=0;  i<X.length;  i+=2*n) //�������е������ж�
            merge(X, Y, i, i+n, n); //�˷������������������ڵ�������
        Array1.print(Y);
    }
    //��X�зֱ���begin1��begin2��ʼ���������������й鲢�����򣩵�Y�У������г���Ϊn
    private static void merge(int[] X, int[] Y, int begin1, int begin2, int n)
    {
        int i=begin1, j=begin2, k=begin1;
        while (i<begin1+n && j<begin2+n && j<X.length) //���������ڵ������й鲢��Y��
            if (X[i]<X[j]) //����С��ֵ�ȸ��Ƶ�Y��
                Y[k++]=X[i++];
            else
                Y[k++]=X[j++];
            //���ʱ��������һ�������Ѿ��������
        while (i<begin1+n && i<X.length) //����һ�����е�ʣ�²���ֱ�Ӳ���
            Y[k++]=X[i++];
        while (j<begin2+n && j<X.length)
            Y[k++]=X[j++];
    }
}
