public class Main9 {
    public int JumpFloorII(int target) {
        if (target == 1) {
            return 1;
        }
        int[] a = new int[target + 1];
        int sum = 1; /// 设置一个sum变量去记录1到n-1阶的总的情况数
        for (int i = 2; i <= target; i++) {
            a[i] = sum + 1; /// 对于第i阶台阶他是等于从第1阶到第i-1阶台阶的情况数之和然后再加上1(从起点到i阶的情况)
            sum = sum + a[i]; /// 需要去更新1到i阶的情况数
        }
        return a[target];
    }
}
