package algorithm.od202401.combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * dfs中的for循环不是固定从0开始，而是从传入的begin开始。
 * 第一个元素从0开始找，第二个元素就只能从1开始找。总是从排序数组的下个元素找，包含两个隐含信息，
 * 同一个元素不可能被同时选择多次；下一个总是大于上一个元素。所以之前的剪枝逻辑都可以去掉。
 */
public class Subsets_01_优化 {
    private final List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        if (nums.length == 0) return res;
        List<Integer> path = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, 0, path);
        return res;
    }

    private void dfs(int[] nums, int begin, List<Integer> path) {
        res.add(new ArrayList<>(path));
        for (int i = begin; i < nums.length; i++) {
            path.add(nums[i]);
            dfs(nums, i + 1, path); // 不能继续找当前元素，直接找下个元素，path中不可能选择到同一个元素，下一个也始终比上一个大
            path.remove(path.size() - 1);
        }
    }
}
