public class Solution {
    public static int NumberOf1Between1AndN_Solution(int n) {
        if (n == 0) {
            return 0;
        }
        String temp = "" + n;
        int len = temp.length();
        if (len == 1) {
            return 1;
        }
        int res = (int) Math.pow(10, len - 1);
        int firstNumber = n / res;
        int firstBit = firstNumber == 1 ? (n % res) + 1 : res;
        int otherBit = firstNumber * (len - 1) * res / 10;
        return firstBit + otherBit + NumberOf1Between1AndN_Solution(n % res);
    }

    public static void main(String[] args) {
        System.out.println(NumberOf1Between1AndN_Solution(13));
    }
}