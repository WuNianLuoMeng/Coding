public class Main38 {
    public int TreeDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(TreeDepth(node.left), TreeDepth(node.right)) + 1; // + 1就是当前node对路径产生的影响
    }
}
