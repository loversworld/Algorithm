package demo.adan.com.myapplication.sorttest;

import org.junit.Test;

import java.util.Arrays;

public class Shell {

    @Test
    public void main() {
        int[] arr = Bubble.arr;

        System.out.println(Arrays.toString(arr));

        // 增量(递减)循环
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 根据当前增量对数据分组
            for (int i = 0; i < gap; i++) { // i: 第几组数据/该组数据的第一个元素下标
                // 对该组数据插入排序
                for (int j = i + gap; j < arr.length; j += gap) { // 循环插入排序未排序部分
                    for (int k = j - gap; k >= 0 ; k -= gap) {  // 循环插入排序已排序部分
                        if (arr[k] <= arr[k+gap]) {
                            break;
                        }
                        Bubble.changeValue(arr,k,k+gap);
                    }
                }
            }

        }

        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void shell() {
        int[] arr = Bubble.arr;

        System.out.println(Arrays.toString(arr));

        for (int gap = arr.length; gap > 0; gap /= 2) {
            // 排序
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] <= arr[j+gap]) {
                        break;
                    }
                    Bubble.changeValue(arr,j,j+gap);
                }
            }
        }


        System.out.println(Arrays.toString(arr));
    }

}
