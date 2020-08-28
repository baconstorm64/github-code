import java.util.Arrays;

public class BinarySearch {
    public static int rank(int key, int[] a) {
        int lo = 0;
        int hi = a.length-1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        // read ints from args[0] and store in whitelist
        In in1 = new In(args[0]);
        int[] whiteList = in1.readAllInts();
        //Arrays.sort(whiteList);
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
