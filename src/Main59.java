import java.util.*;

public class Main59 {
    /*public int[] levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        ArrayList<Integer> list = new ArrayList<>();
        while (!queue.isEmpty() && root != null) {
            TreeNode node = queue.poll();
            list.add(node.val);

            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        int[] ans = new int[list.size()];
        int index = 0;
        for (int x : list) {
            ans[index++] = x;
        }
        return ans;
    }*/

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int sum = 1; // 用来保存每一层的节点的个数
        int num = 1;

        while (!queue.isEmpty() && root != null) {
            List<Integer> list = new LinkedList<>();
            int temp = 0;
            while (sum > 0) {
                TreeNode node = queue.poll();
                assert node != null;
                list.add(node.val);
                if(node.left != null) {
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
            if(num % 2 == 0) {
                for (int i  = 0, j = list.size() - 1; i < j; i++, j--) {
                    int res = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, res);
                }
            }
            num++;
            ans.add(list);
        }
        return ans;
    }
}
