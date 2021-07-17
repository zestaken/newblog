package array;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class FindDisappearedNumbersTest {

    @Test
    public void test1() {
        FindDisappearedNumbers448 findDisappearedNumbers = new FindDisappearedNumbers448();
        int[] nums1 = {4,3,2,7,8,2,3,1};
        List<Integer> expect1 = new ArrayList<Integer>() {
            {
                add(5);
                add(6);
            }
        };

        int[] nums2 = {1, 1};
        List<Integer> expect2 = new ArrayList<Integer>() {
            {
                add(2);
            }
        };

        int[] nums3 = {1, 1, 2, 2};
        List<Integer> expect3 = new ArrayList<Integer>() {
            {
                add(3);
                add(4);
            }
        };

        int[] num4 = {27,40,6,21,14,36,10,19,44,10,41,26,39,20,25,19,14,7,29,27,40,38,11,44,4,6,48,39,9,13,7,45,41,23,31,8,24,1,3,5,28,11,49,29,18,4,38,32,24,15};
        Arrays.sort(num4);
        for(int i : num4) {
            System.out.print(i + " ");
        }
        System.out.println();
        List<Integer> res1 = findDisappearedNumbers.findDisappearedNumbers(nums1);
        List<Integer> res2 = findDisappearedNumbers.findDisappearedNumbers(nums2);
        List<Integer> res3 = findDisappearedNumbers.findDisappearedNumbers(nums3);
        for(int i : res1) {
            System.out.println(i);
        }
        System.out.println("---");
        for(int i : res2) {
            System.out.println(i);
        }
        assert res1.equals(expect1);
        assert res2.equals(expect2);
        assert res3.equals(expect3);
    }
}
