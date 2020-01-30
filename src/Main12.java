public class Main12 {
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
}
