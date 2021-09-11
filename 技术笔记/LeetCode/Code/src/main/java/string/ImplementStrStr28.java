package string;

public class ImplementStrStr28 {

    public int strStr(String haystack, String needle) {
        //将字符串转化为字符数组，方便遍历
        char[] s1 = haystack.toCharArray();
        char[] s2 = needle.toCharArray();
        int l1 = s1.length;
        int l2 = s2.length;
        //如果子串为空字符串，返回0
        if(s2.length == 0) {
            return 0;
        }
        //结果默认为-1
        int res = -1;

        //遍历字符串，直到所剩长度比子串长度小
        for(int i = 0; i + l2 <= l1; i++) {
            //如果当前字符与子串第一个字符相同，则继续比较之后的字符
            if(s1[i] == s2[0]) {
                int j = i + 1;
                int t = 1;
                //当子串只有一位字符时，可以直接返回
                if(t == l2) {
                    return res = i;
                }
                //双指针，遍历两个字符串，逐个比较字符是否相同，直到子串遍历完
                while (t < l2) {
                    if(s1[j] != s2[t]) {
                        break;
                    }
                    t++;
                    j++;
                }
                //如果子串被遍历完了，说明完全匹配，返回开始下标即可
                if(t == l2) {
                    return res = i;
                }
            }
        }
        //到达这里，说明前面没有找到匹配子串，直接返回默认值-1
        return res;
    }
}
