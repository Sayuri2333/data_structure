public class Expression {
    public static StringBuffer toPostfix(String infix){ //���غ�׺���ʽ
        Stack<String> stack = new SeqStack<String>(infix.length()); //�洢���ŵ�ջ
        StringBuffer postfix = new StringBuffer(infix.length() * 2); //������ַ���
        int i = 0;
        while(i < infix.length()){
            char ch = infix.charAt(i);
            switch (ch){
                case '+': case '-': // �������Ӻźͼ��ŵ�ʱ��
                    while(!stack.isEmpty() && !stack.peek().equals("(")) //�Ӽ������ȶȽ�������(, ���ֻҪ�ǳ�(����Ķ�Ҫ����
                        postfix.append(stack.pop());
                    stack.push(ch + ""); //����Ӽ��� �ַ�����ַ���
                    i++;
                    break;
                case '*': case '/': //�������˳���ʱ��
                    while (!stack.isEmpty() && (stack.peek().equals("*") || stack.peek().equals("/"))) //ֻ���²�ĳ˳���Ҫ����
                        postfix.append(stack.pop());
                    stack.push(ch + "");
                    i++;
                    break;
                case '(':
                    stack.push(ch + ""); //������ֱ�Ӳ���
                    i++;
                    break;
                case ')':
                    String out = stack.pop();
                    while (out != null && !out.equals("(")){ //���������ŵ�ʱ����ջ ������һ���������ڵ�����Ԫ�ز�д���ַ���(����������)
                        postfix.append(out);
                        out = stack.pop();
                    }
                    i++;
                    break;
                default: //�������ֵ�ʱ��
                    while (i < infix.length() && ch >= '0' && ch <= '9'){ //���ڶ����ÿ������(���뵽�ո��ʱ��ֹͣ)
                        postfix.append(ch); //д���ַ���
                        i++; // ���ڲ������ԭ������Ҫ�ں�����Ͽո�
                        if (i < infix.length()) //�����ֵ�Ƕ�λ��
                            ch = infix.charAt(i); //�����������
                    }
                    postfix.append(" "); //ʹ�ÿո������зָ�
            }
        }
        while (!stack.isEmpty()){ //��ʣ�µ����з��ų�ջ
            postfix.append(stack.pop());
        }
        return postfix;
    }
    public static int toValue(StringBuffer postfix){ //�����׺���ʽ��ֵ
        Stack<Integer> stack = new LinkedStack<Integer>(); //�洢��ֵ��ջ
        int value = 0; //��ʱ���ڴ洢�������Լ���ֵ�ı���
        for (int i = 0; i < postfix.length(); i++){ //����postfix�е�ÿһ���ַ�
            char ch = postfix.charAt(i);
            if (ch >= '0' && ch <= '9'){ //�������ֵ�ַ�
                value = 0; //��ʼ��
                while(ch != ' '){ //���������ֵ(�������ո�֮ǰ)
                    value = value * 10 + ch - '0'; //����ע��һ��Ҫ��ȥ0(�ַ�����֮��Ĳ�)
                    ch = postfix.charAt(++i); //ͬ������i��ֵ
                }
                stack.push(value); //��ֵ��ջ
            }
            else if(ch != ' '){ //���ڷ���
                int y = stack.pop();
                int x = stack.pop(); //ȡ�ϲ��������ֵ
                switch (ch){
                    case '+': value = x + y;break;
                    case '-': value = x - y;break;
                    case '*': value = x * y;break;
                    case '/': value = x / y;break;
                } //��������
                System.out.print(x + (ch + "") + y + "=" + value + ", "); //���������
                stack.push(value); //����ǰ��������ջ
            }
        }
        return stack.pop(); //�����ǰջ�ڵ�Ψһһ��Ԫ��
    }
    public static void main(String[] args){
//        String infix = "123+10*(45-50+20)/((35-25)*2+10)-11";
//        StringBuffer postfix = toPostfix(infix);
//        System.out.println("infix: " + infix + "\npostfix: " + postfix + "\nvalue: " + toValue(postfix));
        String infix = "1+((2+3)*4)-5";
        StringBuffer frontfix = toPostfix(infix);
        System.out.println(frontfix);
    }
}
