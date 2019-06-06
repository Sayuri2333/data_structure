//import org.w3c.dom.DOMStringList;
//
//public class Seqlist_String {
//    public static void main(String[] args){
//        String str = "x^2";
//        String[] strlist = str.split("x\\^");
//        System.out.println(strlist[0].length());
//    }
//    public static SortedSeqlist<Integer> createPrime(int max){
//        Integer[] numbers = new Integer[max - 1];
//        for (int i = 0; i < max - 1; i++){
//            numbers[i] = i + 2;
//        }
//        SortedSeqlist<Integer> list = new SortedSeqlist<Integer>(numbers);
//        System.out.println(list.toString());
//        for (int i = 1; i < list.size(); i++){
//            Integer number = (Integer)list.get(i);
//            for (int j = 2; j <= Math.sqrt(number); j++){
//                if (number % j == 0){
//                    list.remove(i);
//                    i--;
//                    break;
//                }
//            }
//        }
//        return list;
//    }
//    public static SortedSeqlist<Integer> createPrime2(int max){
//        SortedSeqlist<Integer> list = new SortedSeqlist<>(max);
//        if (max >= 2){
//            for (int i = 2; i <= max; i++){
//                if (i == 2)
//                    list.insert(i);
//                else {
//                    for (int j = 0; j < list.size(); j++) {
//                        if (i % list.get(j) == 0)
//                            break;
//                        if (j == list.size() - 1)
//                            list.insert(i);
//                    }
//                }
//            }
//            return list;
//        }
//        return null;
//    }
//}
