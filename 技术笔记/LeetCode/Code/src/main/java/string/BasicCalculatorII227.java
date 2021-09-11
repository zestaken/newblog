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

            if(isNum(c)) {
                i = getNums(s1, i);
            }

            if(isSpace(c)) {
                if(!tempNums.isEmpty()) {
                    nums.add(getNum(tempNums));
                }
                i++;
                continue;
            } else if(isOp(c)) {
                if(!tempNums.isEmpty()) {
                    nums.add(getNum(tempNums));
                }

                if(ops.isEmpty()) {
                    ops.add(c);
                    i++;
                    continue;
                }
               char temp = ops.peek();
               if(temp == '+' || temp == '-' ) {
                   if (c == '*' ) {
                       int num1 = nums.pop();
                       i = getNums(s1, i + 1);
                       int num2 = getNum(tempNums);
                       int num3;
                       num3 = num2 * num1;
                       nums.push(num3);
                   } else if (c == '/') {
                       int num1 = nums.pop();
                       i = getNums(s1, i + 1);
                       int num2 = getNum(tempNums);
                       int num3;
                       num3 = num1 / num2;
                       nums.push(num3);
                   } else {
                       i++;
                       ops.add(c);
                       continue;
                   }
               } else {
                   i++;
                   ops.add(c);
                   continue;
               }

            }
        }

        if(!tempNums.isEmpty()) {
            nums.add(getNum(tempNums));
        }

        if(ops.isEmpty()) {
            return nums.pop();
        }

        while(!nums.isEmpty() && !ops.isEmpty()) {
            int num1 = nums.pop();
            int num2 = nums.pop();
            char op = ops.pop();
            if(op == '+') {
                res = num1 + num2;
            } else if (op == '-'){
                res = num2 - num1;
            } else if (op == '*') {
                res = num2 * num1;
            } else if(op == '/') {
                res = num2 / num1;
            }
            nums.add(res);
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

    /**
     * 获取连续数字，并依次存入集合中
     * @param s
     * @param start 开始位置
     * @return 返回下一个非数字字符位置
     */
    public int getNums(char[] s, int start) {
        int i;
        int j;
        for(i = start; i < s.length; i++) {
            char c = s[i];
            if(!isSpace(c)) {
                break;
            }
        }

        for(j = i; j < s.length; j++) {
            char c = s[j];
            if(isNum(c)) {
                tempNums.add(c - '0');
            } else {
                break;
            }
        }
        return j;
    }
}
