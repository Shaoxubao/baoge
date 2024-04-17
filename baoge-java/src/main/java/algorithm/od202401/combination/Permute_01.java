package algorithm.od202401.combination;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 全排列:https://blog.csdn.net/qq_31076523/article/details/134444655
 * 题目描述
 * 给定一个不含重复数字的数组 nums ，返回其所有可能的全排列 。你可以按任意顺序返回答案。
 * 示例 1：
 * 输入： nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * 示例 2：
 * 输入： nums = [0,1]
 * 输出：[[0,1],[1,0]]
 *
 * 示例 3：
 * 输入： nums = [1]
 * 输出：[[1]]
 */
public class Permute_01 {

    private static final List<List<Integer>> res = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nums = new int[3];
        for (int i = 0; i < 3; i++) {
            nums[i] = sc.nextInt();
        }
        System.out.println(permute(nums));
    }

    public static List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) return res;
        LinkedList<Integer> path = new LinkedList<>();
        int[] used = new int[nums.length];
        dfs(nums, path, used);
        return res;
    }

    private static void dfs(int[] nums, LinkedList<Integer> path, int[] used) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] == 1)
                continue; // 剪枝，同个元素不能选择多次
            path.addLast(nums[i]);
            used[i] = 1;
            dfs(nums, path, used);
            path.removeLast();
            used[i] = 0;
        }
    }
}
