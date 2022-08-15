package demo.adan.com.myapplication.Leetcode.adan.LintCode;

import org.junit.Test;

/**
 * created by adan on 2018/8/2.
 */
public class N6V2_MergeArray {

    /**
     * 描述
     * 合并两个排序的整数数组A和B变成一个新的数组。
     * <p>
     * 样例
     * 给出A=[1,2,3,4]，B=[2,4,5,6]，返回 [1,2,2,3,4,4,5,6]
     * <p>
     * 挑战
     * 你能否优化你的算法，如果其中一个数组很大而另一个数组很小？
     */
    @Test
    public void test() {
        int[] result = merge(new int[]{3, 10, 31, 40, 46, 88}, new int[]{2, 3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 15, 20, 21, 22, 23, 34, 56, 67, 89, 99, 100, 101, 123, 134, 135, 190, 309, 450});
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }

    private int[] merge(int arr1[], int arr2[]) {
        int len1 = arr1.length;
        int len2 = arr2.length;
        int[] result = new int[len1 + len2];
        if (arr1[0] > arr2[len2 - 1]) {
            // arr1>arr2
            for (int i = 0; i < len2; i++) {
                result[i] = arr2[i];
            }
            for (int i = 0; i < len1; i++) {
                result[len2 + i] = arr1[i];
            }
        } else if (arr1[len1 - 1] < arr2[0]) {
            // arr1<arr2
            for (int i = 0; i < len1; i++) {
                result[i] = arr1[i];
            }
            for (int i = 0; i < len2; i++) {
                result[len1 + i] = arr2[i];
            }
        } else {
            int i = 0, j = 0;
            while (i < len1) {
                if (j >= len2) {
                    for (int a = i; a < len1; a++) {
                        result[a + j] = arr1[a];
                    }
                    break;
                }
                int index = find2(arr1, i, len1 - 1, arr2[j]);
                for (int a = i; a < index; a++) {
                    result[a + j] = arr1[a];
                }
                i = index;
                result[i + j] = arr2[j];
                j++;
            }
            for (int a = j; a < len2; a++) {
                result[i + a] = arr2[a];
            }
        }
        return result;
    }

    private int find2(int[] arr, int start, int end, int key) {
        if (start >= end) {
            if (arr[start] > key) {
                return start;
            } else {
                return start + 1;
            }
        }
        int m = start;
        while (start < end) {
            int middle = (start + end) / 2;
            if (arr[start] >= key) {
                return start;
            } else if (arr[end] <= key) {
                return end + 1;
            } else {
                if (arr[middle] <= key) {
                    if (arr[middle + 1] >= key) {
                        return middle + 1;
                    } else {
                        start = middle++;
                    }
                } else {
                    end = middle--;
                }
            }
        }
        return m;
    }

}
