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
        System.out.println(longestPalindrome1("acdbbdaalkmmmjmmmkl"));
        System.out.println(longestPalindrome2("acdbbdaa"));
        System.out.println(longestPalindromeSubString("babad"));
        System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindromeByDynamicProgramming("babad"));
    }

    public static String longestPalindrome1(String s) {

        if (s == null || s.length() == 0) {
            return "";
        }
        int strLen = s.length();
        int left = 0;
        int right = 0;
        int len = 1;
        int maxStart = 0;
        int maxLen = 0;

        for (int i = 0; i < strLen; i++) {
            left = i - 1;
            right = i + 1;
            while (left >= 0 && s.charAt(left) == s.charAt(i)) {
                len++;
                left--;
            }
            while (right < strLen && s.charAt(right) == s.charAt(i)) {
                len++;
                right++;
            }
            while (left >= 0 && right < strLen && s.charAt(right) == s.charAt(left)) {
                len = len + 2;
                left--;
                right++;
            }
            if (len > maxLen) {
                maxLen = len;
                maxStart = left;
            }
            len = 1;
        }
        return s.substring(maxStart + 1, maxStart + maxLen + 1);

    }

    /**
     * 上面中心扩散的方法，其实做了很多重复计算。动态规划就是为了减少重复计算的问题。动态规划听起来很高大上。其实说白了就是空间换时间，将计算结果暂存起来，
     * 避免重复计算。作用和工程中用 redis 做缓存有异曲同工之妙。 我们用一个 boolean dp[l][r] 表示字符串从 i 到 j 这段是否为回文。
     * 试想如果 dp[l][r]=true，我们要判断 dp[l-1][r+1] 是否为回文。只需要判断字符串在(l-1)和（r+1)两个位置是否为相同的字符，是不是减少了很多重复计算。
     * 进入正题，动态规划关键是找到初始状态和状态转移方程。 初始状态，l=r 时，此时 dp[l][r]=true。
     * 状态转移方程，dp[l][r]=true 并且(l-1)和（r+1)两个位置为相同的字符，此时 dp[l-1][r+1]=true。
     */
    public static String longestPalindrome2(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int strLen = s.length();
        int maxStart = 0;  // 最长回文串的起点
        int maxEnd = 0;    // 最长回文串的终点
        int maxLen = 1;    // 最长回文串的长度

        boolean[][] dp = new boolean[strLen][strLen];

        for (int r = 1; r < strLen; r++) {
            for (int l = 0; l < r; l++) {
                if (s.charAt(l) == s.charAt(r) && (r - l <= 2 || dp[l + 1][r - 1])) {
                    dp[l][r] = true;
                    if (r - l + 1 > maxLen) {
                        maxLen = r - l + 1;
                        maxStart = l;
                        maxEnd = r;
                    }
                }
            }
        }
        return s.substring(maxStart, maxEnd + 1);

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
        if (s.isEmpty()) {
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

    /**
     * 动态规划:
     * 我们创建一个二维数组，boolean[][]dp,其中dp[i][j]表示字符串第i到j是否为回文。
     * 那么边界值其实很清楚了，字符串长度为1的都为true。
     * 状态转换如何设定呢？当字符串i所在的字符等于字符串j所在的字符，并且它的内部(dp[i+1][j-1])为回文那么dp[i][j]为true。
     * 因为这样的规律，我们要保证判断dp[i][j]的时候dp[i+1][j-1]已经判断，所以我们遍历采用i降序j升序的嵌套遍历的方式
     */
    public static String longestPalindromeByDynamicProgramming(String s) { // "babad"
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
                // 3  4             3              4      4   3           3      4
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]); // 小于3是因为aba一定是回文
                if (dp[i][j] && right - left < j - i) {
                    left = i;
                    right = j;
                }
            }
        }
        return s.substring(left, right + 1);
    }

}
