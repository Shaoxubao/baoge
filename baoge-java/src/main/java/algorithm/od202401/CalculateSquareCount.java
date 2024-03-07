package algorithm.od202401;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 输入 N 个互不相同的二维整数坐标, 求这 N 个坐标可以构成的正方形数量。(内积为零的两个向量垂直)
 第一行输入为 N，N 代表坐标数量，N为正整数。N <= 100
 之后的 K 行输入为坐标 x y以空格分隔，x, y 为整数, -10 <= x,y <= 10
 用例：
 输入
 3
 1 3
 2 4
 3 1
 输出
 0
 说明：3个点不足以构成正方形
 输入：
 4
 0 0
 1 2
 3 1
 2 -1
 输出：1
 说明：此4个点可以构成正方形
 题解:
 已知：正方形的两个点 (x1, y1)  (x2, y2)
 则：正方形另外两个点的坐标为
 x3 = x1 + (y1 - y2)   y3 = y1 - (x1 - x2)
 x4 = x2 + (y1 - y2)   y4 = y2 - (x1 - x2)
 或
 x3 = x1 - (y1 - y2)   y3 = y1 + (x1 - x2)
 x4 = x2 - (y1 - y2)   y4 = y2 + (x1 - x2)
 从另一位博主的文章中学到这一计算公式
 原文链接：https://blog.csdn.net/qfc_128220/article/details/127417851
 通过上述的公式，只要枚举两个顶点，根据这两个顶点计算出要满足正方形需要的剩下两个顶点，然后判断该顶点是否在输入的列表中是否存在，
 如果都存在则方案数+1，最后所得方案数要除以4，因为一个正方形有四条边，每条边都会对这个正方形判断一次。

 */
public class CalculateSquareCount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String[] points = new String[n];
        for (int i = 0; i < n; i++) {
            points[i] = scanner.nextLine();
        }
        System.out.println(calculateSquareCount(n, points));
    }

    public static int calculateSquareCount(int n, String[] points) {
        int squareCount = 0;
        Set<String> pointSet = new HashSet<>(Arrays.asList(points));
        for (int i = 0; i < n; i++) {
            int[] pointArr1 = Arrays.stream(points[i].split(" ")).mapToInt(Integer::parseInt).toArray();
            int x1 = pointArr1[0];
            int y1 = pointArr1[1];
            for (int j = i + 1; j < n; j++) {
                int[] pointArr2 = Arrays.stream(points[j].split(" ")).mapToInt(Integer::parseInt).toArray();
                int x2 = pointArr2[0];
                int y2 = pointArr2[1];

                // 求另外两个点
                int x3 = x1 - (y1 - y2);
                int y3 = y1 + (x1 - x2);
                int x4 = x2 - (y1 - y2);
                int y4 = y2 + (x1 - x2);
                if (pointSet.contains(x3 + " " + y3) && pointSet.contains(x4 + " " + y4)) {
                    squareCount++;
                }

                // 或者另外两个点
                int x5 = x1 + (y1 - y2);
                int y5 = y1 - (x1 - x2);
                int x6 = x2 + (y1 - y2);
                int y6 = y2 - (x1 - x2);
                if (pointSet.contains(x5 + " " + y5) && pointSet.contains(x6 + " " + y6)) {
                    squareCount++;
                }
            }
        }
        return squareCount / 4;
    }
}
