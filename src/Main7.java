public class Main7 {
//    public int Fibonacci(int n) {
//        if (n == 0) {
//            return 0;
//        }
//        int[] a = new int[n + 1];
//        if (n == 1 || n == 2) {
//            return 1;
//        }
//        a[1] = 1;
//        a[2] = 1;
//        for (int i = 3; i <= n; i++) {
//            a[i] = a[i - 1] + a[i - 2];
//        }
//        return a[n];
//    }

//    a[n] = a[n - 1] + a[n - 2];

    private static int Fibonacci(int n) {
        if (n == 0) {
            return 0; /// 终止递归的条件
        }
        if (n == 1 || n == 2) {
            return 1; /// 终止递归的条件
        }
        return Fibonacci(n - 1) + Fibonacci(n -2);
    }
}
