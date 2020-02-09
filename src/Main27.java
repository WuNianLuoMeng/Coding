import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Main27 {
    private String change(char[] a) {
        StringBuilder res = new StringBuilder();
        for (char value : a) {
            res.append(value);
        }
        return res.toString();
    }
    private void solve(ArrayList<String> ans, char[] a, int index, int length) {
        if (index == length - 1) {
            String res = change(a);
            ans.add(res);
        } else {
            // 就说明现在要去确定index位置的字符
            for (int i = index; i < length; i++) {
                char temp = a[i];
                a[i] = a[index];
                a[index] = temp;
                // 当前index位置的字符已经通过交换找到了，那么就递归去找下一个位置的字符
                solve(ans, a, index + 1, length);
                // 其实就是去为了消除当前层去递归的时候的进行交换字符的影响，如果不消除的话，那么就会造成原index位置的字符发生变化
                temp = a[i];
                a[i] = a[index];
                a[index] = temp;

            }
        }
    }
    public ArrayList<String> Permutation(String str) {
        char[] a = str.toCharArray();
        ArrayList<String> ans = new ArrayList<>();
        solve(ans, a, 0, str.length());
        ans = new ArrayList<String>(new HashSet<String>(ans)); // 去重操作
        Collections.sort(ans); // 字典排序 -> ans.sort(null);
        return ans;
    }
}
