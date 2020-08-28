public class BruteForceSearch {
    public static int rank(int key, int[] a) {
        for (int i = 0; i < a.length; i++)
            if (a[i] == key) return i;
        return -1;
    }

    public static void main(String[] args) {
        // read ints from args[0] and store in whitelist
        In in1 = new In(args[0]);
        int[] whiteList = in1.readAllInts();
        // read ints from args[1] and store in checklist
        In in2 = new In(args[1]);
        int[] checkList = in2.readAllInts();
        // print each int that is in checklist but is not in whitelist
        for(int i = 0; i < checkList.length; i++){
            if(rank(checkList[i], whiteList) == -1){
                StdOut.println(checkList[i]);
            }
        }
    }
}