package string;

import org.junit.jupiter.api.Test;

public class BasicCalculatorIITest {

    @Test
    public void test() {

        BasicCalculatorII227 basicCalculatorII = new BasicCalculatorII227();

        String s1 = "3+2*2";
        int e1 = 7;

        String s2 = " 3/2 ";
        int e2 = 1;
        
        String s3 = " 3+5 / 2 ";
        int e3 = 5;

        String s4 = "1";
        int e4 = 1;
        
        String s5 = "5   ";
        int e5 = 5;
        
        String s6 = "1 + 1";
        int e6 = 2;

        String s7 = "0*1";
        int e7 = 0;
        
        String s8 = "1+1+1";
        int e8 = 3;


        int res1 = basicCalculatorII.calculate(s1);
        assert res1 == e1 : res1;

        int res2 = basicCalculatorII.calculate(s2);
        assert res2 == e2 : res2;

        int res3 = basicCalculatorII.calculate(s3);
        assert res3 == e3 : res3;

        int res4 = basicCalculatorII.calculate(s4);
        assert res4 == e4 : res4;

        int res5 = basicCalculatorII.calculate(s5);
        assert res5 == e5 : res5;

        int res6 = basicCalculatorII.calculate(s6);
        assert res6 == e6 : res6;

        int res7 = basicCalculatorII.calculate(s7);
        assert res7 == e7 : res7;

        int res8 = basicCalculatorII.calculate(s8);
        assert res8 == e8 : res8;
    }
}
