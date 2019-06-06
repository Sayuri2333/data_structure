public class Tree_city {
    public static void main(String[] args){
        String text = "ABCDEFGH";
        int[] weights = {19, 2, 13, 5, 11, 7, 3, 17};
        HuffmanTree huffman = new HuffmanTree(weights);
        System.out.println(huffman.toString());
        String compressed = huffman.encode(text);
        System.out.println(compressed);
    }
}
