package stack;

import java.util.Stack;

public class DailyTemperatures739 {

    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        Stack<Integer> stack = new Stack<>();
        int[] days = new int[len];

        for(int i = 0; i < len; i++) {
            if(stack.isEmpty() || temperatures[i] > temperatures[stack.peek()]) {
                days[stack.peek()] = i - stack.peek();
            } else if(temperatures[i] <= temperatures[stack.peek()]) {
                stack.push(i);
            }
        }

        return days;
    }

}
