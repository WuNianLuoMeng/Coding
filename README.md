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