package demo.adan.com.myapplication.sorttest;

import org.junit.Test;

public class Temp {

    @Test
    public void main() {

        int[] arr = Bubble.arr;

        // 增量
        int gap = arr.length / 2;

        while (gap>=1){

            // 组数
            for (int i = 0; i < gap; i++) {

                // 每组
                for (int j = i + gap; j < arr.length; j += gap) {
                    // 插入排序 从每组第二个元素(也就是下表为i + gap的元素)开始往前插入
                    for (int k = j - gap; k >= i; k -= gap) {
                        if (arr[k] > arr[k + gap]) {
                            Bubble.changeValue(arr, k, k + gap);
                        }

                    }

                }
            }

            gap /= 2;
        }

//        // 遍历增量
//        for (int gap = arr.length / 2; gap > 0 ; gap /= 2) {  // gap: 第几个增量
//            // 增量为gap时, 分几组 排序
//            for (int i = 0; i < gap; i++) {
//                // 对第i组进行插入排序 arr[i] arr[i+gap] arr[i+2gap] arr[i+3gap] arr[i+4gap]...
//                // 遍历未排序部分
//                for (int j = i + gap; j < arr.length; j += gap) {  // j:第i组的元素下标
//                    // 插入排序:遍历已排序的部分
//                    for (int k = j - gap; k >= i; k -= gap) {
//                        if (arr[k] > arr[k+gap]) {
//                            Bubble.changeValue(arr,k,k+gap);
//                        }
//                    }
//                }
//
//            }
//
//        }

    }


    public void quickSort(int[] arr, int left, int right) {

        if (right - left <= 1) return;

        int indexContent = arr[left];
        int l = left;
        int r = right;

        while (l < r) {


            while (l < r && arr[r] >= indexContent) {
                r--;
            }

            if (l < r) {
                arr[l] = arr[r];
                l++;
            }

            while (l < r && arr[l] <= indexContent) {
                l++;
            }

            if (l < r) {
                arr[r] = arr[l];
                r--;
            }

        }

        arr[l] = indexContent;

        quickSort(arr, left, l - 1);
        quickSort(arr, l + 1, right);
    }
}
