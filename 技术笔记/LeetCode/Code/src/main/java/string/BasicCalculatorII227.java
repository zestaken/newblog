package string;

import java.util.ArrayList;
import java.util.Stack;

public class BasicCalculatorII227 {

    ArrayList<Integer> tempNums = new ArrayList<>();

    public int calculate(String s) {

        char[] s1 = s.toCharArray();
        Stack<Integer> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();
        int res = 0;

        for(int i = 0; i < s1.length;) {
            char c = s1[i];

            i = getNums(s1, i);

            if(isSpace(c)) {
                i++;
                continue;
            } else if(isOp(c)) {
                nums.add(getNum(tempNums));

                if(ops.isEmpty()) {
                    ops.add(c);
                    i++;
                    continue;
                }
               char temp = ops.peek();
               if(temp == '+' || temp == '-' ) {
                   i++;
                   ops.add(c);
                   continue;
               }
               if (c == '*' ) {
                   int num1 = nums.pop();
                   i = getNums(s1, i);
                   int num2 = getNum(tempNums);
                   int num3;
                   num3 = num2 * num1;
                   nums.push(num3);
               }

               if(c == '/') {
                   int num1 = nums.pop();
                   i = getNums(s1, i);
                   int num2 = getNum(tempNums);
                   int num3;
                   num3 = num2 * num1;
                   nums.push(num3);
               }
            }
        }

        while(!nums.isEmpty() && !ops.isEmpty()) {
            int num1 = nums.pop();
            int num2 = nums.pop();
            int num3;
            char op = ops.pop();
            if(op == '+') {
                num3 = num1 + num2;
                res += num3;
            } else {
                num3 = num2 - num1;
                res += num3;
            }
        }
        return res;
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
        //清空集合
        list.clear();

        return res;
    }

    public int getNums(char[] s, int start) {
        int i;
        for(i = start; i < s.length; i++) {
            char c = s[i];
            if(isNum(c)) {
                tempNums.add(c - '0');
            } else {
                break;
            }
        }
        return i;
    }
}
