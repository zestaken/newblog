package array;

import java.util.ArrayList;

public class Intersect350 {
    public int[] intersect(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (length1 >= length2) {
            for(int i : nums2) {
                for(int j : nums1) {
                    if(i == j) {
                        res.add(i);
                    }
                }
            }
        } else {
            for(int i : nums1) {
                for(int j : nums2) {
                    if(i == j) {
                        res.add(i);
                    }
                }
            }
        }

        return res.toArray(new Integer[(length1 >= length2?length1:length2)]);
    }
}
