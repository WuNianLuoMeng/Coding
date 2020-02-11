public class Main31 {
    /*public int NumberOf1Between1AndN_Solution(int n) {
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
    }*/

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
}
