package algorithm.zigzag_conversion;

/**
 * @Author shaoxubao
 * @Date 2020/1/9 11:27
 *
 * Z 字形变换:
 *
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 *
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 *
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * string convert(string s, int numRows);
 * 示例 1:
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 *
 * 示例 2:
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 *
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 */
public class ZigZagConversion {

    public static void main(String[] args) {
        System.out.println(convert("LEETCODEISHIRING", 3));
        System.out.println(convert1("LEETCODEISHIRING", 3));
    }

    /**
     * 观察示例可知，往下走要走numRows步，往上走要走numRows-2步（除去头尾，因为和往下走重叠）
     * 模拟Z字遍历字符串
     *
     * 往下走numRows步
     * 往上走numRows-2步
     * 重复1，2直到字符串遍历结束
     */
    public static String convert(String s, int numRows) {
        if (numRows < 2)
            return s;

        StringBuilder[] sbs = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++)
            sbs[i] = new StringBuilder();

        int index = 0, len = s.length();
        while (index < len) {
            for (int row = 0; row < numRows && index < len; row++)     // 往下走
                sbs[row].append(s.charAt(index++));
            for (int row = numRows - 2; row > 0 && index < len; row--) // 往上走
                sbs[row].append(s.charAt(index++));
        }

        for (int row = 1; row < numRows; row++)
            sbs[0].append(sbs[row]);

        return sbs[0].toString();
    }

    /**
     * 当排列行数为 n=3 时：（没加括号的数字表示在原字符串中的下标，加括号的数字为相邻两数字的差）
     *
     * 0 (4) 4 (4)       8   12
     * 1 (2) 3 (2) 5 (2) 7 9 11
     * 2 (4)       6 (4)   10
     * 当排列行数为 n=4 时：
     *
     * 0 (6) 6 (6)       12
     * 1 (4) 5 (2) 7 (4) 11 (2) 13
     * 2 (2) 4 (4) 8 (2) 10 (4) 14
     * 3 (6)       9 (6)        15
     * 当排列行数为 n=5 时：
     *
     * 0 (8) 8 (8)        16 (8)
     * 1 (6) 7 (2) 9  (6) 15 (2) 17
     * 2 (4) 6 (4) 10 (4) 14 (4) 18
     * 3 (2) 5 (6) 11 (2) 13 (6) 19
     * 4 (8)       12 (8)        20
     *
     * 规律如下：
     * 按行看，
     * 第 0 行和第 n-1 行的下标间距为 2*(n-1)，
     * 当 i = 1 到 n-2 时，第 i 行的下标间距为 2*(n - 1 - i) 和 2*i 交替。
     * 编码技巧如下：
     * 从上面可以知道，中间行的下标间距是在两个值之间来回变动的。我们可以使用一点小技巧方便的判断当前的间距应该是哪个值，使得代码更简洁优美。
     * 下面两点小技巧效果是等同的：
     *
     * 利用异或运算，假设初始化 a = 0，每次操作完后，令 a = a ^ 1，就可以自动让 a 在 0，1 之间来回变动，我们只需要判断当前的 a 为何值就可知道现在应该是用哪个间距值。
     * 我们也可以初始化 a = -1，然后每次操作完后，令 a = -a，可以让 a 在 -1，1 之间来回变动，从而实现相同的效果。
     *
     */
    public static String convert1(String s, int numRows) {
        if(s == null || s.length() == 0 || numRows <= 1) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s.length());
        int a, j;
        for(int i = 0; i < numRows; ++i) {
            a = 0;
            j = i;
            while (true) {
                if (j < s.length()) {
                    sb.append(s.charAt(j));
                } else break;
                if ((a ^= 1) == 1) {
                    j += 2 * ((numRows - i - 1) == 0 ? i : numRows - i - 1);
                } else {
                    j += 2 * (i == 0 ? numRows - i - 1 : i);
                }
            }
        }
        return sb.toString();
    }

}
