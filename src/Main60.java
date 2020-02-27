import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main60 {
    ArrayList<ArrayList<Integer>> Print(TreeNode root) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int sum = 1; // 用来保存每一层的节点的个数

        while (!queue.isEmpty() && root != null) {
            ArrayList<Integer> list = new ArrayList<>();
            int temp = 0;
            while (sum > 0) {
                TreeNode node = queue.poll();
                assert node != null;
                list.add(node.val);
                if (node.left != null) {
                    temp++;
                    queue.add(node.left);
                }
                if (node.right != null) {
                    temp++;
                    queue.add(node.right);
                }
                sum--;
            }
            sum = temp;
            ans.add(list);
        }
        return ans;
    }
}
