package algorithm.od202401.combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 在上面的代码中，每次dfs都是要对path求和，效率低下，我们可以直接传入target，
 * 固定第一个元素后，找下一个元素，target应该要减去当前元素。比如要在2,3,5中找和为8的组合，
 * 那么固定第一个元素2，下面就应该时找等于8-2的组合。当target为0时，
 * 说明path的和就等于target，当target小于0时，说明path中的累加和已经超过了原来的target，此时return。
 */
public class CombinationSum_01_优化 {
    private final List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> path = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, 0, path, target);
        return res;
    }

    private void dfs(int[] candidates, int begin, List<Integer> path, int target) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i < candidates.length; i++) {
            path.add(candidates[i]);
            dfs(candidates, i, path, target - candidates[i]);
            path.remove(path.size() - 1);
        }
    }
}
