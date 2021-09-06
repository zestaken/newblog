package array;

import org.junit.jupiter.api.Test;

public class NumMatrixTest {

    @Test
    public void test1() {

        int[][] matrix1 = {{3,0,1,4,2},{5,6,3,2,1},{1,2,0,1,5},{4,1,0,1,7},{1,0,3,0,5}};
        NumMatrix numMatrix1 = new NumMatrix(matrix1);

        int res1 = numMatrix1.sumRegion(1,1, 2, 2);
        int expect1 = 11;
        assert res1 == expect1 : res1;

    }
}
