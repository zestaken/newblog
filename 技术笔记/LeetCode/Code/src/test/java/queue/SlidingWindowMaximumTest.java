package queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SlidingWindowMaximumTest {

    @Test
    public void test1() {
        SlidingWindowMaximum239 slidingWindowMaximum239 = new SlidingWindowMaximum239();
        int[] nums1 = {1,3,-1,-3,5,3,6,7};
        int k1 = 3;
        int[] expect1 = {3,3,5,5,6,7};

        int[] nums2 = {1};
        int k2 = 1;
        int[] expect2 = {1};

        int[] nums3 = {1,3,1,2,0,5};
        int k3 = 3;
        int[] expect3 = {3, 3, 2, 5};

        int[] nums4 = {1, -1};
        int k4 = 1;
        int[] expect4 = {1, -1};

        int[] res1 = slidingWindowMaximum239.maxSlidingWindow(nums1, k1);
        System.out.print("res1: ");
        for(int i = 0; i < res1.length; i++) {
            System.out.print(res1[i]);
        }
        assertArrayEquals(res1, expect1);

        int[] res2 = slidingWindowMaximum239.maxSlidingWindow(nums2, k2);
        System.out.print("\nres2: ");
        for(int i = 0; i < res2.length; i++) {
            System.out.print(res2[i]);
        }
        assertArrayEquals(res2, expect2);

        int[] res3 = slidingWindowMaximum239.maxSlidingWindow(nums3, k3);
        System.out.print("\nres3: ");
        for(int i = 0; i < res3.length; i++) {
            System.out.print(res3[i]);
        }
        assertArrayEquals(res3, expect3);

        int[] res4 = slidingWindowMaximum239.maxSlidingWindow(nums4, k4);
        System.out.print("\nres4: ");
        for(int i = 0; i < res4.length; i++) {
            System.out.print(res4[i]);
        }
        assertArrayEquals(res4, expect4);
    }
}
