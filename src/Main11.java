public class Main11 {
//    public int NumberOf1(int n) {
//        int sum = 0; /// 记录1的个数
//        int temp = 1; /// 本质上是用temp变量去判断n的每一位数字是否为1
//        while (temp != 0) { /// 当temp为0的时候，说明已经移动了32次，然后就说明已经遍历完了n的每一位
//            sum = (n & temp) != 0 ? sum + 1 : sum;
//            temp = temp << 1;
//        }
//        return sum;
//    }

    public int NumberOf1(int n) {
        int sum = 0; /// 记录1的个数
        while (n != 0) {  /// 说明当前n的二进制表示中肯定有1
            sum++;
            n = n & (n - 1); /// 本质上就是消除从右往左数的第一个位置的1。
        }
        return sum;
    }

    public static void main(String[] args) {
//        int i = -16;
//        while (i != 0) {
//            System.out.println(i);
//            i = i >> 1;
//        }
    }
}

/// 第二种方法
// 5 -》 101 & 1 —》 10 & 1  -》 1 & 1 -》 0 & 1这种方法是有问题的。
// 1 -> 0000000...01 -> (-1) -> 11....11111 -> 右移1位，数字-1的二进制的左边是补1的，也就是说，无论你右移多少次，结果都是-1.
//
// 改进：对n&运算的后面的那个数字进行操作：
// 5-》 101 & 1 -》 101 & 10 -》 101 & 100


/// 第三种方法
// 5 -》 101 & 100(101 - 1) = 100 -》 100 & 011(100 - 1) = 000 -》 000

