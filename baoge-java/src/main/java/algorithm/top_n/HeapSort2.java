package algorithm.top_n;

import java.util.Arrays;

/**
 * @Author shaoxubao
 * @Date 2019/7/18 9:33
 */
public class HeapSort2 {

    public static void heapSort(int[] array, int size) {
        int i;
        int j;
        int temp;

        for (i = (int) Math.floor(size / 2) - 1; i >= 0; i--) {
            heapify(array, size, i);
        }

        for (j = size - 1; j >= 0; j--) {
            temp = array[0];
            array[0] = array[j];
            array[j] = temp;

            heapify(array, j, 0);

        }
    }

    public static void heapify(int[] array, int size, int root) {
        int largest = root;
        int left = 2 * root + 1;
        int right = 2 * root + 2;
        int temp;

        if (left < size && array[left] > array[largest]) {
            largest = left;
        }

        if (right < size && array[right] > array[largest]) {
            largest = right;
        }

        if (largest != root) {
            temp = array[root];
            array[root] = array[largest];
            array[largest] = temp;

            heapify(array, size, largest);
        }
    }

    public static void main(String[] args) {

        int []arr = {7, 6, 7, 11, 5, 12, 3, 0, 1};

        System.out.println("排序前："+ Arrays.toString(arr));
        heapSort(arr, arr.length);
        System.out.println("排序后："+ Arrays.toString(arr));
    }


}
