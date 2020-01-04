package algorithm_practice.SwordOffer.old;

/**
 * 题目：输入一个矩阵，按照从外向里以顺时针的顺序依次扫印出每一个数字
 *
 * Created by nibnait on 2016/9/24.
 */
public class d20_顺时针打印矩阵 {

    public static void printMatrixClockWisely(int[][] numbers) {
        if (numbers == null || numbers.length==0){
            System.out.println("数组不能为空");
            return;
        }
        int rows = numbers.length;
        int columns = numbers[0].length;
        int start = 0;  //每一行起点的位置：(1,1), (2,2), (3,3), ...
        while (columns>2*start && rows>2*start){
            printMatrixInCircle(numbers, rows, columns, start);
            start++;
        }

    }

    private static void printMatrixInCircle(int[][] numbers, int rows, int columns, int start) {
        //打印一圈，分四步
        int endX = columns-1-start;
        int endY = rows-1-start;
        //1. 从左到右 打印一行
        for (int i = start; i <= endX; i++) {
            System.out.print(numbers[start][i]+" ");
        }

        //2. 从上到下打印一列
        //  环的高度至少是2，才会输出右边的一列
        //  即 要终止行号 > 起始行号
        if (endY > start){
            for (int i = start+1; i <= endY; i++) {
                System.out.print(numbers[i][endX]+" ");
            }
        }

        //3. 从右往左打印一行
        //  环的高度>=2 && 环的宽度至少也得是2，才会输出下面的那一行
        if (start<endY && start<endX){
            for (int i = endX; i >= start; i--) {
                System.out.print(numbers[endY][i]+" ");
            }
        }

        //4. 从下到上打印一行
        //  环的高度至少是3 && 宽度>=2
        if (start<endY-1 && start<endX){
            for (int i = endY-1; i >= start; i--) {
                System.out.print(numbers[i][start]+" ");
            }
        }
    }


    public static void main(String[] args) {
        int[][] numbers = {
                {1, 2, 3, 4, 5},
                {16, 17, 18, 19, 6},
                {15, 24, 25, 20, 7},
                {14, 23, 22, 21, 8},
                {13, 12, 11, 10, 9},
        };
        printMatrixClockWisely(numbers);
        System.out.println();
        int[][] numbers2 = {
                {1, 2, 3, 4, 5, 6, 7, 8},
                {22, 23, 24, 25, 26, 27, 28, 9},
                {21, 36, 37, 38, 39, 40, 29, 10},
                {20, 35, 34, 33, 32, 31, 30, 11},
                {19, 18, 17, 16, 15, 14, 13, 12},
        };
        printMatrixClockWisely(numbers2);
        System.out.println();
        int[][] numbers3 = {
                {1, 2, 3, 4, 5, 6, 7, 8}
        };
        printMatrixClockWisely(numbers3);
        System.out.println();
        int[][] numbers4 = {
                {1, 2, 3, 4, 5, 6, 7, 8},
                {16, 15, 14, 13, 12, 11, 10, 9}
        };
        printMatrixClockWisely(numbers4);
        System.out.println();
        int[][] numbers5 = {
                {1},
                {2},
                {3},
                {4},
                {5},
                {6},
                {7},
                {8}
        };
        printMatrixClockWisely(numbers5);
        System.out.println();
        int[][] numbers6 = {
                {0, 1},
                {15, 2},
                {14, 3},
                {13, 4},
                {12, 5},
                {11, 6},
                {10, 7},
                {9, 8}
        };
        printMatrixClockWisely(numbers6);
        System.out.println();
        int[][] numbers7 = {
                {1, 2},
                {4, 3}
        };
        printMatrixClockWisely(numbers7);
        System.out.println();
        int[][] numbers8 = {
                {1}
        };
        printMatrixClockWisely(numbers8);
        System.out.println();
        // 0个元素的数组
        printMatrixClockWisely(new int[][]{{}});    //numbers.length=1, numbers[0].length=0;
        // 空数组
        printMatrixClockWisely(null);

    }

}
