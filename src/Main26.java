public class Main26 {
    private TreeNode ans;  // 最终返回得双向链表得头部
    private TreeNode removeNode; // 双向链表的尾部节点
    // flag -> 代表的是第n层节点到达第n+1层节点的方向,0->第n+1层节点是第n层节点得左孩子，1->第n+1层节点是第n层节点得右孩子
    private void dfs(TreeNode node, int flag) {
        if (node.left != null) {
            dfs(node.left, 0);
        }
        if (ans == null) {
            ans = node;
            removeNode = node;
        } else {
            // 做一般处理->添加边，修改边
            if(flag == 0) {
                removeNode.right = node; // 从尾部节点引出一条边指向当前节点，也就是说创建一条从小到大的边
                node.left = removeNode; // 这行代码对于非root节点是没有影响的，主要是为了修改root的左孩子的指向
            } else {
                removeNode.right = node; // 这行代码对于非root节点是没有影响的，主要是为了修改root的右孩子的指向
                node.left = removeNode; // 从当前节点引出一条边指向尾部节点，也就是说创建一条从大到小的边

            }
            removeNode = node; // 更新双向链表得尾部节点的值
        }
        if (node.right != null) {
            dfs(node.right, 1);
        }
    }
    public TreeNode Convert(TreeNode pRootOfTree) {
        if(pRootOfTree == null) {
            return null;
        }
        ans = null;
        removeNode = null;
        dfs(pRootOfTree, 0);
        return ans;
    }
}
