package stack;

import java.util.Stack;

public class MyQueue {
    Stack<Integer> in;
    Stack<Integer> out;

    public MyQueue(){
        in = new Stack<>();
        out = new Stack<>();
    }

    public void push(int x) {
        in.push(x);
    }

    public int pop() {
        while(!in.isEmpty()) {
            int temp = in.pop();
            out.push(temp);
        }
        int res =  out.pop();
        while(!out.isEmpty()) {
            int temp = out.pop();
            in.push(temp);
        }
        return res;
    }

    public int peek() {
        while(!in.isEmpty()) {
            int temp = in.pop();
            out.push(temp);
        }
        int res = out.peek();
        while(!out.isEmpty()) {
            int temp = out.pop();
            in.push(temp);
        }
        return res;
    }

    public boolean empty() {
        return in.isEmpty();
    }
}
