package algorithm.od202401.combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 子集 II
 * 题目描述
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 * 示例 1：
 * 输入： nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 *
 * 示例 2：
 * 输入： nums = [0]
 * 输出： [[],[0]]
 *
 * 思路:
 * 和78 子集相比，多了以下限制：
 * nums可能包含重复数组
 * 去重逻辑：同层相同则剪枝，nums[i]==nums[i-1]&&used[i-1]==0
 * 小结:
 * 求数组（有重复元素）的子集：
 * 1.对nums排序
 * 2.修改dfs中的for循环，让i从begin开始，下次遍历时用dfs(nums, i+1,path)
 * 3. 增加同层相同元素的剪枝逻辑：i > 0 && nums[i] == nums[i - 1] && used[i - 1] == 0
 */
public class Subsets_02 {
    private final List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums.length == 0) return res;
        List<Integer> path = new ArrayList<>();
        int[] used = new int[nums.length];
        Arrays.sort(nums);
        dfs(nums, 0, path, used);
        return res;
    }

    private void dfs(int[] nums, int begin, List<Integer> path, int[] used) {
        res.add(new ArrayList<>(path));
        for (int i = begin; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == 0) continue; // 同层相同，则剪枝
            path.add(nums[i]);
            used[i] = 1;
            dfs(nums, i + 1, path, used);
            path.remove(path.size() - 1);
            used[i] = 0;
        }
    }
}
