package algorithm.palindrome_substr;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author shaoxubao
 * @Date 2020/1/8 16:56
 *
 * 最长回文子串
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 */
public class PalindromeSubString {

    public static void main(String[] args) {
        System.out.println(longestPalindromeSubString("babad"));
        System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindromeByDynamicProgramming("babad"));
    }

    public static String longestPalindromeSubString(String input) {
        // 用来记录上次最长的回文串长度
        int max = 1;
        // 存储当前长度对应的回文串
        Map<Integer, String> subStringMap = new HashMap<>();
        subStringMap.put(1, Character.toString(input.charAt(0)));
        int len = input.length();
        // 当前迭代回文串存在的条件是,要么上次迭代存在回文串,要么上上次存在
        boolean flag = false;
        for (int i = 2; i <= len; i++) {
            for (int t = 0; t + i <= len; t++) {
                if ((i - 1) != max && (i - 2) != max)
                    break;
                if (flag) {
                    flag = false;
                    continue;
                }
                String subString = input.substring(t, t + i);
                if (check(subString)) {
                    // 注意,只要当前长度找到一个回文串就可以了,不需要再找了
                    max = i;
                    if (!subStringMap.containsKey(i)) {
                        subStringMap.put(i, subString);
                    } else
                        flag = true;
                    break;
                }
            }
        }
        return subStringMap.get(max);
    }

    private static boolean check(String subString) {
        int len = subString.length();
        int mid = len / 2;
        for (int i = 0; i < mid; i++) {
            // 从两遍向中间靠拢对比
            if (subString.charAt(i) != subString.charAt(len - 1 - i))
                return false;
        }
        return true;
    }

    public static String longestPalindrome(String s) {
        if(s.isEmpty()) {
            return s;
        }
        String res = s.substring(0, 1);
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String k = s.substring(i, j);
                String rk = new StringBuffer(k).reverse().toString();
                if (k.equals(rk) && k.length() > res.length()) {
                    res = k;
                }
            }
        }
        return res;
    }

    // 动态规划
    public static String longestPalindromeByDynamicProgramming(String s) {
        if (s.isEmpty()) {
            return s;
        }
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int left = 0;
        int right = 0;
        for (int i = n - 2; i >= 0; i--) {
            dp[i][i] = true;
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && ( j - i < 3 || dp[i + 1][j - 1]); // 小于3是因为aba一定是回文
                if (dp[i][j] && right - left < j - i) {
                    left = i;
                    right = j;
                }
            }
        }
        return s.substring(left, right + 1);
    }

}
