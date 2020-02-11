import java.util.ArrayList;
import java.util.Comparator;

public class Main32 {
    public String PrintMinNumber(int [] numbers) {
        ArrayList<String> list = new ArrayList<>();
        for (int x : numbers) {
            list.add(x + "");
        }
        list.sort((o1, o2) -> {
            // 下面的排序规则是核心
            String a1 = o1 + o2;
            String a2 = o2 + o1;
            return a1.compareTo(a2);
        });
        StringBuilder ans = new StringBuilder();
        for (String x : list) {
            ans.append(x);
        }
        return ans.toString();
    }
}
