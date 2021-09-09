package string;

import java.util.Stack;

public class CountBinarySubstrings696 {

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
