package string;

import org.junit.jupiter.api.Test;

public class ValidAnagramTest {

    @Test
    public void test1() {

        ValidAnagram242 validAnagram = new ValidAnagram242();
        String s1_a = "anagram";
        String s1_b = "nagaram";
        boolean expect1 = true;

        String s2_a = "rat";
        String s2_b = "car";
        boolean expect2 = false;

        boolean res1 = validAnagram.isAnagram(s1_a, s1_b);
        assert res1 == expect1 : res1;

        boolean res2 = validAnagram.isAnagram(s2_a, s2_b);
        assert res2 == expect2 : res2;
    }
}
