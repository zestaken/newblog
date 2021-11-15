package string;

public class ImplementStrStr28 {

//    public int strStr(String haystack, String needle) {
//        //将字符串转化为字符数组，方便遍历
//        char[] s1 = haystack.toCharArray();
//        char[] s2 = needle.toCharArray();
//        int l1 = s1.length;
//        int l2 = s2.length;
//        //如果子串为空字符串，返回0
//        if(s2.length == 0) {
//            return 0;
//        }
//        //结果默认为-1
//        int res = -1;
//
//        //遍历字符串，直到所剩长度比子串长度小
//        for(int i = 0; i + l2 <= l1; i++) {
//            //如果当前字符与子串第一个字符相同，则继续比较之后的字符
//            if(s1[i] == s2[0]) {
//                int j = i + 1;
//                int t = 1;
//                //当子串只有一位字符时，可以直接返回
//                if(t == l2) {
//                    return res = i;
//                }
//                //双指针，遍历两个字符串，逐个比较字符是否相同，直到子串遍历完
//                while (t < l2) {
//                    if(s1[j] != s2[t]) {
//                        break;
//                    }
//                    t++;
//                    j++;
//                }
//                //如果子串被遍历完了，说明完全匹配，返回开始下标即可
//                if(t == l2) {
//                    return res = i;
//                }
//            }
//        }
//        //到达这里，说明前面没有找到匹配子串，直接返回默认值-1
//        return res;
//    }

    /**
     * KMP解法
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        char[] s1 = haystack.toCharArray();
        char[] s2 = needle.toCharArray();
        int l1 = s1.length;
        int l2 = s2.length;
        int result = -1; //结果默认为-1
        //子串为空串直接返回0
        if(l2 == 0) {
            result = 0;
            return  result;
        }

        //求子串每一个元素的next数组（即当前元素之前对称的元素对数）
        int i = 0, k = -1;
        int[] next = new int[l2];
        next[0] = -1; //第一个元素设为-1，因为它之前没有元素
        while(i < l2 - 1) { //最后一个元素在倒数第二个元素遍历时已经记录
            if(k == -1 || s2[i] == s2[k]) {
                i++; //若之前有元素相等，则记在下一个元素的next值上
                k++; //k值从-1开始，与i对应元素依此比过来，如果相等则k与i同时移位，如果不等k回溯到它的next值处再比较，i不变
                //next值的求取实质是子串前半部分与后半部分比较，得元素对应相等个数；可以看成是两个串的匹配，又可以用next回溯的思想，有点递归的感觉
                next[i] = k; //k的值代表着对称相等的元素对数
            } else {
                k = next[k];
            }
        }

        int j = 0, m = 0;
        while(j + l2 - m <= l1 && m < l2) {
            if(m == -1 || s2[m] == s1[j]) { //m等于负一说明子串从头开始寻找，主串后移一位
                j++;
                m++;
            } else {
                m = next[m];
            }

            if(m  == l2) {
                result = j - m; //当前位置是子串最后一个元素对应的位置，减去子串长度即为开始匹配的位置
            }

        }

        return result;
    }

}
