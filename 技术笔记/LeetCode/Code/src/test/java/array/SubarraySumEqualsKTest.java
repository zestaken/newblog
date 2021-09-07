package array;

import org.junit.jupiter.api.Test;

public class SubarraySumEqualsKTest {

    @Test
    public void test1() {
        SubarraySumEqualsK560 subarraySumEqualsK = new SubarraySumEqualsK560();
        int[] nums1 = {1, 1, 1};
        int k1 = 2;
        int expect1 = 2;

        int[] nums2 = {1};
        int k2 = 0;
        int expect2 = 0;

        int res1 = subarraySumEqualsK.subarraySum(nums1, k1);
        assert res1 == expect1 : res1;

        int res2 = subarraySumEqualsK.subarraySum(nums2, k2);
        assert res2 == expect2 : res2;
    }
}
