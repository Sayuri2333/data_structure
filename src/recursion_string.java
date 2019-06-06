public class recursion_string {
    public static String reverseString(String str){
        return reverseString(str.length() - 1, str);
    }
    public static String reverseString(int i, String str){
        if (i == -1){
            return "";
        }
        String ch = str.charAt(i) + "";
        return ch + reverseString(--i, str);
    }
    public static void main(String args[]){
        String str = "asdfghjkl";
        System.out.println(reverseString(str));
    }
}
