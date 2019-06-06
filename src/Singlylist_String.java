public class Singlylist_String {
    public static void main(String[] args){
        Integer[] values = {1, 2, 3, 4, 5};
        //Integer[] value = {1, 2, 3};
        Singlylist<Integer> intlist = new Singlylist<Integer>(values);
        //Singlylist<Integer> intlist2 = new Singlylist<Integer>(value);
        System.out.println("Original list: " + intlist.toString());
        //System.out.println("Original list2: " + intlist2.toString());
        double average = Singlylist.averageExceptMaxMin(intlist);
        Singlylist<Integer> list2 = intlist.remove(0, 3);
        System.out.println(list2);
        System.out.println(intlist);
    }
}
