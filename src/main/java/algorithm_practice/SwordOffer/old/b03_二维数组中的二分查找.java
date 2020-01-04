package algorithm_practice.SwordOffer.old;

/**
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 * 【思路】
 * 从二维数组的右上角往左下角开始比较。
 * Created by nibnait on 2016/9/20.
 */
public class b03_二维数组中的二分查找 {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15}
        };
        System.out.println(findInPartiallySortedMatrix(matrix, 6));
    }

    private static int findInPartiallySortedMatrix(int[][] matrix, int key) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        if (matrix==null || rows<1 || cols<1){
            return 0;
        }
        int row = 0;
        int col = cols-1;
        while (row<rows && col>=0){
            if (matrix[row][col] == key){
                return 1;
            } else if (matrix[row][col] < key){
                row++;
            } else {
                col--;
            }
        }
        return 0;
    }

}
