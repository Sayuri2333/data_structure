public class BinarySortTree<T extends Comparable<? super T>> { //����������
    public TriNode<T> root; //���ڵ�
    private int count = 0; //������
    public BinarySortTree(){ //����յĶ���������
        this.root = null;
    }
    public BinarySortTree(T[] values){ //���ݸ����������幹�����������
        this();
        this.addAll(values);
    }
    public boolean isEmpty(){ //�ж��Ƿ�Ϊ��
        return this.root == null;
    }
    public TriNode<T> searchNode(T key){ //���ݸ���������������Ӧ�Ľڵ�
        TriNode<T> p = this.root; //��ʼ��ָ��
        while (p != null && key.compareTo(p.data) != 0){ //�жϸ�����Ԫ����ָ����ָ��Ԫ�صĴ�С��ϵ
            if (key.compareTo(p.data) < 0) //���������������һ����ȥ��
                p = p.left;
            else
                p = p.right;
        }
        return p != null ? p : null; //����ֵ
    }
    public T search(T key){ //���ݸ�����ֵ������,�鿴�Ƿ������ֵ
        TriNode<T> find = this.searchNode(key); //����searchNode������ýڵ�
        return find != null ? find.data : null; //��ýڵ������ֵ
    }
    public boolean add(T x){ //����������ӽڵ�
        if (this.root == null) //�����ǰ��Ϊ��
            this.root = new TriNode<>(x); //���������óɸ��ڵ�
        else {
            TriNode<T> p = this.root, parent = null; //�½�pָ���parentָ��
            while (p != null){ //Ѱ�Ҳ���ڵ��λ��
                if (x.compareTo(p.data) == 0) //�������һ���Ľڵ�ͷ���false��ʾ�ڵ��Ѿ�����
                    return false;
                parent = p; //parentָ���ƶ���pָ��λ��
                if (x.compareTo(p.data) < 0) //���ݴ�С��ϵ����pָ���λ��
                    p = p.left;
                else
                    p = p.right;
            }
            if (x.compareTo(parent.data) < 0) //��ʱpָ����ָ��λ�ü�����λ��,parent�������½ڵ�ĸ�ĸ�ڵ�
                parent.left = new TriNode<>(x, parent, null, null); //���ݴ�С��ϵ����ڵ�
            else
                parent.right = new TriNode<>(x, parent, null, null);
        }
        count++; //���¼�������ֵ
        return true; //���ز���ɹ�
    }
    public String toString(){ //���������ַ���(�и�����)
        String str = "[";
        TriNode<T> p = this.first(this.root); //Ѱ�ҵ�ǰ�и����������µĵ�һ���ڵ�
        while (p != null){
            str += p.data.toString() + " "; //���
            p = this.next(p); //�л�����һ���ڵ�
        }
        return str + "]"; //�����ַ���
    }
    public void inorder(){
        System.out.println(this.toString());
    }
    public TriNode<T> first(TriNode<T> p){ //������pΪ���ڵ�����е��и����������µĵ�һ���ڵ�
        if (p != null) //�Ϸ����ж�
            while (p.left != null) //��pΪ���ڵ�����������ڵ�
                p = p.left;
        return p; //����
    }
    public TriNode<T> next(TriNode<T> p){ //p�ڵ�ĺ�̽ڵ�(Ӧ�����������ϵĵ�һ���ڵ�)
        if (p != null){ //�Ϸ����ж�
            if (p.right != null) //��������
                return this.first(p.right); //���������ϵĵ�һ���ڵ�
            while (p.parent != null){ //���û��������,�и�ĸ�ڵ�
                if (p.parent.left == p) //���p�Ǹ�ĸ�ڵ������
                    return p.parent; //���ظ�ĸ�ڵ�
                p = p.parent; //�������Ѱ�Ҹ�ĸ�ڵ�
            }
        }
        return null; //�Ҳ������ؿ�
    }
    public boolean contains(T key){
        return this.searchNode(key) != null;
    }
    public void addAll(T[] values){
        for (int i = 0; i < values.length; i++)
            this.add(values[i]);
    }
    public void clear(){
        this.root = null;
    }
    public int size(){
        return count;
    }
    public T remove(T key){ // ���ݸ�����ֵɾ���ڵ�
        TriNode<T> p = this.searchNode(key); //�����Ҫ��ɾ���Ľڵ�
        if (p != null && p.left != null && p.right != null){ //�������ڵ�Ϊ���Ƚڵ�(�����Һ���)
            TriNode<T> insucc = this.first(p.right); //�ҵ��и����������´˽ڵ�ĺ�̽ڵ�
            T temp = p.data;
            p.data = insucc.data;
            insucc.data = temp; //������ǰ�ڵ�ͺ�̽ڵ��ֵ(������ԭ�������Ҫ������ȷ��ɾ��ֵ)
            p = insucc; //��Ҫɾ���Ľڵ��Ϊ��ǰ�ڵ�ĺ�̽ڵ�(���Ȼ��һ�Ƚڵ������Ҷ�ӽ��)
        }
        if (p != null && p == this.root){ //���Ҫɾ�����Ǹ��ڵ�(����ڵ�һ��ΪҶ�ӽ�������һ�Ƚڵ�)
            if (this.root.left != null) //�����ӵĻ�(��û���Һ���)
                this.root = p.left; //���ڵ�����Ϊ����
            else
                this.root = p.right; //��������Ϊ�Һ���
            if (this.root != null) //�ж����ú�ĸ��ڵ��Ƿ�Ϊ��(Ҳ����ԭ���ĸ��ڵ��Ƿ���Ҷ�ӽ��)
                this.root.parent = null; //��Ϊ������parent��Ϊnull
            return p.data; //����ɾ���ڵ��ֵ
        }
        if (p != null && p == p.parent.left){ //���Ҫɾ���Ľڵ��Ǹ�ĸ������
            if(p.left != null){ //�����ǰ�ڵ�������
                p.parent.left = p.left;
                p.left.parent = p.parent; //����ǰ�ڵ�ĸ�ĸ�ڵ����������Ϊ��ǰ�ڵ������
            }else { //��ǰ�ڵ����Һ��ӻ�����Ҷ�ӽڵ�
                p.parent.left = p.right; //����ǰ�ڵ�ĸ�ĸ�ڵ����������Ϊ��ǰ�ڵ���Һ���
                if (p.right != null) //�����ǰ�ڵ���Һ��Ӳ�Ϊ��
                    p.right.parent = p.parent; //������parent��
            }
        }
        if (p != null && p == p.parent.right){ //���Ҫɾ���Ľڵ��Ǹ�ĸ���Һ���
            if (p.left != null){ //�����ǰ�ڵ�������
                p.parent.right = p.left;
                p.left.parent = p.parent; //����ǰ�ڵ�ĸ�ĸ�ڵ����������Ϊ��ǰ�ڵ������
            }else{ //�ȵ�ͬ��
                p.parent.right = p.right;
                if (p.right != null)
                    p.right.parent = p.parent;
            }
        }
        return p != null ? p.data : null; //����ɾ���ڵ��ֵ
    }
    public T removeRoot(){
        return this.remove(this.root.data);
    }
}
