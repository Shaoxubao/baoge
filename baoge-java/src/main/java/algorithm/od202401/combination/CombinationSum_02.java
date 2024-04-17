package algorithm.od202401.combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 组合总和 II
 * 题目描述
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，
 * 找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 * 注意：解集不能包含重复的组合。
 *
 * 示例 1：
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 *
 * 示例 2：
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 输出:
 * [
 * [1,2,2],
 * [5]
 * ]
 *
 * 思路
 * 和39 组合总和相比，多了以下限制：
 *
 * 一个元素只能选择一次
 * 可能存在重复元素
 * 对于限制1
 * 可以dfs中遍历时，查找下一个元素即可：dfs(candidates, i+1, path, target-candidates[i]);
 *
 * 对于限制2：
 * 新增去重逻辑：同层相同则剪枝，nums[i]==nums[i-1]&&used[i-1]==0
 */
public class CombinationSum_02 {
    private final List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        LinkedList<Integer> path = new LinkedList<>();
        int[] used = new int[candidates.length];
        dfs(candidates, 0, path, target, used);
        return res;
    }

    public void dfs(int[] candidates, int begin, LinkedList<Integer> path, int target, int[] used) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(path));
        }

        for (int i = begin; i < candidates.length; i++) {
            if (i > 0 && candidates[i] == candidates[i - 1] && used[i - 1] == 0)
                continue; // 同层相同剪枝
            path.addLast(candidates[i]);
            used[i] = 1;
            dfs(candidates, i + 1, path, target - candidates[i], used);
            path.removeLast();
            used[i] = 0;
        }
    }
}
