package string;

import org.junit.jupiter.api.Test;

public class IsomorphicStringsTest {

    @Test
    public void test1() {
        IsomorphicStrings205 isomorphicStrings = new IsomorphicStrings205();

        String s1_a = "paper";
        String s1_b = "title";
        boolean expect1 = true;

        String s2_a = "bbbaaaba";
        String s2_b = "aaabbbba";
        boolean expect2 = false;

        boolean res1 = isomorphicStrings.isIsomorphic(s1_a, s1_b);
        assert res1 == expect1 : res1;

        boolean res2 = isomorphicStrings.isIsomorphic(s2_a, s2_b);
        assert res2 == expect2 : res2;
    }
}
