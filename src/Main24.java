import java.util.ArrayList;

public class Main24 {
    private ArrayList<ArrayList<Integer>> ans;

    /**
     *
     * @param node 二叉树节点
     * @param target 目标权值和
     * @param sum 当前路径的权值和
     * @param list 保存当前路径
     */
    private void solve(TreeNode node, int target, int sum, ArrayList<Integer> list) {
        if (node != null) {
            sum += node.val;
            list.add(node.val);
            if (node.left == null && node.right == null) {
                if (sum == target) {
                    ArrayList<Integer> res = new ArrayList<>(list); // ArrayList是引用传递
                    ans.add(res);
                }
            } else {
                solve(node.left, target, sum, list); // 递归左子树
                solve(node.right, target, sum, list); // 递归右子树
            }
            // 消除掉当前节点对查找路径的影响 --> 至关重要
            list.remove(list.size() - 1);
        }
    }
    private void change() {
        for (int i = 0; i < ans.size(); i++) {
            int index = i;
            for (int j = i + 1; j < ans.size(); j++) {
                if (ans.get(j).size() > ans.get(index).size()) {
                    index = j;
                }
            }
            if (i != index)
            {
                ArrayList<Integer> temp = ans.get(i);
                ans.set(i, ans.get(index));
                ans.set(index, temp);
            }
        }
    }
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ans = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> list = new ArrayList<>();
        solve(root, target, 0, list);
        change();
        return ans;
    }
}
