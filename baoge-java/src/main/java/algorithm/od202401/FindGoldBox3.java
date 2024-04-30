package algorithm.od202401;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
   阿里巴巴找黄金宝箱(III)
   樵夫阿里巴巴在去砍柴的路上，无意中发现了强盗集团的藏宝地，藏宝地有编号从0~N的箱子，每个箱子上面贴有一个数字。
   阿里巴巴念出一个咒语数字，查看宝箱是否存在两个不同箱子，这两个箱子上贴的数字相同，同时这两个箱子的编号之差的绝对值小于等于咒语数字，
   如果存在这样的一对宝箱，请返回最先找到的那对宝箱左边箱子的编号，如果不存在则返回-1。
   输入描述:
   第一行输入一个数字字串，数字之间使用逗号分隔，例如: 1,2,3,1字串中数字个数>=1，<=100000; 每人数字值>=-100000，<=100000:
   第二行输入咒语数字，例如: 3，咒语数字>=1，<=100000
   输出描述:
   存在这样的一对宝箱，请返回最先找到的那对宝箱左边箱子的编号，如果不存在则返回-1
   示例1
   输入:
   6,3,1,6
   3
   输出:
   0
   示例2
   输入:
   5,6,7,5,6,7
   2
   输出:
   -1
   思路：
   题目翻译过来就是在一个区间（区间长度为咒语数字n+1）内，是否有存在编号相同的宝箱。比如示例1中，
   [0,3]区间存在相同的6(刚好在边界，位置0，和位置3)，两个位置的差值为3，一定小于等于咒语3。所以返回左边箱子的编号0。
   而在示例二中，初始区间为[0,2]，没有重复数字，将区间不断右移，依然找不到重复数字，所以返回-1.
 */
public class FindGoldBox3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] inputs = scanner.nextLine().split(",");
        int[] boxes = Arrays.stream(inputs).mapToInt(Integer::parseInt).toArray();
        int num = scanner.nextInt();
        System.out.println(findGoldBox3(boxes, num));
    }

    public static int findGoldBox3( int[] boxes, int num) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int right = 0; right < boxes.length; right++) {
            int left = right - num;
            if (map.containsKey(boxes[right])) {
                return map.get(boxes[right]);
            }
            map.put(boxes[right], right);
            if (left >= 0) {
                map.remove(boxes[left]);
            }
        }
        return -1;
    }
}
