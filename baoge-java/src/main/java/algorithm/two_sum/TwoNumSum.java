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
 *
 * 思路
 * 思路1: 根据题意我们其实可以直接使用双重for循环,然后拿到两个值相加就等于目标值的那两个下标返回即可, 只是这样的时间复杂度是O(N^2)
 * 思路2: 我们可以转换思路,先将目标值与我们需要下标对应元素值保存起来,等到下一个我们需要的差值,就会拿到之前key, 既可以得到两个下标
 * 复杂度分析
 * 时间复杂度：O(N) 循环所有数组元素,当然根据目标值,每次查找花费O(1)时间,所以最后复杂度为O(N) 空间复杂度：O(N) 使用map数据结构,存储的元素取决于传入的元素数量
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

    // 这道题虽然不难，但在解决的过程中会发现有更优质的方法去解决
    public int[] twoSum(int[] nums, int target) {
        // 第一眼看到这个题的时候，很像冒泡排除，选择排序
        int i = 0; int j = i + 1;
        // 其实这个效率低的方法直接冒泡就可以
        // 确定要循环几次，来走完所有的情况
        for (i = 0; i < nums.length - 1; i++) {
            // 确定在上面定义的每一次循环中遍历所有的情况
            for(j = i + 1; j < nums.length; j++) {
                // 进行比对，更优质的应该是二维数组，考虑有多个值的情况
                if (nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[] {i, j};
    }
    // 做完这道题，会发现如果我用map，以健值对的形式放进去，所以你就将下标作为键，值作为value
    // 但是后面你会发现，无法根据值来获得键的位置，所以当时应该把值作为键，下标作为值可以解决

}
