package string;

import java.util.HashMap;
import java.util.Map;

public class IsomorphicStrings205 {
    public boolean isIsomorphic(String s, String t) {
        //将字符串转化为字符数组，便于遍历
        char[] s1 = s.toCharArray();
        char[] s2 = t.toCharArray();
        //创建两个哈希集合，存储字符串中字符值及其第一次出现的位置
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        //遍历两个字符数组，同时记录位置，如果对应位置的字符的第一次出现的位置不同，则不是同构字符串
        for(int i = 0; i < s1.length; i++) {
            //用两个变量记录对应位置字母第一次出现的位置
            int pos1 = i;
            int pos2 = i;
            //如果当前位置的字母是第一次出现，则存入哈希表，如果不是，则取出第一次出现位置
            if(!map1.containsKey(s1[i])) {
                map1.put(s1[i], i);
            } else {
               pos1 = map1.get(s1[i]);
            }

            if(!map2.containsKey(s2[i])) {
                map2.put(s2[i], i);
            } else {
                pos2 = map2.get(s2[i]);
            }
            //如果两个字母第一次出现的位置不同，则不是同构字符串
            if(pos1 != pos2) {
                return false;
            }
        }
        return true;
    }
}
