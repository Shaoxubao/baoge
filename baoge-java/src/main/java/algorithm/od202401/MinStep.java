package algorithm.od202401;

import java.util.Scanner;

/**
 求从坐标零点到坐标n的最小步数，一次只能沿横坐标轴向左或向右移动2或3。注意：途经的坐标可以为负数。
 输入描述
 坐标点n
 输出描述
 输出从坐标零点移动到坐标n的移动步数。
 示例一
 输入:
 4
 输出：
 2
 说明：
 从坐标零点移动到4，最小需要两步，即向右移2，再向右移2，共移动2次即2步。
 解题推演：
 从后向前推，走向n时，它的前一步是 n-2，或者是 n-3，这样只需要一步就能走到n，
 在其他位置时，相当于求它的前一步是 n-2，或者是 n-3的最小步数，
 可以得到一个公式: fn(n) = Math.min(fn(n-2)+1 , fn(n-3)+1)
 n=1时，fn(1) = 2;
 n=2时，fn(2) = 1;
 n=3时，fn(3) = 1;
 n=4时，fn(4) = 2;  //  fn(4) = Math.min(fn(4-2)+1 , fn(4-3)+1) = Math.min(1 + 1, 2 + 1);
 n=5时，fn(5) = 2;  //  fn(5) = Math.min(fn(5-2)+1 , fn(5-3)+1) = Math.min(1 + 1, 1 + 1);
 n=6时，fn(6) = 2;
 n=7时，fn(7) = 3;    // 3 2 2
 n=8时，fn(8) = 3;    // 3 3 2
 n=9时，fn(9) = 3;    // 3 3 3
 n=10时，fn(10) = 3;  // 3 3 2 2
 ...
 n=15时，fn(15) = 5;  // 3 3 3 3 3
 n=16时，fn(16) = 6;  // 3 3 3 3 2 2
 ...
 */
public class MinStep {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            int n = Integer.parseInt(scanner.nextLine());
            System.out.println(minStep(n));
            System.out.println(minStep2(n));
        }
    }

    public static int minStep(int n) {
        if (n == 1) {
            return 2; // -2,3（右2左3共2步）
        }
        if (n == 2 || n == 3) {
            return 1;
        }
        return Math.min(minStep(n - 2) + 1, minStep(n - 3) + 1);
    }

    public static int minStep2(int n) {
        int result = 0;
        if (n == 1 || n == -1) {
            result = 2;
        } else if (n % 3 == 1 || n % 3 == 2) {
            result = n / 3 + 1;
        } else if (n % 3 == 0) {
            result = n / 3;
        }
        return result;
    }
}
