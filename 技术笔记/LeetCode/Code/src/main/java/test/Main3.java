package test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

public class Main3 {

    public static void main(String[] args) {
        //读取输入
        InputStream in = System.in;
        Scanner scanner = new Scanner(in);
        int n  = Integer.parseInt(scanner.nextLine());
        String[] s = scanner.nextLine().split(" ");
        int[] a = new int[n];
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(s[i]);
        }
        int p = 0;
        //记录编号与卡片映射关系
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            if(i+1 != a[i]) {
                map.put(i+1, a[i]);
            } else {
                p++;
            }
        }
        for(int i = 0; i < n; i++) {
                int j = i + 1;
                boolean flag = false;
                while (map.containsKey(j)){
                    flag = true;
                    int temp = j;
                    j = map.get(j);
                    map.remove(temp);
                }
                if(flag) {
                    //已经建立联系的两个人看作一个整体, 一个整体只需问一人
                    p++;
                }
        }
        System.out.println(p);

    }
}
