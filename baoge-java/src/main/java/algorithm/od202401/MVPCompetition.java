package algorithm.od202401;

import java.util.Arrays;
import java.util.Scanner;

/**
 * MVP争夺战
 * 题目说明	在星球争霸篮球赛对抗赛中，强大的宇宙战队，希望每个人都能拿到MVP。
 * MVP的条件是，单场最高分得分获得者，可以并列，所以宇宙战队决定在比赛中，尽可能让更多的队员上场，且让所有有得分的队员得分都相同。
 * 然而比赛过程中的每一分钟的得分都只能由某一个人包揽。
 * 输入描述	输入第一行为一个数字t，表示有得分的分钟数（ 1 <= t <= 50）。
 * 第二行为t个数字，代表每一分钟的得分 p（1 <= p <= 50）。
 * 输出描述	输出有得分的队员都是MVP时最少的MVP得分。
 * 示例1
 * 输入	9
 * 5 2 1 5 2 1 5 2 1
 * 输出	6
 * 说明	样例解释：一共4人得分，分别都为6分
 * 5 + 1
 * 5 + 1
 * 5 + 1
 * 2 + 2 + 2
 * 题目解读：
 * 输入一系列数字代表球员的得分，要求把这些分数尽可能分给多的球员，且保证每个球员的得分之和相等。求出当平均分配的人数最多时，这个平均得分是多少。
 * 换种说法，就是把指定的一组数字分成若干组（分成多少组不确定，每组的数字个数也不固定），使每组数字之和相等。求这个和的最小值。和最小，意味着分成的组数最多。
 * 题目中给出了 n 个数字，要求把这 n 个数字划分成 m 组，保证 m 组中每组的数字之和相等，即每组的数字之和等于 sun / m（注： sum/m 取整）。
 * 需要注意：每组的数字个数并不是固定的，可能各不相同。
 * 解题思路：
 * 根据题目要求，先计算出总分，然后 i 开始从 2 枚举，看是否能够均分成 i 份。如果可以，则更新答案。
 * 在尝试是否能够均分的过程中，可以理解为有 i 个桶，尝试将这些元素放在桶中，要求最后 i 个桶中的元素和一致。也可以理解为，
 * 每个通的容量为 sum / i，是否能够将桶装满的同时保证所有元素都已经被放在桶中。
 */
public class MVPCompetition {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String[] scores = scanner.nextLine().split(" ");
        int[] nums = new int[n];
        int sum = 0;

        // 处理输入，数组处理同时统计总和
        for (int i = 0; i < scores.length; i++) {
            nums[i] = Integer.parseInt(scores[i]);
            sum += nums[i];
        }

        // t个数字都相等，则分成最大t份，直接返回数组中任意值
        if (allElementsEqual(nums)) {
            System.out.println(nums[0]);
            return;
        }

        // 得分排序，排序可以使得在尝试将元素放在桶中时的剪枝
        Arrays.sort(nums);

        // MVP人数
        int res = 1;
        // 每个子数组的和，最小也要从数组中的最大值开始，因为子数组的和肯定 >= sum。
        for (int i = 2; i <= sum / 2; i++) {
            // 如果总分能够均分到 i 个桶，则尝试每个具体的分是否能够均分到 i 个桶
            if (sum % i == 0) {
                int target = sum / i;
                if (dfs(nums, nums.length - 1, new int[i], target)) {
                    // 最多的桶数量
                    res = Math.max(res, i);
                }
            }
        }

        System.out.println(sum / res);
    }

    // 尝试将总分均分到 bucket 桶中，则尝试每个具体的分是否能够均分到 i 个桶
    private static boolean dfs(int[] nums, int i, int[] bucket, int target) {
        // 已经成功放完所有的分数
        if (i < 0) {
            return true;
        }

        // 尝试把第 i 个分数放置在第 j 个桶
        for (int j = 0; j < bucket.length; j++) {
            // 如果这只桶的值和上只桶放的值一样，则跳过。排序剪枝的作用在这体现
            if (j > 0 && bucket[j] == bucket[j - 1]) {
                continue;
            }

            if (bucket[j] + nums[i] <= target) {
                bucket[j] += nums[i];
                // 放下一个
                if (dfs(nums, i - 1, bucket, target)) {
                    return true;
                }
                // 放在这个桶中，最终并不能放完所有的球，拿出该球重新尝试下一个桶
                bucket[j] -= nums[i];
            }
        }

        return false;
    }

    public static boolean allElementsEqual(int[] array) {
        boolean flag = true;
        for (int i = 1; i < array.length; i++) {
            if (array[i] != array[i - 1]) {
                flag = false;
                break;
            }
        }
        return flag;
    }

}
