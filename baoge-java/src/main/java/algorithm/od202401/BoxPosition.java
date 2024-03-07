package algorithm.od202401;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
  要求将一批箱子按从上到下以‘之’字形的顺序摆放在宽度为 n 的空地上，输出箱子的摆放位置，
  例如：箱子ABCDEFG，空地宽为3。
  如输入：
  ABCDEFG 3
  输出：
  AFG
  BE
  CD
  注：最后一行不得输出额外的空行
  str只包含数字和数字，1<=len(str)<=1000，1<=n<=1000。
  解题思路：
  主要的技巧点在于处理奇数列和偶数列的时候，正序和倒序的问题；
  1.将输入的字符串按空格分割为两部分，分别为箱子的字符串和空地的宽度；
  2.创建一个HashMap，用于存储每行的字符串；Key为行的下标，Value为对应的字符串；
  3.遍历箱子的字符串，根据空地的宽度确定每个字符应该放置的行和列；
  计算当前字符所在的列，即columnIdx = i / num，其中i为当前字符的索引；
  如果columnIdx是偶数，则表示从左往右摆放，行索引不变，列索引为i % num；
  如果columnIdx是奇数，则表示从右往左摆放，行索引不变，列索引为num - 1 - (i % num)，即倒序摆放；
  将当前字符添加到对应行的字符串中；
     过程推演：
     i:                   0 1 2 3 4 5 6
     columnIdx = i / num: 0 0 0 1 1 1 2
     i % num:             0 1 2       0
     num - 1 - (i % num)        2 1 0
  4.遍历HashMap map，按行输出箱子的摆放位置；
  5.输出结果。
 */
public class BoxPosition {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] arr = line.split(" ");
        // 表格的行数
        int num = Integer.parseInt(arr[1]);
        // key：每一行的下标，value：对应的字符串
        Map<Integer, String> map = new HashMap<>();
        String str = arr[0];
        for (int i = 0; i < str.length(); i++) {
            int columnIdx = i / num;
            // 行数索引
            int index;
            if (columnIdx % 2 == 0) {
                // 偶索引正序
                index = i % num;
            } else {
                // 奇数列倒序
                index = num - 1 - i % num;
            }
            String strFromMap = map.getOrDefault(index, "");
            String val = strFromMap + str.charAt(i);
            map.put(index, val);
        }
        for (int key : map.keySet()) {
            System.out.println(map.get(key));
        }
    }
}
