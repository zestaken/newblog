package array;

import org.junit.jupiter.api.Test;

public class SearchMatrixTest {

    @Test
    public void test() {
        SearchMatrix240 searchMatrix240 = new SearchMatrix240();
        int[][] matrix1 = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        int target1 = 5;
        int target2 = 20;
        boolean expect1 = true;
        boolean expect2 = false;
        boolean res1 = searchMatrix240.searchMatrix(matrix1, target1);
        boolean res2 = searchMatrix240.searchMatrix(matrix1, target2);
        assert res1 == expect1;
        assert res2 == expect2;
    }
}
