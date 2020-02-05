import java.util.Stack;

public class Main20 {
    private Stack<Integer> dataStack = new Stack<>(); // 数据栈
    private Stack<Integer> minStack = new Stack<>(); // 维护min函数的栈

    public void push(int node) {
        dataStack.push(node);

        if (minStack.isEmpty() || minStack.peek() > dataStack.peek()) {
            minStack.push(dataStack.peek()); // 当前minStack的栈顶元素大于数据栈的栈顶元素
        } else {
            minStack.push(minStack.peek()); // 当前minStack的栈顶元素小于数据栈的栈顶元素
        }
    }

    public void pop() {
        if (!dataStack.isEmpty()) {
            dataStack.pop();
        }
        if (!minStack.isEmpty()) {
            minStack.pop();
        }
    }

    public int top() {
        // 取出数据栈的栈顶元素
        return dataStack.peek();
    }

    public int min() {
        // 取出维护min函数的栈的栈顶元素
        return minStack.peek();
    }
}
