public class HuffmanTree { //Huffman树类
    private String charset; //字符集合
    private TriElement[] huftree; //静态三叉链表节点数组
    public HuffmanTree(int[] weights){ // 根据权重初始化
        this.charset = ""; //初始化字符集合
        for (int i = 0; i < weights.length; i++) // 默认字符结合是从A开始的weights.length个字符
            this.charset += (char)('A' + i); // 存储字符
        int n = weights.length; //用n存储权重数组的长度
        this.huftree = new TriElement[2*n-1]; //根据给定的数组长度生成节点数组的长度
        for (int i = 0; i < n; i++) // 对于节点数组的前n个位置, 存储原来的三叉链表节点
            this.huftree[i] = new TriElement(weights[i]); //三叉链表节点的数据域存储的是权重
        for(int i = n; i < 2 * n - 1; i++){ //构造n-1个二度节点
            int min1 = Integer.MAX_VALUE; //将最小值初始化为最大值
            int min2 = min1; //同样的初始化次小值
            int x1 = -1; //初始化最小值的索引值
            int x2 = -1; //次小值索引值
            for(int j = 0; j < i; j++){ //寻找两个没有父母的具有最小权重的节点下标
                if (this.huftree[j].parent == -1){ //如果没有父母
                    if (this.huftree[j].data < min1){ //如果第j个节点权重最小
                        min2 = min1; //将之前最小值的值记做次小值
                        x2 = x1; //次小值下标更新
                        min1 = this.huftree[j].data; //更新最小值和最小值下标
                        x1 = j;
                    }else if(this.huftree[j].data < min2){ //如果这个值比最小值大但是比次小值小
                        min2 = huftree[j].data; //更新次小值
                        x2 = j;
                    }
                }
            }
            this.huftree[x1].parent = i; //将两个小值的父母设为当前节点
            this.huftree[x2].parent = i;
            this.huftree[i] = new TriElement(min1 + min2, -1, x1, x2); //构造节点作为当前两个小值的父母
        }
    }
    private String getCode(int i){ //返回charset的第i个字符的Huffman描述字符串
        int n = 8;
        char[] hufcode = new char[n]; //声明字符数组暂时存储Huffman编码(最多长度为8)
        int child = i;
        int parent = this.huftree[child].parent; //求当前节点的parent位置
        for (i = n - 1; parent != -1; i--){ //由叶子结点向上直到根节点, 反序存储存储编码
            hufcode[i] = (this.huftree[parent].left == child) ? '0': '1'; //看当前节点是parent的左结点还是右结点来判断0或者是1
            child = parent; // 移动child指针
            parent = huftree[child].parent; //更新parent位置
        }
        return new String(hufcode, i+1, n-1-i); // 返回描述字符串
    }
    public String toString(){ //返回Huffman树的节点数组和所有的字符的编码串
        String str = "Huffman 树的节点数组:"; // 返回整个树的描述字符串数组
        for (int i = 0; i < this.huftree.length; i++)
            str += this.huftree[i].toString() + ", ";
        str +=  "\n Huffman编码: "; //返回字符以及对应的编码串
        for (int i = 0; i < this.charset.length(); i++)
            str += this.charset.charAt(i) + ": " + getCode(i) + ", ";
        return str;
    }
    public String encode(String text){ //数据压缩,将各个字符转换成编码方式存储
        String compressed = "";  //初始化被压缩的字符串
        for (int i = 0; i < text.length(); i++){ //将各个字符转换成编码字符
            compressed += getCode(text.charAt(i) - 'A'); //编码(记住一定要减去A, 这样才能表示这个字符在字母表中的位置)
        }
        return compressed; //返回编码字符串
    }
    public String decode(String compressed){ //将编码字符串解码
        String text = ""; //初始化解码字符串
        int node = this.huftree.length - 1; //node搜索一条从根节点到各个叶节点的路径
        for (int i = 0; i < compressed.length(); i++){
            if (compressed.charAt(i) == '0') //根据编码字符串指导行进方向
                node = huftree[node].left;
            else
                node = huftree[node].right;
            if (huftree[node].isLeaf()){ //如果到达了叶子结点(证明已经可以进行解码)
                text += this.charset.charAt(node); //根据当前node的位置进行解码, (因为charset中字母表的排序和huftree中的字母的排序一致)
                node = this.huftree.length - 1; //将node指针归零, 从而继续指导下一个字符的解码
            }
        }
        return text;
    }
}
