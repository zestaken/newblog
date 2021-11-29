package test;

import java.io.InputStream;
import java.util.Scanner;

public class Main5 {

    public static void main(String[] args) {
        InputStream in = System.in;
        Scanner scanner = new Scanner(in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int j = 0;
        int[][] nums = new int[n][m];
        while(j < n) {
            String next = scanner.next();
            char[] chars = next.toCharArray();
            for(int i = 0; i < m; i++) {
                nums[j][i] = Integer.parseInt(String.valueOf(chars[i]));
            }
            j++;
        }

        int[][] state = new int[n][m];




    }
}
