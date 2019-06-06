public class DoublyList_String {
    public static void main(String[] args){
        Integer[] values = { 1, 1, 1, 4, 5, 1, 1, 1, 1, 4, 5};
        DoublyList<Integer> intlist = new DoublyList<Integer>(values);
        System.out.println("Original list: " + intlist.toString());
//        Integer[] value_pattern = {1, 1, 1, 1};
//        DoublyList<Integer> intpattern = new DoublyList<Integer>(value_pattern);
//        System.out.println("Given list: " + intpattern.toString());
//        intlist.removeAll(intpattern);
//        System.out.println("Modified list: " + intlist.toString());
//        intlist = new DoublyList<Integer>(values);
        intlist.remove(0, 3);
        System.out.println("Modified List: " + intlist.toString());
    }
}
