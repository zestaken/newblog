package string;

import java.util.*;

public class BasicCalculatorII227 {

    //用于临时存储连续的数字以便计算数值
    ArrayList<Integer> tempNums = new ArrayList<>();

    /**
     * 主计算函数，遍历字符串，按序获取对应的数值以及运算符
     * @param s
     * @return 表达式计算结果
     */
    public int calculate(String s) {
        //将字符串转换为字符数组，便于遍历
        char[] s1 = s.toCharArray();
        //用两个双端队列，按序存储数值和运算符
        Deque<Integer> nums = new LinkedList<>();
        Deque<Character> ops = new LinkedList<>();
        //存储运算结果
        int res = 0;

        //遍历整个字符数组
        for(int i = 0; i < s1.length;) {
            //获取当前位置的值
            char c = s1[i];

            //如果是数字，则获取接下来连续出现的数字，存储到动态数组中，并修改下标
            if(isNum(c)) {
                i = getNums(s1, i);
            }
            //如果是空格
            if(isSpace(c)) {
                //空格是连续数字的分隔符，计算数值，存入数值队列中
                if(!tempNums.isEmpty()) {
                    nums.addLast(getNum(tempNums));
                }
                //下标前进到下一位，进入下一次循环
                i++;
                continue;
            } else if(isOp(c)) {
                //如果是运算符
                if(!tempNums.isEmpty()) {
                    //运算符也是数字的分隔符，计算数值，存入数值队列
                    nums.addLast(getNum(tempNums));
                }
                //如果运算符队列为空，则直接存入运算符，进入下一次循环
                if(ops.isEmpty()) {
                    ops.addLast(c);
                    i++;
                    continue;
                }
                //运算符队列不为空，获取队列尾运算符
               char temp = ops.getLast();
                //如果队列尾运算符是+或-
               if(temp == '+' || temp == '-' ) {
                   //* / 的优先级比 + -高，先将乘除运算结束，将结果存入数值队列中
                   if (c == '*' ) {
                       //获取数值队列末尾元素
                       int num1 = nums.pollLast();
                       //获取当前运算符之后的连续数字并得出其值
                       i = getNums(s1, i + 1);
                       int num2 = getNum(tempNums);
                       //运算，结果存入数值队列尾
                       int num3;
                       num3 = num2 * num1;
                       nums.addLast(num3);
                   } else if (c == '/') {
                       int num1 = nums.pollLast();
                       i = getNums(s1, i + 1);
                       int num2 = getNum(tempNums);
                       int num3;
                       num3 = num1 / num2;
                       nums.addLast(num3);
                   } else {
                       //如果当前运算符是+ -，则直接存入运算符队列
                       i++;
                       ops.addLast(c);
                       continue;
                   }
               } else {
                   //没有比 * /优先级高的运算，直接存入运算符队列
                   i++;
                   ops.addLast(c);
                   continue;
               }

            }
        }
        //因为以空格和运算符作为连续数字的分隔符，所以末尾的数字有可能没有存入数值队列
        if(!tempNums.isEmpty()) {
            nums.addLast(getNum(tempNums));
        }
        //如果运算符队列为空，则说明数值队列只有一个数，无需运算，直接返回
        if(ops.isEmpty()) {
            return nums.pollFirst();
        }
        //从队列头部取出数值和运算符进行运算
        //运算规则是从左到右，如果还是用栈的思路，就是从右到左计算
        //因为高优先级的运算符都已经计算为结果了，所以都是同一优先级的运算
        while(!nums.isEmpty() && !ops.isEmpty()) {
            int num1 = nums.pollFirst();
            int num2 = nums.pollFirst();
            char op = ops.pollFirst();
            if(op == '+') {
                res = num1 + num2;
            } else if (op == '-'){
                res = num1 - num2;
            } else if (op == '*') {
                res = num1 * num2;
            } else if(op == '/') {
                res = num1 / num2;
            }
            //每一次运算的结果是下一次运算的前一位运算数，重新存入数值队列头部
            nums.addFirst(res);
        }

        return res;
    }

    /**
     * 判断字符是否是数字
     * @param c
     * @return
     */
    public boolean isNum(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        }
        return false;
    }

    /**
     * 判断字符是否是运算符
     * @param c
     * @return
     */
    public boolean isOp(char c) {
        if(c == '+' || c == '-' || c == '*' || c == '/') {
            return true;
        }
        return false;
    }

    /**
     * 判断字符是否是空格
     * @param c
     * @return
     */
    public boolean isSpace(char c) {
        if(c == ' ') {
            return true;
        }
        return false;
    }

    /**
     * 将连续的数字转换为值
     * @param list
     * @return
     */
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
        //如果开始是连续的空格，则跳过，知道有数字出现为止
        for(i = start; i < s.length; i++) {
            char c = s[i];
            if(!isSpace(c)) {
                break;
            }
        }
        //从跳过空格后的位置开始读取数字
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
