public class Main10 {
    public int RectCover(int target) {
        if (target == 0) {
            return 0;
        }
        if (target == 1) {
            return 1;
        }
        if (target == 2) {
            return 2;
        }
        int[] a = new int[target + 1];
        a[1] = 1;
        a[2] = 2;
        for (int i = 3; i <= target; i++) {
            a[i] = a[i - 1] + a[i - 2];  /// 对于2*i的矩形，他的情况数就是等于2*(i-1)的基础上在右边放置一个竖着的2*1的小矩阵，然后再加上2*(i-2)的矩形的基础上在右边横着放置两个2*1的小矩阵。
        }
        return a[target];
    }
}
