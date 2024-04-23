package algorithm.od202401;

/**
 *给定一个非空的整数数组，计算长度为k的连续子数组的最大和。
 * 输入：array={10,20,30,50}，k=2。
 * 输出：80。
 */
public class MaxSumSliding {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 50};
        System.out.println(maxSumSliding(arr, 2));
    }

    public static int maxSumSliding(int[] arr, int k) {
        int size = arr.length;
        if (size < k) {
            return -1;
        }

        int maxSum = 0;
        for (int i = 0; i < k; i++) {
            maxSum += arr[i];
        }

        int sum = maxSum;
        for (int i = k; i < size; i++) {
            sum = sum + arr[i] - arr[i - k];
            maxSum = Math.max(sum, maxSum);
        }

        return maxSum;
    }

}
