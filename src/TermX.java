public class TermX implements Comparable<TermX>, Addible<TermX> {  // 一元多项式的项类
    protected int coef, xexp; // 拥有系数和指数
    public TermX(int coef, int xexp){ // 新建一个项
        this.coef = coef;
        this.xexp = xexp;
    }
    public TermX(TermX term){ //根据已有的项新建一个项(深拷贝)
        this.coef = term.coef;
        this.xexp = term.xexp;
    }
    public TermX(String termstr){ // 根据文本字符串来生成项
        if (termstr.indexOf("x") != -1){ //如果此项含有x的话
            if (termstr.split("x").length == 0){ // 根据x来切分字符串时, 如果一项都没有的话肯定为x
                this.coef = 1;
                this.xexp = 1;
            }
            else if (termstr.split("x").length == 1){ //如果根据x来分割的时候只有一个值,证明这个项使用默认指数(1) nx
                this.coef = Integer.valueOf(termstr.split("x")[0]);
                this.xexp = 1;
            }else { // 有两项的话证明已经使用了指数
                String[] strlist = termstr.split("x\\^"); //使用新pattern划分
                if (strlist[0].length() == 0){ // 如果有两个项,但是前项的长度为0,即使用默认系数(1) x^n
                    this.coef = 1;
                    this.xexp = Integer.valueOf(strlist[1]);
                }else { //否则正常操作 ax^n
                    this.coef = Integer.valueOf(strlist[0]);
                    this.xexp = Integer.valueOf(strlist[1]);
                }
            }
        }else { // 常数项
            this.coef = Integer.valueOf(termstr);
            this.xexp = 0;
        }
    }
    public String toString(){
        return this.coef + "x^" + this.xexp;
    } //返回描述字符串
    public boolean equals(Object obj){ //比较是否相等
        if (obj instanceof TermX) //如果给定的对象为TermX类的
            return this.coef == ((TermX) obj).coef && this.xexp == ((TermX) obj).xexp; //比较系数和指数的值
        return false;
    }
    public int compareTo(TermX term){
        return this.xexp - term.xexp;
    } //比较指数的值
    public void add(TermX term){ // 在指数一样的基础上相加
        if (this.xexp == term.xexp) //如果两个项拥有相同的指数
            this.coef += term.coef; //系数相加
    }
    public boolean removable(){
        return this.coef == 0;
    } // 如果系数为0则能够删除
}
