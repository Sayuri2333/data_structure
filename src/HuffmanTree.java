public class HuffmanTree { //Huffman����
    private String charset; //�ַ�����
    private TriElement[] huftree; //��̬��������ڵ�����
    public HuffmanTree(int[] weights){ // ����Ȩ�س�ʼ��
        this.charset = ""; //��ʼ���ַ�����
        for (int i = 0; i < weights.length; i++) // Ĭ���ַ�����Ǵ�A��ʼ��weights.length���ַ�
            this.charset += (char)('A' + i); // �洢�ַ�
        int n = weights.length; //��n�洢Ȩ������ĳ���
        this.huftree = new TriElement[2*n-1]; //���ݸ��������鳤�����ɽڵ�����ĳ���
        for (int i = 0; i < n; i++) // ���ڽڵ������ǰn��λ��, �洢ԭ������������ڵ�
            this.huftree[i] = new TriElement(weights[i]); //��������ڵ��������洢����Ȩ��
        for(int i = n; i < 2 * n - 1; i++){ //����n-1�����Ƚڵ�
            int min1 = Integer.MAX_VALUE; //����Сֵ��ʼ��Ϊ���ֵ
            int min2 = min1; //ͬ���ĳ�ʼ����Сֵ
            int x1 = -1; //��ʼ����Сֵ������ֵ
            int x2 = -1; //��Сֵ����ֵ
            for(int j = 0; j < i; j++){ //Ѱ������û�и�ĸ�ľ�����СȨ�صĽڵ��±�
                if (this.huftree[j].parent == -1){ //���û�и�ĸ
                    if (this.huftree[j].data < min1){ //�����j���ڵ�Ȩ����С
                        min2 = min1; //��֮ǰ��Сֵ��ֵ������Сֵ
                        x2 = x1; //��Сֵ�±����
                        min1 = this.huftree[j].data; //������Сֵ����Сֵ�±�
                        x1 = j;
                    }else if(this.huftree[j].data < min2){ //������ֵ����Сֵ���Ǳȴ�СֵС
                        min2 = huftree[j].data; //���´�Сֵ
                        x2 = j;
                    }
                }
            }
            this.huftree[x1].parent = i; //������Сֵ�ĸ�ĸ��Ϊ��ǰ�ڵ�
            this.huftree[x2].parent = i;
            this.huftree[i] = new TriElement(min1 + min2, -1, x1, x2); //����ڵ���Ϊ��ǰ����Сֵ�ĸ�ĸ
        }
    }
    private String getCode(int i){ //����charset�ĵ�i���ַ���Huffman�����ַ���
        int n = 8;
        char[] hufcode = new char[n]; //�����ַ�������ʱ�洢Huffman����(��೤��Ϊ8)
        int child = i;
        int parent = this.huftree[child].parent; //��ǰ�ڵ��parentλ��
        for (i = n - 1; parent != -1; i--){ //��Ҷ�ӽ������ֱ�����ڵ�, ����洢�洢����
            hufcode[i] = (this.huftree[parent].left == child) ? '0': '1'; //����ǰ�ڵ���parent�����㻹���ҽ�����ж�0������1
            child = parent; // �ƶ�childָ��
            parent = huftree[child].parent; //����parentλ��
        }
        return new String(hufcode, i+1, n-1-i); // ���������ַ���
    }
    public String toString(){ //����Huffman���Ľڵ���������е��ַ��ı��봮
        String str = "Huffman ���Ľڵ�����:"; // �����������������ַ�������
        for (int i = 0; i < this.huftree.length; i++)
            str += this.huftree[i].toString() + ", ";
        str +=  "\n Huffman����: "; //�����ַ��Լ���Ӧ�ı��봮
        for (int i = 0; i < this.charset.length(); i++)
            str += this.charset.charAt(i) + ": " + getCode(i) + ", ";
        return str;
    }
    public String encode(String text){ //����ѹ��,�������ַ�ת���ɱ��뷽ʽ�洢
        String compressed = "";  //��ʼ����ѹ�����ַ���
        for (int i = 0; i < text.length(); i++){ //�������ַ�ת���ɱ����ַ�
            compressed += getCode(text.charAt(i) - 'A'); //����(��סһ��Ҫ��ȥA, �������ܱ�ʾ����ַ�����ĸ���е�λ��)
        }
        return compressed; //���ر����ַ���
    }
    public String decode(String compressed){ //�������ַ�������
        String text = ""; //��ʼ�������ַ���
        int node = this.huftree.length - 1; //node����һ���Ӹ��ڵ㵽����Ҷ�ڵ��·��
        for (int i = 0; i < compressed.length(); i++){
            if (compressed.charAt(i) == '0') //���ݱ����ַ���ָ���н�����
                node = huftree[node].left;
            else
                node = huftree[node].right;
            if (huftree[node].isLeaf()){ //���������Ҷ�ӽ��(֤���Ѿ����Խ��н���)
                text += this.charset.charAt(node); //���ݵ�ǰnode��λ�ý��н���, (��Ϊcharset����ĸ��������huftree�е���ĸ������һ��)
                node = this.huftree.length - 1; //��nodeָ�����, �Ӷ�����ָ����һ���ַ��Ľ���
            }
        }
        return text;
    }
}
