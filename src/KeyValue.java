public class KeyValue<K, V> {
    protected final K key;
    public V value;
    public KeyValue(K key, V value){
        this.key = key;
        this.value = value;
    }
    public String toString(){
        return "(" + this.key + ", " + this.value + ")";
    }
    public final int hashCode(){
        return this.key.hashCode();
    }
    public boolean equals(Object obj){
        return obj == this || obj instanceof KeyValue<?, ?> && this.key.equals(((KeyValue) obj).key);
    }
}
