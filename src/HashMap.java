public class HashMap<K, V> implements Map<K, V> { //散列映射
    HashSet<KeyValue<K, V>> set; //使用散列表存储散列映射

    public HashMap(int length) { //根据给定的长度创建散列映射
        this.set = new HashSet<>(length); //根据给定的长度创建散列表
    }

    public HashMap() { //创建默认长度的散列映射
        this.set = new HashSet<>();
    }

    public boolean isEmpty() { //判断是否为空
        return this.set.isEmpty(); //调用散列表的方法
    }

    public int size() { //返回元素的个数
        return this.set.size();
    }

    public String toString() { //返回描述字符串
        return this.set.toString();
    }

    public V get(K key) { //根据给定的键找对应的值
        KeyValue<K, V> find = this.set.search(new KeyValue<>(key, null)); //根据键来寻找对应的值
        return find != null ? find.value : null; //返回结果
    }

    public V put(K key, V value) { //根据给定的键和值插入键值对
        KeyValue<K, V> kv = new KeyValue<>(key, value); //根据给定的数据初始化键值对
        if (!this.set.add(kv)) //如果在散列表中找到了当前键值对的相同键
            this.set.search(kv).value = value; //修改对应键的值
        return value; //返回值
    }

    public V remove(K key) { //根据给定的值删除键值对
        return this.set.remove(new KeyValue<>(key, null)).value; //调用散列表的remove方法,返回删除的值
    }

    public void clear() { //清空散列映射
        this.set.clear();
    }

    public boolean containsKey(K key) { //根据给定的键查找是否含有对应的键值对
        return this.get(key) != null;
    }

    public Object[] values() { //输出存储的所有值
        Object[] values = new Object[this.size()]; // 新建数组存储
        Object[] keyvalues = this.set.toArray(); // 调用散列表的toArrays方法获得键值对
        for (int i = 0; i < keyvalues.length; i++) // 将键值对的存储转换为值的存储
            values[i] = ((KeyValue<?, ?>) keyvalues[i]).value;
        return values; //返回值的数组
    }

    public void printAll() {
        this.set.printAll();
    }
}