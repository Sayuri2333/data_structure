public class Set<T> {
    public Object[] elements;
    public int n = 0;
    public Set(){
        elements = new Object[64];
        n = 0;
    }
    public Set(int length){
        elements = new Object[length];
        n = 0;
    }
    public boolean isEmpty(){
        return n == 0;
    }
    public int size(){
        return n;
    }
    public T search(T key){
        for (int i = 0; i < n; i++){
            if (elements[i].equals(key))
                return key;
        }
        return null;
    }
    public boolean contains(T key){
        for (int i = 0; i < n; i++){
            if (elements[i].equals(key))
                return true;
        }
        return false;
    }
    public boolean add(T x){
        if (x == null)
            return false;
        if (!this.contains(x)){
            if (this.n == this.elements.length){
                Object[] copy = this.elements;
                this.elements = new Object[this.elements.length * 2];
                for (int i = 0; i < n; i++){
                    this.elements[i] = copy[i];
                }
            }
            this.elements[n] = x;
            this.n += 1;
            return true;
        }
        return false;
    }
    public T remove(T key){
        for (int i = 0; i < n; i++){
            if (elements[i].equals(key)){
                for (int j = i; j < n; j++){
                    elements[j] = elements[j + 1];
                }
                this.n--;
                return key;
            }
        }
        return null;
    }
    public void clear(){
        this.n = 0;
    }
    public String toString(){
        String str = this.getClass().getName() + "(";
        for (int i = 0; i < n; i++){
            str += elements[i].toString();
            if (i != n - 1){
                str += ", ";
            }
        }
        return str + ")";
    }
    public boolean equals(Object obj){
        if (obj instanceof Set){
            if (obj == this)
                return true;
            else if (this.n == ((Set) obj).n){
                for (int i = 0; i < n; i++){
                    if (this.elements[i] != ((Set) obj).elements[i])
                        return false;
                }
                return true;
            }
        }
        return false;
    }
    public Object[] toArray(){
        Object[] set = new Object[this.n];
        for (int i = 0; i < n; i++){
            set[i] = this.elements[i];
        }
        return set;
    }
    public boolean containsAll(Set<T> set){
        boolean contains = true;
        for (int i = 0; i < set.n; i++){
            T key = (T)set.elements[i];
            for (int j = 0; j < this.n; j++){
                if (key.equals(elements[j])){
                    for (int k = j; k < this.n; k++){
                        elements[k] = elements[k + 1];
                    }
                    this.n--;
                    break;
                }
                contains = false;
            }
            if (!contains)
                return contains;
        }
        return contains;
    }
    public boolean addAll(Set<T> set){
        for (int i = 0; i < set.n; i++){
            this.add((T)set.elements[i]);
        }
        return true;
    }
    public boolean removeAll(Set<T> set){return false;}
    public boolean retainAll(Set<T> set){return true;}
}
