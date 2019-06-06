public class Seqlist_student {
    public static int[] gradeCount(Seqlist<Student> list, int[] grade){
        int[] results = new int[grade.length];
        for (int i = 0; i < list.size(); i++){
            Student stu = list.get(i);
            for (int j = 0; j < grade.length - 1; j++){
                if (stu.score >= grade[j] && stu.score <= grade[i + 1]){
                    results[i]++;
                    break;
                }
            }
        }
        return results;
    }
    public static void printCount(Seqlist<Student> list, String[] titles, int[] results){
        System.out.println("Set of student: " + list.toString() + "\n" + list.size() + "person totally, Grade count: ");
        for (int i =  0; i < titles.length; i++){
            System.out.println(titles[i] + results[i] + "persons, ");
        }
        System.out.println();
    }
}
