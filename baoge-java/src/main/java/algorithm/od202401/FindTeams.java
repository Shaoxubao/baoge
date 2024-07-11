package algorithm.od202401;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * 用数组代表每个人的能力，一个比赛活动要求参赛团队的最低能力值为N，每个团队可以由1人或2人组成，且1个人只能参加1个团队，
 * 请计算出最多可以派出多少支符合要求的团队?

 * 输入描述
 * 第一行数组代表总人数，范围[1,500000]
 * 第二行数组代表每个人的能力，每个元素的取值范围[1,500000]，数组的大小范围[1,500000]
 * 第三行数值为团队要求的最低能力值，范围[1,500000]

 * 输出描述
 * 最多可以派出的团队数量

 * 示例
 * 输入
 * 5
 * 3 1 5 7 9
 * 8
 * 输出
 * 3
 * 说明
 * 3`和`5`组成一队，`1`和`7`组成一队，`9`自己一个队，故输出`3
 * 解题思路:一道非常典型的双指针贪心题
 */
public class FindTeams {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        int m = scanner.nextInt();

        Arrays.sort(nums);

        if (nums[0] >= m) {
            System.out.println(n);
        } else {
            int left = 0, right = n - 1;
            int ans = 0;

            while (left < right) {
                if (nums[right] >= m) {
                    right--;
                    ans++;
                } else if (nums[right] + nums[left] >= m) {
                    left++;
                    right--;
                    ans++;
                } else {
                    left++;
                }
            }

            System.out.println(ans);
        }
    }
}
