public class Main65 {
    public boolean exist(char[][] board, String word) {
        boolean[][] vis = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (solve(board, word, i, j, vis, 0)) {
                    // 找到一种情况即可
                    return true;
                }
            }
        }
        return false;
    }

    private boolean solve(char[][] board, String word, int x, int y, boolean[][] vis, int index) {
        // 越界处理以及每个方格只能访问一次
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || vis[x][y]) {
            return false;
        }
        // 匹配到某一位置不满足条件
        if (word.charAt(index) != board[x][y]) {
            return false;
        }
        // 匹配成功
        if (index == word.length() - 1) {
            return true;
        }

        vis[x][y] = true; // x，y位置的标记
        boolean flag = solve(board, word, x + 1, y, vis, index + 1) ||
                       solve(board, word, x - 1, y, vis, index + 1) ||
                       solve(board, word, x, y + 1, vis, index + 1) ||
                       solve(board, word, x, y - 1, vis, index + 1);
        vis[x][y] = false; // x，y位置的标记状态回溯
        return flag;
    }
}
