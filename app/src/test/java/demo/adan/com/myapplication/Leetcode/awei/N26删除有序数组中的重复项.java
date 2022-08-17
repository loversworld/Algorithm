package demo.adan.com.myapplication.Leetcode.awei;

import org.junit.Test;

import java.util.Arrays;

public class N26删除有序数组中的重复项 {

    @Test
    public void main(){
        int r = removeDuplicates(new int[]{1,1,2});
        System.out.println(r);
    }

    public int removeDuplicates(int[] nums) {
        int r = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 < nums.length && nums[i] == nums[i + 1]) {
                if (nums[nums.length-1] != nums[i]) {
                    r ++;
                }
                for (int j = i + 1; j < nums.length; j++) {
                    if (j + 1 < nums.length) {
                        nums[j] = nums[j + 1];
                    }
                }
            }
        }
        return r + 1;
    }
}
