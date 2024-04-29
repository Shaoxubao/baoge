package algorithm.od202401;

/**
 * 深度优先搜索岛屿数量
   1 题目描述
    有一个由0和1组成的二维矩阵，其中1代表陆地，0代表水，岛屿由水平或垂直方向上相邻的陆地连接形成。
    假设矩阵的四周均被水包围，请计算岛屿的数量。
    输入：matrix =[[1,1,0,0],[0,0,1,0],[0,0,0,0],[0,0,1,1],]，
    如图所示:
             —————————————————
             | 1 | 1 |   |   |
             —————————————————
             |   |   | 1 |   |
             —————————————————
             |   |   |   |   |
             —————————————————
             |   |   | 1 | 1 |
             —————————————————
    输出：3
   2 题目解析
    扫描整个矩阵，每遇到1就将它置为0，并以它为起始节点开始进行深度优先搜索。最终岛屿的数量就是进行深度优先搜索的次数。
 */
public class NumOfLands {

    private static int ans = 0;

    public static void main(String[] args) {
        int[][] matrix = {{1, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 0}, {0 ,0, 1, 1}};
        System.out.println(numOfLands(matrix));
    }

    public static int numOfLands(int[][] matrix) {
        if (matrix == null) {
            return 0;
        }
        // 岛屿的数量就是进行深度优先搜索的次数
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    ans ++;
                   dfs(matrix, i, j);
                }
            }
        }
        return ans;
    }

    private static void dfs(int[][] matrix, int i, int j) {
        // 搜索的边界
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length) {
            return;
        }
        // 每次遇到1就将它置为0，并以它为起始节点开始进行深度优先搜索
        if (matrix[i][j] == 1) {
            matrix[i][j] = 0;
            // 搜索四个方向：上、下、左、右
            dfs(matrix, i - 1, j);
            dfs(matrix, i + 1, j);
            dfs(matrix, i, j - 1);
            dfs(matrix, i, j + 1);
        }
    }

}
