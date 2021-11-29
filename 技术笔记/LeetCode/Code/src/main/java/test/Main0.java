package test;

import java.io.InputStream;
import java.util.Scanner;

public class Main0 {

    public static void main(String[] args) {
        //读取输入
        InputStream in = System.in;
        Scanner scanner = new Scanner(in);
        int[] nums = {0, 0, 0, 0, 0,0};
        int j = 0;
        while (j < 20) {
            String next = scanner.next();
            char[] chars = next.toCharArray();
            for(char c : chars) {
                int i = c - 'A';
                nums[i]++;
            }
            j++;
        }

        for(int i : nums) {
            System.out.println(i);
        }
    }
}
