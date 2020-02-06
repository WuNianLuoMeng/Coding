import java.util.ArrayList;

public class Main23 {
    private boolean solve(ArrayList<Integer> list) {
        // 递归终止的条件
        if (list.size() == 0 || list.size() == 1) {
            return true;
        }
        ArrayList<Integer> minList = new ArrayList<>(); // 用来保存小于endNumber数字的序列
        ArrayList<Integer> maxList = new ArrayList<>(); // 用来保存大于endNumber数字的序列
        int endNumber = list.get(list.size() - 1);
        int minIndex = -1; // 用来记录minList中第一个数字的位置
        int maxIndex = -1; // 用来记录maxList中第一个数字的位置
        // 下面这个循环其实就是对当前list序列的一个分割(分割条件就是endNumber)
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) > endNumber) {
                if (maxIndex == -1) {
                    maxIndex = i;
                }
                maxList.add(list.get(i));
            } else if (list.get(i) < endNumber) {
                if (minIndex == -1) {
                    minIndex = i;
                }
                minList.add(list.get(i));
            }
        }
        if (minIndex != -1 && maxIndex != -1) {
            if (minIndex > maxIndex) {
                return false; // 本质上使右子树的序列在左子树的前面，不满足后序遍历
            }
            for (int i = maxIndex; i < list.size(); i++) {
                if (list.get(i) < endNumber) {
                    return false; // 说明在大于endNumber的序列初始位置到末尾，不连续，中间有小于endNumber的数字分割开来
                }
            }
        }
        return solve(minList) && solve(maxList); // && -> 每一个子序列都是需要满足的
    }
    public boolean VerifySquenceOfBST(int [] sequence) {
        if (sequence.length == 0) {
            return false;
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < sequence.length; i++) {
            list.add(sequence[i]);
        }
        return solve(list);
    }
}
