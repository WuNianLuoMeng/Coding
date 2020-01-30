import java.util.ArrayList;

public class Solution {
    public static void reOrderArray(int [] array) {
        int len = array.length;
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();

        for (int i = 0; i < len; i++) {
            if (array[i] % 2 == 0) {
                list2.add(array[i]);
            } else {
                list1.add(array[i]);
            }
        }
        int index = 0;
        for (int x : list1) {
            array[index++] = x;
        }
        for (int x : list2) {
            array[index++] = x;
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{1,2,3,4,5,6};
        reOrderArray(a);
        for (int x : a) {
            System.out.println(x);
        }
    }
}