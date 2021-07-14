package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class IntersectTest {

    @Test
    public void test1() {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        int[] nums3 = {4, 9, 5};
        int[] nums4 = {9, 4, 9, 8, 4};
        int[] nums5 = {1, 2};
        int[] nums6 = {1, 1};
        Intersect350 intersect = new Intersect350();
        int[] res1 = intersect.intersect(nums1, nums2);
        int[] expect1 = {2, 2};
        int[] res2 = intersect.intersect(nums3, nums4);
        int[] expect2 = {4, 9};
        int[] expect2_1 = {9, 4};
        int[] res3 = intersect.intersect(nums5, nums6);
        int[] expect3 = {1};
        for(int i : res1) {
            System.out.println(i);
        }

        assertArrayEquals(expect1, res1);
        assertArrayEquals(expect2, res2 );
//        assertArrayEquals(expect2_1, res2);
        assertArrayEquals(expect3, res3);
    }
}
