package string;

public class ValidAnagram242 {

    public boolean isAnagram(String s, String t) {
        //存储判断结果
        boolean res = true;
        //用长度为26的整数数组表示每个字母出现的次数（下标表示字母，值代表出现次数）
        int[] abc = new int[26];
        //将字符串转换为字符数组遍历，将字母转化为数字，存储出现次数
        for(char i : s.toCharArray()) {
            abc[i - 'a']++;
        }
        //遍历第二个字符数组，出现一个字母则对应位置次数减一
        for(char i : t.toCharArray()) {
            abc[i - 'a']--;
        }

        //遍历数组，如果有对应位置字母出现次数不同，则对应位置的值不为0，返回false
        for(int i = 0; i < 26; i++) {
            if(abc[i] != 0) {
                return res = false;
            }
        }
        //所有元素都相等
        return res;
    }
}
