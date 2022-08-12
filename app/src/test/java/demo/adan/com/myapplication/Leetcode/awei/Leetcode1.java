package demo.adan.com.myapplication.Leetcode.awei;

import org.junit.Test;

import java.util.ArrayList;

public class Leetcode1 {

    @Test
    public void main(){
        int[] nums =new int[]{0,1,3,-3,4,-2};
        int target = -5;
        int[] ints = twoSum2(nums, target);
        System.out.println(ints[0]+" "+ints[1]);
    }

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            int n1 = nums[i];
            for (int j = i+1; j < nums.length; j++) {
                int n2 = nums[j];
                if (n1 + n2 == target) {
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{-1,-1};
    }

    public int[] twoSum2(int[] nums, int target) {
        int[] tempNums = new int[nums.length];
        int k = 0, l = nums.length - 1, t = -1;
        int tar = target / 2;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == tar && target%2 == 0) {
                if (t != -1) {
                    return new int[]{t,i};
                }
                t = i;
            }
            if (tar > 0) {
                if (nums[i] <= tar) {
                    tempNums[k] = nums[i];
                    k ++;
                }else  {
                    tempNums[l] = nums[i];
                    l --;
                }
            }else  {
                if (nums[i] < tar) {
                    tempNums[k] = nums[i];
                    k ++;
                }else  {
                    tempNums[l] = nums[i];
                    l --;
                }
            }

        }
        int m = -1,n = -1;
        for (int j = 0; j < k; j++) {
            for (int i = l+1; i < tempNums.length; i++) {
                if (tempNums[j] + tempNums[i] == target) {
                    m = tempNums[j];
                    n = tempNums[i];
                    break;
                }
            }
        }
        int[] result = new int[]{-1,-1};
        for (int i = 0; i < nums.length; i++) {
            if (m == nums[i]) {
                result[0] = i;
            }else if (n == nums[i]) {
                result[1] = i;
            }
        }
        return result;
    }
}
