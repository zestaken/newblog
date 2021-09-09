package string;

public class CountBinarySubstrings696 {

    public int countBinarySubstrings2(String s) {
        //统计结果
        int count = 0;
        //将字符串转为字符数组，便于遍历
        char[] s1 = s.toCharArray();
        //记录当前字符和之前不同字符的连续出现次数
        int pre = 0, cur = 0;

        //遍历字符数组，记录当前字符连续出现次数，以及之前相对当前字符不同的字符的连续出现次数
        //temp标记当前字符是0还是1
        char temp = '0';
        for(char c : s1) {

            if(c == temp) {
                //如果字符没变，则计数加一
                cur++;
            } else {
                //如果字符变化了，则将之前记录的字符连续次数，赋给统计之前次数的变量，重新开始计数
                pre = cur;
                cur = 1;
                //修改当前字符种类
                temp = c;
            }
            //当之前连续出现的字符的次数的不小于当前字符连续出现次数时，可以构成对称，计数一次
            if(pre >= cur) {
                count++;
            }
        }

        return count;
    }

    public int countBinarySubstrings(String s) {

        int count = 0;

        char[] s1 = s.toCharArray();
        int length = s1.length;

        for(int i = 0; i < length; i++) {
            int tempCount = 1;
            boolean flag = true;
            int j = i + 1;


            while(j < length && tempCount != 0) {
                if(s1[i] == s1[j]) {
                    if(flag) {
                        tempCount++;
                    } else {
                        break;
                    }

                } else {
                    tempCount--;
                    flag = false;
                }
                j++;
            }

            if(tempCount == 0) {
                count++;
            }
        }
        return count;
    }

    public int countBinarySubstrings1(String s) {

        int count = 0;

        char[] s1 = s.toCharArray();
        int length = s1.length;
        int[] log = new int[3];
        for(int m = 0; m < log.length; m++) {
            log[m] = 0;
        }

        for(int i = 0; i < length;) {
            int temp;

            if(s1[i] == '0') {
                temp = 0;
            } else {
                temp = 1;
            }
            if(i == 0) {
                log[0] = temp;
            }

            if(temp == log[0]) {
                log[1]++;
                if(log[1] == 1) {
                    log[2] = i + 1;
                }
            } else {
                log[1]--;
                if(log[1] == 0) {
                    count++;
                    if(s1[log[2]] == '0') {
                        log[0] = 0;
                    } else {
                        log[0] = 1;
                    }
                    log[1] = 0;
                    i = log[2];
                    continue;
                }
            }
            i++;
            if( i >= length && log[2] < length) {
                if(s1[log[2]] == '0') {
                    log[0] = 0;
                } else {
                    log[0] = 1;
                }
                log[1] = 0;
                i = log[2];
            }
        }
        return count;
    }
}
