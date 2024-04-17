package algorithm.od202401.combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 子集
 * 题目描述:
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集不能包含重复的子集。你可以按任意顺序返回解集。
 * 示例 1：
 * 输入： nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *
 * 示例 2：
 * 输入： nums = [0]
 * 输出： [[],[0]]
 *
 * 思路:
 * 和模板代码相比，多了以下限制：
 * 一个元素只能选择一次。
 * 求子集，其长度不一定是nums.length，而是在这个范围：[0,nums.length]
 * 求的是组合，而非排列，即[1,2]，[2,1]是同一种结果
 */
public class Subsets_01 {
    private final List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        if (nums.length == 0) return res;
        LinkedList<Integer> path = new LinkedList<>();
        int[] used = new int[nums.length];
        Arrays.sort(nums); // 保证后入的元素一定大于先入的元素，所以排序
        for (int i = 0; i <= nums.length; i++) {
            dfs(nums, path, used, i);
        }

        return res;
    }

    private void dfs(int[] nums, LinkedList<Integer> path, int[] used, int len) {
        if (path.size() == len) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] == 1)
                continue; // 剪枝，同个元素不能选多次，
            if (!path.isEmpty() && nums[i] < path.peekLast())
                continue; // 剪枝，选择的下个元素比上个元素还要小
            path.addLast(nums[i]);
            used[i] = 1;
            dfs(nums, path, used, len);
            path.removeLast();
            used[i] = 0;
        }
    }
}
