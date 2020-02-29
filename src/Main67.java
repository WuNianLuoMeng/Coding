import java.math.BigDecimal;
import java.math.BigInteger;

public class Main67 {
    public static int cutRope(int n) {
        BigInteger[] dp = new BigInteger[n + 1];
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        dp[1] = BigInteger.ONE;
        dp[2] = BigInteger.valueOf(2);
        dp[3] = BigInteger.valueOf(3); // 1 + 2
        for (int k = 4; k <= n; k++) {
            dp[k] = BigInteger.ZERO;
            for (int i = 1; i <= k / 2; i++) {
                BigInteger temp = dp[i].multiply(dp[k - i]);
                if (dp[k].compareTo(temp) < 0) {
                    dp[k] = temp;
                }
            }
        }
        return dp[n].mod(BigInteger.valueOf(1000000007)).intValue();
    }
}
