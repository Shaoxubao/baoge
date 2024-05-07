package algorithm.od202401;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 编码能力提升计划
 * 为了提升软件编码能力，小王制定了刷题计划，他选了题库中的n道题，编号从0到n-1，并计划在m天内按照题目编号顺序刷完所有的题目(注意，小王不能用多天完成同一题)
 * 在小王刷题计划中，小王需要用time[i]的时间完成编号i的题目此外，小王还可以查看答案，可以省去该题的做题时间。为了真正达到刷题效果，
 * 小王每天最多直接看一次答案。我们定义m天中做题时间最多的一天耗时为T(直接看答案的题目不计入做题总时间)。请你帮小王求出最小的T是多少
 * 输入描述
 * 第一行输入为time,time[i]的时间完成编号i的题目
 * 第二行输入为m，m表示几天内完成所有题目，1<= m<= 180
 * 输出描述
 * 最小耗时整数T
 * 示例1:
 * 输入
 * 999,999,999
 * 4
 * 输出
 * 0
 * 说明
 * 在前三天中，小王每天都直接看答案，这样他可以在三天内完成所有的题目并不花任何时间
 * 示例2:
 * 输入
 * 1,2,2,3,5,4,6,7,8
 * 5
 * 输出
 * 4
 * 说明
 * 第一天完成前3题，第3题看答案；
 * 第二天完成第4题和第5题，第5题看答案；
 * 第三天完成第6和第7题，第7题看答案；
 * 第四天完成第8题，直接看答案；
 * 第五天完成第9题，直接看答案
 * 题意概述
 * 给定一个数组，将其划分成 M 份，使得每份元素之和最大值最小，每份可以任意减去其中一个元素。
 */
public class CodeImprovePlan {
    private static int res = Integer.MAX_VALUE;
    private static int cnt;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nums = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int day = sc.nextInt();
        System.out.println(codeImprovePlan3(nums, day));
        System.out.println(minTime2(nums, day));
    }

    // 回溯
    private static int codeImprovePlan(int[] nums, int day) {
        int size = nums.length;

        if (size <= day) return 0;
        if (day == 1) {
            int sum = 0, max = 0;
            for (int k = 0; k < nums.length; k++) {
                sum += nums[k];
                max = Math.max(nums[k], max);
            }
            return sum - max;
        }
        cnt = day - 1; // 划分day段，那么就是找day-1个数，在其前面划短杠表示分段
        LinkedList<Integer> path = new LinkedList<>();
        dfs(nums, 1, path);
        return res;
    }

    private static void dfs(int[] nums, int start, LinkedList<Integer> path) {
        if (path.size() == cnt) {
            ArrayList<Integer> list = new ArrayList<>(path);
            int curMax = 0; // 基于当前分段得到的最大值
            int left, right;
            for (int i = 0; i <= list.size(); i++) {
                if (i == list.size()) {
                    left = list.get(i - 1);
                    right = nums.length;
                } else {
                    right = list.get(i);
                    left = i == 0 ? 0 : list.get(i - 1);
                }

                int maxTmp = 0, sumTmp = 0; // 某段的最大值和累计和
                for (int k = left; k < right; k++) {
                    sumTmp += nums[k];
                    maxTmp = Math.max(nums[k], maxTmp);
                }
                curMax = Math.max(curMax, sumTmp - maxTmp);
            }

            res = Math.min(curMax, res);
            return;
        }

        for (int i = start; i < nums.length; i++) {
            path.addLast(i);
            dfs(nums, i + 1, path);
            path.removeLast();
        }
    }

    // 动态规划
    private static int codeImprovePlan2(int[] nums, int day) {
        int size = nums.length;
        if (day >= size) return 0;
        int[][] dp = new int[size + 1][day + 1];
        for (int i = 0; i < size + 1; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = 1;
        int[] sub = new int[size + 1];
        for (int i = 0; i < nums.length; i++) {
            sub[i + 1] = sub[i] + nums[i];
        }
        for (int i = 1; i < size + 1; i++) {
            for (int j = 1; j < day + 1; j++) {
                for (int k = 0; k < i; k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], sub[i] - sub[k] - getMax(nums, k - 1, i - 1)));
                }
            }
        }
        return dp[size][day];
    }

    private static int getMax(int[] nums, int start, int end) {
        start = Math.max(0, start);
        int res = 0;
        for (int i = start; i <= end; i++) {
            res = Math.max(nums[i], res);
        }
        return res;
    }

    // 二分法
    private static int codeImprovePlan3(int[] nums, int day) {
        int left = 0, right = 0, sum = 0, max = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(max, nums[i]);
        }
        right = sum - max;
        while (left < right) {
            int mid = left + (right - left >> 1);
            if (checked(nums, mid, day)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static boolean checked(int[] nums, int mid, int day) {
        int cnt = 0, sum = 0, max = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(nums[i], max);
            if (sum - max > mid) {
                cnt++;
                sum = nums[i];
                max = nums[i];
            }
        }
        return cnt + 1 <= day; // +1：加上最后一段未统计的分组
    }

    // 二分法
    public static int minTime(int[] time, int m) {
        int left = 0;
        int right = Integer.MAX_VALUE;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 如果这个时间限制可以在规定天数里面完成刷题计划
            if (check(time, mid, m)) {
                // 向左缩小查找范围
                right = mid - 1;
            } else {
                // 反之 向右缩小查找范围
                left = mid + 1;
            }
        }
        return left;
    }

    private static boolean check(int[] time, int target, int m) {
        int maxTime = 0;
        int total = 0;
        // 如果第一天就都做完了
        // 后续代码不会将days进行递增 应该初始化为1
        int days = 1;
        // 是否使用过了场外援助
        boolean helper = true;
        for (int i = 0; i < time.length; i++) {
            // 维护花费时间最长的题目
            maxTime = Math.max(maxTime, time[i]);
            // 累加这一天的总做题时间
            total += time[i];
            // 如果超过当天做题时间限制了
            if (total > target) {
                // 如果未使用过场外援助
                if (helper) {
                    // 减去耗时最多的题目
                    total -= maxTime;
                    helper = false;
                } else {
                    // 超时并且使用过场外援助
                    // 得从下一天重新开始了
                    days++;
                    // 刷新场外援助
                    helper = true;
                    // 当天最大值刷新
                    maxTime = 0;
                    // 总计时间刷新
                    total = 0;
                    // 最重要的一点也很容易遗忘
                    // 这道没有时间做的题目留到下一天重新开始做
                    i--;
                }
            }
        }
        return m >= days;
    }

    public static int minTime2(int[] time, int m) {
        int n = time.length;
        if (m >= n) { // 如果天数大于等于题目数，每题都可以分配在不同天，并由求助完成
            return 0;
        }
        int l = 0, r = 1000000000;
        while (l < r) { // 二分查找
            int mid = (l + r) >> 1;
            if (check2(time, m, mid)) { // 如果当前限制下可以满足，缩小右边界
                r = mid;
            } else { // 否则左边界加一
                l = mid + 1;
            }
        }
        return l;
    }

    public static boolean check2(int[] time, int m, int limit) {
        int cur = 0, sum = 0, max = 0, day = 1; // 当前遍历到的题目，当前组的总耗时，当前组的最大耗时，需要的天数
        while (cur < time.length) {
            sum += time[cur];
            max = Math.max(max, time[cur]);
            if (sum - max > limit) { // 当前组总耗时减去组内最大耗时仍超出限制，则需要开启额外一天
                day++;
                if (day > m) { // 超出总天数m，无法完成分配
                    return false;
                }
                sum = time[cur]; // sum和max更新为新组的值
                max = time[cur];
            }
            cur++;
        }
        return true; // 能遍历完所有题目即完成了分配
    }

}
