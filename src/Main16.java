public class Main16 {
    public ListNode Merge(ListNode list1,ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode headNode; /// 最终合成链表得头节点。
        if (list1.val > list2.val) {
            headNode = list2;
            list2 = list2.next;
        } else {
            headNode = list1;
            list1 = list1.next;
        }
        ListNode removeNode = headNode; /// 其实在当前位置就是合成链表得长度为1，头节点和尾节点是一样的。

        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                removeNode.next = list2; /// 将合成链表的尾部节点添加链表2中当前所指向的节点
                removeNode = list2; /// 去更新合成链表的尾部节点
                list2 = list2.next;
            } else {
                removeNode.next = list1; /// 将合成链表的尾部节点添加链表2中当前所指向的节点
                removeNode = list1; /// 去更新合成链表的尾部节点
                list1 = list1.next;
            }
        }

        /// 其实就是将剩余的链表1中的节点放入到合成链表中
        while (list1 != null) {
            removeNode.next = list1;
            removeNode = list1;
            list1 = list1.next;
        }
        /// 其实就是将剩余的链表2中的节点放入到合成链表中
        while (list2 != null) {
            removeNode.next = list2;
            removeNode = list2;
            list2 = list2.next;
        }
        return headNode;
    }
}
