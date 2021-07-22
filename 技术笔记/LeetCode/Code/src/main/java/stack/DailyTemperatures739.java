package stack;

import java.util.Stack;

public class DailyTemperatures739 {

    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        //存储下标的栈
        Stack<Integer> stack = new Stack<>();
        //用来存储结果的数组
        int[] days = new int[len];

        //遍历整个温度数组
        for(int i = 0; i < len; i++) {
            //当栈为空，或者当前遍历到的温度不大于栈中下标对应的温度，则将对应下标压入栈中
            if(stack.isEmpty() || temperatures[i] <= temperatures[stack.peek()]) {
                stack.push(i);
                continue;
            }

            //当栈不为空，或者当前温度高于栈顶下标对应的温度
            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                //当遇到比自己温度高的，出栈，将下标相减就是间隔天数
                int index = stack.pop();
                days[index] = i - index;
            }
            stack.push(i);
        }

        //最后还留在栈中的就是没有找到之后比自己温度高的，直接赋为0
        while(!stack.isEmpty()) {
            int index = stack.pop();
            days[index] = 0;
        }

        return days;
    }

}
