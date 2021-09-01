package hashTable;

import org.junit.jupiter.api.Test;

public class MaxPointsOnALineTest {

    @Test
    public void test1() {

        MaxPointsOnALine149 maxPointsOnALine = new MaxPointsOnALine149();

        int[][]points1 = {{1,1}, {2, 2}, {3,3}};
        int expect1 = 3;

        int[][] points2 = {{1,1},{3,2},{5,3},{4,1},{2,3},{1,4}};
        int expect2 = 4;
        
        int[][] points3 = {{0, 0}};
        int expect3 = 1;

        int[][] points4 = {{2, 3}, {3, 3}, {-5, 3}};
        int expect4 = 3;

        int res1 = maxPointsOnALine.maxPoints(points1);
        System.out.println(res1);
        assert res1 == expect1 : res1;

        int res2 = maxPointsOnALine.maxPoints(points2);
        System.out.println(res2);
        assert res2 == expect2 : res2;

        int res3 = maxPointsOnALine.maxPoints(points3);
        System.out.println(res3);
        assert res3 == expect3 : res3;

        int res4 = maxPointsOnALine.maxPoints(points4);
        System.out.println(res4);
        assert res4 == expect4 : res4;
    }
}
