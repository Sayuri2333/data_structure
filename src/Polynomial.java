public class Polynomial {
    private PolySinglyList<TermX> list;
    public Polynomial(){
        this.list = new PolySinglyList<TermX>();
    }
    public Polynomial(TermX term[]){
        this.list = new PolySinglyList<>(term);
    }
    public Polynomial(String polystr){
        this();
        String[] tlist = polystr.split("\\+");
        for (int i = 0; i < tlist.length; i++){
        }
        for (int i = 0; i < tlist.length; i++){
            TermX term = new TermX(tlist[i]);
            this.list.insert(term);
        }
    }
    public String toString(){
        String str = this.getClass().getName() + "(";
        if (!this.list.isEmpty()){
            for (Node<TermX> p = this.list.head.next; p != null; p = p.next){
                str += p.data.toString();
                if (p.next != null)
                    str += ", ";
            }
            return str + ")";
        }
        return null;
    }
    public Polynomial(Polynomial poly){
        this();
        Node<TermX> rear = this.list.head;
        for (Node<TermX> p = poly.list.head.next; p != null; p = p.next){
            rear.next = new Node<TermX>(new TermX(p.data), null);
            rear = rear.next;
        }
    }
    public void addAll(Polynomial poly){
        this.list.addAll(poly.list);
    }
    public Polynomial union(Polynomial poly){
        Polynomial cpoly = new Polynomial(this);
        cpoly.addAll(poly);
        return cpoly;
    }
    public boolean equals(Object obj){
        if (obj instanceof Polynomial){
            if (this.list == ((Polynomial) obj).list){
                return true;
            }else if (this.list.size() == ((Polynomial) obj).list.size()){
                Node<TermX> p;
                Node<TermX> q = ((Polynomial) obj).list.head.next;
                for (p = this.list.head.next; p != null; p = p.next){
                    if (!p.data.equals(q.data))
                        return false;
                }
                return true;
            }else
                return false;
        }else
            return false;
    }
}
