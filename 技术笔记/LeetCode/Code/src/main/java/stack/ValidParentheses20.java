package stack;

import java.util.Stack;

public class ValidParentheses20 {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        int len = s.length();

        if(len % 2 != 0) { //括号数为奇数肯定不对
            return false;
        }

        //遍历括号，左括号进栈，右括号出栈比对
        int i;
        for(i = 0; i < len; i++) {
            char temp = s.charAt(i);
            if(temp == '(' || temp == '[' || temp == '{') {
                stack.push(temp);
            } else {
                char temp2;
                if(!stack.isEmpty()) {
                    temp2 = stack.pop();
                } else {
                    return false;
                }
                if(temp2 == '(' && temp == ')') {
                    continue;
                } else if(temp2 == '[' && temp == ']') {
                    continue;
                } else if(temp2 == '{' && temp == '}') {
                    continue;
                } else {
                    return false;
                }
            }
        }

        //最终字符串遍历完并且栈空则说明配对成功
        if(stack.isEmpty() || i == len - 1) {
            return true;
        } else {
            return false;
        }
    }

}
