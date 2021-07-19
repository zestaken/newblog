package array;

public class Rotate48 {
    public void rotate(int[][] matrix) {
        //从外向内一圈圈找，用times表示在第几圈
        int times = 0;
        //nums表示矩阵的列数（行数）
        int nums = matrix.length;

        while(times <= (nums / 2)) { //比如4x4矩阵就是两圈，3x3也是两圈
            //在每一圈中再确定依次交换值的四个元素
            int len = nums - times * 2; //len是圈的范围，每缩小一圈减少两个
            for(int i = 0; i < len - 1; i++) {
                //确定每一圈第一个元素的位置并用临时变量存储其值
                //通过i来将第一个元素右移一位，其它三个元素顺势右移，遇到边界就贴着边界转换方向接着移
                int temp = matrix[times][times + i];
                //将上一个元素的值由其下一位元素的值来替代
                matrix[times][times + 1] = matrix[times + len - 1 - i][times];
                matrix[times + len - 1 - i][times] = matrix[times + len - 1][times + len - 1 - i];
                matrix[times + len - 1][times + len - 1 - i] = matrix[times + i][times + len - 1];
                matrix[times + i][times + len - 1] = temp;
                //虽然看起来复杂，但是每一个元素又一个坐标都是不随i的变化而变化，只要关注i对其有影响的坐标，顺势移动即可
            }
            //进入下一圈
            times++;
        }
    }
}
