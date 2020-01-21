import java.net.JarURLConnection;

public class Main8 {
   /* public int JumpFloor(int target) {
        if (target == 1) {
            return 1; /// 目前递归到第1阶台阶，就没有必要往下去递归了
        }
        if (target == 2) {
            return 1 + JumpFloor(target - 1); /// 如果target == 2，其实就是等于从起点位置直接跳2阶 + 递归到第一阶的情况的总的跳阶的次数
//            return 2;
        }
        return JumpFloor(target - 1) + JumpFloor(target - 2); /// 当前target台阶的次数等于往前跳1阶加上往前跳2阶
    }*/

    public int JumpFloor(int target) {
        if (target == 1) {
            return 1;
        }
        if (target == 2) {
            return 2;
        }
        int[] a = new int[target + 1]; /// a[i] 代表从起点到第i阶的总的情况数
        a[1] = 1; /// 第一阶的总情况数是1
        a[2] = 2; /// 第二阶的总情况数是2
        for (int i = 3; i <= target; i++) {
            a[i] = a[i - 1] + a[i - 2]; /// 对于第i阶的总情况数就等于从起点到第i-1阶的情况数(从0 -> i-1 -> +1 = i)加上从起点到第i-2阶的情况数(0 -> i-2 -> +2 ->i)
        }
        return a[target];
    }


}
