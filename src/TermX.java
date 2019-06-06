public class TermX implements Comparable<TermX>, Addible<TermX> {  // һԪ����ʽ������
    protected int coef, xexp; // ӵ��ϵ����ָ��
    public TermX(int coef, int xexp){ // �½�һ����
        this.coef = coef;
        this.xexp = xexp;
    }
    public TermX(TermX term){ //�������е����½�һ����(���)
        this.coef = term.coef;
        this.xexp = term.xexp;
    }
    public TermX(String termstr){ // �����ı��ַ�����������
        if (termstr.indexOf("x") != -1){ //��������x�Ļ�
            if (termstr.split("x").length == 0){ // ����x���з��ַ���ʱ, ���һ�û�еĻ��϶�Ϊx
                this.coef = 1;
                this.xexp = 1;
            }
            else if (termstr.split("x").length == 1){ //�������x���ָ��ʱ��ֻ��һ��ֵ,֤�������ʹ��Ĭ��ָ��(1) nx
                this.coef = Integer.valueOf(termstr.split("x")[0]);
                this.xexp = 1;
            }else { // ������Ļ�֤���Ѿ�ʹ����ָ��
                String[] strlist = termstr.split("x\\^"); //ʹ����pattern����
                if (strlist[0].length() == 0){ // �����������,����ǰ��ĳ���Ϊ0,��ʹ��Ĭ��ϵ��(1) x^n
                    this.coef = 1;
                    this.xexp = Integer.valueOf(strlist[1]);
                }else { //������������ ax^n
                    this.coef = Integer.valueOf(strlist[0]);
                    this.xexp = Integer.valueOf(strlist[1]);
                }
            }
        }else { // ������
            this.coef = Integer.valueOf(termstr);
            this.xexp = 0;
        }
    }
    public String toString(){
        return this.coef + "x^" + this.xexp;
    } //���������ַ���
    public boolean equals(Object obj){ //�Ƚ��Ƿ����
        if (obj instanceof TermX) //��������Ķ���ΪTermX���
            return this.coef == ((TermX) obj).coef && this.xexp == ((TermX) obj).xexp; //�Ƚ�ϵ����ָ����ֵ
        return false;
    }
    public int compareTo(TermX term){
        return this.xexp - term.xexp;
    } //�Ƚ�ָ����ֵ
    public void add(TermX term){ // ��ָ��һ���Ļ��������
        if (this.xexp == term.xexp) //���������ӵ����ͬ��ָ��
            this.coef += term.coef; //ϵ�����
    }
    public boolean removable(){
        return this.coef == 0;
    } // ���ϵ��Ϊ0���ܹ�ɾ��
}
