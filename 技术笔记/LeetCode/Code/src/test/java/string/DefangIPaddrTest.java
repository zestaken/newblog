package string;

import org.junit.jupiter.api.Test;

public class DefangIPaddrTest {

    @Test
    public void testDeFangIpaddr1() {
        DefangIPaddr test = new DefangIPaddr();
        String res = test.deFangIpaddr1("127.0.0.1");
        String expRes = "127[.]0[.]0[.]1";
        assert res.equals(expRes) : res;
    }

    @Test
    public void testDeFangIpaddr2() {
        DefangIPaddr test = new DefangIPaddr();
        String res = test.deFangIpaddr2("127.0.0.1");
        String expRes = "127[.]0[.]0[.]1";
        assert res.equals(expRes) : res;
    }

    @Test
    public void testDeFangIpaddr3() {
        DefangIPaddr test = new DefangIPaddr();
        String res = test.deFangIpaddr3("127.0.0.1");
        String expRes = "127[.]0[.]0[.]1";
        assert res.equals(expRes) : res;
    }

    @Test
    public void testDeFangIpaddr4() {
        DefangIPaddr test = new DefangIPaddr();
        String res = test.deFangIpaddr4("127.0.0.1");
        String expRes = "127[.]0[.]0[.]1";
        assert res.equals(expRes) : res;
    }


    @Test
    public void testDeFangIpaddr5() {
        DefangIPaddr test = new DefangIPaddr();
        String res = test.deFangIpaddr5("127.0.0.1");
        String expRes = "127[.]0[.]0[.]1";
        assert res.equals(expRes) : res;
    }
}
