package demo.adan.com.myapplication.Leetcode.adan;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author adan
 * on 2022/8/15
 * ClassName：
 */
public class N26删除有序数组中的重复项 {

    @Test
    public void main() {
        int[] nums = new int[]{1, 1, 2};
        int result = removeDuplicates(nums);
        System.out.println(result + "," + Arrays.toString(nums));

        nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        result = removeDuplicates(nums);
        System.out.println(result + "," + Arrays.toString(nums));
    }

    public int removeDuplicates(int[] nums) {
        int c = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[c] == nums[i]) continue;
            c++;
            if (c == i) continue;
            nums[c] = nums[i];
        }
        return c + 1;
    }

}
