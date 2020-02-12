public class Main37 {
    private int findFirstPosition(int[] array, int k) {
        int l = 0;
        int r = array.length - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (array[mid] == k) {
                if(mid - 1 >= 0 && array[mid - 1] == k) {
                    // 说明mid当前的位置不是初始位置,k的初始位置是在l~mid-1区间
                    r = mid - 1;
                } else {
                    // 就可以说明mid位置的数字就是k的初始位置
                    return mid;
                }
            } else if (array[mid] > k) {
                r = mid - 1; // k是属于l~mid-1区间
            } else {
                l = mid + 1; // k是属于mid+1~r区间
            }
        }
        return l;
    }

    private int findLastPosition(int[] array, int k) {
        int l = 0;
        int r = array.length - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (array[mid] == k) {
                if(mid + 1 < array.length && array[mid + 1] == k) {
                    // 说明mid当前的位置不是终止位置,k的初始位置是在mid+1~r区间
                    l = mid + 1;
                } else {
                    // 就可以说明mid位置的数字就是k的终止位置
                    return mid;
                }
            } else if (array[mid] > k) {
                r = mid - 1; // k是属于l~mid-1区间
            } else {
                l = mid + 1; // k是属于mid+1~r区间
            }
        }
        return l;
    }

    public int GetNumberOfK(int [] array , int k) {
        if (array.length == 0) {
            return 0;
        }
        int firstPosition = findFirstPosition(array, k);
        int lastPosition = findLastPosition(array, k);
        if (array[firstPosition] != k) {
            return 0;
        }
        return lastPosition - firstPosition + 1;
    }
}
