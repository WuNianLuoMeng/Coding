import java.util.ArrayList;
import java.util.Arrays;

public class Main64 {

    public static ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (num.length == 0 || size == 0 || size > num.length) {
            return ans;
        }

        int Max = Integer.MIN_VALUE;
        int pos = -1;
        for (int i = 0; i < size; i++) {
            if (num[i] > Max) {
                Max = num[i];
                pos = i;
            }
        }
        ans.add(Max);
        for (int i = size; i <= num.length - 1; i++) { // i - > 窗口的右区间
            if (i - size + 1 <= pos) {
                if (num[i] > Max) {
                    Max = num[i];
                    pos = i;
                }
            } else {
                Max = Integer.MIN_VALUE;
                for (int j = i - size + 1; j <= i; j++) {
                    if (num[j] > Max) {
                        Max = num[j];
                        pos = j;
                    }
                }
            }
            ans.add(Max);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] a = new int[]{2, 3, 4, 2, 6, 2, 5, 1};
        System.out.println(Arrays.toString(maxInWindows(a, 3).toArray()));
    }
}
