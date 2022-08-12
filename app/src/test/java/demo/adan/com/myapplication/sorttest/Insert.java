package demo.adan.com.myapplication.sorttest;

import org.junit.Test;

import java.util.Arrays;

public class Insert {

    @Test
    public void main() {
        int[] arr = Bubble.arr;
        System.out.println(Arrays.toString(arr));

        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j - 1] <= arr[j]) {
                    break;
                }
                Bubble.changeValue(arr, j, j-1);
            }
        }

        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void InsertTest() {
        int[] arr = Bubble.arr;
        System.out.println(Arrays.toString(arr));
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] < arr[j]){
                    Bubble.changeValue(arr,j,i);
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
