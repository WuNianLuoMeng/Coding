public class Main35 {
    private long sum; // 用来去统计逆序对的个数
    public int InversePairs(int [] array) {
        sum = 0;
        int l = 0;
        int r = array.length - 1;
        divide(l ,r, array);
        return (int) (sum % 1000000007);
    }

    private void divide(int l, int r, int[] array) {
        if (l != r) {
            int mid = (l + r) >> 1;
            divide(l, mid, array);
            divide(mid + 1, r, array);
            merge(l, r, mid, array);
        }
    }

    private void merge(int l, int r, int mid, int[] array) {
        int i = l; // 左区间的起点
        int j = mid + 1; // 右区间的起点
        int[] temp = new int[r - l + 1];
        int index = 0;
        while (i <= mid && j <= r) {
            if (array[i] > array[j]) {
                temp[index++] = array[j++];
                sum += mid - i + 1; // 这一行是核心，去统计逆序对个数，统计的基础是在归并排序的合并过程中，合并的两个子序列都是有序的
            } else {
                temp[index++] = array[i++];
            }
        }
        while (i <= mid) {
            temp[index++] = array[i++];
        }
        while (j <= r) {
            temp[index++] = array[j++];
        }
        index = 0;
        for (int k = l; k <= r; k++) {
            array[k] = temp[index++];
        }
    }
}
