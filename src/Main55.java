import java.util.HashMap;
import java.util.Map;

public class Main55 {
    public ListNode EntryNodeOfLoop(ListNode pHead)
    {
        Map<ListNode, Integer> map = new HashMap<>();
        ListNode node =  pHead;
        while (node != null) {
            map.put(node, map.getOrDefault(node, 0) + 1);
            if (map.get(node) == 2) {
                return node;
            }
            node = node.next;
        }
        return null;
    }
}
