package algorithm.od202401;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
   静态扫描快速识别源代码的缺陷，静态扫描的结果以扫描报告作为输出:
   1、文件扫描的成本和文件大小相关，如果文件大小为N，则扫描成本为N个金币
   2、扫描报告的缓存成本和文件大小无关，每缓存一个报告需要M个金币
   3、扫描报告缓存后，后继再碰到该文件则不需要扫描成本，直接获取缓存结果
   给出源代码文件标识序列和文件大小序列，求解采用合理的缓存策略，最少需要的金币数。
   输入描述：
   第一行为缓存一个报告金币数M，1<=M<=100
   第二行为文件标识序列：F1,F2,F3...Fn,  其中 1<=N<=10000， 1<=Fi<=1000
   第三行为文件大小序列：S1,S2,S3...Sn, 其中 1<=N<=10000， 1<=Si<=10
   输出描述：
   采用合理的缓存策略，需要的最少金币数
   补充说明：
   收起
   示例1
   输入：
    5
    1 2 2 1 2 3 4
    1 1 1 1 1 1 1
   输出：
    7
   说明：
   文件大小相同，扫描成本均为1个金币。缓存任意文件均不合算，因而最少成本为7金币
   示例2
   输入：
    5
    2 2 2 2 2 5 2 2 2
    3 3 3 3 3 1 3 3 3
   输出：
    9
   说明：
   2号文件出现了8次，扫描加缓存成本共计3+5=8，不缓存成本为3*8=24，显然缓存更优。最优最成本为8+1=9

   解题思路:
   本题的核心思想是贪心算法。贪心算法的基本思想是把最优化问题的求解看作是一系列选择，每次选择当前状态下的最优选择（局部最优解）。
   每做一次选择后，所求问题会简化为一个规模更小的子问题，从而通过每一步的最优解逐步达到整体的最优解。
   每个文件，扫描时第一次就建立缓存，或者每次都重新扫描，没有其他情况。
   所以我们需要考虑两种情况：
   （1）每次遇到该文件都重新扫描；
   （2）缓存扫描报告。
   再比较这两种情况的成本，选择较小的成本作为当前文件的最优策略。
   对于每个文件，都选择最优策略，然后将所有文件的最优策略累加，得到采用合理的缓存策略所需的最少金币数。最后，输出最少金币数即可。
 */
public class StaticScanning {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) { // 输入多个测试用例
            int m = Integer.parseInt(scanner.nextLine());
            String[] file = scanner.nextLine().split(" ");
            String[] fileSize = scanner.nextLine().split(" ");
            // 记录每个文件个数
            Map<String, Integer> fileMap = new HashMap<>();
            for (String item : file) {
                if (fileMap.containsKey(item)) {
                    fileMap.put(item, fileMap.get(item) + 1);
                } else {
                    fileMap.put(item, 1);
                }
            }
            // 记录每个文件大小
            Map<String, Integer> fileSizeMap = new HashMap<>();
            for (int i = 0; i < file.length; i++) {
                fileSizeMap.put(file[i], Integer.parseInt(fileSize[i]));
            }
            int cost = 0;
            for (String item : fileMap.keySet()) {
                int noCacheCost = fileSizeMap.get(item) * fileMap.get(item); // 一个文件大小 * 文件个数
                int cacheCost = fileSizeMap.get(item) + m;  // 一个文件大小 + m(一个文件缓存需要的金币)
                cost += Math.min(noCacheCost, cacheCost);
            }
            System.out.println(cost);
        }
    }
}
