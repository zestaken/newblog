package array;

import org.junit.jupiter.api.Test;

public class MaxChunksToSortedTest {

    @Test
    public void test() {
        MaxChunksToSorted769 maxChunksToSorted769 = new MaxChunksToSorted769();

        int[] arr1 = {4, 3, 2, 1, 0};
        int expect1 = 1;
        int[] arr2 = {1, 0, 2, 3, 4};
        int expect2 = 4;
        int[] arr3 = {1, 2, 0, 3};
        int expect3 = 2;
        int[] arr4 = {0, 2, 1};
        int expect4 = 2;
        int[] arr5 = {0};
        int expect5 = 1;

        int res1 = maxChunksToSorted769.maxChunksToSorted(arr1);
        int res2 = maxChunksToSorted769.maxChunksToSorted(arr2);
        int res3 = maxChunksToSorted769.maxChunksToSorted(arr3);
        int res4 = maxChunksToSorted769.maxChunksToSorted(arr4);
        int res5 = maxChunksToSorted769.maxChunksToSorted(arr5);

        assert res1 == expect1 : res1;
        assert res2 == expect2 : res2;
        assert res3 == expect3 : res3;
        assert res4 == expect4 : res4;
        assert res5 == expect5 : res5;
    }
}
