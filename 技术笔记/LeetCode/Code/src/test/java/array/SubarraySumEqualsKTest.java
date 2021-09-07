package array;

import org.junit.jupiter.api.Test;

public class SubarraySumEqualsKTest {

    @Test
    public void test1() {
        SubarraySumEqualsK560 subarraySumEqualsK = new SubarraySumEqualsK560();
        int[] nums1 = {1, 1, 1};
        int k1 = 2;
        int expect1 = 2;

        int res1 = subarraySumEqualsK.subarraySum(nums1, k1);
        assert res1 == expect1 : res1;
    }
}
