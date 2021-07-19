package array;

public class SearchMatrix240 {
    public boolean searchMatrix(int[][] matrix, int target) {
        //从右上角开始搜索
        int lines = matrix.length;
        int rows = matrix[0].length;

        for(int i = 0, j = rows - 1; i < lines && 0 <= j; ) {
            if(matrix[i][j] > target) {
                //比目标大，向左横向移动
                j--;
                if(j < 0) {
                    return false;
                }
            }else if(matrix[i][j] < target) {
                //比目标小，向下移动
                i++;
                if(i >= lines) {
                    return false;
                }
            } else if (matrix[i][j] == target) {
                return true;
            }
        }
        return false;
    }

    public boolean searchMatrix1(int[][] matrix, int target) {
        //矩阵的行数和列数
        int lines = matrix.length;
        int rows = matrix[0].length;

        //从小到大遍历矩阵找指定元素
        for(int i = 0, j = 0; i < lines && j < rows ; ) {
            if(matrix[i][j] == target) {
                //如果找到指定元素，则直接返回
                return true;
            } else if(matrix[i][j] < target) {
                //当前元素比目标小
                if(j + 1 < rows) {
                    //当前元素不是矩阵最右边的列的元素则右移
                    j++;
                } else {
                    //如果当前元素是矩阵最右边的元素，则从最左侧扫描下一行
                    j = 0;
                    if(i + 1 < lines) {
                        i++;
                    } else {
                        return false;
                    }

                }
            } else if(matrix[i][j] > target) {
                //当前元素比目标大
                if(j != 0) {
                    j -= 1;
                    //向下遍历
                    while(i < rows) {
                        if(matrix[i][j] < target) {
                            i++;
                        } else if(matrix[i][j] > target) {
                            if(j != 0) {
                                j -= 1;
                                i = 1;
                            } else {
                                return false;
                            }
                        } else {
                            return true;
                        }
                    }
                } else {
                    //第一个元素是最小的，如果它都比目标大，则不存在
                    return false;
                }
            }
        }
        return false;
    }
}
