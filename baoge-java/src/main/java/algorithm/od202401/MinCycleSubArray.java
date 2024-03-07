package algorithm.od202401;

import java.util.Arrays;
import java.util.Scanner;

/**
 给定一个由若干整数组成的数组nums，请检查数组是否是由某个子数组重复循环拼接而成，请输出这个最小的子数组。
 输入描述
 第一行输入数组中元素个数n，1 <= n <= 100000
 第二行输入数组的数字序列nums，以空格分割，0 <= nums[i]<= 10
 输出描述
 输出最小的子数组的数字序列，以空格分割;
 备注
 数组本身是其最大的子数组，循环1次可生成的自身
 示例1:
 输入
 9
 1 2 1 1 2 1 1 2 1
 输出
 1 2 1
 说明
 数组[1,2,1,1,2,1,1,2,1]可由子数组[1,2,1]重复循环3次拼接而成

 题解：
 1.暴力查找
 依次遍历nums，检查nums是否能够有0~i的子串循环拼接而成（checked方法）
 checked逻辑，假定nums可以由tmp循环拼接而成，那么遍历nums，对于每一位i，
 其值应该等于tmp中对应位置的值，即：nums[i]=tmp[i%length]，length为tmp的长度。
 还需要注意，如果nums可以由tmp循环拼接而成，那么nums的长度一定是tmp长度的整数倍。
 2.转字符串匹配
 因为输入的是0~10的数字，可以将他们转成字符串，比如：原来的输入是：1 10 2 1 10 2，记为s1
 两个s1加空格拼接后的字符串s2: 1 10 2 1 10 2 1 10 2 1 10 2
 从s2中查找第二次出现s1的索引位置，即：s2.indexOf(s1, 1)，1 10 2 1 10 2 1 10 2 1 10 2，标黄色部分为第二次出现s1的子串，
 所以循环字符就是其前面的字符串：1 10 2，直接输出即可。
 */
public class MinCycleSubArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String numStr = scanner.nextLine();
            int[] nums = Arrays.stream(numStr.split(" ")).mapToInt(Integer::parseInt).toArray();
            // 方法一：暴力查找
            int[] res = minCycleSubArray1(nums);
            for (int item : res) {
                System.out.print(item + " ");
            }
            System.out.println();
            // 方法二：转字符串匹配
            System.out.println(minCycleSubArray2(numStr));

        }
    }

    /**
     * 暴力查找
     */
    public static int[] minCycleSubArray1(int[] nums) {
        for (int i = 0; i < nums.length / 2; i++) {
            if (matchJoint(nums, i)) { // 匹配满足拼接
                return Arrays.copyOfRange(nums, 0, i + 1);
            }
        }
        return nums;
    }

    public static boolean matchJoint(int[] nums, int i) {
        int[] tmp = Arrays.copyOfRange(nums, 0, i + 1);
        if (nums.length % tmp.length != 0) {
            return false;
        }
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != tmp[j % tmp.length]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 转字符串匹配
     */
    public static String minCycleSubArray2(String nums) {         // 1 2 1 2
        int idx = (nums +" "+ nums).indexOf(nums, 1); // 1 2 1 2 1 2 1 2, indexOf(str, index)用于从给定的fromIndex获取字符串中指定子字符串的索引。 这意味着从指定索引( fromIndex )开始搜索子字符串
        idx = Math.min(nums.length(), idx);
//        System.out.println( "idx = " + idx);
        return nums.substring(0, idx).trim();
    }
}
