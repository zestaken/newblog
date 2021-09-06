package array;

public class NumMatrix {

    private int[][] partialSum;
    public NumMatrix(int[][] matrix) {
        //初始化前缀和数组
        partialSum = new int[matrix.length][matrix[0].length + 1];
        //计算前缀和
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                partialSum[i][j+1] = partialSum[i][j] + matrix[i][j];
            }
        }

    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        for(int i = 0; i < partialSum.length; i++) {
            System.out.println();
            for(int j = 0; j < partialSum[0].length; j++) {
                System.out.print(partialSum[i][j] + " ");
            }
        }
        int sum = 0;
        //逐行扫描，每一行依次用前缀和相减计算中间和
        for(int i = row1; i <= row2; i++) {
            sum += (partialSum[i][col1 + 1] - partialSum[i][col2]);
        }
        return sum;
    }
}
