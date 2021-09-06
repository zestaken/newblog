package array;

import org.junit.jupiter.api.Test;

public class NumArrayTest {

    @Test
    public void test1() {

        int[] nums1 = {-2, 0, 3, -5, 2, -1};
        NumArray numArray1 = new NumArray(nums1);
        int res1 = numArray1.sumRange(2, 5);
        int expect1 = -1;
        assert res1 == expect1 : res1;
        int res2 = numArray1.sumRange(0, 2);
        int expect2 = 1;
        assert res2 == expect2 : res2;
    }
}
