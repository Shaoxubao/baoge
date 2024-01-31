package algorithm.od202401;

import java.util.Arrays;
import java.util.Scanner;

/**
    动态规划——0-1背包问题
    一 题目描述:
    有n个物品，它们有各自的体积和价值，现有给定容量的背包，如何让背包里装入的物品具有最大的价值总和？
    如：现有4个物品，小偷背包总容量为8，怎么可以偷得价值最多的物品？
    输入：
    背包总容：8
    物品编号：1  2  3  4
    物品重量：2  3  4  5
    物品价值：3  4  5  8
    二 总体思路:
    根据动态规划解题步骤（问题抽象化、建立模型、寻找约束条件、判断是否满足最优性原理、找大问题与小问题的递推关系式、
    填表、寻找解组成）找出01背包问题的最优解以及解组成，然后编写代码实现。
    三 动态规划的原理:
    动态规划方法的原理就是把多阶段决策过程转化为一系列的单阶段决策问题，利用各个阶段之间的递推关系，
    逐个确定每个阶段的最优化决策，最终堆叠出多阶段决策的最优化决策结果。
    解题思路：
    https://blog.csdn.net/Biteht/article/details/124651895
    输入示例：
8
1 2 3 4
2 3 4 5
3 4 5 8
    输出：
    12
 */
public class Bag01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int C = Integer.parseInt(scanner.nextLine());  // 背包总容量
        int[] n = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray(); // 物品编号
        int[] weight = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray(); // 物品重量
        int[] value = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray(); // 物品价值
        System.out.println(bag01(value, weight, C, n.length));
    }

    /**
     * @param v 价值
     * @param w 重量
     * @param C 背包容量
     * @param N 总物品数
     * @return  最优解
     */
    public static int bag01(int[] v, int[] w, int C, int N) {
        int[][] f = new int[N + 1][C + 1];  // 创建背包矩阵，第0列和第0行都是0
        for (int i = 1; i <= N; i++) {      // 行填充值，物品从第一个到第N个
            for (int j = 1; j <= C; j++) {  // 列填充值，容量从1~C
                if (w[i - 1] <= j) {   // 如果当前物品重量小于等于背包中的当前重量
                    // 比较不加入该物品时该重量的最大价值（前一行）与当前物品的价值+可以容纳的剩余重量的价值
                    f[i][j] = Math.max(f[i - 1][j - w[i - 1]] + v[i - 1], f[i - 1][j]);
                } else { // 如果当前物品重量大于背包中的当前重量
                    f[i][j] = f[i - 1][j];  // 直接使用前一行的最优解
                }
            }
        }
        // 打印填充结果矩阵
//        for (int[] rows: f) {
//            for (int col : rows) {
//                System.out.format("%5d", col);
//            }
//            System.out.println();
//        }
        return f[N][C];
    }
}
