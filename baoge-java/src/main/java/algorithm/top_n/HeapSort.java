package algorithm.top_n;

import java.util.Arrays;

/**
 * @Author shaoxubao
 * @Date 2019/7/16 18:58
 * 一 堆排序的基本思路：
 *    a.将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆;
 *
 *    b.将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
 *
 *    c.重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
 *
 * 二 复杂度分析
 *
 *    1. 时间复杂度：堆排序是一种选择排序，整体主要由构建初始堆+交换堆顶元素和末尾元素并重建堆两部分组成。其中构建初始堆经推导复杂度为O(n)，在交换并重建堆的过程中，需交换n-1次，
 *    而重建堆的过程中，根据完全二叉树的性质，[log2(n-1),log2(n-2)...1]逐步递减，近似为nlogn。所以堆排序时间复杂度最好和最坏情况下都是O(nlogn)级。
 *
 *    2. 空间复杂度：堆排序不要任何辅助数组，只需要一个辅助变量，所占空间是常数与n无关，所以空间复杂度为O(1)。
 */
public class HeapSort {
    public static void main(String []args){
        int []arr = {7, 6, 7, 11, 5, 12, 3, 0, 1};
        System.out.println("排序前："+ Arrays.toString(arr));
        sort(arr);
        System.out.println("排序后："+ Arrays.toString(arr));
    }

    public static void sort(int []arr){
        // 1.构建大顶堆
        for(int i = arr.length / 2 - 1; i >= 0; i--) {
            // 从第一个非叶子结点(最后一个父节点)从下至上，从右至左调整结构
            adjustHeap(arr, i, arr.length);
        }
        // 2.调整堆结构+交换堆顶元素与末尾元素
        for(int j = arr.length - 1; j > 0; j--) {
            swap(arr,0, j);          // 将堆顶元素与末尾元素进行交换
            adjustHeap(arr,0, j);     // 重新对堆进行调整
        }

    }

    /**
     * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）                7(0)
     * @param arr                                         6(1)      |       7(2)
     * @param i                                     11(3)     5(4)  |  12(5)     3(6)
     * @param length                             0(7)   1(8)        |
     */
    public static void adjustHeap(int []arr, int i, int length) {
        int temp = arr[i]; // 先取出当前元素i
        for(int k = i * 2 + 1; k < length; k = k * 2 + 1) { // 从i结点的左子结点开始，也就是2i+1处开始
            if(k + 1 < length && arr[k] < arr[k + 1]) {     // 如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            if(arr[k] > temp) { // 如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp; // 将temp值放到最终的位置
    }

    /**
     * 交换元素
     * @param arr
     * @param a
     * @param b
     */
    public static void swap(int []arr,int a ,int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
