## 二维数组中的查找
### 方法一:
通过遍历array数组，去查找array数组中有没有target的值。它的时间复杂度是(O(n * m))
~~~ java 
    public boolean Find(int target, int [][] array) {
        for (int i = 0; i < array.length; i++)
        {
            for (int j = 0; j < array[0].length; j++)
            {
                if (array[i][j] == target)
                    return true;
            }
        }
        return false;
    }
~~~ 

### 方法二(从矩阵的右上角开始找):
设置一个i，j表示所找当前位置，如果说array[i][j] > target大的话，接着就往左找,反之往下找，直到找到array[i][j] == target为止。它的时间复杂度是：O(n + m)
~~~ java
public boolean Find(int target, int [][] array) {
        int i = 0;
        int j = array[0].length - 1;
        while(i >= 0 && i < array.length && j >= 0 && j < array[0].length)
        {
            // array[i][j]
            if (array[i][j] == target)
                return true;
            else if (array[i][j] > target)
                j--;
            else
                i++;
        }
        return false;
    }
~~~
### 方法三(从矩阵的左下角开始找):
设置一个i，j表示所找当前位置，如果说array[i][j] > target大的话，接着就往上找,反之往右找，直到找到array[i][j] == target为止。它的时间复杂度是：O(n + m)
~~~ java
public boolean Find(int target, int [][] array) {
        int i = array.length - 1;
        int j = 0;
        while(i >= 0 && i < array.length && j >= 0 && j < array[0].length)
        {
            // array[i][j]
            if (array[i][j] == target)
                return true;
            else if (array[i][j] > target)
                i--;
            else
                j++;
        }
        return false;
    }
~~~

## 替换空格
### 方法一:去遍历字符串，然后判断当前位置的字符是否为空格，如果为空格的话，就追加"%20",如果不为空格的话，那么就追加当前位置的字符
~~~ java
public String replaceSpace(StringBuffer str) {
        int len = str.length();
        String res = "%20";
        StringBuffer ans = new StringBuffer();
        for (int i = 0; i < len; i++) {
            ans.append(str.charAt(i) == ' ' ? res : str.charAt(i));
        }
        return ans.toString();
    }
~~~

## 从尾到头打印链表
### 方法一：通过Java中的Stack类去模拟栈的过程
~~~ java
public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        while(listNode != null) {
            stack.add(listNode.val); /// 取当前节点的值放入栈中
            listNode = listNode.next; /// 更新当前节点为下一个节点
        }
        while (!stack.isEmpty()) {
            list.add(stack.pop()); /// 取出当前栈顶元素然后放入list中
        }
        return list;
    }
~~~
### 方法二:采用递归的方式去模拟链表反置的作用
~~~ java
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
        list.add(listNode.val);
//        System.out.println(list);
        return list;
    }
~~~

## 重建二叉树
### 方法一：通过依次遍历前序序列，然后在中序序列中确定当前遍历的前序序列中的数字所在的位置，然后在去划分出当前节点的左右子树，最后在去传入递归程序即可。
~~~ java
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class Main4 {
    private static int index = 0;
    private static TreeNode solve(int[] pre, int[] tempIn) {
        int len1 = 0; /// 当前节点的左子树的节点的个数
        int len2 = 0; /// 当前节点的右子树的节点的个数
        for (int i = 0; i < tempIn.length; i++) {
            if (pre[index] == tempIn[i]) {
                break;
            }
            len1 ++; /// 左子树节点的个数++
        }
        len2 = tempIn.length - len1 - 1;

        int index1 = 0;
        int index2 = 0;
        int[] temp1 = new int[len1]; /// 当前节点的左子树
        int[] temp2 = new int[len2]; /// 当前节点的右子树
        boolean flag = false;
        for (int i = 0; i < tempIn.length; i++) {
            if (pre[index] == tempIn[i]) {
                flag = true;
            } else if (!flag) {
                temp1[index1++] = tempIn[i];
            } else {
                temp2[index2++] = tempIn[i];
            }
        }
        TreeNode node = new TreeNode(pre[index]);
        node.right = null;
        node.left = null;
//        System.out.printf("%d左子树:", pre[index]);
//        for (int i = 0; i < temp1.length; i++) {
//            System.out.printf("%d ", temp1[i]);
//        }
//        System.out.printf(",");
//        System.out.printf("%d右子树:", pre[index]);
//        for (int i = 0; i < temp2.length; i++) {
//            System.out.printf("%d ", temp2[i]);
//        }
//        System.out.println();
        if (index < pre.length && temp1.length > 0) {
            index++; /// 遍历前序序列的下标加1
            node.left = solve(pre, temp1); /// 创建当前节点的左子树
        }
        if (index < pre.length && temp2.length > 0) {
            index++; /// 遍历前序序列的下标加1
            node.right = solve(pre, temp2); /// 创建当前节点的右子树
        }
        return node;
    }
    private static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        index = 0; /// 遍历前序序列的下标
        return solve(pre, in);
    }

    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8}; /// 前序遍历
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6}; /// 中序遍历
        TreeNode root = reConstructBinaryTree(pre, in);

        dfs1(root);
        System.out.println();
        dfs2(root);
        System.out.println();
        dfs3(root);
        System.out.println();

    }
    private static void dfs1(TreeNode node) {
        System.out.printf("%d ", node.val);
        if (node.left != null) {
            dfs1(node.left);
        }
        if (node.right != null) {
            dfs1(node.right);
        }
    }
    private static void dfs3(TreeNode node) {
        if (node.left != null) {
            dfs3(node.left);
        }
        if (node.right != null) {
            dfs3(node.right);
        }
        System.out.printf("%d ", node.val);
    }

    private static void dfs2(TreeNode node) {
        if (node.left != null) {
            dfs2(node.left);
        }
        System.out.printf("%d ", node.val);
        if (node.right != null) {
            dfs2(node.right);
        }
    }

}
~~~ 
## 用两个栈去实现队列
### 方法一：通过两个栈中元素之间的的复制交换去实现了队列的功能。
~~~ java
import java.util.Stack;

public class Main5 {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());  /// 将栈2中的元素放入到栈1中
        }
        stack1.push(node);
    }

    public int pop() {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop()); /// 将栈1中的元素放入到栈2中
        }
        return stack2.pop();
    }
}
~~~

## 旋转数组的最小数字
### 方法一：遍历数组，不断去更新保存最小值的变量。时间复杂度是O(n)
~~~ java
    public int minNumberInRotateArray(int [] array) {
        if (array.length == 0) {
            return 0;
        }
        int ans = array[0];
        for (int i = 1; i < array.length; i++) {
            ans = Math.min(ans, array[i]);
        }
        return ans;
    }
~~~ 
### 方法二：通过二分的方法，不断去更新存在于两个子数组(两个非递减排序子数组)中的下标。时间复杂度是O(log(n))
~~~ java
 public int minNumberInRotateArray(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        int l = 0;
        int r = array.length - 1;
        while (l < r - 1) {
            int mid = (l + r) >> 1;
            if (array[mid] >= array[l]) {
                l = mid; /// 说明mid所在的位置是在第一个非递减子数组中
            } else if (array[mid] <= array[r]) {
                r = mid; /// 说明mid所在的位置是在第二个非递减子数组中
            }
        }
        return array[r];
    }
~~~

## 斐波那契额数列
### 方法一：采用递推的方式去求出a[n]的值
~~~ java
    public int Fibonacci(int n) {
        if (n == 0) {
            return 0;
        }
        int[] a = new int[n + 1];
        if (n == 1 || n == 2) {
            return 1;
        }
        a[1] = 1;
        a[2] = 1;
        for (int i = 3; i <= n; i++) {
            a[i] = a[i - 1] + a[i - 2];
        }
        return a[n];
    }
~~~

### 方法二：采用递归的方式去求出a[n]的值
~~~ java
public int Fibonacci(int n) {
        if (n == 0) {
            return 0; /// 终止递归的条件
        }
        if (n == 1 || n == 2) {
            return 1; /// 终止递归的条件
        }
        return Fibonacci(n - 1) + Fibonacci(n -2);
    }
~~~

## 跳台阶
### 方法一：采用递归的方式去模拟出每次跳台阶的时候所作出的选择:要么跳1阶，要么跳2阶。
~~~ java
 public int JumpFloor(int target) {
        if (target == 1) {
            return 1; /// 目前递归到第1阶台阶，就没有必要往下去递归了
        }
        if (target == 2) {
            return 1 + JumpFloor(target - 1); /// 如果target == 2，其实就是等于从起点位置直接跳2阶 + 递归到第一阶的情况的总的跳阶的次数
//            return 2;
        }
        return JumpFloor(target - 1) + JumpFloor(target - 2); /// 当前target台阶的次数等于往前跳1阶加上往前跳2阶
    }
~~~

### 方法二：采用递推的方式去计算出从起点到第i阶的总的情况数与从起点到第i - 1阶的总的情况数和从起点到第i - 2阶的总的情况数之间的关系等式。
~~~ java
public int JumpFloor(int target) {
        if (target == 1) {
            return 1;
        }
        if (target == 2) {
            return 2;
        }
        int[] a = new int[target + 1]; /// a[i] 代表从起点到第i阶的总的情况数
        a[1] = 1; /// 第一阶的总情况数是1
        a[2] = 2; /// 第二阶的总情况数是2
        for (int i = 3; i <= target; i++) {
            a[i] = a[i - 1] + a[i - 2]; /// 对于第i阶的总情况数就等于从起点到第i-1阶的情况数(从0 -> i-1 -> +1 = i)加上从起点到第i-2阶的情况数(0 -> i-2 -> +2 ->i)
        }
        return a[target];
    }
~~~

### 变态跳台阶
### 方法一：采用递推的方式，对于第i阶台阶的跳法的总次数，他是等于从第一阶到第i-1阶的情况数总和然后再加上从起点到终点的这一种情况数。
~~~ java
public int JumpFloorII(int target) {
        if (target == 1) {
            return 1;
        }
        int[] a = new int[target + 1];
        int sum = 1; /// 设置一个sum变量去记录1到n-1阶的总的情况数
        for (int i = 2; i <= target; i++) {
            a[i] = sum + 1; /// 对于第i阶台阶他是等于从第1阶到第i-1阶台阶的情况数之和然后再加上1(从起点到i阶的情况)
            sum = sum + a[i]; /// 需要去更新1到i阶的情况数
        }
        return a[target];
    }
~~~

### 矩阵覆盖
### 方法一：对于2*i的矩形，他的情况数就是等于2*(i-1)的基础上在右边放置一个竖着的2*1的小矩阵，然后再加上2*(i-2)的矩形的基础上在右边横着放置两个2*1的小矩阵。
~~~ java
public int RectCover(int target) {
        if (target == 0) {
            return 0;
        }
        if (target == 1) {
            return 1;
        }
        if (target == 2) {
            return 2;
        }
        int[] a = new int[target + 1];
        a[1] = 1;
        a[2] = 2;
        for (int i = 3; i <= target; i++) {
            a[i] = a[i - 1] + a[i - 2];  /// 对于2*i的矩形，他的情况数就是等于2*(i-1)的基础上在右边放置一个竖着的2*1的小矩阵，然后再加上2*(i-2)的矩形的基础上在右边横着放置两个2*1的小矩阵。
        }
        return a[target];
    }
~~~

## 二进制中1的个数
### 方法一：本质上就是对n的二进制表示中的每一位进行判断。 
eg:
5 -》 101 & 1 —》 10 & 1  -》 1 & 1 -》 0 & 1这种方法是有问题的。
1 -> 0000000...01 -> (-1) -> 11....11111 -> 右移1位，数字-1的二进制的左边是补1的，也就是说，无论你右移多少次，结果都是-1.

 改进：对n&运算的后面的那个数字进行操作：
 5-》 101 & 1 -》 101 & 10 -》 101 & 100
 
~~~ java
    public int NumberOf1(int n) {
        int sum = 0; /// 记录1的个数
        int temp = 1; /// 本质上是用temp变量去判断n的每一位数字是否为1
        while (temp != 0) { /// 当temp为0的时候，说明已经移动了32次，然后就说明已经遍历完了n的每一位
            sum = (n & temp) != 0 ? sum + 1 : sum;
            temp = temp << 1;
        }
        return sum;
    }
~~~
 
 ### 方法二：本质上对n的二进制表示中的1的位置的判断。
 eg:
 5 -》 101 & 100(101 - 1) = 100 -》 100 & 011(100 - 1) = 000 -》 000
~~~ java
public int NumberOf1(int n) {
        int sum = 0; /// 记录1的个数
        while (n != 0) {  /// 说明当前n的二进制表示中肯定有1
            sum++;
            n = n & (n - 1); /// 本质上就是消除从右往左数的第一个位置的1。
        }
        return sum;
    }
~~~

## 数值的整数次方
### 方法一：对exponent进行分类讨论，主要是当exponent小于0的时候，我们需要求出base的-exponent次方的值，然后拿1除以这个结果即可。
~~~ java
public double Power(double base, int exponent) {
        double ans = 1.0;
        if (exponent >= 0) {
            for (int i = 1; i<= exponent; i++) {
                ans = ans * base;
            }
        } else {
            for (int i = 1; i<= -exponent; i++) {   /// 注意一下exponent是一个负数
                ans = ans * base;
            }
            ans = 1 / ans;
        }
        return ans;
    }
~~~

## 调整数组顺序使奇数位于偶数前面
### 方法一：类似于插入排序的思想，遇见奇数就将当前的奇数往前移动，知道往前移动的过程中，遇到奇数时停止移动。时间复杂度是O(n^2)
~~~ java
        public void reOrderArray(int [] array) {
        int len = array.length;

        for (int i = 0; i < len; i++) {
            if (array[i] % 2 != 0) {
                for (int j = i - 1; j >= 0; j--) {
                    if (array[j] % 2 == 0) {
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    } else {
                        break;
                    }
                }
            }
        }
    }
~~~
### 方法二： 本质上就是开辟两个空间去存储奇数和偶数，最终将这两个空间中的值合并即可。时间复杂度是O(n)，但是空间复杂度比方法一要大。
~~~ java
public void reOrderArray(int[] array) {
        int len = array.length;
        ArrayList<Integer> list1 = new ArrayList<>();  /// 保存奇数
        ArrayList<Integer> list2 = new ArrayList<>();  /// 保存偶数

        for (int i = 0; i < len; i++) {
            if (array[i] % 2 != 0) {
                list1.add(array[i]);
            } else {
                list2.add(array[i]);
            }
        }
        int index = 0;
        for (int x : list1) {
            array[index++] = x;
        }
        for (int x : list2) {
            array[index++] = x;
        }
    }
~~~
## 链表中倒数第k个节点
### 方法一：采用递归的方式去模拟链表从尾到头的这样一个方向，然后在从尾到头的过程中，去判断当前节点的位置，是否为倒数第k个即可。
~~~ java
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
~~~
### 方法二：通过初始化两个移动节点的位置距离为k，然后同时移动两个节点，知道第二个节点移动到链表的末尾时，移动节点1的位置就是链表倒数第k个节点。
~~~ java
        public ListNode FindKthToTail(ListNode head,int k) {
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
    }
~~~
## 反转链表
### 方法一：就是通过两个距离为1的移动节点，去不断的去反转原链表相邻的节点之间的指向。
~~~ java
    public ListNode ReverseList(ListNode head) {
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
    }
~~~
### 方法二：通过栈去模拟反置的过程（不推荐）
~~~ java
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
~~~

## 合并两个排序的链表
### 方法一：类似于归并排序中子序列合并过程，不断去比较两个链表中节点的val值，然后去判断那个节点优先需要添加到合成链表的尾部。
~~~ java
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
~~~

## 树的子结构
### 方法一：主要是分为两个过程，过程一就是查找过程，只有在查找过程中当前A中节点的val等于B中root节点val值一样时，才会进入到匹配过程，匹配过程的话对两个二叉树就是采用同样的比例方式去比较每次递归的节点的val是否一样。然后去判断两个二叉树结构是否一样。
~~~ java
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
~~~

## 二叉树的镜像
### 方法一：采用二叉树的后序遍历的方式，当对某一结点的左右孩子节点遍历完之后，那么就交换左右孩子节点。
~~~ java
public void Mirror(TreeNode node) {
        if (node != null) {
            if (node.left != null) {
                Mirror(node.left);
            }
            if (node.right != null) {
                Mirror(node.right);
            }
            /// 下面三行就是交换node节点的左右孩子
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
        }
    }
~~~
## 顺时针打印矩阵
### 方法一：通过一个flag变量去不断的去更新遍历矩阵下标x，y的值(通过越界和flag当前的值)。
~~~ java
public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> ans = new ArrayList<>();
        int flag = 1;// 1->right, 2->down, 3->left, 4->up
        int x = 0;
        int y = 0;
        boolean[][] vis = new boolean[matrix.length][matrix[0].length]; // 这个就是用来标记已经走过的点
        while (ans.size() < matrix.length * matrix[0].length) {
            if(x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || vis[x][y]) { // vis[x][y] -> 已经遍历过的位置也当作越界处理
                if(flag == 1) {
                    flag = 2; // 往下走
                    y--; // 消除越界的影响
                    x++; // 本质上就是到达下一个位置的横坐标
                } else if(flag == 2) {
                    flag = 3; // 往左走
                    x--; // 消除越界的影响
                    y--; // 本质上就是到达下一个位置的纵坐标
                } else if (flag == 3) {
                    flag = 4; // 往上走
                    y++; // 消除越界的影响
                    x--; // 本质上就是到达下一个位置的横坐标
                } else {
                    flag = 1;//往右走
                    x++; // 消除越界的影响
                    y++; // 本质上就是到达下一个位置的纵坐标
                }

            } else {
                ans.add(matrix[x][y]);
                vis[x][y] = true; // 去标记已经遍历过的位置
                // 根据flag的值更新遍历矩阵的下标x，y的值
                if(flag == 1) {
                    y++;
                } else if (flag == 2) {
                    x++;
                } else if (flag == 3) {
                    y--;
                } else {
                    x--;
                }
            }
        }
        return ans;
    }
~~~
## 包含min函数的栈
### 方法一：其实就是维护两个栈，首先第一个栈是普通的数据站，跟我们平常的栈一样，存储入栈数据；第二个栈就是建立在第一个栈的基础之上，他是维护第一个栈，就是去维护第一个栈中元素的最小值。
~~~ java
import java.util.Stack;

public class Main20 {
    private Stack<Integer> dataStack = new Stack<>(); // 数据栈
    private Stack<Integer> minStack = new Stack<>(); // 维护min函数的栈

    public void push(int node) {
        dataStack.push(node);

        if (minStack.isEmpty() || minStack.peek() > dataStack.peek()) {
            minStack.push(dataStack.peek()); // 当前minStack的栈顶元素大于数据栈的栈顶元素
        } else {
            minStack.push(minStack.peek()); // 当前minStack的栈顶元素小于数据栈的栈顶元素
        }
    }

    public void pop() {
        if (!dataStack.isEmpty()) {
            dataStack.pop();
        }
        if (!minStack.isEmpty()) {
            minStack.pop();
        }
    }

    public int top() {
        // 取出数据栈的栈顶元素
        return dataStack.peek();
    }

    public int min() {
        // 取出维护min函数的栈的栈顶元素
        return minStack.peek();
    }
}

~~~

## 栈的压入，弹出序列
### 方法一：对入栈序列进行入栈的模拟，然后在模拟的过程当中，判断栈顶元素和出栈序列的相等关系，从而判断出对栈顶元素的操作。
~~~ java
public boolean IsPopOrder(int [] pushA,int [] popA) {
        Stack<Integer> stack = new Stack<>();
        int pushIndex = 0; // 入栈序列的下标
        int popIndex = 0; // 出栈序列的下标

        while (pushIndex < pushA.length) {
            if (!stack.isEmpty() && stack.peek() == popA[popIndex]) {
                stack.pop();
                popIndex++;
            } else {
                stack.push(pushA[pushIndex]);
                pushIndex++;
            }
        }

        // 下面的这个while循环其实就是为了防止当所有入栈的元素都压入栈的时候，栈顶元素和出栈序列的下标所指的数字没有来得及比较。
        while (!stack.isEmpty()) {
            if (stack.peek() == popA[popIndex]) {
                stack.pop();
                popIndex++;
            } else {
                return false;
            }
        }
        return true;

    }
~~~
## 从上往下打印二叉树
### 方法一：利用queue去模拟对二叉树宽搜的过程。进而得到二叉树的层序遍历。
~~~ java
public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>(); // 放入遍历二叉树的节点(本质上是维护宽搜)
        if (root != null) {
            queue.add(root);
        }
        // 迭代的过程->宽搜
        while (!queue.isEmpty()) {
            TreeNode node = queue.peek();
            ans.add(node.val); // 将当前节点的val值放入ArrayList中
            // 同层节点从左至右打印
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
            queue.poll(); // 当前节点val值已经放入ans中，所以要删去
        }
        return ans;
    }
~~~
## 二叉搜索树的后序遍历序列
### 方法一：主要是根据两个性质，第一个性质就是后序遍历的末尾数字是二叉树的root节点，第二个性质是二叉搜索树的性质：对于某一个节点而言，它的左子树都是小于当前节点的，右子树都是大于当前节点的。根据性质1去确定每一个节点的位置，然后根据性质2去分割后序遍历序列。
~~~ java
private boolean solve(ArrayList<Integer> list) {
        // 递归终止的条件
        if (list.size() == 0 || list.size() == 1) {
            return true;
        }
        ArrayList<Integer> minList = new ArrayList<>(); // 用来保存小于endNumber数字的序列
        ArrayList<Integer> maxList = new ArrayList<>(); // 用来保存大于endNumber数字的序列
        int endNumber = list.get(list.size() - 1);
        int minIndex = -1; // 用来记录minList中第一个数字的位置
        int maxIndex = -1; // 用来记录maxList中第一个数字的位置
        // 下面这个循环其实就是对当前list序列的一个分割(分割条件就是endNumber)
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) > endNumber) {
                if (maxIndex == -1) {
                    maxIndex = i;
                }
                maxList.add(list.get(i));
            } else if (list.get(i) < endNumber) {
                if (minIndex == -1) {
                    minIndex = i;
                }
                minList.add(list.get(i));
            }
        }
        if (minIndex != -1 && maxIndex != -1) {
            if (minIndex > maxIndex) {
                return false; // 本质上使右子树的序列在左子树的前面，不满足后序遍历
            }
            for (int i = maxIndex; i < list.size(); i++) {
                if (list.get(i) < endNumber) {
                    return false; // 说明在大于endNumber的序列初始位置到末尾，不连续，中间有小于endNumber的数字分割开来
                }
            }
        }
        return solve(minList) && solve(maxList); // && -> 每一个子序列都是需要满足的
    }
    public boolean VerifySquenceOfBST(int [] sequence) {
        if (sequence.length == 0) {
            return false;
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < sequence.length; i++) {
            list.add(sequence[i]);
        }
        return solve(list);
    }
~~~
## 二叉树中和为某一值的路径
### 方法一：通过深搜遍历二叉树节点元素，去搜索从二叉树root节点到叶子节点之间的路径的权值和是否等于target。
~~~ java
private ArrayList<ArrayList<Integer>> ans;

    /**
     *
     * @param node 二叉树节点
     * @param target 目标权值和
     * @param sum 当前路径的权值和
     * @param list 保存当前路径
     */
    private void solve(TreeNode node, int target, int sum, ArrayList<Integer> list) {
        if (node != null) {
            sum += node.val;
            list.add(node.val);
            if (node.left == null && node.right == null) {
                if (sum == target) {
                    ArrayList<Integer> res = new ArrayList<>(list); // ArrayList是引用传递
                    ans.add(res);
                }
            } else {
                solve(node.left, target, sum, list); // 递归左子树
                solve(node.right, target, sum, list); // 递归右子树
            }
            // 消除掉当前节点对查找路径的影响 --> 至关重要
            list.remove(list.size() - 1);
        }
    }
    private void change() {
        for (int i = 0; i < ans.size(); i++) {
            int index = i;
            for (int j = i + 1; j < ans.size(); j++) {
                if (ans.get(j).size() > ans.get(index).size()) {
                    index = j;
                }
            }
            if (i != index)
            {
                ArrayList<Integer> temp = ans.get(i);
                ans.set(i, ans.get(index));
                ans.set(index, temp);
            }
        }
    }
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ans = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> list = new ArrayList<>();
        solve(root, target, 0, list);
        change();
        return ans;
    }
~~~
## 复杂链表的复制
### 方法一：主要是通过去维护原链表节点和新链表节点之间的映射关系，然后在根据原链表中节点之间的连接关系去创建新链表中的节点之间的关系。空间复杂度是O(N)
~~~ java
    public RandomListNode Clone(RandomListNode pHead) {
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
        }
~~~
### 方法二：主要是通过创建新链表中的节点在原链表中，去优化了第一种方法的O(N)的空间复杂度，第二种方法分为三个过程，1->创建新节点以及实现新节点和元链表节点的连接，2->根据原链表的rangdom指向去生成新的节点的random的指向，3->链表的分割。
~~~ java
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
~~~
## 二叉搜索树与双向链表
### 方法一：通过对二叉搜索树的中序遍历，在遍历过程中，动态的去创建双向链表的尾部节点即可。
~~~ java
private TreeNode ans;  // 最终返回得双向链表得头部
    private TreeNode removeNode; // 双向链表的尾部节点
    // flag -> 代表的是第n层节点到达第n+1层节点的方向,0->第n+1层节点是第n层节点得左孩子，1->第n+1层节点是第n层节点得右孩子
    private void dfs(TreeNode node, int flag) {
        if (node.left != null) {
            dfs(node.left, 0);
        }
        if (ans == null) {
            ans = node;
            removeNode = node;
        } else {
            // 做一般处理->添加边，修改边
            if(flag == 0) {
                removeNode.right = node; // 从尾部节点引出一条边指向当前节点，也就是说创建一条从小到大的边
                node.left = removeNode; // 这行代码对于非root节点是没有影响的，主要是为了修改root的左孩子的指向
            } else {
                removeNode.right = node; // 这行代码对于非root节点是没有影响的，主要是为了修改root的右孩子的指向
                node.left = removeNode; // 从当前节点引出一条边指向尾部节点，也就是说创建一条从大到小的边
            }
            removeNode = node; // 更新双向链表得尾部节点的值
        }
        if (node.right != null) {
            dfs(node.right, 1);
        }
    }
    public TreeNode Convert(TreeNode pRootOfTree) {
        if(pRootOfTree == null) {
            return null;
        }
        ans = null;
        removeNode = null;
        dfs(pRootOfTree, 0);
        return ans;
    }
~~~
## 字符串的排列
### 方法一：通过递归的去查找每一个位置的字符可能出现情况。比如说现在要找index下标位置的字符，那么体现在代码中就是交换index以及index位置之后的那些字符即可。
~~~ java
private String change(char[] a) {
        StringBuilder res = new StringBuilder();
        for (char value : a) {
            res.append(value);
        }
        return res.toString();
    }
    private void solve(ArrayList<String> ans, char[] a, int index, int length) {
        if (index == length - 1) {
            String res = change(a);
            ans.add(res);
        } else {
            // 就说明现在要去确定index位置的字符
            for (int i = index; i < length; i++) {
                char temp = a[i];
                a[i] = a[index];
                a[index] = temp;
                // 当前index位置的字符已经通过交换找到了，那么就递归去找下一个位置的字符
                solve(ans, a, index + 1, length);
                // 其实就是去为了消除当前层去递归的时候的进行交换字符的影响，如果不消除的话，那么就会造成原index位置的字符发生变化
                temp = a[i];
                a[i] = a[index];
                a[index] = temp;

            }
        }
    }
    public ArrayList<String> Permutation(String str) {
        char[] a = str.toCharArray();
        ArrayList<String> ans = new ArrayList<>();
        solve(ans, a, 0, str.length());
        ans = new ArrayList<String>(new HashSet<String>(ans)); // 去重操作
        Collections.sort(ans); // 字典排序 -> ans.sort(null);
        return ans;
    }
~~~
## 最小的K个数
### 方法一：通过维护大顶堆去实现最小的K个数字的查找，本质就是大顶堆的维护
~~~ java
public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        if (k  > input.length || k == 0) {
            return new ArrayList<>();
        }

        int[] a = new int[k]; // 用数组去模拟k个节点的堆结构
        System.arraycopy(input, 0, a, 0, k); // 初始化堆中元素
        // 下面就开始维护堆使其成为大顶堆 - > 堆的初始化
        for (int i = k / 2 - 1; i >= 0; i--) {
            // i -> i其实就是我们所要去维护堆的节点下标
            initiate(i, a, k);
        }
        // 去遍历剩余的len - k个节点
        for (int i = k; i < input.length; i++) {
            if (input[i] < a[0]) {
                a[0] = input[i];
                initiate(0, a, k);
            }
        }
        // 将大顶堆中的节点元素进行升序操作
        for (int i = a.length - 1; i > 0; i--) {
            // 分为两个过程， 第一步交换，第二步固定(固定的操作其实是通过控制堆的节点个数去实现的)
            int temp = a[i];
            a[i] = a[0];
            a[0] = temp;
            initiate(0, a, i);
        }
        // 返回
        ArrayList<Integer> ans = new ArrayList<>();
        for (int x : a) {
            ans.add(x);
        }
        return ans;
    }

    /**
     * 初始化堆的函数,其实就是维护每一个节点的位置的函数
     * @param index 维护当前堆的下标
     * @param a 数组->堆
     * @param length 堆的节点个数
     */
    private void initiate(int index, int[] a, int length) {
        int temp = a[index]; // 先去保存当前位置的值
        for (int k = index * 2 + 1; k < length; k = k * 2 + 1) {
            if ((k + 1) < length && a[k + 1] > a[k]) {
                // 取出当前位置的左右孩子中节点值最大的节点
                k++;
            }
            if (a[k] > temp) {
                a[index] = a[k];
                index = k; // 更新index的值，index -> 代表的是temp数字最终在堆中位置，当k = k * 2 + 1执行后,index和k的关系其实就是父亲节点和孩子节点的关系。
            } else {
                break; // 由于我们是从下往上去维护的，所以说我们就没有往下更新的必要了
            }
        }
        a[index] = temp; // index所在的位置进行更新就行了

    }
~~~
## 连续子序列的最大和
### 方法一：通过枚举起点和终点去统计起点到终点的序列的和。时间复杂度O(n^2)
~~~ java
   public int FindGreatestSumOfSubArray(int[] array) {
           int[] sum = new int[array.length]; // 用来去统计0-i位置的和
           sum[0] = array[0];
           for (int i = 1; i < array.length; i++) {
               sum[i] = sum[i - 1] + array[i];
           }
           int Max = sum[0]; // 默认第一个元素
           // i是终点，j是起点
           for (int i = 0; i < array.length; i++) {
               for (int j = 0; j <= i; j++) {
                   if (j == 0) {
                       Max = Math.max(Max, sum[i]); // 说明起点在0位置
                   } else {
                       Max = Math.max(Max, sum[i] - sum[j - 1]); // j-i的和它就等于从起点到i位置之和减去从起点到j-1的位置之和
                   }
               }
           }
           return Max;
       }
~~~
### 方法二：通过定义一个sum变量去统计若干段连续子序列的和，然后再去比较出每段子序列和的最大值即可。时间复杂度为O(n)
~~~ java
public int FindGreatestSumOfSubArray(int[] array) {
        int sum = 0;
        int Max = array[0];
        for (int i = 0; i < array.length; i++) {
            // 这几行代码的过程就是：通过sum变量去统计当前连续子序列的和，统计完之后，更新Max的值，最后判断是否更新sum的值
            sum += array[i];
            Max = Math.max(Max, sum);
            if (sum < 0) {
                sum = 0;
            }
        }
        return Max;
    }
~~~
## 整数中1出现的次数
### 方法一：暴力1-n个数字即可。时间复杂度O(n*log(n))
~~~ java
public int NumberOf1Between1AndN_Solution(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            int x = i;
            while (x != 0) {
                if (x % 10 == 1) {
                    sum++;
                }
                x = x / 10;
            }
        }
        return sum;
    }
~~~
### 方法二：通过对1-n的分区间讨论(递归的过程)，在求1-n的时候，分为两个区间，第一个区间是1-b(b是n去掉首部数字之后的数字)， 第二个区间是（b+1, n）。对于每个区间的计算时，分为了两种情况，第一种情况是当前n的首部数字是1，第二种情况是除了首位的其他位是1。主要是这两中情乱的讨论。
~~~ java
public int NumberOf1Between1AndN_Solution(int n) {
        if (n == 0) {
            return 0;
        }
        String str = "" + n;
        int len = str.length();
        if (len == 1) {
            return 1;
        }
        int res = (int) Math.pow(10, len - 1); // 是获取当前n的幂级
//        int firstNumber = str.charAt(0) - '0';
        int firstNumber = n / res;
        int firstBit = firstNumber == 1 ? (n % res) + 1 : res;
        int otherBit = (len - 1) * firstNumber * res / 10; //(len - 1)的意思就是剩余位的个数(C(len-1, 1) -> 从剩余的len-1位中选取一位来作为1)，res/10的意思就是剩余的len-2位可能出现的情况
        return firstBit + otherBit + NumberOf1Between1AndN_Solution(n % res);
    }
~~~
## 把数组排成最小的数
### 方法一：通过去设置一种比较优先级排序即可，优先级为：将比较的两个元素拼接的两种结果去比较大小，然后由他们的大小关系去比较所拼接元素的优先级。
~~~ java
public String PrintMinNumber(int [] numbers) {
        ArrayList<String> list = new ArrayList<>();
        for (int x : numbers) {
            list.add(x + "");
        }
        list.sort((o1, o2) -> {
            // 下面的排序规则是核心
            String a1 = o1 + o2;
            String a2 = o2 + o1;
            return a1.compareTo(a2);
        });
        StringBuilder ans = new StringBuilder();
        for (String x : list) {
            ans.append(x);
        }
        return ans.toString();
    }
~~~
## 丑数
### 方法一：就是说通过去根据2*丑数，3*丑数，5*丑数这三个队列去判断出当前位置的值，然后去更新遍历这三个队列下标的值即可
~~~ java
public int GetUglyNumber_Solution(int index) {
        int[] a = new int[index];
        a[0] = 1;
        int index1 = 0; // 遍历丑数*2的队列
        int index2 = 0; // 遍历丑数*3的队列
        int index3 = 0; // 遍历丑数*5的队列

        for (int i = 1; i < index; i++) {
            a[i] = Math.min(Math.min(a[index1] * 2, a[index2] * 3) , a[index3] * 5);
            // 根据放在第i个位置上的数字更新遍历三个队列的下标
            if (a[i] == a[index1] * 2) {
                index1++;
            }
            if (a[i] == a[index2] * 3) {
                index2++;
            }
            if (a[i] == a[index3] * 5) {
                index3++;
            }
        }
        return a[index - 1];
    }
~~~
## 第一个只出现一次的字符位置
### 方法一：通过map结构去保存每个字符出现的次数，然后再对字符进行遍历判断即可。
~~~ java
public int FirstNotRepeatingChar(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (int  i = 0; i < str.length(); i++) {
            map.put(str.charAt(i), map.getOrDefault(str.charAt(i), 0) + 1);
        }
        for (int i = 0; i < str.length(); i++) {
            if (map.get(str.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }
~~~
## 数组中的逆序对
### 方法一：通过对并排序的合并过程去统计逆序对的个数，那么统计逆序对的前提就是在归并排序的合并过程中，合并的两个子序列都是有序的，所以说才可以用归并排序去统计逆序对的个数
~~~ java
private long sum; // 用来去统计逆序对的个数
    public int InversePairs(int [] array) {
        sum = 0;
        int l = 0;
        int r = array.length - 1;
        divide(l ,r, array);
        return (int) (sum % 1000000007);
    }

    private void divide(int l, int r, int[] array) {
        if (l != r) {
            int mid = (l + r) >> 1;
            divide(l, mid, array);
            divide(mid + 1, r, array);
            merge(l, r, mid, array);
        }
    }

    private void merge(int l, int r, int mid, int[] array) {
        int i = l; // 左区间的起点
        int j = mid + 1; // 右区间的起点
        int[] temp = new int[r - l + 1];
        int index = 0;
        while (i <= mid && j <= r) {
            if (array[i] > array[j]) {
                temp[index++] = array[j++];
                sum += mid - i + 1; // 这一行是核心，去统计逆序对个数，统计的基础是在归并排序的合并过程中，合并的两个子序列都是有序的
            } else {
                temp[index++] = array[i++];
            }
        }
        while (i <= mid) {
            temp[index++] = array[i++];
        }
        while (j <= r) {
            temp[index++] = array[j++];
        }
        index = 0;
        for (int k = l; k <= r; k++) {
            array[k] = temp[index++];
        }
    }
~~~
## 两个链表的第一个公共结点
###方法一：通过栈去模拟从链表的尾部往前遍历两个链表的重合的部分，找到最左侧重合点即可
~~~ java
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();

        while (pHead1 != null) {
            stack1.add(pHead1);
            pHead1 = pHead1.next;
        }
        while (pHead2 != null) {
            stack2.add(pHead2);
            pHead2 = pHead2.next;
        }
        ListNode ans = null;
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            if(stack1.peek().val == stack2.peek().val) {
                ans = stack1.peek();
                stack1.pop();
                stack2.pop();
            } else {
                break;
            }
        }
        return ans;
    }
~~~
### 方法二：先去判断两个链表的长度，移动其中一个链表的头节点，使其两个链表的长度一样，最后从两个链表的头部开始遍历，找到第一个重合点即可。
~~~ java
public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        int len1 = 0;
        int len2 = 0;
        ListNode removeNode1 = pHead1;
        ListNode removeNode2 = pHead2;
        while (removeNode1 != null) {
            len1++;
            removeNode1 = removeNode1.next;
        }
        while (removeNode2 != null) {
            len2++;
            removeNode2 = removeNode2.next;
        }

        // 下面的两个判断就是是的两个链表的length相同
        if (len1 > len2) {
            for (int i = 1; i <= len1 - len2; i++) {
                pHead1 = pHead1.next;
            }
        } else if (len2 > len1) {
            for (int i = 1; i <= len2 - len1; i++) {
                pHead2 = pHead2.next;
            }
        }
        ListNode ans = null;
        while (pHead1 != null) {
            if (pHead1.val ==pHead2.val) {
                ans = pHead1;
                break;
            }
            pHead1 = pHead1.next;
            pHead2 = pHead2.next;
        }
        return ans;
    }
~~~
## 数字在排序数组中出现的次数
### 方法一：根据二分查找找到数字K的起始位置和终止位置即可
~~~ java
private int findFirstPosition(int[] array, int k) {
        int l = 0;
        int r = array.length - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (array[mid] == k) {
                if(mid - 1 >= 0 && array[mid - 1] == k) {
                    // 说明mid当前的位置不是初始位置,k的初始位置是在l~mid-1区间
                    r = mid - 1;
                } else {
                    // 就可以说明mid位置的数字就是k的初始位置
                    return mid;
                }
            } else if (array[mid] > k) {
                r = mid - 1; // k是属于l~mid-1区间
            } else {
                l = mid + 1; // k是属于mid+1~r区间
            }
        }
        return l;
    }

    private int findLastPosition(int[] array, int k) {
        int l = 0;
        int r = array.length - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (array[mid] == k) {
                if(mid + 1 < array.length && array[mid + 1] == k) {
                    // 说明mid当前的位置不是终止位置,k的初始位置是在mid+1~r区间
                    l = mid + 1;
                } else {
                    // 就可以说明mid位置的数字就是k的终止位置
                    return mid;
                }
            } else if (array[mid] > k) {
                r = mid - 1; // k是属于l~mid-1区间
            } else {
                l = mid + 1; // k是属于mid+1~r区间
            }
        }
        return l;
    }

    public int GetNumberOfK(int [] array , int k) {
        if (array.length == 0) {
            return 0;
        }
        int firstPosition = findFirstPosition(array, k);
        int lastPosition = findLastPosition(array, k);
        if (array[firstPosition] != k) {
            return 0;
        }
        return lastPosition - firstPosition + 1;
    }
~~~
## 二叉树的深度
### 方法一：通过递归去求解从每一个节点的左右孩子节点的出发到叶子节点的节点个数，去推断出当前节点到叶子节点的节点个数
~~~ java
public int TreeDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(TreeDepth(node.left), TreeDepth(node.right)) + 1; // + 1就是当前node对路径产生的影响
    }
~~~
## 平衡二叉树
### 方法一：判断二叉树中的每一个节点的左右孩子的高度差的绝对值是否大于1即可
~~~ java
    private boolean ans;
    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null) {
            return true;
        }
        ans = true;
        TreeDepth(root);
        return ans;
    }

    private int TreeDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (ans) { // 剪枝操作
            int leftDepth = TreeDepth(node.left); // 求出当前节点的左子树的高度
            int rightDepth = TreeDepth(node.right); // 求出当前节点的右子树的高度
            if (Math.abs(leftDepth - rightDepth) > 1) {
                ans = false;
            }
            return Math.max(leftDepth, rightDepth) + 1;
        }
        return 0; // 这个地方返回什么已经不重要了，因为我们已经找一个节点不满足条件了
    }
~~~
## 数组中只出现过一次的数字
### 方法一：通过对原数组中的所有数字异或运算之后，找到划分出只出现过一次的数字的方式，然后在对剩余数组也采取相同的划分方式，对于划分的两个子序列，进行异或运算即可
~~~ java
public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        if (array.length != 0) {
            int ans = 0;
            // 通过第一个循环找到那两个出现过一次的数字的异或结果
            for (int x : array) {
                ans ^= x;
            }
            // 根据这个异或结果去找到分割出这两个数字的方式->从右往左找到第一个位置不为0的地方
            int lastNumberOfOne = find(ans);
            num1[0] = 0;
            num2[0] = 0;
            for (int x : array) {
                if (judge(x, lastNumberOfOne) == 0) {
                    num1[0] ^= x;
                } else {
                    num2[0] ^= x;
                }
            }
        }
    }

    // 判断x的从右往左看，第lastNumberOfOne位是否为1
    private int judge(int x, int lastNumberOfOne) {
        x >>= (lastNumberOfOne - 1); // 将x的第lastNumberOfOne位移到最右边
        return x & 1;
    }

    private int find(int ans) {
        int sum = 1;
        int res = 1;
        while ((ans & res) == 0) {
            sum++;
            res <<= 1;
        }
        return sum;
    }
~~~
## 和为S的两个数字
### 方法一:通过设置两个所有指针去查找和为S的两个数字，对于当前l和r指针指向的两个数字和而言，如果说小于S，那就往右移动L指针，如果说大于S，那就往左移动R指针。
~~~ java
public ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {
        ArrayList<Integer> list = new ArrayList<>();
        int l = 0;
        int r = array.length - 1;

        while (l <= r - 1) {
            if (array[l] + array[r] == sum) {
                list.add(array[l]);
                list.add(array[r]);
                break;
            } else if (array[l] + array[r] < sum) {
                l++;
            } else {
                r--;
            }
        }
        return list;
    }
~~~
## 和为S的连续正数序列
### 方法一：通过区动态的移动滑动窗口的左右区间的端点位置，找出所有连续序列数字之和等于S的情况。
~~~ java
    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        int l = 1;
        int r = 2;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        while (l < (sum + 1) / 2) {
            int tempSum = cul(l, r);
            while (tempSum > sum) {
                tempSum -= l;
                l++;
                list.remove(0);
            }
            if (tempSum == sum) {
                ans.add(new ArrayList<>(list));
                l++;
                list.remove(0);
            }
            r++;
            list.add(r);
        }
        return ans;
    }

    private int cul(int l, int r) {
        return (l + r) * (r - l + 1) / 2;
    }
~~~
### 方法二:通过等差数列的求和公式去得出第一项和项数以及S的关系，通过去枚举第一项，然后算出项数n，判断n是否为整数即可
~~~ java
public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for (int i = 1; i <= sum; i++) {
            int x = 2 * i - 1;
            double temp = Math.sqrt(x * x + 8 * sum);
            if (judge(temp)) {
                int res = (int) temp;
                double n1 = (double) (-x + res) / 2;
                double n2 = (double) (-x - res) / 2;
                if (judge(n1) && n1 > 1) {
                    ArrayList<Integer> list = new ArrayList<>();
                    for (int j = i, k = 1; k <= n1; j++, k++) {
                        list.add(j);
                    }
                    ans.add(list);
                }
                if (judge(n2) && n2 > 1) {
                    ArrayList<Integer> list = new ArrayList<>();
                    for (int j = i, k = 1; k <= n2; j++, k++) {
                        list.add(j);
                    }
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    private boolean judge(double x) {
        int res = (int) x;
        return x - res <= 0.00000001;
    }
~~~
## 翻转单词序列
### 方法一：通过两次翻转，第一次翻转使得每一个单词处于最终的位置，第二次翻转为了消除第一次翻转的时候对每一个单词产生的影响。
~~~ java
public String ReverseSentence(String str) {
        String flipStr = new StringBuilder(str).reverse().toString(); // 第一次翻转
        StringBuilder res = new StringBuilder(); // 用来遍历每一个单词
        StringBuilder ans = new StringBuilder(); // 用来保存结果
        for (int i = 0; i < flipStr.length(); i++) {
            if (flipStr.charAt(i) == ' ') {
                ans.append(res.reverse().toString()).append(" "); // 第二次翻转
                res = new StringBuilder();
            } else {
                res.append(flipStr.charAt(i));
            }
        }
        ans.append(res.reverse().toString()); // 最后那个单词的翻转结果保存到ans当中
        return ans.toString();
    }
~~~
## 左旋转字符串
### 方法一：通过两次翻转，第一次翻转使得两个子序列处于最终的位置，第二次翻转为了消除第一次翻转的时候对每一个子序列产生的影响。
~~~ java
public String LeftRotateString(String str,int n) {
        if (str.length() == 0) {
            return "";
        }
        StringBuilder flipStr = new StringBuilder(str).reverse(); // 第一次翻转
        String str1 = flipStr.substring(0, flipStr.length() - n); // 取出第一个子序列
        String str2 = flipStr.substring(flipStr.length() - n); // 取出第二个子序列
        return new StringBuilder(str1).reverse().append(new StringBuilder(str2).reverse()).toString(); // 第二次翻转
    }
~~~
## 扑克牌顺子
### 方法一：先去统计出数组中0的个数，然后在对数组排序，最后判断数组中相邻两个位置之间是否需要0填充以及0的个数是否够用即可。
~~~ java
public boolean isContinuous(int [] numbers) {
        if (numbers.length == 0) {
            return false;
        }
        int sum = 0;
        for (int x : numbers) {
            if (x == 0) {
                sum++;
            }
        }
        Arrays.sort(numbers);
        for (int i = sum + 1; i < numbers.length; i++) {
            sum -= numbers[i] - numbers[i - 1] - 1;
            if (sum < 0 || numbers[i] == numbers[i - 1]) {
                return false;
            }
        }
        return true;
    }
~~~
## 孩子们的游戏(圆圈中最后剩下的数)
### 方法一：模拟每一个学生的被移除的过程，通过一个vis数组来去标记已经移除过的学生的位置。
~~~ java
    public static int LastRemaining_Solution(int n, int m) {
        if (n == 0 || m == 0) {
            return -1;
        }
        boolean[] vis = new boolean[n]; // vis[i] = true代表第i个小朋友移除圆桌
        int sum = 0; // 用来记录当前已经移除的人数总和
        int res = 0; // 用来记录某一次循环的过程中已经计数人数的个数
        int index = 0;
        while (sum < n - 1) {
            res = 0;
            // 在某一次循环中，让index处于没有被移除的学生位置
            while (vis[index]) {
                index = (index + 1) % n;
            }
            // 模拟找第m个位置的学生
            while (res < m) {
                if(!vis[index]) {
                    res++;
                }
                index = (index + 1) % n;
            }
            vis[(index + n - 1) % n] = true; // 标记当前循环移除的学生位置的下标
            sum++;
        }
        // 在返回结果时，让index处于没有被移除的学生位置
        while (vis[index]) {
            index = (index + 1) % n;
        }
        return index;
    }
~~~
### 方法二：通过公式的推导可以推导出：f(n, m) = (f(n-1, m) + m) % n
~~~ java
    public int LastRemaining_Solution(int n, int m) {
        if (n == 0 || m == 0) {
            return -1;
        }
        int ans = 0;
        for (int i = 2; i <= n; i++) {
            ans = (ans + m) % i;
        }
        return ans;
    }
~~~
## 求1+2+3+4+...+n
### 方法一:采用递归地方式，去求出等差数列的前n项和，只不过递归终止的条件是通过&&原酸来去写的
~~~ java
    public int Sum_Solution(int n) {
        int sum = n;
        boolean flag = (n > 0) && (sum += Sum_Solution(n - 1)) > 0;
        return sum;
    }
~~~
## 不用加减乘除做加法
### 方法一：将两个数字象加分为两个步骤：第一步统计两个数字每一位的相加结果(不计算进位情况)， 第二步计算两个数字的进位结果，然后把这两步的结果在进行前面所说的两步操作，直到进位结果为0即可
~~~ java
public int Add(int num1,int num2) {
        int sum1, sum2;
        do{
            sum1 = num1 ^ num2;
            sum2 = (num1 & num2) << 1;
            num1 = sum1;
            num2 = sum2;
        }while (num2 != 0);
        return num1;
    }
~~~
## 把字符串转换成整数
### 方法一：先删除掉最左侧的空格，然后在判断最终结果的正负性，接着遍历字符，看是否合法，如果不合法，返回0，反之继续遍历，知道遍历到字符串的结尾。
~~~ java
public static int StrToInt(String str) {
        int len = str.length();
        int index = 0;
        // 第一步，删除前面的空格
        while (index < len) {
            if (str.charAt(index) == ' ') {
                index++;
            } else {
                break;
            }
        }
        int flag = 0;
        long ans = 0; // 最终返回的结果
        boolean flag1 = false;
        while (index < len) {
            // "3-2"
            if (!flag1 && (str.charAt(index) == '-' || str.charAt(index) == '+')) {
                if (flag != 0) {
                    return 0; // "-123-3", 第二个-号是非法字符， 返回0
                }
                flag = str.charAt(index) == '-' ? -1 : 1;
            } else if (str.charAt(index) >= '0' && str.charAt(index) <= '9') {
                flag1 =true;
                ans = ans * 10 + str.charAt(index )  -'0'; // "-123"
                if (judge(ans, flag)) { // 对ans是否溢出int类型做下判断
                    return 0;
                }
            } else {
                return 0; // 既不是数字，也不是正负号，那就是其他字符了，返回0
            }
            index++;
        }
        return flag == -1 ? (int) ans * (-1) : (int) ans;
    }

    private static boolean judge(long ans, int flag) {
        if (flag == -1) {
            if (ans * (-1) < Integer.MIN_VALUE) {
                return true;
            }
            return false;
        } else {
            if (ans > Integer.MAX_VALUE) {
                return true;
            }
            return false;
        }
    }
~~~
## 数组中重复的数字
### 方法一：通过维护 nums[nums[i]] = nums[i]这个关系式，在对当前位置的数字进行遍历时，判断当前位置的数字是否满足前面的不等式，如果满足，说明当前的数字存在的次数超过1次(只有前面的维护过程中才能使得当前的数字满足等式)。
~~~ java
public static int findRepeatNumber(int[] nums) {
        int ans = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == i) {
                continue; // 不需要去维护index和index对应的值相等的关系
            }
            if (nums[nums[i]] == nums[i]) {
                ans = nums[i];
                break;
            }
            // 下面的三行是交换两个数字，目的是为了维护 nums[nums[i]] = nums[i]
            int temp = nums[i];
            nums[i] = nums[nums[i]];
            nums[temp] = temp;
        }
        return ans;
    }
~~~
## 构造乘积数组
### 方法一：通过维护前i项积和后i项积这两个数组即可。
~~~ java
public static int[] multiply(int[] A) {
        int[] f1 = new int[A.length]; // 0到i-1的乘积
        int[] f2 = new int[A.length]; // i+1到n-1的乘积

        int ans1 = 1; // 0-(i-1)的乘积
        int ans2 = 1; // (i+1)-n-1的乘积
        for (int i = 0, j = A.length - 1; i < A.length; i++, j--) {
            f1[i] = ans1;
            ans1 *= A[i];

            f2[j] = ans2;
            ans2 *= A[j];
        }
        int[] B = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = f1[i] * f2[i];
        }
        return B;
    }
~~~
## 正则表达式匹配
### 方法一：通过深搜的方式去匹配两个字符串，主要是对*字符的讨论，通过深搜的方式去模拟*号前面的字符出现的次数。
~~~ java
 public static boolean isMatch(String s, String p) {
        return solve(s, p, 0, 0);
    }

    /**
     * 字符串匹配
     * @param s 字符串1
     * @param p 字符串2
     * @param index1 字符串1的下标
     * @param index2 字符串2的下标
     * @return 当前s和p的匹配结果
     */
    private static boolean solve(String s, String p, int index1, int index2) {

        // 递归终止条件1
        if (index1 == s.length() && (index2 == p.length() || (index2 + 1 == p.length() - 1 && p.charAt(index2 + 1) == '*'))) {
            return true;
        }

        // 递归终止条件2
        if (index1 == s.length() || p.length() == index2) {
            if (index1 == s.length()) {
                return change(p, index2);
            } else {
                return false;
            }
        }

        // p当前字符的下一个位置的字符时*
        if(index2 + 1 < p.length() && p.charAt(index2 + 1) == '*') {
            if(judge(s.charAt(index1), p.charAt(index2))) {
                return solve(s, p, index1, index2 + 2) || solve(s, p, index1 + 1, index2);
            } else {
                return solve(s, p, index1, index2 + 2);
            }
        }

        // 当前两个下标所指的字符匹配
        if (judge(s.charAt(index1), p.charAt(index2))) {
            return solve(s, p, index1 + 1, index2 + 1);
        }

        return false; // 当前的index1所指的字符与index2所指的字符不一致
    }

    private static boolean change(String p, int index2) {
        while (index2 < p.length()) {
            if (index2 + 1 < p.length() && p.charAt(index2 + 1) == '*') {
                index2 += 2;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param s1 字符1
     * @param s2 字符2
     * @return 两个字符是否匹配的结果
     */
    private static boolean judge(char s1, char s2) {
        if (s1 == s2 || s2 == '.') {
            return true;
        }
        return false;
    }
    public static boolean match(char[] str, char[] pattern) {
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();

        for (char x : str) {
            s1.append(x);
        }
        for (char x : pattern) {
            s2.append(x);
        }
        return solve(s1.toString(), s2.toString(), 0, 0);
    }
~~~
## 表示数值的字符串
### 方法一：通过调用Double类的转换成Double类型的方法，判断转换的过程当中是否抛出异常即可。
~~~ java
 public boolean isNumber(String s) {
        if (s.endsWith("f") || s.endsWith("d") || s.endsWith("F") || s.endsWith("D")) {
            return false;
        }
        try {
            Double.parseDouble(s);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
~~~
## 字符流中第一个不重复的字符
### 方法一：由于字符流是不固定的，所以可以通过一个Map结构来去保存每一个字符出现的次数即可。
~~~ java
private Map<Character, Integer> map = new HashMap<>(); // 保存每个字符出现的次数
    private StringBuilder str = new StringBuilder(); // 保存字符流
    private int index = 0; // 用来保存字符只出现一次的第一个位置

    //Insert one char from stringstream
    public void Insert(char ch)
    {
        str.append(ch);
        map.put(ch, map.getOrDefault(ch, 0) + 1);
    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce()
    {
        while (index < str.length()) {
            if (map.get(str.charAt(index)) == 1) {
                return str.charAt(index);
            }
            index++;
        }
        return '#';
    }
~~~
## 链表中环的入口结点
### 方法一：通过Map结构保存链表中每个节点出现的次数，第一次出现两次的节点就是我们所要找的环的入口结点，如果没有找到，返回null
~~~ java
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
~~~
## 删除链表中重复的节点
### 方法一：首先判断出最终的链表的头节点，然后从头节点开始到链表的尾部节点这段区间内的节点元素，判断每一个节点是否满足非重复的的条件，如果满足，则将该节点采用尾接法的方式去添加到最终挂你的链表中去。
~~~ java
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
~~~
## 二叉树中的下一个节点
### 方法一：主要是分为三种情况，第一种情况就是pNode节点有右孩子时，那么pNode的下一个节点就是右孩子对应的那颗子树的最左侧的节点；如果说当前节点的右孩子为空，并且pNode是pNode父亲节点的左孩子，那么直接返回pNode的父亲节点即可；如果说当前节点的右孩子为空，并且pNode是pNode父亲节点的右孩子那么就返回pNode节点的爷爷节点。当然还有些特殊情况，比如说：二叉树的最右侧节点的判断，以及父亲节点是否为空的判断。
~~~ java
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
~~~
## 对称的二叉树
### 方法一：通过定义连个变量去遍历root节点对应的两个子树种的对应位置的节点是否相同即可。
~~~ java
public boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) {
            return true;
        }
        return solve(pRoot.left, pRoot.right);
    }

    private boolean solve(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }
        if (node1.val != node2.val) {
            return false;
        }
        return solve(node1.left, node2.right) && solve(node1.right, node2.left);
    }
~~~
## 按之字形顺序打印二叉树
### 方法一：按照宽搜的方式对二叉树进行遍历，只需要判断出每一个节点属于第几层即可，然后把每一层节点封装成一个数组，然后在判断当前层是否为偶数层，再决定是否对封装好的数组进行转置的操作
~~~ java
/*public int[] levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        ArrayList<Integer> list = new ArrayList<>();
        while (!queue.isEmpty() && root != null) {
            TreeNode node = queue.poll();
            list.add(node.val);

            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        int[] ans = new int[list.size()];
        int index = 0;
        for (int x : list) {
            ans[index++] = x;
        }
        return ans;
    }*/

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int sum = 1; // 用来保存每一层的节点的个数
        int num = 1;

        while (!queue.isEmpty() && root != null) {
            List<Integer> list = new LinkedList<>();
            int temp = 0;
            while (sum > 0) {
                TreeNode node = queue.poll();
                assert node != null;
                list.add(node.val);
                if(node.left != null) {
                    temp++;
                    queue.add(node.left);
                }
                if (node.right != null) {
                    temp++;
                    queue.add(node.right);
                }
                sum--;
            }
            sum = temp;
            if(num % 2 == 0) {
                for (int i  = 0, j = list.size() - 1; i < j; i++, j--) {
                    int res = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, res);
                }
            }
            num++;
            ans.add(list);
        }
        return ans;
    }
~~~
## 把二叉树打印成多行
### 方法一：见上道题
~~~ java
ArrayList<ArrayList<Integer>> Print(TreeNode root) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int sum = 1; // 用来保存每一层的节点的个数

        while (!queue.isEmpty() && root != null) {
            ArrayList<Integer> list = new ArrayList<>();
            int temp = 0;
            while (sum > 0) {
                TreeNode node = queue.poll();
                assert node != null;
                list.add(node.val);
                if (node.left != null) {
                    temp++;
                    queue.add(node.left);
                }
                if (node.right != null) {
                    temp++;
                    queue.add(node.right);
                }
                sum--;
            }
            sum = temp;
            ans.add(list);
        }
        return ans;
    }
~~~
## 序列化二叉树
### 方法一：序列化操作是通过对二叉树宽搜，如果当前节点是null的话，然后判断当前节点的后面的节点当中是否有非空节点，如果有，就将null写入序列胡结果，反置不写入。反序列化操作也是采用宽搜的方式，对于最终的二叉树的节点逐个的去创建。
~~~ java
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

~~~
## 二叉搜索树的第k个节点
### 方法一：通过对二叉搜索树的一个中序遍历即可，在遍历节点的过程中，判断当前遍历的节点是否为第k个节点就行了
~~~ java
private TreeNode ans;
    private int index;

    public TreeNode KthNode(TreeNode pRoot, int k) {
        index = 1;
        ans = null;
        if (k != 0 && pRoot != null) {
            solve(pRoot, k);
        }
        return ans;
    }

    private void solve(TreeNode node, int k) {
        if (ans == null) {
            if (node.left != null) {
                solve(node.left, k);
            }
            if (index == k) {
                ans = node;
            }
            index++;
            if (node.right != null) {
                solve(node.right, k);
            }
        }
    }
~~~ 
## 数据流中的中位数
### 方法一：通过大顶堆和小顶堆来去维护当前数据流中的中位数
~~~ java
private PriorityQueue<Integer> queue1 = new PriorityQueue<>(((o1, o2) -> (o2 - o1))); // 中位数的左区间 > 大顶堆
    private PriorityQueue<Integer> queue2 = new PriorityQueue<>(); // 中位数的右区间 > 小顶堆
    private int sum = 0; // 数据流中个数

    public void Insert(Integer num) {
        if (sum % 2 == 0) {
            // 当两个堆的元素个数一样的时候，此时新增一个元素，放入大顶堆(左区间)
            queue1.add(num);
        } else {
            queue2.add(num);
        }
        if(!queue2.isEmpty() && queue1.peek() > queue2.peek()) {
            assert !queue1.isEmpty();
            int temp1 = queue1.poll();
            int temp2 = queue2.poll();
            queue1.add(temp2);
            queue2.add(temp1);
        }
        sum++;
    }

    public Double GetMedian() {
        if (sum % 2 == 1) {
            return (double) queue1.peek();
        } else {
            return (queue1.peek() + queue2.peek()) / 2.0;
        }
    }
~~~
## 滑动窗口的最大值
### 方法一：通过记录之前保存的窗口中的最大值和最大值的下标来去更新当前窗口的最大值，分为两种情况，第一种情况：之前最大值还在当前数组中，那么就去比较当前区间的右端点和之前记录的最大值即可。第二种情况：之前保存的最大值不在当前区间，那么就从当前区间的左端点遍历到右端点在重新的找到一个最大值就行了
~~~ java
    public static ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (num.length == 0 || size == 0 || size > num.length) {
            return ans;
        }

        int Max = Integer.MIN_VALUE;
        int pos = -1;
        for (int i = 0; i < size; i++) {
            if (num[i] > Max) {
                Max = num[i];
                pos = i;
            }
        }
        ans.add(Max);
        for (int i = size; i <= num.length - 1; i++) { // i - > 窗口的右区间
            if (i - size + 1 <= pos) {
                if (num[i] > Max) {
                    Max = num[i];
                    pos = i;
                }
            } else {
                Max = Integer.MIN_VALUE;
                for (int j = i - size + 1; j <= i; j++) {
                    if (num[j] > Max) {
                        Max = num[j];
                        pos = j;
                    }
                }
            }
            ans.add(Max);
        }
        return ans;
    }
~~~