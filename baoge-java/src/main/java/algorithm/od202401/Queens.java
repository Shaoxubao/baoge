package algorithm.od202401;

import java.util.*;

/**
   算法解释
   回溯算法通过探索所有可能的候选解来找出所有解，搜索尝试过程中若发现不满足求解条件，则“回溯”返回，尝试别的路径。
   经典题目—n皇后问题
   1 题目描述
   n个皇后放置在n×n的棋盘上，要求任意两个皇后不同行、不同列，也不在同一条斜线上，给定一个整数n，返回所有不同的n皇后问题解决方案。四皇后问题的两种摆放方式如图所示:
   —————————————————       ————————————————
   |   | Q |   |   |       |   |   | Q |   |
   —————————————————       ————————————————
   |   |   |   | Q |       | Q |   |   |   |
   —————————————————       ————————————————
   | Q |   |   |   |       |   |   |   | Q |
   —————————————————       ————————————————
   |   |   | Q |   |       |   | Q |   |   |
   —————————————————       ————————————————
   2 题目解析
   对于四皇后问题，把皇后Q1放置在(1，1)，皇后Q2经过第一、第二列及斜线的失败尝试后，可能的位置是(2，3)，随后被证明这也不是可行解，因为皇后Q3将没有位置可放，如图2-5所示。
   算法进行回溯，将皇后Q2放在下一个可能的位置(2，4)，这样皇后Q3就可以放在(3，2)上，但结果是Q4无处安放，但结果是Q4无处安放，如图2-6所示。
   算法再一次回溯，Q1放在(1，1)位置不存在可行解，探索将Q1放在(1，2)位置上，接着Q2放置在(2，4)、Q3放置在(3，1)、Q4放置在(4，3)上，得到一个问题的解，如图2-7所示。
   在编写具体代码时，我们使用一个数组记录每行放置皇后的列下标，依次在每一行放置一个皇后，当n个皇后都放置完毕，则找到一个可能的解；不满足条件时就回退。
   每次放置皇后时需要快速判断该位置是否满足条件：(1)不放在同一列，很容易判断。(2)不在同一条斜线上，判断方法如下：斜线从左上到右下方向，同一条斜线上的每个位置满足行下标与列下标之差相等，如图2-8所示。
   斜线从右上到左下方向，同一条斜线上的每个位置满足行下标与列下标之和相等，如图2-9所示。
 */
public class Queens {
    public static void main(String[] args) {
        Set<Integer> column = new HashSet<>();
        Set<Integer> diagonalsDown = new HashSet<>();
        Set<Integer> diagonalsUp = new HashSet<>();
//        System.out.print(backtrack(4, 0, column, diagonalsDown, diagonalsUp));

        List<List<String>> ans = new ArrayList<>();
        int[] queens = new int[4];
        backtrack2(ans, queens,4, 0, column, diagonalsDown, diagonalsUp);
        System.out.println(ans);
    }

    /**
     *
     * @param n n皇后
     * @param row 在第几行放置皇后
     * @param columns 已放置皇后的列
     * @param diagonalsDown 斜线从左上到右下方向
     * @param diagonalsUp   斜线从右上到左下方向
     * @return
     */
    public static int backtrack(int n, int row, Set<Integer> columns, Set<Integer> diagonalsDown, Set<Integer> diagonalsUp) {
        if (row == n) return 1;
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (columns.contains(i)) continue;
            int diagonal1 = row - i;
            if (diagonalsDown.contains(diagonal1)) continue;
            int diagonal2 = row + i;
            if (diagonalsUp.contains(diagonal2)) continue;
            columns.add(i);
            diagonalsDown.add(diagonal1);
            diagonalsUp.add(diagonal2);
            count += backtrack(n, row + 1, columns, diagonalsDown, diagonalsUp);
            columns.remove(i);
            diagonalsDown.remove(diagonal1);
            diagonalsUp.remove(diagonal2);
        }
        return count;
    }

    /**
     * @param ans 最终答案
     * @param queens queens[index] = val 表示皇后放在第index行 val 列，初始化时填充-1
     * @param n n皇后
     * @param row 在第几行放置皇后
     * @param columns 已放置皇后的列
     * @param diagonalsDown 斜线从左上到右下方向
     * @param diagonalsUp   斜线从右上到左下方向
     */
    public static void backtrack2(List<List<String>> ans, int[] queens, int n, int row, Set<Integer> columns,
                                 Set<Integer> diagonalsDown, Set<Integer> diagonalsUp) {
        if (row == n) {
            List<String> board = generateFormatAns(queens, n);
            ans.add(board);
        } else {
            for (int i = 0; i < n; i++) {
                // 判断位置是否符合要求：同行、上下两个对角线
                if (columns.contains(i)) {
                    continue;
                }
                int diagonal1 = row - i;
                if (diagonalsDown.contains(diagonal1)) {
                    continue;
                }
                int diagonal2 = row + i;
                if (diagonalsUp.contains(diagonal2))
                    continue;
                // 放置皇后
                queens[row] = i;
                columns.add(i);
                diagonalsDown.add(diagonal1);
                diagonalsUp.add(diagonal2);
                // 继续尝试放置下一个皇后
                backtrack2(ans, queens, n, row + 1, columns, diagonalsDown, diagonalsUp);
                // 回退
                queens[row] = -1;
                columns.remove(i);
                diagonalsDown.remove(diagonal1);
                diagonalsUp.remove(diagonal2);
            }
        }
    }

    // 打印结果
    public static List<String> generateFormatAns(int[] queens, int n) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '*');
            row[queens[i]] = 'Q';
            ans.add(new String(row));
        }
        return ans;
    }
}
