package test;

import java.io.InputStream;
import java.util.Scanner;

public class Main1 {

    public static void main(String[] args) {
        //读取输入
        InputStream in = System.in;
        Scanner scanner = new Scanner(in);
        String[] s = scanner.nextLine().split(" ");
        int p = Integer.parseInt(s[0]);
        int t = Integer.parseInt(s[1]);

        int m = 0;

        if(t <= 12) {
            m = p;
            System.out.println(m);
        } else {
            m = (t / 12) * p + p;
            System.out.println(m);
        }
    }
}
