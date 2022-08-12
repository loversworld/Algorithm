package demo.adan.com.myapplication.sorttest;

import android.os.Bundle;

import org.junit.Test;

import java.util.Arrays;

public class Quick {

    @Test
    public void main() {

        int[] arr = Bubble.arr;

        System.out.println(Arrays.toString(arr));

//        quickSort(arr, 0, arr.length - 1);

//        quickSort2(arr, 0, arr.length - 1);
        quickSort3(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public void quickSort3(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int l = left;
        int r = right;
        int pivot = arr[left];

        for (int k = 0; k < arr.length; k++) {
            for (int i = right; i > right; i--) {
                if (pivot >= arr[right]) {
                    Bubble.changeValue(arr,pivot,right);
                    right--;
                }
            }

            if (left < right) {
                right--;
            }

            for (int i = left; i < right; i++) {
                if (pivot <= arr[left]) {
                    Bubble.changeValue(arr,pivot,right);
                }
                left++;
            }
            if (left < right) {
                left++;
            }
        }


        quickSort(arr,left,r-1);
        quickSort(arr,right + 1,r);
    }


    public void quickSort2(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int l = left;
        int r = right;
        int pivot = arr[left];

        while (left < right) {

            while (left < right && arr[right] >= pivot) {
                right --;
            }
            if (left < right) {
                arr[left] = arr[right];
            }

            while (left < right && arr[left] <= pivot) {
                left ++;
            }
            if (left < right) {
                arr[right] = arr[left];
            }
        }
        quickSort(arr, left, r - 1);
        quickSort(arr, right + 1, r);
    }

    public void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int l = left;
        int r = right;
        int pivot = arr[left];

        while (left < right) {

            while (left < right && arr[right] >= pivot) {
                right --;
            }
            if (left < right) {
                arr[left] = arr[right];
                left ++;
            }

            while (left < right && arr[left] <= pivot) {
                left ++;
            }
            if (left < right) {
                arr[right] = arr[left];
                right --;
            }
        }
        arr[left] = pivot;
        quickSort(arr, left, r - 1);
        quickSort(arr, right + 1, r);
    }
}
