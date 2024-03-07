package algorithm.od202401;

import java.util.*;

/**
  题目描述
  一贫如洗的樵夫阿里巴巴在去砍柴的路上，无意中发现了强盗集团的藏宝地，藏宝地有编号从0-N的箱子，
  每个箱子上面贴有箱子中藏有金币Q的数量。 从金币数量中选出一个数字集合，
  并销毁贴有这些数字的每个箱子，如果能销毁一半及以上的箱子，则返回这个数字集合的最小大小
  输入描述:
  第一行1个数字字串，数字之间使用逗号分隔，例如:6,6,6,6,3,3,3,1,1,5字串中数字的个数为偶数，并且
  个数>=1，<=100000; 每个数字>=1，<=100000;
  输出描述
  这个数字集合的最小大小
  样例
  输入：
  1,1,1,1,3,3,3,6,6,8
  输出:
  2
  说明:
  选择集合{1,8}，销毁后的结果数组为[3,3,3,6,6]，长度为5，长度为原数组的一半。
  大小为 2 的可行集合还有{1,3},{1,6},{3,6}。
  选择{6,8}集合是不可行的，它销后的结果数组为[1,1,1,1,3,3,3]，新数组长度大于原数组的二分之一。
  输入：
  2,2,2,2
  输出:
  1
  说明:
  我们只能选择集合{2}，销毁后的结果数组为空。
 */
public class FindGoldBox {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            int[] nums = Arrays.stream(scanner.nextLine().split(","))
                    .mapToInt(Integer::parseInt).toArray();
            System.out.println(findGoldBox(nums));
        }
    }

    public static int findGoldBox(int[] nums) {
        // 排序
        Arrays.sort(nums);
        // 对原数组进行计数统计
        List<Integer> countList = new ArrayList<>();
        int cnt = 1;
        for (int i = 1; i <= nums.length - 1; i++) {
            if (nums[i] == nums[i - 1]) {
                cnt++;
            } else {
                countList.add(cnt);
                cnt = 1;
            }
        }
        countList.add(cnt);
        // 对countList数组排序，从计数多的数开始删
        Integer[] countArray = countList.toArray(new Integer[0]);
        Arrays.sort(countArray, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        int tmp = 0;
        int m = countArray.length;
        for (int i = 0; i < m; i++) {
            tmp += countArray[i];
            // 选择集合数字删除后跟原数组长度除以2比较
            if (tmp >= nums.length / 2) {
                return i + 1;
            }
        }
        return 0;
    }
}
