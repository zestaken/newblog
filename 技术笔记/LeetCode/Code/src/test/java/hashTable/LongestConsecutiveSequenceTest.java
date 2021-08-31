package hashTable;

import org.junit.jupiter.api.Test;

public class LongestConsecutiveSequenceTest {

    @Test
    public void test1() {

        LongestConsecutiveSequence128 longestConsecutiveSequence = new LongestConsecutiveSequence128();

        int[] nums1 = {100,4,200,1,3,2};
        int expect1 = 4;

        int[] nums2 = {0,3,7,2,5,8,4,6,0,1};
        int expect2 = 9;

        int[] nums3 = {4,2,2,-4,0,-2,4,-3,-4,-4,-5,1,4,-9,5,0,6,-8,-1,-3,6,5,-8,-1,-5,-1,2,-9,1};
        int expect3 = 8;
        
        int res1 = longestConsecutiveSequence.longestConsecutive(nums1);
        assert res1 == expect1 : res1;

        int res2 = longestConsecutiveSequence.longestConsecutive(nums2);
        assert res2 == expect2 : res2;

        int res3 = longestConsecutiveSequence.longestConsecutive(nums3);
        assert res3 == expect3 : res3;
    }
}
