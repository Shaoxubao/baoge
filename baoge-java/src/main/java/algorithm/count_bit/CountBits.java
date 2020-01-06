package algorithm.count_bit;

/**
 * @Author shaoxubao
 * @Date 2020/1/6 11:20
 *
 * 比特位计算:
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 *
 * 示例 1:
 * 输入: 2
 * 输出: [0,1,1]
 *
 * 示例 2:
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 *
 */
public class CountBits {

    public static void main(String[] args) {
        int[] bits = countBits(2);
        for (int i = 0; i < bits.length; i++) {
            System.out.println(bits[i]);
        }

        System.out.println("======================");

        int[] bits2 = countBits(5);
        for (int i = 0; i < bits2.length; i++) {
            System.out.println(bits2[i]);
        }
    }

    /**
     * 时间复杂度：O(n)， 方法只用了一个循环，取决于整数num的大小， 所以时间复杂度是O(n)
     * 空间复杂度：O(n)， 新建数组res，占用了n+1的空间， 所以空间复杂度是O(n)
     *
     *  num:   二进制   1的数目
     *  0      0000      0
     *  1      0001      1
     *  2      0010      1
     *  3      0011      2
     *  4      0100      1
     *  5      0101      2
     *  6      0110      2
     *  7      0111      3
     */
    public static int[] countBits(int num) {
        // 新建一个数组，数组长度为num + 1
        // 因为题设 0 ≤ i ≤ num，从0到num总共需要num+1的空间
        int[] res = new int[num + 1];
        // 从i = 1开始，在num的长度内进行遍历
        // 因为0的二进制数里位1的个数为0，res[0]的值本身就等于0，所以i=0无需统计
        for (int i = 1; i <= num; i++) {
            // i & (i - 1)的位与运算会消去i的二进制数中最低有效的1位
            // 所以正整数i的二进制数中位1的个数，与i & (i - 1)相比少了1个
            // res[i & (i - 1)]找到正整数i & (i - 1)中位1的个数
            // 再多加1个，即得到正整数i中位1的个数
            res[i] = res[i & (i - 1)] + 1;
            // 完整过程举例：
            // 假设 i = 6，n的32位二进制数的最右边四位是 0110
            // i - 1 的32位二进制数的最右边四位是 0101
            // i & (i-1)，即 0110 & 0101 = 0100
            // 原本 0110 中的最低有效1位被消去，即右向左数的第一个1
        }
        return res;
    }

    /**
     * 时间复杂度：O(n)， 方法只用了一个循环，取决于整数num的大小， 所以时间复杂度是O(n)
     * 空间复杂度：O(n)， 新建数组f，占用了n+1的空间， 所以空间复杂度是O(n)
     */
    public static int[] countBits2(int num) {
        // 新建一个数组，数组长度为num+1
        // 因为题设 0 ≤ i ≤ num，从0到num总共需要num+1的空间
        int[] f = new int[num + 1];
        // 从i=1开始，在num的长度内进行遍历
        // 因为0的二进制数里位1的个数为0，f[0]的值本身就等于0，所以i=0无需统计
        for (int i = 1; i <= num; i++) {
            // i >> 1 二进制右移1位，相当于整数运算中的 i / 2
            // i & 1 二进制的位与运算，相当于整数运算中的 i % 2
            // 数组f记录了每个相应位置正整数的二进制数里位1的个数
            // f[i/2]就是i/2这个正整数的二进制数里位1的个数
            // 加上 i % 2，也就是这个正整数模2的余数
            // 每一个正整数i，都遵循 f[i] = f[i/2] + (i%2)
            f[i] = f[i >> 1] + (i & 1);
            // 为什么会有上述结果
            // 实际上是这行代码利用了正整数转二进制数计算方法中的规律；
            // 正整数转二进制数就是该正整数与2相除得到的整数结果继续除以2
            // 重复上述计算，直到运算结果等于1为止，
            // 假设运算过程中有n次余数为1，那么二进制数中就n+1个位1
            // 其中n加上1是最终运算结果里的1
            // 举例如下：
            // 9/2 = 4...1
            // 4/2 = 2...0
            // 2/2 = 1...0
            // 9在与2相除的过程中，出现了1次余数为1的情况，
            // 最终结果为1，运算中总共出现了2次1，所以9的二进制数中有2个1
            // 从上述计算中还可以发现，9的二进制数中有多少个1，
            // 可以参照9/2的正整数结果4的二进制数中有多少个1，
            // 用4的二进制数中1的个数，加上9/2的余数，即9的二进制数中1的个数
            // f[i] = f[i >> 1] + (i & 1); 正是利用了上述规律
        }
        return f;
    }

}
