package test;

import java.io.InputStream;
import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {
        //读取输入
        InputStream in = System.in;
        Scanner scanner = new Scanner(in);
        String[] s = scanner.nextLine().split(" ");
        //保存边长的数组
        float[] edges = new float[3];
        edges[0] = Float.parseFloat(s[0]);
        edges[1] = Float.parseFloat(s[1]);
        edges[2] = Float.parseFloat(s[2]);

        //对边长进行冒泡排序，大的边长在前面
        for(int i = 0; i < edges.length; i++) {
            for(int j = 0; j < edges.length; j++) {
                if(edges[i] > edges[j]) {
                    float temp = edges[i];
                    edges[i] = edges[j];
                    edges[j] = temp;
                }
            }
        }
        //根据两小边之平方和等于大边平方判断
        if(edges[0] * edges[0] == edges[1] * edges[1] + edges[2] * edges[2]) {
            System.out.println("YES");
        } else {
            System.out.println("No");
        }

    }
}
