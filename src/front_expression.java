public class front_expression {
    public static StringBuffer toFrontfix(String infix){
        Stack<String> stack = new LinkedStack<>();
        StringBuffer frontfix = new StringBuffer(infix.length() * 2);
        int i = infix.length() - 1;
        while (i >= 0){
            char ch = infix.charAt(i);
//            System.out.println(ch +"");
            switch (ch){
                case '+': case '-':
                    while (!stack.isEmpty() && !stack.peek().equals(")") && !stack.peek().equals("+") && !stack.peek().equals("-"))
                        frontfix.insert(0, stack.pop());
                    stack.push(ch + "");
                    i--;
                    break;
                case '*': case '/':
                    stack.push(ch + "");
                    i--;
                    break;
                case ')':
                    stack.push(ch + "");
                    i--;
                    break;
                case '(':
                    String out = stack.pop();
                    while (out != null && !out.equals(")")){
                        frontfix.insert(0, out);
                        out = stack.pop();
                    }
                    i--;
                    break;
                default:
                    while (i >= 0 && ch >= '0' && ch <= '9'){
                        frontfix.insert(0, ch + "");
                        i--;
                        if (i >= 0)
                            ch = infix.charAt(i);
                    }
                    frontfix.insert(0, " ");
            }
        }
        while(!stack.isEmpty())
            frontfix.insert(0, stack.pop());
        return frontfix;
    }
    public static double toValue(StringBuffer frontfix){
        Stack<Integer> stack = new LinkedStack<>();
        double value = 0;
        for (int i = frontfix.length() - 1; i >= 0; i--){
            char ch = frontfix.charAt(i);
            if (ch >= '0' && ch <= '9'){
                value = 0;
                double count = 0;
                while (ch != ' '){
                    value = (ch - '0') * Math.pow(10.0, count) + value;
//                    System.out.println(value + " a");
                    ch = frontfix.charAt(--i);
                    count++;
                }
//                System.out.println(value);
                stack.push((int)value);
            }else if(ch != ' '){
                int x = stack.pop();
                int y = stack.pop();
                switch (ch){
                    case '+': value = x + y;break;
                    case '-': value = x - y;break;
                    case '*': value = x * y;break;
                    case '/': value = x / y;break;
                }
                stack.push((int)value);
            }
        }
        return stack.pop();
    }
    public static void main(String[] args){
        String infix = "123+10*(45-50+20)/((35-25)*2+10)-11";
        System.out.println("Original infix: " + infix);
        StringBuffer frontfix = toFrontfix(infix);
        System.out.println(frontfix);
        System.out.println(toValue(frontfix));
    }
}
