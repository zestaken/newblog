package hashTable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TwoSumTest {

    @Test
    public void test1() {

        TwoSum1 twoSum1 = new TwoSum1();

        int[] nums1 = {2, 7, 11, 5};
        int target1 = 9;
        int[] expect1 = {0, 1};

        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        int[] expect2 = {1, 2};

        int[] res1 = twoSum1.twoSum(nums1, target1);
        assertArrayEquals(res1, expect1);

        int[] res2 = twoSum1.twoSum(nums2, target2);
        assertArrayEquals(res2, expect2);

    }

}
