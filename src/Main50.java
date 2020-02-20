public class Main50 {
    public static int findRepeatNumber(int[] nums) {
        int ans = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == i) {
                continue; // 不需要去维护index和index对应的值相等的关系
            }
            if (nums[nums[i]] == nums[i]) {
                ans = nums[i];
                break;
            }
            // 下面的三行是交换两个数字，目的是为了维护 nums[nums[i]] = nums[i]
            int temp = nums[i];
            nums[i] = nums[nums[i]];
            nums[temp] = temp;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] a = new int[]{2, 3, 1, 0, 2, 5, 3};
        System.out.println(findRepeatNumber(a));
    }
}
