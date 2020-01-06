package algorithm.two_add;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author shaoxubao
 * @Date 2020/1/6 16:35
 *
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 题目只是单纯的要求两个数相加求和，那我们直接用迭代的方式来控制对应位置上数字两两相加。
 * 需要注意的有两点：一是需要考虑两个个位数相加的进位，超过10之后向前进一，这里用一个变量来保存进位。二是因为两个数字的长度不一定一样，长度短的如果位数不够，则用0来补足。
 */
public class TwoAdd {

    public static void main(String[] args) {

        List<Integer> l1 = Arrays.asList(2, 4, 3);             //   2765
        List<Integer> l2 = Arrays.asList(5, 6, 7, 2);          // +  342

        List<Integer> list = twoAdd(l1, l2);
        System.out.println(list);
    }

    public static List<Integer> twoAdd(List<Integer> l1, List<Integer> l2) {
        // 定义返回list
        List<Integer> result = new ArrayList<>();

        // 保存两个一位数相加后结果的十位数上的值：结果大于等于10则为1，否则为0
        int carry = 0;
        int sum = 0, x = 0, y = 0;
        int l1_size = l1.size();
        int l2_size = l2.size();
        int maxSize = l1_size > l2_size ? l1_size : l2_size; // 取list最大size
        int index = 0;
        while (maxSize != index) {

            // 如果当前链表的当前节点为空，则值为null
            x = (index > l1_size - 1) ? 0 : l1.get(index);
            y = (index > l2_size - 1) ? 0 : l2.get(index);

            sum = x + y + carry;

            // 两个位数之和再与10取余，结果存在list
            result.add(sum % 10);

            // 是否有进位
            carry = sum > 9 ? 1 : 0;

            // 链表当前节点不为空，则向后推移一个节点，若为空，则不变
            index++;
        }
        // 当两个链表中的所以节点都相加完之后，判断最后一个相加的结果是否超过10，超过10的时候需要额外增加一个节点来保存进位的结果
        if (carry == 1) result.add(1);

        return result;
    }



}
