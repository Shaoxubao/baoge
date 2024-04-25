package algorithm.od202401;

/**
 * 最小路径和
 * 1 题目描述给出一个包含非负整数的m×n矩阵，从左上角出发至右下角，每次只能向右或者向下移动一步，找出数字之和最小的路径。
 * 输入：matrix = [[1,2,7],[2,5,3],[1,1,1]]，如图2-3所示。
 * 1  2  7
 * 2  5  3
 * 1  1  1
 * 输出：6。
 * 解释：路径1→2→1→1→1的总和最小。
 * 2 题目解析
 * (1)定义问题状态：定义一个m×n二维数组dp[][]，dp[i][j]代表从左上角出发到(i,j)位置的最小路径和。
 * (2)定义初始条件：dp[0][0] = matrix [0][0]。
 * (3)确定转移方程：根据题意，每次只能向右或者向下移动一步，可确定状态转移方程：
 * ● 当j = 0时，dp[i][0] = dp[i-1][0] + matrix[i][0]。
 * ● 当i = 0时，dp[0][j] = dp[0][j-1] + matrix[0][j]。
 * ● 当i > 0 且 j > 0时，dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + matrix[i][j]。
 * 最终，dp[m-1][n-1]就是待求结果。
 */
public class MinPathSum {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 7}, {2, 5, 3}, {1, 1, 1}};
        System.out.println(minPathSum(matrix));
    }

    public static int minPathSum(int[][] matrix) {
        int row = matrix.length; // 行数
        int col = matrix[0].length; // 列数
        int[][] dp = new int[row][col];
        dp[0][0] = matrix[0][0];
        // 先初始化dp第一行
        for (int i = 1; i < row; i++) {
            dp[i][0] = matrix[i][0] + dp[i - 1][0];
        }
        // 初始化dp第一列
        for (int j = 1; j < col; j++) {
            dp[0][j] = matrix[0][j] + dp[0][j - 1];
        }
        // 计算dp[i][j]，i和j从1开始
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = matrix[i][j] + Math.min(dp[i - 1][j], dp[i][j -1]);
            }
        }
        return dp[row - 1][col - 1];
    }
}
