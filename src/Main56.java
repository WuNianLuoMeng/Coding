public class Main56 {
    private ListNode change(ListNode x) {
        int temp = x.val;
        while (x != null && x.val == temp) {
            x = x.next;
        }
        return x;
    }
    public ListNode deleteDuplication(ListNode pHead) {
        ListNode ans = pHead; // 最终链表的头节点
        // 确定最终链表的头节点
        while (ans != null) {
            if (ans.next != null && ans.val == ans.next.val) {
                // 当前ans所指的节点是重复节点
                ans = change(ans);
            } else {
                // 当前ans所指的节点就是我们最终链表的头节点
                break;
            }
        }
        if (ans == null) {
            return null;
        }
        // 判断从ans到链表的尾部，判断每一个节点是否为重复节点。
        ListNode lastNode = ans; // 最终链表的尾部节点
        ListNode removeNode = lastNode.next; // 遍历剩余的节点的变量
        while (removeNode != null) {
            if (removeNode.next != null && removeNode.val == removeNode.next.val) {
                // 当前removeNode所指的节点是重复节点
                removeNode = change(removeNode);
            } else {
                lastNode.next = removeNode;
                lastNode = removeNode;
                removeNode = removeNode.next;
            }
        }
        lastNode.next = null; // 1 -> 2 -> 3 -> 4 -> 4
        return ans;
    }
}

// 1 -> 2 -> 3 -> 4 -> 4
