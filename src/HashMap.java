public class HashMap<K, V> implements Map<K, V> { //ɢ��ӳ��
    HashSet<KeyValue<K, V>> set; //ʹ��ɢ�б�洢ɢ��ӳ��

    public HashMap(int length) { //���ݸ����ĳ��ȴ���ɢ��ӳ��
        this.set = new HashSet<>(length); //���ݸ����ĳ��ȴ���ɢ�б�
    }

    public HashMap() { //����Ĭ�ϳ��ȵ�ɢ��ӳ��
        this.set = new HashSet<>();
    }

    public boolean isEmpty() { //�ж��Ƿ�Ϊ��
        return this.set.isEmpty(); //����ɢ�б�ķ���
    }

    public int size() { //����Ԫ�صĸ���
        return this.set.size();
    }

    public String toString() { //���������ַ���
        return this.set.toString();
    }

    public V get(K key) { //���ݸ����ļ��Ҷ�Ӧ��ֵ
        KeyValue<K, V> find = this.set.search(new KeyValue<>(key, null)); //���ݼ���Ѱ�Ҷ�Ӧ��ֵ
        return find != null ? find.value : null; //���ؽ��
    }

    public V put(K key, V value) { //���ݸ����ļ���ֵ�����ֵ��
        KeyValue<K, V> kv = new KeyValue<>(key, value); //���ݸ��������ݳ�ʼ����ֵ��
        if (!this.set.add(kv)) //�����ɢ�б����ҵ��˵�ǰ��ֵ�Ե���ͬ��
            this.set.search(kv).value = value; //�޸Ķ�Ӧ����ֵ
        return value; //����ֵ
    }

    public V remove(K key) { //���ݸ�����ֵɾ����ֵ��
        return this.set.remove(new KeyValue<>(key, null)).value; //����ɢ�б��remove����,����ɾ����ֵ
    }

    public void clear() { //���ɢ��ӳ��
        this.set.clear();
    }

    public boolean containsKey(K key) { //���ݸ����ļ������Ƿ��ж�Ӧ�ļ�ֵ��
        return this.get(key) != null;
    }

    public Object[] values() { //����洢������ֵ
        Object[] values = new Object[this.size()]; // �½�����洢
        Object[] keyvalues = this.set.toArray(); // ����ɢ�б��toArrays������ü�ֵ��
        for (int i = 0; i < keyvalues.length; i++) // ����ֵ�ԵĴ洢ת��Ϊֵ�Ĵ洢
            values[i] = ((KeyValue<?, ?>) keyvalues[i]).value;
        return values; //����ֵ������
    }

    public void printAll() {
        this.set.printAll();
    }
}