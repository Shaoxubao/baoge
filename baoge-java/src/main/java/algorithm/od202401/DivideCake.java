package algorithm.od202401;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 分薄饼问题
 * 幼儿园的老师给小朋友们分薄饼。已知每个小朋友最多只能分到一块薄饼，对于每个小朋友i，都有一个需求值gi，
 * 即能让小朋友i满足需求的薄饼的最小尺寸。同时每块薄饼j都有一个尺寸sj，如果sj≥gi，就可以将薄饼j分给小朋友i。
 * 请输出最多能满足几位小朋友。
 * 举例说明如下。
 * 输入：[1，2，3]，[1，1]，其中[1，2，3]表示3个小朋友的需求值，[1，1]表示两块薄饼的尺寸。
 * 输出：1，因为只能让需求值为1的小朋友得到满足。
 * 输入：[1，2]，[1，2，3]，其中[1，2]表示两个小朋友的需求值，[1，2，3]表示三块薄饼的尺寸。
 * 输出：2，可将尺寸为1的薄饼分给需求值为1的小朋友，可将尺寸为2或3的薄饼分给需求值为2的小朋友，最多满足两个小朋友。
 * 题解：用贪心策略回避穷举每一种组合的操作，只要遵循“用尽量小尺寸的薄饼满足不同小朋友的需求值”这一贪心策略，就可以得到本题的最优解。
 */
public class DivideCake {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] g = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] s = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getContentedChildren(g, s));
    }

    public static int getContentedChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0, j = 0;
        int count = 0; // 记录可满足的孩子数量
        while (i <= g.length - 1 && j <= s.length - 1) {
            if (g[i] <= s[j]) { // s[j]可以满足g[i]的需求值，所以将s[j]分给g[i]
                count++;        // 可满足的孩子数加1
                i++;
                j++;
            } else {
                j++; // 用尺寸更大的饼去匹配
            }
        }
        return count;
    }
}
