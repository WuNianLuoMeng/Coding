import java.util.HashMap;
import java.util.Map;
class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}
public class Main25 {
    /*    public RandomListNode Clone(RandomListNode pHead) {
            Map<RandomListNode, RandomListNode> map = new HashMap<>();
            RandomListNode removeNode = pHead;
            // 去创建新链表中的节点元素和原链表节点元素与新链表节点元素之间的映射关系
            while (removeNode != null) {
                RandomListNode node = new RandomListNode(removeNode.label);
                map.put(removeNode, node);
                removeNode = removeNode.next;
            }
            removeNode = pHead;
            // 去创建新链表中每个节点的结构关系(根据原链表的节点的结构关系)
            while (removeNode != null) {
                RandomListNode node = map.get(removeNode);
                node.next = map.get(removeNode.next);
                node.random = map.get(removeNode.random);
                removeNode = removeNode.next;
            }
            return map.getOrDefault(pHead, null);
        }*/
    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }
        // 第一个过程->创建新链表节点插入到原链表中
        RandomListNode removeNode = pHead;
        while (removeNode != null) {
            RandomListNode temp = removeNode.next;
            RandomListNode node = new RandomListNode(removeNode.label);
            removeNode.next = node; // 原节点指向新节点
            node.next = temp; // 新节点指向当前节点的next
            removeNode = temp;
        }
        // 第二个过程->创建rangdom节点指向
        removeNode = pHead;
        while (removeNode != null) {
            removeNode.next.random = removeNode.random == null ? null : removeNode.random.next;
            removeNode = removeNode.next.next; // 用两个next是把新链表节点隔过去
        }

        // 第三个过程->链表的分割
        removeNode = pHead;
        RandomListNode cloneHead = pHead.next;
        while (removeNode != null) {
            RandomListNode node = removeNode.next;
            removeNode.next = node.next; // 原链表中节点的结构之间关系的维护
            node.next = node.next == null ? null : node.next.next;// 维护新链表中节点关系的维护
            removeNode = removeNode.next;
        }
        return cloneHead;
    }

}
