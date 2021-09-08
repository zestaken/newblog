package string;

public class PalindromicSubstrings647 {

    public int countSubstrings(String s) {

        //将字符串转化为字符数组
        char[] s1 = s.toCharArray();
        int length = s1.length;
        int count = 0;

        //遍历字符数组，用当前元素确认中轴，找寻前后元素，看是否对称
        for(int i = 0; i < length; i++) {
            //要区分回文子串是偶数个还是奇数个
            //偶数个的中轴在当前元素和下一元素之间（或者说中轴是两个元素）
            //奇数个的中轴就在当前元素上
            int l = i;
            int r1 = i;
            int r2 = i + 1;
            //查找奇数个数的回文子串
            while(l >= 0 && r1 < length && s1[l] == s1[r1]) {
                count++;
                l--;
                r1++;
            }
            //复原l的值
            l = i;
            //查找偶数个的回文子串
            while(l >= 0 && r2 < length && s1[l] == s1[r2]) {
                count++;
                l--;
                r2++;
            }
        }

        return count;
    }
}
