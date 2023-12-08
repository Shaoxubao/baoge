package algorithm.od20230803;

import java.util.*;

/**
 * 题目描述：
 * 放暑假了，小明决定到某旅游景点游玩，他在网上搜索到了各种价位的酒店（长度为n的数组A），
 * 他的心理价位是x元，请帮他筛选出k个最接近x元的酒店（n>=k>0），并由低到高打印酒店的价格。
 * 备注：
 * 1）酒店价格数组A和小明的心理价位x均为整型数据；(0 < n，k，x < 10000)
 * 2）优先选择价格最接近心理价位的酒店；若两家酒店和心理价位差价相同，
 *    则选择价格较低的酒店。（比如100元和300元距离心理价位200元同样接近，此时选择100元）;
 * 3）酒店价格可能相同重复。
 *
 * 输入描述：
 * 第一行：n, k, x
 * 第二行：A[0] A[1] A[2]…A[n-1]
 *
 * 输出描述：
 * 由低到高打印筛选出的酒店价格
 *
 * 补充说明：
 * 1）酒店价格数组A和小明的心理价位x均为整型数据
 * 2）优先选择价格最接近心理价位的酒店；若两家酒店距离心理价位差价相同，
 *    则选择价格较低的酒店。（比如100元和300元距离心理价位200元同样接近，此时选择100元）
 * 3）酒店价格可能相同重复。
 *
 * 示例1
 * 输入：
 * 10 5 6
 * 1 2 3 4 5 6 7 8 9 10
 * 输出：
 * 4 5 6 7 8
 * 说明：
 * 数组长度n = 10，筛选个数k = 5，目标价位x=6
 * 示例2
 * 输入：
 * 10 4 6
 * 10 9 8 7 6 5 4 3 2 1
 * 输出：
 * 4 5 6 7
 * 说明：
 * 数组长度n = 10，筛选个数k = 4，目标价位x=6
 * 当4和8距离x相同时，优先选择价格低的4
 *
 * 示例3
 * 输入：
 * 6 3 1000
 * 30 30 200 500 70 300
 * 输出：
 * 200 300 500
 */
public class BookHotel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = 0;
        int k = 0;
        int target = 0;
        int[] arr = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        n = arr[0];
        k = arr[1];
        target = arr[2];
        List<Integer> hotelPrice = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            hotelPrice.add(scanner.nextInt());
        }
        // 根据酒店价格从低到高排序(每个价格减去target)
        int finalTarget = target;
        hotelPrice.sort((o1, o2) -> {
            int diff1 = Math.abs(o1 - finalTarget);
            int diff2 = Math.abs(o2 - finalTarget);
            if (diff1 != diff2) {
                return Integer.compare(diff1, diff2);
            } else {
                return Integer.compare(o1, o2);
            }
        });

        // 取出差值最小的几个数，按酒店价格从低到高排列
        List<Integer> targetHotelValue = new ArrayList<>();
        for (int j = 0; j < k; j++) {
            targetHotelValue.add(hotelPrice.get(j));
        }
        Collections.sort(targetHotelValue);
        targetHotelValue.forEach(System.out::println);
    }
}
