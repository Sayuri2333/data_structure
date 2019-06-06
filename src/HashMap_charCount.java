public class HashMap_charCount {
    public static Map<String, Integer> charCount(String text)
    {
        System.out.println("text=\""+text+"\"");
        HashMap<String, Integer> map = new HashMap<String, Integer>(10);//����ɢ�б�����Ϊ10
//        Map<String, Integer> map = new TreeMap<String, Integer>();//Ĭ��Kʵ��Comparable< T>�ӿ�
        for (int i=0; i<text.length(); i++)                //����ַ����Ҽ���
        {
            String key = text.substring(i,i+1);            //���1���ַ�����Ϊ�ؼ���
            Integer value = map.get(key);                  //��ùؼ���key���ַ���ӳ���ֵ
            int count = value==null ? 0 : value.intValue();//ת����int����
            map.put(key, new Integer(count+1));            //���Ӽ������ؼ�����ͬʱ���滻ֵ
        }
        map.printAll();
        return map;
    }

    public static void main(String[] args)
    {
//        String text="AAAABBBCDDBBAAA";                     //��6.4����
//        String text="CDAAAABBBDBBAAA";                     //��6.4���ݣ�ɢ�б�û���
//        String text="class Hash";                            //ͼ8.14����
        String text="public class";                            //ͼ8.1����
        System.out.println(charCount(text).toString());      //ͳ��text�и��ַ��ĳ��ִ���
    }
}
