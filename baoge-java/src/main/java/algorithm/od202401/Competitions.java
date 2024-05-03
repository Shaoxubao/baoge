package algorithm.od202401;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @Author: xubao_shao@163.com
 * @Description:
 * @Date 2024/5/3 19:32
 *
 * 比赛的冠亚季军
 * 题目描述
 * 有N(3<=N<10000)个运动员，他们的id为0到N-1,他们的实力由一组整数表示。他们之间进行比赛，需要决出冠亚军。
 * 比赛的规则是0号和1号比赛，2号和3号比赛，以此类推，每一轮，相邻的运动员进行比赛，获胜的进入下轮;实力值大的获胜，
 * 实力值相等的情况，id小的情况下获胜:轮空的直接进入下一轮.
 * 输入描述:
 * 输入一行N个数字代表N的运动员的实力值(0<=实力值<=10000000000).
 * 输出描述:
 * 输出冠亚季军的id，用空格隔开.
 * 示例1
 * 输入:
 * 2 3 4 5
 * 输出:
 * 3 1 2
 * 说明:
 * 第一轮比赛，id为0实力值为2的运动员和id为1实力值为3的运动员比赛，1号胜出进入下一轮争夺冠亚军，id为2的运动员和id为3的运动员比赛，
 * 3号胜出进入下一轮争夺冠亚军；
 * 冠亚军比赛，3号胜1号:故冠军为3号，亚军为1号。
 * 2号和0号，比赛进行季军的争夺，2号实力值为4，0号实力值2，故2号胜出，得季军。
 * 冠亚季军为3 1 2。
 * 思路
 * 每轮相邻的运动员分入一组比赛，每轮结束后，数组的长度由len变为(len+1)/2，加一的原因为轮空的直接进入下一轮。具体来讲：
 * 假设len为偶数8，那么每轮len的变化过程为：8>4>2>1，长度为2时，争夺冠亚，长度为1时，得到冠军，长度为4时，可以得到4强，季军为4强中非冠亚的较大值。
 * 假设len为基数9，那么每轮len的变化过程为：9>5>3>2>1，长度为2时，争夺冠亚，长度为1时得到冠军，长度为3时，3个运动员中减去冠亚就得到季军。
 * 所以当数组长度降低为3或4时，最终的冠亚季军都在这个数组中，可以先保存数组的值。然后继续下一轮，当进行到最后一轮时（len=1），得到了冠军，
 * 再反推回来，就可以分别得到亚军和季军。考虑到实力分值范围最大值为10000000000，超过了Integer，所以实力分值应该用long表示。
 */
public class Competitions {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToLong(Long::parseLong).toArray();
        int[] res = competition(nums);
        for (int i = 0; i < res.length; i++) {
            if (i != 0) System.out.print(" ");
            System.out.print(res[i]);
        }
    }

    private static int[] competition(long[] nums) {
        int[] res = new int[3]; // 存储最终的冠亚季军id
        int[] indexs = new int[nums.length]; // 记录每个人的id，每轮淘汰时，淘汰id即可
        for (int i = 0; i < nums.length; i++) {
            indexs[i] = i;
        }
        List<Integer> lst4 = new ArrayList<>(); // 存储4强
        while (indexs.length > 1) {
            if (indexs.length == 4 || indexs.length == 3) {
                lst4 = Arrays.stream(indexs).boxed().collect(Collectors.toList());
            }
            int[] newIndexs = new int[(indexs.length + 1) / 2]; // 每轮淘汰一半的人数
            for (int i = 0; i < indexs.length; i += 2) {
                if (i + 1 < indexs.length && nums[indexs[i]] < nums[indexs[i + 1]]) {
                    newIndexs[i / 2] = indexs[i + 1];
                } else {
                    newIndexs[i / 2] = indexs[i];
                }
            }
            if (newIndexs.length == 1) {
                // 得出冠军
                res[0] = newIndexs[0];
                // 得到亚军，此时indexs长度必为2，排除冠军就是亚军了
                res[1] = indexs[0] == res[0] ? indexs[1] : indexs[0];
            }
            indexs = newIndexs;

        }
        Integer[] lst4new = lst4.stream().filter(x -> x != res[0] && x != res[1]).toArray(Integer[]::new);
        // 从4强里排除冠亚军，如果剩余1个，那么那一个就是季军，如果剩余2个，那么其中的较大值是季军。
        if (lst4new.length == 1 || lst4new[0] >= lst4new[1]) {
            res[2] = lst4new[0];
        } else {
            res[2] = lst4new[1];
        }
        return res;
    }

}
