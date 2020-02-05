import java.util.Stack;

public class Main21 {
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        Stack<Integer> stack = new Stack<>();
        int pushIndex = 0; // 入栈序列的下标
        int popIndex = 0; // 出栈序列的下标

        while (pushIndex < pushA.length) {
            if (!stack.isEmpty() && stack.peek() == popA[popIndex]) {
                stack.pop();
                popIndex++;
            } else {
                stack.push(pushA[pushIndex]);
                pushIndex++;
            }
        }

        // 下面的这个while循环其实就是为了防止当所有入栈的元素都压入栈的时候，栈顶元素和出栈序列的下标所指的数字没有来得及比较。
        while (!stack.isEmpty()) {
            if (stack.peek() == popA[popIndex]) {
                stack.pop();
                popIndex++;
            } else {
                return false;
            }
        }
        return true;

    }
}
