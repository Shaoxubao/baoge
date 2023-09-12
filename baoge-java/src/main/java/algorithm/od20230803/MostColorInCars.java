package algorithm.od20230803;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 题目描述：
 * 在一个狭小的路口，每秒只能通过一辆车，假如车辆的颜色只有3种，找出N秒内经过的最多颜色的车辆数量
 * 三种颜色编号为0,1,2
 * 输入描述：
 * 第一行输入的是通过的车辆颜色信息
 * [0,1,1,2] 代表4秒钟通过的车辆颜色分别是0,1,1,2
 * 第二行输入的是统计时间窗，整型，单位为秒
 * 输出描述：
 * 输出指定时间窗内经过的最多颜色的车辆数量
 * 补充说明：
 * 示例1
 * 输入：
 * 0 1 2 1
 * 3
 * 输出：
 * 2
 * 说明：
 * 在[1,2,1]这个3秒时间窗内，1这个颜色出现2次，数量最多
 * 示例2
 * 输入：
 * 0 1 2 1
 * 2
 * 输出：
 * 1
 * 说明：
 * 在2秒时间窗内，每个颜色最多出现1次
 */
public class MostColorInCars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        ArrayList<Integer> scList = new ArrayList<>();
        for (String item : str.split(" ")) {
            scList.add(Integer.parseInt(item));
        }
        int n = Integer.parseInt(scanner.nextLine()); // 不同颜色的车的个数
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < scList.size(); i++) { // 先初始化窗口，i相当于右指针
            // 车辆颜色
            int color = scList.get(i);
            // 如果已经存在此颜色，则再次+1
            if (map.containsKey(color)) {
                map.put(color, map.get(color) + 1);
            } else {
                // 如果首次出现，则为1
                map.put(color, 1);
            }
            if (i >= n) {
                // 左指针 = 右指针-窗口大小
                int left = i - n;
                // 取出脱离窗口的车型颜色
                int leftColor = scList.get(left);
                // 如果map中已经存在，则-1
                if (map.containsKey(leftColor)) {
                    map.put(leftColor, map.get(color) - 1);
                }
                // 算下时间窗内，相同车型出现的最大个数
                max = Math.max(getMaxColorCount(map), max);
            }
        }
        System.out.println(max);
    }

    // 获取字典中出现次数最多的颜色的出现次数
    public static int getMaxColorCount(Map<Integer, Integer> colors) {
        int maxCount = 0;
        for (int count : colors.values()) {
            maxCount = Math.max(maxCount, count);
        }
        return maxCount;
    }
}
