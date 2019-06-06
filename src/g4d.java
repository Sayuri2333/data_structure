public class g4d {
    public static void main(String args[])
    {
        String[] vertices={"A","B","C","D","E"};
        Triple edges[]={new Triple(0,1,10), new Triple(0,3,30), new Triple(0,4,99),
                new Triple(1,2,50), new Triple(1,3,40),
                new Triple(2,4,10), new Triple(3,2,20), new Triple(3,4,60)};
//        MatrixGraph<String> graph = new MatrixGraph<String>(vertices, edges);    //ͼ7.12
        AdjListGraph<String> graph = new AdjListGraph<String>(vertices, edges);//ͼ7.17
        System.out.print("��Ȩ����ͼG4��"+graph.toString());
        System.out.println("������ȱ�������ͼG4��");                //ͼ7.22��b������˼����7-1~2��ϰ���𡿱�������
        for (int i=0; i<graph.vertexCount(); i++)
            graph.DFSTraverse(i);

/*        //7.3   ͼ�ı���
        System.out.println("������ȱ�������ͼG4��");                //ͼ7.22��b������˼����7-1~2��ϰ���𡿱�������
        for (int i=0; i<graph.vertexCount(); i++)
            graph.DFSTraverse(i);

        System.out.println("������ȱ�������ͼG4��");
        for (int i=0; i<graph.vertexCount(); i++)
            graph.BFSTraverse(i);


        //7.5.1   �Ǹ�Ȩֵ�ĵ�Դ���·����Dijkstra�㷨��
        System.out.println("��Ȩ����ͼG4��Dijkstra�㷨����Դ���·�����£�");
        for (int i=0; i<graph.vertexCount(); i++)
            graph.shortestPath(i);               //����vi�ĵ�Դ���·����Dijkstra�㷨
*/

//        graph.shortestPath();                    //����Floyd�㷨���Ȩͼÿ�Զ��������·��
    }
}
