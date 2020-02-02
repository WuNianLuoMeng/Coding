public class Main17 {

    private boolean judge(TreeNode node1, TreeNode node2) { /// 第二部分，匹配
        if (node2 == null) {
            return true; /// 说明二叉树B的某一个方向的节点已经完全的匹配成功。
        }
        if (node1 == null) {
            return false; /// 说明在某一方向上，二叉树A中的节点不缺少的，相对于二叉树B
        }
        if (node1.val == node2.val) {
            boolean flag1 = true; /// 默认左子树是匹配的，假如说不匹配，它就会返回false
            boolean flag2 = true; /// 默认右子树是匹配的，假如说不匹配，它就会返回false
            if (node1.left != null || node2.left != null) {
                flag1 = judge(node1.left, node2.left); /// 比较子树A和二叉树B的左子树
            }
            if (flag1 && (node1.right != null || node2.right != null)) { /// flag1 -> 剪枝
                flag2 = judge(node1.right, node2.right); /// 比较子树A和二叉树B的右子树
            }
            return flag1 && flag2; /// && -> 不光某一个节点的左子树要完全匹配，右子树也是要完全匹配的
        } else {
            return false;
        }
    }

    /// 二叉树的先序遍历
    private boolean dfs(TreeNode node, TreeNode root2) {  /// 第一部分，查找
        boolean flag = false;
        if (node.val == root2.val) {
            flag = judge(node, root2); /// 进入第二部分的匹配过程
        }
        if (flag) {
            return true; /// 通过当前节点已经找到二叉树B的完全匹配结果了，就没有必要再往下去遍历二叉树A了。也可以说是剪枝欸但一个过程
        }
        boolean flag1 = false; /// 用来记录当前节点的左子树中的查找结果(其实也是包含了匹配的过程)，如果查找成功（包含了匹配过程）返回true
        boolean flag2 = false; /// 用来记录当前节点的右子树中的查找结果(其实也是包含了匹配的过程)，如果查找成功（包含了匹配过程）返回true
        if (node.left != null) {
            flag1 = dfs(node.left, root2); /// 当前节点的val不等于二叉树B的root值，那么就去遍历当前节点的左子树，看否找到二叉树B
        }
        if ((!flag1) && node.right != null) { /// !flag1-》剪枝
            flag2 = dfs(node.right, root2); /// 当前节点的val不等于二叉树B的root值，那么就去遍历当前节点的右子树，看否找到二叉树B
        }
        return flag1 || flag2; /// || -》只需要找到节点的某一个方向的子树进行匹配成功就行了
    }

    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if (root1 == null || root2 == null) { /// root1 == null -> 就是二叉树A就是一颗空树， root2 -> 约定空树不是任意一个树的子结构
            return false;
        }
        return dfs(root1, root2);
    }
}
