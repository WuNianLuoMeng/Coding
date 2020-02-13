import java.util.ArrayList;

public class Main42 {
    public ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {
        ArrayList<Integer> list = new ArrayList<>();
        int l = 0;
        int r = array.length - 1;

        while (l <= r - 1) {
            if (array[l] + array[r] == sum) {
                list.add(array[l]);
                list.add(array[r]);
                break;
            } else if (array[l] + array[r] < sum) {
                l++;
            } else {
                r--;
            }
        }
        return list;
    }
}
