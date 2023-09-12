package algorithm.od20230803;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 小华和小微一起通过玩积木游戏学习数学。
 * 他们有很多积木，每个积木块上都有一个数字，积木块上的数字可能相同。
 * 小华随机拿一些积木挨着排成一排，请小微找到这排积木中数字相同且所处位置最远的2块积木块，计算他们的距离，
 * 小微请你帮忙替她解决这个问题。
 *
 * 输入描述
 * 第一行输入为N，表示小华排成一排的积木总数
 * 接下来N行每行一个数字，表示积木上的数字
 *
 * 输出描述
 * 相同数字的积木的位置最远距离；如果所有积木数字都不相同，请返回-1.
 *
 * 示例1 输入输出示例仅供调试，后台判题数据一般不包含示例
 * 输入
 * 5
 * 1
 * 2
 * 3
 * 1
 * 4
 *
 * 输出
 * 3
 *
 * 说明：
 * 共有5个积木，第1个积木和第4个积木数字相同，其距离为3.
 *
 * 示例2 输入输出示例仅供调试，后台判题数据一般不包含示例
 * 输入
 * 2
 * 1
 * 2
 *
 * 输出
 * -1
 *
 * 说明
 * 一共有2个积木，没有积木数字相同，返回-1.
 *
 * 备注：
 * 0 <= 积木上的数字 <= 10^9
 * 1 <= 积木长度 <= 10^5
 */
public class GetFurthestDistanceOfBlocks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = Integer.parseInt(scanner.nextLine());
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(scanner.nextLine());
        }
        // key 为积木上数字，value 为积木数字数组下标
        Map<Integer, Integer> numMap = new HashMap<>();
        int maxDis = 0;
        for (int i = 0; i < nums.length; i++) {
            if (numMap.containsKey(nums[i])) {
                // 获取相同数字最大下标距离
                maxDis = Math.max(maxDis, i - numMap.get(nums[i]));
            } else {
                numMap.put(nums[i], i);
            }
        }

        System.out.println(maxDis);
    }
}
