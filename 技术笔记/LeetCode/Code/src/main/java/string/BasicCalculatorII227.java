package string;

import java.util.ArrayList;
import java.util.Stack;

public class BasicCalculatorII227 {

    public int calculate(String s) {

        char[] s1 = s.toCharArray();
        ArrayList<Integer> nums = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        for(char c : s1) {
            if(isNum(c)) {
                nums.add(c - '0');
            } else if(isSpace(c)) {
                continue;
            } else if(isOp(c)) {

            }
        }
    }

    public boolean isNum(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        }
        return false;
    }

    public boolean isOp(char c) {
        if(c == '+' || c == '-' || c == '*' || c == '/') {
            return true;
        }
        return false;
    }

    public boolean isSpace(char c) {
        if(c == ' ') {
            return true;
        }
        return false;
    }

    public int getNum(ArrayList<Integer> list) {
        int length = list.size();
        int i = 0;
        int res = 0;

        //遍历数字，将每位数乘上其10的幂，加在最后的结果上
        for(int num : list) {
            res += num * Math.pow(10, length - i - 1);
            i++;
        }

        return res;
    }
}
