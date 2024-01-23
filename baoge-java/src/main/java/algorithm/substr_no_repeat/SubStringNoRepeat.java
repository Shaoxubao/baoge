package algorithm.substr_no_repeat;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author shaoxubao
 * @Date 2020/1/6 17:38
 *
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 题目需要求解最长无重复字符的子串长度。首先依次的遍历该字符串，用一个数据结构（set，map等）来保存已经遍历过的字符，当前字符没有出现过，
 * 则将其添加到我们定义的数据结构中，当前无重复子串长度加一。如果当前字符出现过，则需要将数据结构中与该字符相同字符之前的字符全部删除，并重新计数新的子串长度。
 * 此处需要用一个变量来保存之前无重复子串的最大长度，每次出现重复字符时都要跟之前最大的长度比较判断是否需要更新。
 */
public class SubStringNoRepeat {

    public static void main(String[] args) {
        String inputStr = "abcabcdbb";

        System.out.println(subStringNoRepeat(inputStr));
    }

    public static int subStringNoRepeat(String inputStr) {
        int length = inputStr.length();
        Set<String> set = new HashSet<>();

        int begin = 0, end = 0;
        int maxLength = 0;

        while (begin < length && end < length) {
            String charAtEnd = String.valueOf(inputStr.charAt(end));
            if (!set.contains(charAtEnd)) {
                set.add(charAtEnd);
                end = end + 1;
                // 更新maxLength，此处是重点：注意一定要用max函数比较 当前的不重复字符串长度 与 上一次出现重复字符时记录的最长不重复字符串长度
                maxLength = Math.max(maxLength , end - begin);
            } else {
                // 如果set中包含当前字符，则利用循环将出现重复字符之前的字符全部从set中删除，保证set所留的都是需要重新计算长度的字符
                do {
                    set.remove(String.valueOf(inputStr.charAt(begin)));
                    begin = begin + 1;
                } while(inputStr.charAt(begin - 1) != inputStr.charAt(end));
            }
        }

        return maxLength;
    }

}
