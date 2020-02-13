public class Main39 {
    private boolean ans;
    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null) {
            return true;
        }
        ans = true;
        TreeDepth(root);
        return ans;
    }

    private int TreeDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (ans) { // 剪枝操作
            int leftDepth = TreeDepth(node.left); // 求出当前节点的左子树的高度
            int rightDepth = TreeDepth(node.right); // 求出当前节点的右子树的高度
            if (Math.abs(leftDepth - rightDepth) > 1) {
                ans = false;
            }
            return Math.max(leftDepth, rightDepth) + 1;
        }
        return 0; // 这个地方返回什么已经不重要了，因为我们已经找一个节点不满足条件了
    }
}
