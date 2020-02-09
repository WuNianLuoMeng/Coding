import java.util.HashMap;
import java.util.Map;

public class Main28 {
    /*public int MoreThanHalfNum_Solution(int [] array) {
        Map<Integer, Integer> map = new HashMap<>(); // 去存储每个数字出现的次数
        int target = 0; // 用来去存储出现次数最多的那个数字
        int sum = 0; // 用来去存储出现次数最多的那个数字出现的次数
        for (int x : array) {
            map.put(x, map.getOrDefault(x, 0) + 1); // 更新当前位置数字出现的次数
            if (sum < map.get(x)) {
                // 就是说明当前位置的数字出现的次数比之前统计的target数字出现的次数大
                sum = map.get(x);
                target = x;
            }
        }
        if(sum > array.length / 2) {
            return target;
        }
        return 0;
    }*/
    private boolean check(int target, int[] array) {
        int sum = 0;
        for (int x : array) {
            if (x == target) {
                sum++;
            }
        }
        return sum > array.length / 2;
    }
    public int MoreThanHalfNum_Solution(int [] array) {
        if (array.length == 0) {
            return 0;
        }
        int target = array[0]; // 用来保存最终出现次数最多的数字
        int sum = 0; // 用来保存数组从第1个位置到第i个位置中，target出现的次数 - 不是target出现的次数

        for (int x : array) {
            if (x == target) {
                sum ++;
            } else {
                sum--;
            }
            if (sum == 0) {
                target = x;
                sum = 1;
            }
        }
        if (check(target, array)) {
            return target;
        }
        return 0;
    }
}

