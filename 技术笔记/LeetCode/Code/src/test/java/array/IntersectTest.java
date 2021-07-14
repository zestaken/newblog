package array;

import org.junit.jupiter.api.Test;


public class IntersectTest {

    @Test
    public void test1() {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        int[] nums3 = {4, 9, 5};
        int[] nums4 = {9, 4, 9, 8, 4};
        Intersect350 intersect = new Intersect350();
        int[] res1 = intersect.intersect(nums1, nums2);
        int[] expect1 = {2, 2};
        int[] res2 = intersect.intersect(nums3, nums4);
        int[] expect2 = {4, 9};

        assert res1.equals(expect1) : res1;
        assert res2.equals(expect2) : res2;
    }
}
