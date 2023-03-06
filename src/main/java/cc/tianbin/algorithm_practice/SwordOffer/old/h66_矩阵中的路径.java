package cc.tianbin.algorithm_practice.SwordOffer.old;

/**
 * 题目：请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 *      路径可以从矩阵中任意一格开始，每一步可以在矩阵中间向左、右、上、下移动一格。
 *      如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。
 * 例如在下面的 3*4 的矩阵中包含一条字符串“bcced”的路径。
 *                 但矩阵中不包含字符串“abcb”的路径，因为字符串的第一个字符 b 占据了矩阵中的第一行第二格子之后，路径不能再次进入这个格子。

         a b c e
         s f c s
         a d e e
 *
 *【解】：
 *  这是一个可以用回朔法解决的典型题。首先，在矩阵中任选一个格子作为路径的起点。
 *  假设矩阵中某个格子的字符为 ch，那么这个格子不可能处在路径上的第 i 个位置。
 *      如果路径上的第 i 个字符不是 ch，那么这个格子不可能处在路径上的第 i 个位置。
 *      如果路径上的第 i 个字符正好是 ch，那么往相邻的格子寻找路径上的第 i+1 个字符。
 *      除在矩阵边界上的格子之外，其他格子都有 4 个相邻的格子。
 *  重复这个过程知道路径上的所有字符都在矩阵中找到相应的位置。
 *
 *  由于回朔法的递归特性，路径可以被看成一个栈。
 *  当在矩阵中定位了路径中前 n 个字符的位置之后，在与第 n 个字符对应的格子的周围都没有找到第 n+1 个字符，
 *  这个时候只要在路径上回到第 n-1 个字符，重新定位第 n 个字符。
 *
 *  由于路径不能重复进入矩阵的格子，还需要定义和字符矩阵大小一样的布尔值矩阵，用来标识路径是否已经进入每个格子。
 *
 *  当矩阵中坐标为（row,col）的格子和路径字符串中下标为 pathLength 的字符一样时，
 *  从 4 个相邻的格子 (row,col-1),(row-1,col),(row,col+1) 以及 (row+1,col) 中去定位路径字符串中下标为 pathLength+1 的字符。
 *
 *  如果 4 个相邻的格子都没有匹配字符串中下标为 pathLength+1 的字符，表明当前路径字符串中下标为pathLength的字符在矩阵中的定位不正确，
 *  我们需要回到前一个字符 (pathLength-1)，然后重新定位。
 *
 *  一直重复这个过程，直到路径字符串上所有字符都在矩阵中找到合适的位置
 *
 *
 *
 * Created by nibnait on 2016/10/5.
 */
public class h66_矩阵中的路径 {

    /**
     *
     * @param matrix 输入矩阵
     * @param rows   矩阵行数
     * @param cols   矩阵列数
     * @param str    要搜索的字符串
     * @return 是否找到 true是，false否
     */
    public static boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        // 参数校验
        if (matrix == null || matrix.length != rows * cols || str == null || str.length < 1) {
            return false;
        }

        // 变量初始化
        boolean[] visited = new boolean[rows * cols];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }

        // 记录结果的数组，
        int[] pathLength = {0};
        // 以每一个点为起始进行搜索
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (hasPathCore(matrix, rows, cols, str, visited, i, j, pathLength)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 回溯搜索算法
     *
     * @param matrix     输入矩阵
     * @param rows       矩阵行数
     * @param cols       矩阵列数
     * @param str        要搜索的字符串
     * @param visited    访问标记数组
     * @param row        当前处理的行号
     * @param col        当前处理的列号
     * @param pathLength 已经处理的str中字符个数
     * @return 是否找到 true是，false否
     */
    private static boolean hasPathCore(char[] matrix, int rows, int cols, char[] str, boolean[] visited,
                                       int row, int col, int[] pathLength) {

        if (pathLength[0] == str.length) {
            return true;
        }

        boolean hasPath = false;

        // 判断位置是否合法
        if (row >= 0 && row < rows
                && col >= 0 && col < cols
                && matrix[row * cols + col] == str[pathLength[0]]
                && !visited[row * cols + col]) {

            visited[row * cols + col] = true;
            pathLength[0]++;

            // 按左上右下进行回溯
            hasPath = hasPathCore(matrix, rows, cols, str, visited, row, col - 1, pathLength)
                    || hasPathCore(matrix, rows, cols, str, visited, row - 1, col, pathLength)
                    || hasPathCore(matrix, rows, cols, str, visited, row, col + 1, pathLength)
                    || hasPathCore(matrix, rows, cols, str, visited, row + 1, col, pathLength);

            if (!hasPath) {
                pathLength[0]--;
                visited[row * cols + col] = false;
            }

        }

        return hasPath;
    }

    public static void main(String[] args) {
        //ABCE  //ABCCED
        //SFCS
        //ADEE
        System.out.println(hasPath("ABCESFCSADEE".toCharArray(), 3, 4,
                "ABCCED".toCharArray()) + "--true");// true

        //ABCE  //SEE
        //SFCS
        //ADEE
        System.out.println(hasPath("ABCESFCSADEE".toCharArray(), 3, 4,
                "SEE".toCharArray()) + "--true");// true

        //ABCE  //ABCB
        //SFCS
        //ADEE
        System.out.println(hasPath("ABCESFCSADEE".toCharArray(), 3, 4,
                "ABCB".toCharArray()) + "--false");// false

        //ABCEHJIG  //SLHECCEIDEJFGGFIE
        //SFCSLOPQ
        //ADEEMNOE
        //ADIDEJFM
        //VCEIFGGS
        System.out.println(hasPath("ABCEHJIGSFCSLOPQADEEMNOEADIDEJFMVCEIFGGS".toCharArray(), 5, 8,
                "SLHECCEIDEJFGGFIE".toCharArray()) + "--true");// true

        //ABCEHJIG  //SGGFIECVAASABCEHJIGQEM
        //SFCSLOPQ  //
        //ADEEMNOE
        //ADIDEJFM
        //VCEIFGGS
        System.out.println(hasPath("ABCEHJIGSFCSLOPQADEEMNOEADIDEJFMVCEIFGGS".toCharArray(), 5, 8,
                "SGGFIECVAASABCEHJIGQEM".toCharArray()) + "--true");// true

        //ABCEHJIG  //SGGFIECVAASABCEEJIGOEM
        //SFCSLOPQ
        //ADEEMNOE
        //ADIDEJFM
        //VCEIFGGS
        System.out.println(hasPath("ABCEHJIGSFCSLOPQADEEMNOEADIDEJFMVCEIFGGS".toCharArray(), 5, 8,
                "SGGFIECVAASABCEEJIGOEM".toCharArray()) + "--false");// false

        //ABCEHJIG  //SGGFIECVAASABCEHJIGQEMS
        //SFCSLOPQ
        //ADEEMNOE
        //ADIDEJFM
        //VCEIFGGS
        System.out.println(hasPath("ABCEHJIGSFCSLOPQADEEMNOEADIDEJFMVCEIFGGS".toCharArray(), 5, 8,
                "SGGFIECVAASABCEHJIGQEMS".toCharArray()) + "--false");// false

        //AAAA  //AAAAAAAAAAAA
        //AAAA
        //AAAA
        System.out.println(hasPath("AAAAAAAAAAAA".toCharArray(), 3, 4,
                "AAAAAAAAAAAA".toCharArray()) + "--true");// true

        //AAAA  //AAAAAAAAAAAAA
        //AAAA
        //AAAA
        System.out.println(hasPath("AAAAAAAAAAAA".toCharArray(), 3, 4,
                "AAAAAAAAAAAAA".toCharArray()) + "--false");// false

    }

}
