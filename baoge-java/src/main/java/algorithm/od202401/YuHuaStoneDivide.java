package algorithm.od202401;

/**
 * MELON的难题:
 * MELON有一堆精美的雨花石(数量为n，重量各异)，准备送给S和W。MELON希望送给俩人的雨花石重星一致，
 * 请你设计一个程序帮MELON确认是否能将雨花石平均分配。
 * 输入描述
 * 第1行输入为雨花石个数:n，0 < n <31.
 * 第2行输入为空格分割的各雨花石重量: m[0] m[1 ]… m[n - 1], 0 < m[k] < 1001不需要考虑异常输入的情况。
 * 输出描述
 * 如果可以均分，从当前雨花石中最少拿出几块，可以使两堆的重量相等:
 * 如果不能均分，则输出-1。
 * 示例1:
 * 输入
 * 4
 * 1 1 2 2
 * 输出
 * 2
 * 说明
 * 输入第一行代表共4颗雨花石，第二行代表4颗雨花石重量分别为1、1、2、2。均分时只能分别为1,2，
 * 需要拿出重星为1和2的两块雨花石，所以输出2。
 * 示例2:
 * 输入
 * 10
 * 1 1 1 1 1 9 8 3 7 10
 * 输出
 * 3
 * 说明
 * 输入第一行代表共10颗雨花石，第二行代表4颗雨花石重量分别为1、1、1、1、1、9、8、3、7、10。
 * 均分时可以1,1,1,1,1,9,7和10,8,3，也可以1,1,1,1,9.8和10,7,3,1，或者其他均分方式，
 * 但第一种只需要拿出重量为10.8,3的3块雨花石，第二种需要拿出4块,所以输出3(块数最少)。
 */
public class YuHuaStoneDivide {
    public static void main(String[] args) {

    }
}
