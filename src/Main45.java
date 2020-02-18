import java.util.Arrays;

public class Main45 {
    public boolean isContinuous(int [] numbers) {
        if (numbers.length == 0) {
            return false;
        }
        int sum = 0;
        for (int x : numbers) {
            if (x == 0) {
                sum++;
            }
        }
        Arrays.sort(numbers);
        for (int i = sum + 1; i < numbers.length; i++) {
            sum -= numbers[i] - numbers[i - 1] - 1;
            if (sum < 0 || numbers[i] == numbers[i - 1]) {
                return false;
            }
        }
        return true;
    }
}
// 0 0 1 1 5