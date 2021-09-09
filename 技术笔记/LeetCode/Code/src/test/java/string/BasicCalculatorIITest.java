package string;

import org.junit.jupiter.api.Test;

public class BasicCalculatorIITest {

    @Test
    public void test() {

        BasicCalculatorII227 basicCalculatorII = new BasicCalculatorII227();

        String s1 = "3+2*2";
        int e1 = 7;

        int res1 = basicCalculatorII.calculate(s1);
        assert res1 == e1 : res1;
    }
}
