package string;

import org.junit.jupiter.api.Test;

public class ImplementStrStrTest {

    @Test
    public void test() {
        ImplementStrStr28 implementStrStr = new ImplementStrStr28();

        String haystack1 = "hello", needle1 = "ll";
        int e1 = 2;

        String haystack2 = "aaa", needle2 = "a";
        int e2 = 0;

        int res1 = implementStrStr.strStr(haystack1, needle1);
        assert res1 == e1 : res1;

        int res2 = implementStrStr.strStr(haystack2, needle2);
        assert res2 == e2 : res2;
    }
}
