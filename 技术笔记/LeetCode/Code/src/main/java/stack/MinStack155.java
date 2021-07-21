package stack;

import java.util.Stack;

public class MinStack155 {
    Stack<Integer> value;
    Stack<Integer> min;

    public MinStack155() {
        value = new Stack<>();
        min = new Stack<>();
    }

    public void push(int val) {
        //若当前值比栈中存的最小值小，则存入最小值栈中
        if(min.isEmpty()) {
            min.push(val);
        }else if(val <= min.peek()) { //注意与最小值相等也要再压一次，弹出的时候才能持平
            min.push(val);
        }
        value.push(val);
    }

    public int pop() {
        if(value.peek().equals(min.peek())) {
            min.pop();
        }

        return value.pop();
    }

    public int top() {
        return value.peek();
    }

    public int getMin() {
        return min.peek();
    }

}
