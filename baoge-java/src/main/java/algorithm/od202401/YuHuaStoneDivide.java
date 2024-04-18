package algorithm.od202401;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * MELON的难题:
 * MELON有一堆精美的雨花石(数量为n，重量各异)，准备送给S和W。MELON希望送给俩人的雨花石重星一致，
 * 请你设计一个程序帮MELON确认是否能将雨花石平均分配。
 * 输入描述
 * 第1行输入为雨花石个数:n，0 < n <31.
 * 第2行输入为空格分割的各雨花石重量: m[0] m[1 ]… m[n - 1], 0 < m[k] < 1001不需要考虑异常输入的情况。
 * 输出描述
 * 如果可以均分，从当前雨花石中最少拿出几块，可以使两堆的重量相等:
 * 如果不能均分，则输出-1。
 * 示例1:
 * 输入
 * 4
 * 1 1 2 2
 * 输出
 * 2
 * 说明
 * 输入第一行代表共4颗雨花石，第二行代表4颗雨花石重量分别为1、1、2、2。均分时只能分别为1,2，
 * 需要拿出重星为1和2的两块雨花石，所以输出2。
 * 示例2:
 * 输入
 * 10
 * 1 1 1 1 1 9 8 3 7 10
 * 输出
 * 3
 * 说明
 * 输入第一行代表共10颗雨花石，第二行代表4颗雨花石重量分别为1、1、1、1、1、9、8、3、7、10。
 * 均分时可以1,1,1,1,1,9,7和10,8,3，也可以1,1,1,1,9.8和10,7,3,1，或者其他均分方式，
 * 但第一种只需要拿出重量为10.8,3的3块雨花石，第二种需要拿出4块,所以输出3(块数最少)。
 * 1、排列组合思路：
 * 针对本题而言，简要分析如下：
 * 首先计算雨花石的重量总和sum，sum不能被2整除，直接返回-1。能被2整除，那么我们至少需要拿出多少块雨花石，
 * 使其重量和target=sum/2。要求最少数量，先拿重量较大的雨花石，所以可以将输入nums升序排列，剪枝分析如下：
 * 雨花石重量可能存在重复，注意同层相同剪枝
 * 如果某个组合的雨花石重量在加入下一个雨花石之前，
 * 已经不小于target或者组合个数已经不小于之前解的个数，直接剪枝
 * 最后注意，因为要求的res为最小值，初始值设置的Integer.MAX_VALUE ，
 * 如果没有找到合适的组合，res还是等于Integer.MAX_VALUE，那么最后需要返回-1.
 * 2、动态规划思路：
 * 定义：
 * dp[i][j]的含义为：在前i个雨花石选择，使其重量和等于j，需要的最少雨花石个数。
 *
 * 初始化：
 * dp[i][0]，要使目标和等于0，那么选0个即可，所以dp[i][0]=0，其中i的范围为：0~nums.length-1
 * dp[0][j]，从前0个元素选（即只有nums[0]可选，目标和就是nums[0]）,使其累加和为j（j的范围为0~target），
 * 如果j=nums[0]，那么dp[0][j]=1，否则给其一个初始值。具体初始值给多少?
 * 比如nums为：2 3 4 5，dp[0][2]=1。dp[0][3]不可能满足（只选2，不可能使其和等于3），
 * 可以将其结果设置为一个比较大的值（因为最后取的最小值，如果存在一个满足条件的值，
 * 两相比较就能得到最小结果）。这里我设置为nums.length。
 *
 * 递推关系：
 * 对于dp[i][j]（i>0,j>0）,只有两种情况，当前值选和当前值不选
 * 当前值选：dp[i][j]=dp[i-1][j-nums[i]]+1
 * 当前值不选: dp[i][j]=dp[i-1][j]
 * 结果中两者取其小即可。同时需要注意，如果当前值比目标j还大，那肯定走不选的分支
 * 根据初始化和递推关系，最后求得dp[nums.length-1][target]，如果其值等于nums.length（即初始化的值），
 * 说明不存在这样的组合，直接返回-1。
 */
public class YuHuaStoneDivide {
    private static int res = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        System.out.println(yuHuaStoneByCombination(nums));
        System.out.println(yuHuaStoneByDynamicProgramming(nums));
    }

    // ==================排列组合===================
    public static int yuHuaStoneByCombination(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0)
            return -1;
        int target = sum / 2;
        int[] used = new int[nums.length];
        LinkedList<Integer> path = new LinkedList<>();
        Arrays.sort(nums); // 升序排序
        dfs(nums, nums.length - 1, path, used, target);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private static void dfs(int[] nums, int start, LinkedList<Integer> path,
                            int[] used, int target) {
        if (target == 0) {
            res = Math.min(res, path.size());
            return;
        }

        for (int i = start; i >= 0; i--) {
            if (target <= 0 || path.size() >= res) {
                break;
            }
            if (i < nums.length - 1 && nums[i + 1] == nums[i] && used[i + 1] == 0)
                continue;
            path.addLast(nums[i]);
            used[i] = 1;
            dfs(nums, i - 1, path, used, target - nums[i]);
            path.removeLast();
            used[i] = 0;
        }
    }

    // ==================动态规划===================
    public static int yuHuaStoneByDynamicProgramming(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0)
            return -1;
        int target = sum / 2;
        int size = nums.length;
        int[][] dp = new int[size][target + 1];
        for (int j = 0; j < target + 1; j++) {
            if (j == nums[0])
                dp[0][j] = 1;
            else
                dp[0][j] = size;
        }
        for (int i = 1; i < size; i++) {
            for (int j = 1; j <= target; j++) {
                if (nums[i] > j)
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = Math.min(dp[i - 1][j], 1 + dp[i - 1][j - nums[i]]);
            }
        }
        int res = dp[size - 1][target];
        return res == size ? -1 : res;
    }
}
