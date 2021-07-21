package stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class DailyTemperaturesTest {

    @Test
    public void test() {
        DailyTemperatures739 dailyTemperatures = new DailyTemperatures739();

        int[] t1 = {73,74,75,71,69,72,76,73};
        int[] expect1 = {1,1,4,2,1,1,0,0};
        int[] t2 = {30,40,50,60};
        int[] expect2 = {1,1,1,0};
        int[] t3 = {30,60,90};
        int[] expect3 = {1,1,0};

        int[] res1 = dailyTemperatures.dailyTemperatures(t1);
        int[] res2 = dailyTemperatures.dailyTemperatures(t2);
        int[] res3 = dailyTemperatures.dailyTemperatures(t3);

        assertArrayEquals(res1, expect1);
        assertArrayEquals(res2, expect2);
        assertArrayEquals(res3, expect3);
    }

}

