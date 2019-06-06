import java.util.Arrays;

public class KeyWords {
    private static String[] keywords = {"abstract","assert","boolean","break","byte","case","catch",
            "char","class","continue","default","do","double","else","extends","false","final","finally",
            "float","for","if","implements","import","instanceof","int","interface","long","native","new",
            "null","package","private","protected","public","return","short","static","super","switch",
            "synchronized","this","throw","throws","transient","true","try","void","volatile","while"};
    private static class IndexItem implements Comparable<IndexItem>{
        char first;
        int begin;
        int end;
        public IndexItem(char first, int begin, int end){
            this.first = first;
            this.begin = begin;
            this.end = end;
        }
        public String toString(){
            return "(" + this.first + "," + begin + "," + end + ")";
        }
        public int compareTo(IndexItem item){
            return this.first - item.first;
        }
    }
    private static IndexItem[] index;
    static {
        index = new IndexItem[23];
        for (int i = 0,j = 0; i < index.length && j < keywords.length; i++){
            char ch = (char)('a' + i);
            if (keywords[j].charAt(0) > ch)
                index[i] = new IndexItem(ch, -1, -1);
            else {
                int begin = j;
                j++;
                while (j < keywords.length && keywords[j].charAt(0) == ch)
                    j++;
                index[i] = new IndexItem(ch, begin, j-1);
            }
        }
        System.out.println("Index:[]");
        for (int i = 0; i < index.length; i++)
            System.out.print("  " + index[i]);
        System.out.println();
    }
    public static boolean isKeyWord(String str){
        int i = str.charAt(0) - 'a';
        return i >= 0 && i < index.length && index[i].begin != -1 &&
                SortedArray.binarySearch(keywords, index[i].begin, index[i].end, str) >= 0;
    }
    public static void main(String[] args){
        String[] str = {"and", "final", "length", "while", "x"};
        for (int i = 0; i < str.length; i++)
            System.out.println(str[i] + (isKeyWord(str[i]) ? "" : "²»") + "ÊÇ¹Ø¼ü×Ö");
    }
}

