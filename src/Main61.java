import java.util.LinkedList;
import java.util.Queue;

public class Main61 {
    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        StringBuilder ans = new StringBuilder("[");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int sum = 1; // 用来记录当前节点及其后面非空节点的个数
        while (!queue.isEmpty() && root != null) {
            TreeNode node = queue.poll();
            if (node == null) {
                ans.append("null");
            } else {
                ans.append(node.val);
                sum--;
                if (node.left != null) {
                    sum++;
                }
                if (node.right != null) {
                    sum++;
                }
                queue.add(node.left);
                queue.add(node.right);
            }
            if (sum != 0) {
                ans.append(",");
            } else {
                break;
            }
        }
        ans.append("]");
        return ans.toString();
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        String s = data.substring(1, data.length() - 1);
        if ("".equals(s)) {
            return null; // data = "[]"
        }
        String[] a = s.split(",");
        int index = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(change(a[index++]));
        queue.add(root);
        while (!queue.isEmpty() && index < a.length) {
            TreeNode node = queue.poll();
            if (!"null".equals(a[index])) {
                node.left = new TreeNode(change(a[index++]));
                queue.add(node.left);
            } else {
                index++;
            }
            if (index < a.length && !"null".equals(a[index])) {
                node.right = new TreeNode(change(a[index++]));
                queue.add(node.right);
            } else {
                index++;
            }
        }
        return root;
    }

    private static int change(String s) {
        int res = 0;
        int i = 0;
        int flag = 1;
        if (s.charAt(0) == '-') {
            i++;
            flag = -1;
        }
        for (; i < s.length(); i++) {
            res = res * 10 + s.charAt(i) - '0';
        }
        return res * flag;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        TreeNode temp = root.right;
        temp.left = new TreeNode(4);
        temp.right = new TreeNode(5);
//        System.out.println(serialize(root));
        TreeNode newRoot = deserialize(serialize(root));
        assert newRoot != null;
        dfs(newRoot);
    }

    private static void dfs(TreeNode node) {
        if (node.left != null) {
            dfs(node.left);
        }
        System.out.println(node.val);
        if (node.right != null) {
            dfs(node.right);
        }

    }
}