import java.io.Serializable;

public class calling_number_system implements Serializable{
    private Integer total = 0;
    private LinkedQueue<Integer> queue = new LinkedQueue<Integer>();
    public calling_number_system(String[] values){
        for (int i = 0; i < values.length; i++){
            this.add();
        }
    }
    public calling_number_system(){}
    public boolean isEmpty(){
        return queue.isEmpty();
    }
    public int size(){
        return queue.size();
    }
    public int add(){
        this.queue.add(++total);
        return total;
    }
    public int getTotal(){
        return total;
    }
    public int poll(){
        if (!queue.isEmpty())
            return queue.poll();
        else
            return 0;
    }

}
