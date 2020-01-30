import java.util.ArrayList;

public class Main13 {
/*        public void reOrderArray(int [] array) {
        int len = array.length;

        for (int i = 0; i < len; i++) {
            if (array[i] % 2 != 0) {
                for (int j = i - 1; j >= 0; j--) {
                    if (array[j] % 2 == 0) {
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    } else {
                        break;
                    }
                }
            }
        }
    }*/
    public void reOrderArray(int[] array) {
        int len = array.length;
        ArrayList<Integer> list1 = new ArrayList<>();  /// 保存奇数
        ArrayList<Integer> list2 = new ArrayList<>();  /// 保存偶数

        for (int i = 0; i < len; i++) {
            if (array[i] % 2 != 0) {
                list1.add(array[i]);
            } else {
                list2.add(array[i]);
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
}
