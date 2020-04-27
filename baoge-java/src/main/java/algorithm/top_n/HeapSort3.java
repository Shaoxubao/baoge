package algorithm.top_n;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <xubao_shao@163.com>
 * Date:   2020/4/27
 */
public class HeapSort3 {

    /**
     * 调整堆
     * @param arr
     * @param length 数组长度
     * @param i 数组下标
     */
    public static void heapify(int arr[], int length, int i) {
        if (i >= length) {
            return;
        }
        // 找出i节点的左右两个子节点
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        int max = i; // 记录最大值坐标

        if (left < length && arr[left] > arr[max]) {
            max = left;
        }
        if (right < length && arr[right] > arr[max]) {
            max = right;
        }

        // max值改变时交换
        if (max != i) {
            swap(arr, max, i);
            heapify(arr, length, max); // 递归，再以max开始调整
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 构建堆，从最后一个父节点开始调整
     *                                                    7(0)
     *                                           6(1)      |       7(2)
     *                                     11(3)     5(4)  |  12(5)     3(6)
     *                                 0(7)   1(8)         |
     */
    public static void buildHeap(int[] arr, int length) {
        int lastIndex = length - 1;                 // 最后一个节点index
        int lastParentIndex = (lastIndex - 1) / 2;  // 最后一个节点父节点
        for (int i = lastParentIndex; i >= 0; i--) {
            heapify(arr, length, i);
        }
    }

    public static void heapSort(int[] arr, int length) {
        // 构建大顶堆
        buildHeap(arr, length);
        // 交换堆顶和堆尾元素，再用除堆尾的元素再做调整为大顶堆
        for (int i = length - 1; i >= 0; i--) {
            swap(arr, i, 0);
            heapify(arr, i, 0);
        }
    }

    public static void main(String[] args) {
//        int[] arr = {2, 5, 3, 1, 10, 4};
        int []arr = {7, 6, 7, 11, 5, 12, 3, 0, 1};
        int length = arr.length;

        // 调整堆测试
//        heapify(arr, length, 0);
//        for (int i = 0; i < length; i++) {
//            System.out.println(arr[i]);
//        }

        // 构建堆，由下而上
//        buildHeap(arr, length);
//        for (int i = 0; i < length; i++) {
//            System.out.println(arr[i]);
//        }

        // 排序
        heapSort(arr, length);
        for (int i = 0; i < length; i++) {
            System.out.println(arr[i]);
        }
    }

}
