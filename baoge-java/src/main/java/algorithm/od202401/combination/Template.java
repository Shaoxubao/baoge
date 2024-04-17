package algorithm.od202401.combination;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 排列组合模板代码
 * 求nums（无重复元素）的全排列，每个元素允许选择多次。
 * 以1，2，3为例，如下图所示，从上往下看，选择第一个元素的时候，
 * 可以选择1，2，3，假设第一个选定为1（将选定的元素存入path中，即path=[1]），
 * 那么第二个元素也能选择1，2，3，同理，第二个元素也选择1，即path=[1,1]时，选择第三个元素，
 * 依然能选择1，2，3。当第三个元素选定后，此时path的长度等于nums的长度，一个排列结果就计算出来了，
 * 加入到结果res中去，接着回溯，按照同样的逻辑运行下去，最后得到全排列结果。
 */
public class Template {
    private final List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> path = new LinkedList<>();
        dfs(nums, path);
        return res;
    }

    private void dfs(int[] nums, LinkedList<Integer> path) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int num : nums) {
            path.addLast(num);
            dfs(nums, path);
            path.removeLast();
        }
    }
}
