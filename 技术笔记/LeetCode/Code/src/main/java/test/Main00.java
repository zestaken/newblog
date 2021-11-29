package test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Main00 {

    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();
        for(int i = 1; i <= 2021; i++) {
            for(int j = 1; j < 2021; j++) {
                for(int m = j; m < 2021; m++) {
                    int temp = (m + j) * (m -j);
                    if(temp == i) {
                        if(!set.contains(i)) {
                            set.add(i);
                        }
                    }
                }
            }
        }

        System.out.println(set.size());

    }
}
