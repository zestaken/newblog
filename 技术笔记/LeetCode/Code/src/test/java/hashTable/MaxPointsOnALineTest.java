package hashTable;

import org.junit.jupiter.api.Test;

public class MaxPointsOnALineTest {

    @Test
    public void test1() {

        MaxPointsOnALine149 maxPointsOnALine = new MaxPointsOnALine149();

        int[][] points1 = {{1,1}, {2, 2}, {3,3}};
        int expect1 = 3;

        int res1 = maxPointsOnALine.maxPoints(points1);
        System.out.println(res1);
        assert res1 == expect1 : res1;
    }
}
