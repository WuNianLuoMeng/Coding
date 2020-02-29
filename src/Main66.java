public class Main66 {
    private int test(int m, int n, int k) {
        int res = 1;
        boolean[][] vis = new boolean[m][n];
        vis[0][0] = true;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (cul(i, j) <= k) {
                    if (i - 1 >= 0 && vis[i - 1][j]) {
                        // 下
                        res++;
                        vis[i][j] = true;
                    } else if (i + 1 < m && vis[i + 1][j]) {
                        // 上
                        res++;
                        vis[i][j] = true;
                    } else if (j - 1 >= 0 && vis[i][j - 1]) {
                        // 左
                        res++;
                        vis[i][j] = true;
                    } else if (j + 1 < n && vis[i][j + 1]) {
                        // 右
                        res++;
                        vis[i][j] = true;
                    }
                }
            }
        }
        return res;
    }



    private int sum;
    public int movingCount(int threshold, int rows, int cols) {
        sum = 0;
        boolean[][] vis = new boolean[rows][cols];
        solve(0, 0, rows, cols, vis, threshold);
        return sum;
    }
    private int cul(int x, int y) {
        int res = 0;
        while (x != 0) {
            res += x % 10;
            x /= 10;
        }
        while (y != 0) {
            res += y % 10;
            y /= 10;
        }
        return res;
    }

    private void solve(int x, int y, int rows, int cols, boolean[][] vis, int threshold) {
        if (x < 0 || y < 0 || x >= rows || y >= cols || vis[x][y] || (cul(x, y) > threshold)) {
            return;
        }

        // 当前位置(x,y)是可以走的，那么就从当前位置往四个方向移动即可
        vis[x][y] = true;
        sum++;
        solve(x + 1, y, rows, cols, vis, threshold);
        solve(x - 1, y, rows, cols, vis, threshold);
        solve(x, y + 1, rows, cols, vis, threshold);
        solve(x, y - 1, rows, cols, vis, threshold);
    }
}
