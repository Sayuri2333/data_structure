public interface Map<K, V>{
    public abstract boolean isEmpty();
    public abstract int size();
    public abstract String toString();
    public abstract V get(K key);
    public abstract V put(K key, V value);
    public abstract V remove(K key);
    public abstract boolean containsKey(K key);
    public abstract void clear();
    public abstract Object[] values();
}
