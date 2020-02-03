import java.util.ArrayList;

public class Main19 {
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> ans = new ArrayList<>();
        int flag = 1;// 1->right, 2->down, 3->left, 4->up
        int x = 0;
        int y = 0;
        boolean[][] vis = new boolean[matrix.length][matrix[0].length]; // 这个就是用来标记已经走过的点
        while (ans.size() < matrix.length * matrix[0].length) {
            if(x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || vis[x][y]) {
                // vis[x][y] -> 已经遍历过的位置也当作越界处理
                if(flag == 1) {
                    flag = 2; // 往下走
                    y--; // 消除越界的影响
                    x++; // 本质上就是到达下一个位置的横坐标
                } else if(flag == 2) {
                    flag = 3; // 往左走
                    x--; // 消除越界的影响
                    y--; // 本质上就是到达下一个位置的纵坐标
                } else if (flag == 3) {
                    flag = 4; // 往上走
                    y++; // 消除越界的影响
                    x--; // 本质上就是到达下一个位置的横坐标
                } else {
                    flag = 1;//往右走
                    x++; // 消除越界的影响
                    y++; // 本质上就是到达下一个位置的纵坐标
                }

            } else {
                ans.add(matrix[x][y]);
                vis[x][y] = true; // 去标记已经遍历过的位置
                // 根据flag的值更新遍历矩阵的下标x，y的值
                if(flag == 1) {
                    y++;
                } else if (flag == 2) {
                    x++;
                } else if (flag == 3) {
                    y--;
                } else {
                    x--;
                }
            }
        }
        return ans;
    }
}
