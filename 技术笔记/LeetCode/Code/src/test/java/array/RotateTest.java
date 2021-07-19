package array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class RotateTest {

    @Test
    public void test() {
        Rotate48 rotate48 = new Rotate48();
        int[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] expect1 = {
                {7, 4, 1},
                {8, 5, 2},
                {9, 6, 3}
        };

        rotate48.rotate(matrix1);
        for(int[] value : matrix1) {
            for(int i : value) {
                System.out.print(i);
            }
            System.out.println();
        }

        for(int i = 0; i < matrix1.length; i++) {
            assertArrayEquals(expect1[i], matrix1[i]);
        }

    }
}
