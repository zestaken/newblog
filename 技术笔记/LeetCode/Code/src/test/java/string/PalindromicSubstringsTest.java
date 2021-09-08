package string;

import org.junit.jupiter.api.Test;

public class PalindromicSubstringsTest {

    @Test
    public void test1() {

        PalindromicSubstrings647 palindromicSubstrings = new PalindromicSubstrings647();

        String s1 = "abc";
        int expect1 = 3;

        String s2 = "aaa";
        int expect2 = 6;
        
        int res1 = palindromicSubstrings.countSubstrings(s1);
        assert res1 == expect1 : res1;

        int res2 = palindromicSubstrings.countSubstrings(s2);
        assert res2 == expect2 : res2;
    }
}
