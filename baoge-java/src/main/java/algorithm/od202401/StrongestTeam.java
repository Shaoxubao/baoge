package algorithm.od202401;

import java.util.Arrays;
import java.util.Scanner;

/**
  有一个阵营，里面有 n 个小队 (1<=n<=100)，每个小队都有他们的能力值 ai(0<=i)，
  现在有一个紧急情况，需要从这些小队中选出连续的几个小队，组成一个最强的团队。
  最强的团队的定义为这个团队的所有小队的平均能力值最高。如果有多个最强团队，
  则选包含小队最多的一个。 现在请你写个程序，输出这个最强的团队包含的小队的个数。
  输入小队的数量n和n个数，分别代表各小队的能力值 ai 输出一个数表示这个最强团队包含的小队的个数。
  示例 1
  输入：
  6
  1,2,3,3,2,1
  输出：
  2
  题解：
  依照题目可以知道，平均能力值最高值 等于 n个小队中ai最大的（称为max）
  所以该题目所求的小队个数，就是max在数组中最大连续数
  如例题，max = 3，最大连续数为2
 */
public class StrongestTeam {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[] nums = Arrays.stream(scanner.nextLine().split(","))
                .mapToInt(Integer::parseInt).toArray();
        int max = Integer.MIN_VALUE;
        // 先求出最大值
        for (int i = 0; i < n; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        // 将等于最大值的flag数组对应值值为1
        int[] flag = new int[n];
        for (int i = 0; i < n; i++) {
            if (nums[i] == max) {
                flag[i] = 1;
            }
        }
        // 求flag数组中连续1最大个数
        int maxCount = 0;
        int count = 0; // 001101110
        for (int i = 0; i < n; i++) {
            if (flag[i] == 1) {
                count++;
            } else {
                maxCount = Math.max(count, maxCount);
                count = 0;
            }
        }
        maxCount = Math.max(count, maxCount);

        System.out.println(maxCount);
    }
}
