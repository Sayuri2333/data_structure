public class Polynomial_ex {
    public static void main(String[] args){
        TermX[] terms = {new TermX(-7, 9), new TermX(2, 7), new TermX(-9, 4), new TermX(1, 2), new TermX(-1, 1), new TermX(2, 0)};
        Polynomial apoly = new Polynomial(terms);
        Polynomial bpoly = new Polynomial("-1+x+-1x^2+10x^4+-3x^8+5x^10+9x^11");
        Polynomial cpoly = apoly.union(bpoly);
        System.out.println("A=" + apoly.toString() + "\nB=" + bpoly.toString());
        System.out.println("C=A+B, C=" + cpoly.toString());
    }
}
