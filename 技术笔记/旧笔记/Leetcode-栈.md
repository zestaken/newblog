---
title: Leetcode-栈
date: 2021-02-06 12:49:16
tags: [LeetCode, 数据结构]
categories: 技术笔记
---

# 删除最外层的括号 1021

* 题目：
---
有效括号字符串为空 ("")、"(" + A + ")" 或 A + B，其中 A 和 B 都是有效的括号字符串，+ 代表字符串的连接。例如，""，"()"，"(())()" 和 "(()(()))" 都是有效的括号字符串。

如果有效字符串 S 非空，且不存在将其拆分为 S = A+B 的方法，我们称其为原语（primitive），其中 A 和 B 都是非空有效括号字符串。

给出一个非空有效字符串 S，考虑将其进行原语化分解，使得：S = P_1 + P_2 + ... + P_k，其中 P_i 是有效括号字符串原语。

对 S 进行原语化分解，删除分解中每个原语字符串的最外层括号，返回 S 。

 

示例 1：

输入："(()())(())"
输出："()()()"
解释：
输入字符串为 "(()())(())"，原语化分解得到 "(()())" + "(())"，
删除每个部分中的最外层括号后得到 "()()" + "()" = "()()()"。
示例 2：

输入："(()())(())(()(()))"
输出："()()()()(())"
解释：
输入字符串为 "(()())(())(()(()))"，原语化分解得到 "(()())" + "(())" + "(()(()))"，
删除每个部分中的最外层括号后得到 "()()" + "()" + "()(())" = "()()()()(())"。
示例 3：

输入："()()"
输出：""
解释：
输入字符串为 "()()"，原语化分解得到 "()" + "()"，
删除每个部分中的最外层括号后得到 "" + "" = ""。

## java解法

* 法一：
  * 使用一个栈来压入左括号，并将最外层括号设置为`*`来表示，再设置一个StringBuilder来录入除最外层括号的括号。
  * 每一次栈遇到右括号就弹出，以此来逐个匹配括号，从而判断谁为最外层的括号。
```java

import java.util.Stack;

public class Solution {
    public String removeOuterParentheses(String S) {
        Stack<Character> stack = new Stack<>();
        StringBuilder stringBuilder = new StringBuilder();

        //遍历字符串
        for(char i : S.toCharArray()) {
            //如果栈不为空，则证明不是最外层的左括号，存入stringBulider
            if(i == '(' && !stack.isEmpty()) {
                   stringBuilder.append(i);
                    stack.push(i);
            }else if(i == '(' && stack.isEmpty()) {
                stack.push('*');
            } else { //如果为右括号，则栈中内容弹出，并根据边界记号来判断是否到边界，如果不是，则将右括号也存入stringBuilder
                char temp1 = stack.pop();
                if(temp1 != '*') {
                    stringBuilder.append(i);
                }
            }
        }
        return String.valueOf(stringBuilder);
    }

    public static void main(String[] args) {
        String s = "(()())(()(()))";
        Solution solution = new Solution();
        System.out.println(solution.removeOuterParentheses(s));
    }
}
```
* 法二：
  * 设置一个数字来记录括号的层数，最外层的括号的层数为1，当层数不为1时将括号存入stringBuilder对象中。
```java
public String removeOuterParentheses(String S) {
    StringBuilder stringBuilder = new StringBuilder();
    int level = 0;
    for(char i : S.toCharArray()) {
        //层数按照左括号来记录
        if(i == '(') {
            level++;
        }
        //层数大于1时存入，需要放在level减1之前，否则右括号存不进去
        if(level > 1) {
            stringBuilder.append(i);
        }
        //当出现右括号时，将当前的层数减一，类似于用栈记录的出栈效果
        if(i == ')'){
            level--;
        }

    }

    return String.valueOf(stringBuilder);
}
```

# 剑指 Offer 09. 用两个栈实现队列

* 题目：
---
用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )

 

示例 1：

输入：
["CQueue","appendTail","deleteHead","deleteHead"]
[[],[3],[],[]]
输出：[null,null,3,-1]
示例 2：

输入：
["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
[[],[],[5],[2],[],[]]
输出：[null,-1,null,null,5,2]
提示：

1 <= values <= 10000
最多会对 appendTail、deleteHead 进行 10000 次调用

* 题目解释：
---
输入：

`["CQueue","appendTail","deleteHead","deleteHead"] `
这一行表示每一行代码的操作,其CQueue方法是构造器方法，即是创建队列的操作。

`[[],[3],[],[]]`
这个表示每一行代码操作所需要的参数

## java解法

1. 法一：
   1. 设置两个栈，每次添加数据时就向第一个栈中添加数据，每次输出数据时就先将第一个栈中的数据弹出并压入第二个栈中，再依次弹出第二个栈中的数据。
```java

package stack;

import java.util.Stack;

public class CQueue {
    Stack<Integer> stack1;
    Stack<Integer> stack2;

    public CQueue(){
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void appendTail(int value) {
        stack1.push(value);
    }

    public int deleteHead(){
        int temp;

        while(!stack1.isEmpty()) {
            temp = stack1.pop();
            stack2.push(temp);
        }

        if(stack2.isEmpty()) {
            return -1;
        }

        int value = stack2.pop();

        while(!stack2.isEmpty()) {
            temp = stack2.pop();
            stack1.push(temp);
        }

        return value;
    }

    public static void main(String[] args) {
        CQueue cQueue = new CQueue();

        cQueue.appendTail(1);
        cQueue.appendTail(2);
        cQueue.appendTail(3);

        cQueue.deleteHead();
        Stack<Integer> stack = cQueue.stack1;
        while(!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

}
```
# 1047. 删除字符串中的所有相邻重复项

* 题目：
---
给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。

在 S 上反复执行重复项删除操作，直到无法继续删除。

在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。

示例：
```
输入："abbaca"
输出："ca"
解释：
例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
```
提示：
1 <= S.length <= 20000
S 仅由小写英文字母组成。

## java解法

* 法一：
  * 设置一个栈，依次将字符串中字符压栈，栈顶内容始终是当前字符的相邻的前一个字符。
```java
class Solution {
    public String removeDuplicates(String S) {
        Stack<Character> stack = new Stack<>();
        //遍历字符串，将字符串删除重复后存入栈中
        for(char i : S.toCharArray()) {
            if(stack.isEmpty()) {
                stack.push(i);
                continue;
            }
            char temp = stack.pop();

            //如果栈顶的内容与刚读取的字符相同，则弹出栈顶内容，并进行下一次循环；如果相同，则将新字符压入栈
            if(temp == i) {
                continue;
            } else {
                stack.push(temp);
                stack.push(i);
            }
        }

        StringBuilder s1 = new StringBuilder();

        //将栈中内容从头插入新字符串
        while(!stack.isEmpty()) {
            char temp  = stack.pop();
            s1.insert(0,temp);
        }
        return s1.toString();
    }

    public static void main(String[] args) {
        String s = "abbaca";

        Solution solution = new Solution();

        String s1 = solution.removeDuplicates(s);

        System.out.println(s1);
    }
}
```
* 法二：
  * 设置一个存储所有可能的重复情况的集合，然后将字符串与集合中所有内容比较，如果有相同的则替换为空字符串。

```java
    public String removeDuplicates(String S) {
        HashSet<String> duplicates = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        //将重复字符串"aa"到"zz"存入集合中，使用哈希集合的原因是这种集合内部不允许内容重复
        for(char i = 'a'; i <= 'z'; i++) {
            sb.setLength(0);
            sb.append(i);
            sb.append(i);
            duplicates.add(sb.toString());
        }

        //遍历集合，与字符串内容比较，如果字符串中有相同内容则替换为空字符串
        //设置一个记录字符串长度的变量，如果两次while循环之后的长度没变，说明字符串中的重复字符串已经被替换完了
        int preLength = -1;
        while(preLength != S.length()) {
            preLength = S.length();
            for(String d : duplicates) {
                S = S.replace(d,"");
            }
        }
        return S;
    }
```

# 682. 棒球比赛

* 题目：
---
你现在是一场采用特殊赛制棒球比赛的记录员。这场比赛由若干回合组成，过去几回合的得分可能会影响以后几回合的得分。

比赛开始时，记录是空白的。你会得到一个记录操作的字符串列表 ops，其中 ops[i] 是你需要记录的第 i 项操作，ops 遵循下述规则：

整数 x - 表示本回合新获得分数 x
"+" - 表示本回合新获得的得分是前两次得分的总和。题目数据保证记录此操作时前面总是存在两个有效的分数。
"D" - 表示本回合新获得的得分是前一次得分的两倍。题目数据保证记录此操作时前面总是存在一个有效的分数。
"C" - 表示前一次得分无效，将其从记录中移除。题目数据保证记录此操作时前面总是存在一个有效的分数。
请你返回记录中所有得分的总和。

 

示例 1：

输入：ops = ["5","2","C","D","+"]
输出：30
解释：
"5" - 记录加 5 ，记录现在是 [5]
"2" - 记录加 2 ，记录现在是 [5, 2]
"C" - 使前一次得分的记录无效并将其移除，记录现在是 [5].
"D" - 记录加 2 * 5 = 10 ，记录现在是 [5, 10].
"+" - 记录加 5 + 10 = 15 ，记录现在是 [5, 10, 15].
所有得分的总和 5 + 10 + 15 = 30
示例 2：

输入：ops = ["5","-2","4","C","D","9","+","+"]
输出：27
解释：
"5" - 记录加 5 ，记录现在是 [5]
"-2" - 记录加 -2 ，记录现在是 [5, -2]
"4" - 记录加 4 ，记录现在是 [5, -2, 4]
"C" - 使前一次得分的记录无效并将其移除，记录现在是 [5, -2]
"D" - 记录加 2 * -2 = -4 ，记录现在是 [5, -2, -4]
"9" - 记录加 9 ，记录现在是 [5, -2, -4, 9]
"+" - 记录加 -4 + 9 = 5 ，记录现在是 [5, -2, -4, 9, 5]
"+" - 记录加 9 + 5 = 14 ，记录现在是 [5, -2, -4, 9, 5, 14]
所有得分的总和 5 + -2 + -4 + 9 + 5 + 14 = 27
示例 3：

输入：ops = ["1"]
输出：1
 

提示：

1 <= ops.length <= 1000
ops[i] 为 "C"、"D"、"+"，或者一个表示整数的字符串。整数范围是 [-3 * 104, 3 * 104]
对于 "+" 操作，题目数据保证记录此操作时前面总是存在两个有效的分数
对于 "C" 和 "D" 操作，题目数据保证记录此操作时前面总是存在一个有效的分数

## java解法

* 法一：
  * 依次读出字符串数组中的字符串，如果是特殊字符，则执行该字符对应的操作，如果是数字则转换为整型压入栈中,最后遍历栈计算和。
```java
class Solution {
    public int calPoints(String[] ops) {
        Stack<Integer> stack = new Stack<>();

        for(String op : ops) {
            if(op.equals("C")) {
                stack.pop();
            }else if(op.equals("D")) {
                stack.push(stack.peek() * 2);
            }else if(op.equals("+")) {
                int top = stack.pop();
                int newTop = top + stack.peek();
                stack.push(top);
                stack.push(newTop);
            }else {
                stack.push(Integer.parseInt(op));
            }
        }

        int sum = 0;
        for(int point : stack) {
            sum += point;
        }
        return sum;
    }

    public static void main(String[] args) {
        String[] ops = {"5","-2","4","C","D","9","+","+"};
        Solution solution = new Solution();
        int points = solution.calPoints(ops);
        System.out.println(points);
    }
}
```
* 法二：

