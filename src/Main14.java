public class Main14 {
/*        public ListNode FindKthToTail(ListNode head,int k) {
        ListNode removeNode = head;
        while (k != 0) {
            if (removeNode == null) { /// k 大于链表的长度，直接返回null
                return null;
            }
            removeNode = removeNode.next;
            k--;
        }
        while (removeNode != null) {  /// 这个循环其实就是同时移动head和removeNode两个节点。
            removeNode = removeNode.next;
            head = head.next;
        }
        return head;
    }*/

    private ListNode ans; /// 最终返回的结果
    private int sum; /// 用来记录当前节点是倒数第几个节点

    private void dfs(ListNode node, int k) {
        if (node.next != null) {
            dfs(node.next, k); /// 继续递归到下一节点。
        }
        // 下面这部分其实就是判断当前层的节点是倒数第几个节点。
        sum++;
        if (sum == k) {
            ans = node;
        }
    }

    public ListNode FindKthToTail(ListNode head, int k) {
        ans = null;
        sum = 0;
        if (head == null) {  /// 说明链表为null，就没有必要去递归的需要了
            return null;
        }
        dfs(head, k); /// 递归遍历链表
        return ans;
    }
}
