package demo.adan.com.myapplication.Leetcode.adan.LintCode;

import org.junit.Test;

public class N903V3_Update {

    @Test
    public void test() {
        getModifiedArray(5, new int[][]{{1, 3, 2}, {2, 4, 3}, {0, 2, -2}});
    }

    private int[] getModifiedArray(int length, int[][] updates) {
        // Write your code here

        int[] nums = new int[length];

        for (int i = 0; i < length; i++) {
            nums[i] = 0;
        }

        int index = updates.length;

//        while (index > 0) {
//            index--;
//            int[] ss = updates[index];
//            int start = ss[0];
//            int end = ss[1];
//            int value = ss[2];
//
//            while (start <= end) {
//                nums[start] += value;
//                start++;
//            }
//        }

        while (index > 0) {

        }

        return nums;
    }
}
