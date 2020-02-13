public class Main40 {
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
}
