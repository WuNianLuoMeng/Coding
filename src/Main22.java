import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main22 {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>(); // 放入遍历二叉树的节点(本质上是维护宽搜)
        if (root != null) {
            queue.add(root);
        }
        // 迭代的过程->宽搜
        while (!queue.isEmpty()) {
            TreeNode node = queue.peek();
            ans.add(node.val); // 将当前节点的val值放入ArrayList中
            // 同层节点从左至右打印
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
            queue.poll(); // 当前节点val值已经放入ans中，所以要删去
        }
        return ans;
    }
}
