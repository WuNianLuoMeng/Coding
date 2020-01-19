import java.util.ArrayList;

public class Main3 {

//    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
//        ArrayList<Integer> list = new ArrayList<>();
//        Stack<Integer> stack = new Stack<>();
//        while(listNode != null) {
//            stack.add(listNode.val); /// 取当前节点的值放入栈中
//            listNode = listNode.next; /// 更新当前节点为下一个节点
//        }
//        while (!stack.isEmpty()) {
//            list.add(stack.pop()); /// 取出当前栈顶元素然后放入list中
//        }
//        return list;
//    }

    private static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        if (listNode == null) {
            return list;
        }
        return solve(list, listNode);
    }
    // 1->2->3->4
    private static ArrayList<Integer> solve(ArrayList<Integer> list, ListNode listNode) {
        if (listNode.next != null) {   /// 当前节点的下一个节点不为空
            list = solve(list, listNode.next); /// 往下递归
        }
        list.add(listNode.val);  /// 将当前节点的val值放入list列表中
//        System.out.println(list);
        return list;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode removeNode = head;

        for (int i = 1; i < 10; i++) {
            ListNode x = new ListNode(i);
            x.next = null;
            removeNode.next = x;
            removeNode = x;
        }
        System.out.println(printListFromTailToHead(head));
    }
}
class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
