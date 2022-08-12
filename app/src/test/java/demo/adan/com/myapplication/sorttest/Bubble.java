package demo.adan.com.myapplication.sorttest;

import org.junit.Test;

import java.util.Arrays;


public class Bubble {

    public static int[] arr = {4, 7, 5, 6, 3, 8, 35,9, 8};

    @Test
    public void main() {
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                    changeValue(arr, j,j+1);
                }
            }
        }
        System.out.print(Arrays.toString(arr));

    }
    
    public static void changeValue(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
//        arr[a] = arr[a] + arr[b];
//        arr[b] = arr[a] - arr[b];
//        arr[a] = arr[a] - arr[b];
    }

}
