package algorithm.od202401;

import java.util.Scanner;

/**
 * 按单词下标区间翻转文章内容
 * 题目描述：
 * 输入一个英文文章片段，翻转指定区间的单词顺序，标点符号和普通字母一样处理。
 * 例如输入字符串 “I am a developer.”，区间[0,3]则输出 “developer a am I”。
 * 输入描述：
 * 使用换行隔开三个参数
 * 第一个参数为英文文章内容即英文字符串
 * 第二个参数为反转起始单词下标，下标从0开始
 * 第三个参数为结束单词下标，
 * 输出描述：
 * 反转后的英文文章片段，所有单词之间以一个半角空格分割进行输出
 * 示例：
 * 输入	I am a developer.
 * 1
 * 2
 * 输出	I a am developer.
 * 说明	无
 * 输入	hello world
 * -1
 * 1
 * 输出	world hello
 * 说明	下标小于0时，从第一个单词开始
 * 输入	I am a developer
 * 0
 * 5
 * 输出	developer a am I
 * 说明	下标大于实际单词个数，则按最大下标算
 * 输入	I am a developer
 * -2
 * -1
 * 输出	I am a developer
 * 说明	翻转区间无效时，不做翻转
 */
public class ReverseWords {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] strArr = input.split(" ");
        int startIndex = sc.nextInt();
        int endIndex = sc.nextInt();
        if (endIndex < 0 && startIndex < 0 || startIndex > endIndex) {
            System.out.println(input);
            return;
        }
        if (endIndex > strArr.length - 1) {
            endIndex = strArr.length - 1;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }

        for (int i = 0; i < startIndex; i++) {
            System.out.print(strArr[i] + " ");
        }
        for (int i = endIndex; i >= startIndex; i--) {
            System.out.print(strArr[i] + " ");
        }
        for (int i = endIndex + 1; i < strArr.length; i++) {
            System.out.print(strArr[i] + " ");
        }
    }
}
