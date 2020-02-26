public class Main58 {
    public boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) {
            return true;
        }
        return solve(pRoot.left, pRoot.right);
    }

    private boolean solve(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }
        if (node1.val != node2.val) {
            return false;
        }
        return solve(node1.left, node2.right) && solve(node1.right, node2.left);
    }
}
