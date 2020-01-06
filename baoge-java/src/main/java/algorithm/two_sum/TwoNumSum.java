package algorithm.two_sum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author shaoxubao
 * @Date 2020/1/6 16:16
 *
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class TwoNumSum {

    public static void main(String[] args) {

        int[] nums = new int[] {2, 7, 11, 15};


        int[] result = twoNumSum(nums, 26);
        System.out.println(Arrays.toString(result));
    }

    public static int[] twoNumSum(int[] nums, int target) {

        // 定义容器,存放首个下标,当有其差值出现的时候,便可以等到另一个下标
        Map<Integer, Integer> map = new HashMap<>();
        // 用来存储最后返回结果
        int[] result = new int[2];

        for (int i = 0; i < nums.length; i++) {
            // 判断之前的key是否已经保存到map中,如果已经存在,那么当前的值加上之前的值既为目标值
            if (map.containsKey(target - nums[i])) {
                result[0] = map.get(target - nums[i]);
                // 这个时候i所对应的元素还没有放到map中,但是我们要找的值已经找到返回即可
                result[1] = i;

                return result;
            }

            map.put(nums[i], i);
        }

        return result;
    }

}
