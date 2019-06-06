public class SortedArray{
    public static int count = 0;
    public static <T extends Comparable<? super T>> int binarySearch(T[] values, int begin, int end, T key){
        count = 0;
        while(begin <= end){
            int mid = (begin + end) / 2;
            System.out.print("[" + mid +"]=" + values[mid] +"?");
            count++;
            if (key.compareTo(values[mid]) == 0)
                return mid;
            if (key.compareTo(values[mid]) > 0)
                begin = mid + 1;
            else
                end = mid - 1;
        }
        return -1;
    }
    public static <T extends Comparable<? super T>> int binarySearch(T[] values, T key){
        return binarySearch(values, 0, values.length - 1, key);
    }
}
