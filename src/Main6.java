public class Main6 {
//    public int minNumberInRotateArray(int [] array) {
//        if (array.length == 0) {
//            return 0;
//        }
//        int ans = array[0];
//        for (int i = 1; i < array.length; i++) {
//            ans = Math.min(ans, array[i]);
//        }
//        return ans;
//    }


    public static int minNumberInRotateArray(int[] array) {
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

    public static void main(String[] args) {
        int[] array = {3,4,5,1,2};
        System.out.println(minNumberInRotateArray(array));
    }

}

// 2,2,2,2,3 -> 3, 2, 2, 2, 2