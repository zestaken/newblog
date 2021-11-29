package test;

import java.io.InputStream;
import java.util.Scanner;

public class Main4 {

    public static void main(String[] args) {
        //读取输入
        InputStream in = System.in;
        Scanner scanner = new Scanner(in);
        int n = scanner.nextInt();
        //实质是从n个数中抽取m个的组合问题
        int m = n / 2;
        int A = 1;
        for(int i = n; i > n - m; i--) {
            A *= i;
        }
        int B = 1;
        for(int i = 1; i <= m; i++) {
            B *= i;
        }

        int res = A / B;
        System.out.println(res);
    }
}
