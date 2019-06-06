public class Expression {
    public static StringBuffer toPostfix(String infix){ //返回后缀表达式
        Stack<String> stack = new SeqStack<String>(infix.length()); //存储符号的栈
        StringBuffer postfix = new StringBuffer(infix.length() * 2); //输出的字符串
        int i = 0;
        while(i < infix.length()){
            char ch = infix.charAt(i);
            switch (ch){
                case '+': case '-': // 在遇到加号和减号的时候
                    while(!stack.isEmpty() && !stack.peek().equals("(")) //加减的优先度仅仅高于(, 因此只要是除(以外的都要弹出
                        postfix.append(stack.pop());
                    stack.push(ch + ""); //插入加减号 字符变成字符串
                    i++;
                    break;
                case '*': case '/': //在遇到乘除的时候
                    while (!stack.isEmpty() && (stack.peek().equals("*") || stack.peek().equals("/"))) //只有下层的乘除需要弹出
                        postfix.append(stack.pop());
                    stack.push(ch + "");
                    i++;
                    break;
                case '(':
                    stack.push(ch + ""); //左括号直接插入
                    i++;
                    break;
                case ')':
                    String out = stack.pop();
                    while (out != null && !out.equals("(")){ //见到右括号的时候不入栈 弹出上一个左括号内的所有元素并写入字符串(除左括号外)
                        postfix.append(out);
                        out = stack.pop();
                    }
                    i++;
                    break;
                default: //遇见数字的时候
                    while (i < infix.length() && ch >= '0' && ch <= '9'){ //对于读入的每个数字(读入到空格的时候停止)
                        postfix.append(ch); //写入字符串
                        i++; // 在内部处理的原因是想要在后面加上空格
                        if (i < infix.length()) //如果数值是多位的
                            ch = infix.charAt(i); //连续将其插入
                    }
                    postfix.append(" "); //使用空格来进行分割
            }
        }
        while (!stack.isEmpty()){ //将剩下的所有符号出栈
            postfix.append(stack.pop());
        }
        return postfix;
    }
    public static int toValue(StringBuffer postfix){ //计算后缀表达式的值
        Stack<Integer> stack = new LinkedStack<Integer>(); //存储数值的栈
        int value = 0; //临时用于存储计算结果以及数值的变量
        for (int i = 0; i < postfix.length(); i++){ //迭代postfix中的每一个字符
            char ch = postfix.charAt(i);
            if (ch >= '0' && ch <= '9'){ //如果是数值字符
                value = 0; //初始化
                while(ch != ' '){ //计算这个数值(在遇到空格之前)
                    value = value * 10 + ch - '0'; //这里注意一定要减去0(字符编码之间的差)
                    ch = postfix.charAt(++i); //同步更新i的值
                }
                stack.push(value); //数值入栈
            }
            else if(ch != ' '){ //对于符号
                int y = stack.pop();
                int x = stack.pop(); //取上层的两个数值
                switch (ch){
                    case '+': value = x + y;break;
                    case '-': value = x - y;break;
                    case '*': value = x * y;break;
                    case '/': value = x / y;break;
                } //进行运算
                System.out.print(x + (ch + "") + y + "=" + value + ", "); //输出计算结果
                stack.push(value); //将当前计算结果入栈
            }
        }
        return stack.pop(); //输出当前栈内的唯一一个元素
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
