public class Main18 {
    public void Mirror(TreeNode node) {
        if (node != null) {
            if (node.left != null) {
                Mirror(node.left);
            }
            if (node.right != null) {
                Mirror(node.right);
            }
            /// 下面三行就是交换node节点的左右孩子
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
        }
    }
}
