package demo.adan.com.myapplication.sorttest;

import android.os.Bundle;

import org.junit.Test;

import java.util.Arrays;

public class Selection {

    @Test
    public void main() {
        int[] arr = Bubble.arr;
        System.out.println(Arrays.toString(arr));

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            Bubble.changeValue(arr,minIndex, i);
        }

        System.out.println(Arrays.toString(arr));
    }
}
