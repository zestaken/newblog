package stack;

import org.junit.jupiter.api.Test;

public class ValidParenthesesTest {

    @Test
    public void test() {
        ValidParentheses20 validParentheses = new ValidParentheses20();
        String s1 = "()";
        boolean expect1 = true;
        String s2 = "()[]{}";
        boolean expect2 = true;
        String s3 = "(]";
        boolean expect3 = false;
        String s4 = "([)]";
        boolean expect4 = false;
        String s5 = "{[]}";
        boolean expect5 = true;
        String s6 = "]";
        boolean expect6 = false;
        String s7 = "){";
        boolean expect7 = false;

        boolean res1 = validParentheses.isValid(s1);
        boolean res2 = validParentheses.isValid(s2);
        boolean res3 = validParentheses.isValid(s3);
        boolean res4 = validParentheses.isValid(s4);
        boolean res5 = validParentheses.isValid(s5);
        boolean res6 = validParentheses.isValid(s6);
        boolean res7 = validParentheses.isValid(s7);

        assert res1 == expect1 : res1;
        assert res2 == expect2 : res2;
        assert res3 == expect3 : res3;
        assert res4 == expect4 : res4;
        assert res5 == expect5 : res5;
        assert res6 == expect6 : res6;
        assert res7 == expect7 : res7;
    }
}
