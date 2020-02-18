public class Main46 {
/*    public static int LastRemaining_Solution(int n, int m) {
        if (n == 0 || m == 0) {
            return -1;
        }
        boolean[] vis = new boolean[n]; // vis[i] = true代表第i个小朋友移除圆桌
        int sum = 0; // 用来记录当前已经移除的人数总和
        int res = 0; // 用来记录某一次循环的过程中已经计数人数的个数
        int index = 0;
        while (sum < n - 1) {
            res = 0;
            // 在某一次循环中，让index处于没有被移除的学生位置
            while (vis[index]) {
                index = (index + 1) % n;
            }
            // 模拟找第m个位置的学生
            while (res < m) {
                if(!vis[index]) {
                    res++;
                }
                index = (index + 1) % n;
            }
            vis[(index + n - 1) % n] = true; // 标记当前循环移除的学生位置的下标
            sum++;
        }
        // 在返回结果时，让index处于没有被移除的学生位置
        while (vis[index]) {
            index = (index + 1) % n;
        }
        return index;
    }*/
    public int LastRemaining_Solution(int n, int m) {
        if (n == 0 || m == 0) {
            return -1;
        }
        int ans = 0;
        for (int i = 2; i <= n; i++) {
            ans = (ans + m) % i;
        }
        return ans;
    }
}
