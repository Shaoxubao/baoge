package algorithm.od202401.combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 全排列 II
 * 题目描述
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 * 示例 1：
 * 输入： nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 * [1,2,1],
 * [2,1,1]]
 * <p>
 * 示例 2：
 * 输入： nums = [1,2,3]
 * 输出：
 * [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * 我们第一个元素可以选择1，1，2，很明显选择第一个1的排列和选择第二个1的排列情况相同，
 * 所以选择第二个1的时候应该剪枝。为了判断重复，可以先将nums从小到大排序，
 * 如果：i>0&&nums[i]==nums[i-1]，说明重复，应该剪枝（i等于0时，代表该元素第一次被选择，肯定不存在重复）。
 * 只有同层存在相同元素时才应该剪枝，不同层则不应该剪。
 */
public class Permute_02 {

    private final List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums.length == 0) return res;
        List<Integer> path = new ArrayList<>();
        int[] used = new int[nums.length];
        Arrays.sort(nums); // 排序，方便判断同层是否重复，nums[i-1]==nums[i]则重复
        dfs(nums, path, used);
        return res;
    }

    private void dfs(int[] nums, List<Integer> path, int[] used) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] == 1)
                continue; // 剪枝，同个元素不能选多次，
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == 0)
                continue; // 剪枝，避免同层重复
            path.add(nums[i]);
            used[i] = 1;
            dfs(nums, path, used);
            path.remove(path.size() - 1);
            used[i] = 0;
        }
    }

}
