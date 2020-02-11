public class Main30 {
    /*   public int FindGreatestSumOfSubArray(int[] array) {
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
       }*/
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
}
