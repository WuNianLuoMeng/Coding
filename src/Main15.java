import java.util.Stack;

public class Main15 {
/*    public ListNode ReverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode frontNode = head;
        ListNode removeNode = head.next;

        while (removeNode != null) {
            ListNode tempNode = removeNode.next; /// 用来保存移动节点的下一个节点，不然的话，就会造成节点最终无法往右移动的情况。
            removeNode.next = frontNode; /// 实现链表的反置
            // 下面两行代码就是实现两个节点的向右平移操作。
            frontNode = removeNode;
            removeNode = tempNode;
        }
        head.next = null;
        return frontNode;
    }*/
    public ListNode ReverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }

        ListNode removeNode = stack.pop(); /// 创建新的链表，需要创建一个新的引用
        ListNode ans = removeNode;
        removeNode.next = null; /// 初始化
        while (!stack.isEmpty()) {
            ListNode x = stack.pop(); /// 取出栈顶节点元素，然后初始化节点元素的next值
            x.next = null;
            /// 可以用链表的尾接法去理解
            removeNode.next = x;
            removeNode = x;
        }
        return ans;

    }
}
