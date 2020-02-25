public class Main57 {
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode.right != null) {
            // 第一种情况，pNode节点的右孩子不为空
            pNode = pNode.right;
            while (pNode.left != null) {
                pNode = pNode.left;
            }
            return pNode;
        } else {
            TreeLinkNode tempNode = pNode.next;
            if (tempNode == null) {
                return null;
            }
            if (tempNode.left == pNode) {
                // 第二种情况，当前节点右孩子为空，并且当前节点是父亲节点的左孩子
                return tempNode;
            } else {
                // 第二种情况，当前节点右孩子为空，并且当前节点是父亲节点的右孩子
                boolean flag = false;
                while (tempNode.next != null) {
                    if (tempNode.next.left == tempNode) {
                        flag = true;
                        break;
                    }
                    tempNode = tempNode.next;
                }
                return flag ? tempNode.next : null; // flag尾true时，说明pNode所指的节点不是二叉树中最右侧节点
            }
        }
    }
}


class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null;

    TreeLinkNode(int val) {
        this.val = val;
    }
}
