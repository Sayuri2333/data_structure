public class queue_string {
    public static void main(String[] args){
        LinkedQueue<Integer> queue = new LinkedQueue<>();
        queue.add(1);
        System.out.println(queue.size());
        queue.add(2);
        System.out.println(queue.size());
        queue.poll();
        System.out.println(queue.size());
    }
}
