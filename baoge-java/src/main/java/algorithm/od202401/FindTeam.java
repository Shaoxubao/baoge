package algorithm.od202401;

import java.util.Scanner;

/**
 * 题目描述
 * 总共有 n 个人在机房，每个人有一个标号 (1<=标号<=n) ，他们分成了多个团队，
 * 需要你根据收到的 m 条消息判定指定的两个人是否在一个团队中，具体的:
 * 消息构成为 a b c，整数 a、b 分别代表两个人的标号，整数 c 代表指令。
 * c== 0 代表a和b在一个团队内。
 * c == 1 代表需要判定 a 和b 的关系，如果 a和b是一个团队，输出一行"we are a team",如果不是，输出一行"we are not a team"。
 * c 为其他值，或当前行a或b 超出 1~n 的范围，输出 "da pian zi"。
 * 输入描述
 * 第一行包含两个整数 n，m(1<=n.m<=100000).分别表示有n个人和 m 条消息。
 * 随后的 m 行，每行一条消息，消息格式为: a b c (1<=a,b<=n, 0<=c<=1)
 * 输出描述
 * c ==1.根据 a 和 b 是否在一个团队中输出一行字符串,在一个团队中输出 "we are a team", 不在一个团队中输出 "we are not a team"。
 * c 为其他值，或当前行 a 或 b 的标号小于 1 或者大于 n 时，输出字符串 "da pian zi"。
 * 如果第一行 n 和 m的值超出约定的范围时，输出字符串"NULL"。
 * 示例1
 * 输入
 * 5 6
 * 1 2 0
 * 1 2 1
 * 1 5 0
 * 2 3 1
 * 2 5 1
 * 1 3 2
 *
 * 输出
 * we are a team
 * we are not a team
 * we are a team
 * da pian zi
 */
public class FindTeam {

    private static boolean checkRange(int a, int b, int c) {
        return 1 <= a && a <= 100000 && 1 <= b && b <= 100000 && 0 <= c && c <= 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), m = scanner.nextInt();
        if (!checkRange(n, m, 0)) {
            System.out.println("NULL");
            return;
        }

        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < m; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            if (checkRange(a, b, c)) {
                if (c == 0) {
                    uf.merge(a, b);
                } else if (uf.find(a) == uf.find(b)) {
                    System.out.println("we are a team");
                } else {
                    System.out.println("we are not a team");
                }
            } else {
                System.out.println("da pian zi");
            }
        }
    }

}

/**
 * 并查集
 * 学习参考： https://zhuanlan.zhihu.com/p/93647900
 **/
class UnionFind {
    // father[2] = 3 表示元素2的父节点是3
    public int[] father;

    public UnionFind(int len) {
        father = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            father[i] = i;
        }
    }

    // 查询 x 的根节点
    public int find(int x) {
        if (x < 0 || x >= father.length) {
            throw new RuntimeException("查询越界");
        }

        // 合并（路径压缩）
        return (x == father[x] ? x : (father[x] = find(father[x])));
    }

    // 合并节点, y 的根节点指向 x 的根节点
    public void merge(int x, int y) {
        int xRoot = find(x), yRoot = find(y);
        father[yRoot] = xRoot;
    }
}

