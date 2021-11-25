import java.util.ArrayList;
import java.util.HashMap;

public class Main{

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int len = N;
        //将参数添加到哈希表
        HashMap<String, float[]> map = new HashMap<>();
        String[] names = new String[len];
        for(int i = 1; i < len; i++) {
            String[] strs = args[i].split(" ");
            float[] temp = new float[7];
            float sum = 0;
            for(int j = 1; j < 7; j++) {
                float num = Float.parseFloat(strs[j]);
                sum += num;
                temp[j - 1] = num;
            }
            temp[temp.length] = sum;
            map.put(strs[0], temp);
            names[i - 1] = strs[0];
        }
        //排序
        String[] sort = new String[len - 1];
        for(int i = 0; i < len; i++) {
            for(int j = 0; j < len - i; j++) {
                if(map.get(names[j])[6] < map.get(names[j+i])[6]) {
                    String temp = names[j];
                    names[j]  = names[j + i];
                    names[j + i] = temp;
                }
                if (map.get(names[j])[6] == map.get(names[j+i])[6]) {
                    if(map.get(names[j])[5] < map.get(names[i + j])[5]) {
                        String temp = names[j];
                        names[j]  = names[j + i];
                        names[j + i] = temp;
                    }
                }

            }
        }
        //打印结果
        for(String name : names) {
            System.out.println(name);
        }

    }


}
