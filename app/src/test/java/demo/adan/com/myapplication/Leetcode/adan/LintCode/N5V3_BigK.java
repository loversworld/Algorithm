package demo.adan.com.myapplication.Leetcode.adan.LintCode;

/**
 * created by adan on 2018/8/2.
 */
public class N5V3_BigK {

    /**
     * 描述
     * 在数组中找到第k大的元素
     * <p>
     * 你可以交换数组中的元素的位置
     * <p>
     * 样例
     * 给出数组 [9,3,2,4,8]，第三大的元素是 4
     * 给出数组 [1,2,3,4,5]，第一大的元素是 5，第二大的元素是 4，第三大的元素是 3，以此类推
     * <p>
     * 挑战
     * 要求时间复杂度为O(n)，空间复杂度为O(1)
     */
    public void test() {
//        int result = maopao(5, new int[]{11, 5, 9, 4, 8});
//        int result = charu(5, new int[]{11, 5, 9, 4, 8});
//        int result = kuaisu(5, new int[]{11, 5, 9, 4, 8});
        int result = newKuaiSu(17, new int[]{11, 5, 23, 103, 44, 59, 95, 9, 3, 2, 99, 53, 80, 85, 81, 4, 8});
        System.out.println("result = " + result);
    }

    private int newKuaiSu(int k, int nums[]) {
        return newKuaiSu(nums, 0, nums.length - 1, nums.length - k);
    }

    private int newKuaiSu(int nums[], int l, int h, int k) {
        int low = l;
        int high = h;
        while (low < high) {
            log(nums);
            // Step 1: nums[k]右边比k小的值和num[k]左边比k大的值交换
            // 左边第一个比k大的
            while (low < k && nums[low] <= nums[k]) {
                low++;
            }
            while (high > k && nums[high] >= nums[k]) {
                high--;
            }
            if (low == high) {
                return nums[k];
            } else if (low == k) {
                // Step 2: 排列[k,high]的值，查找nums[k]应在的位置,赋值给high
                high = newSort(nums, k, high);
                // Step 3: 在[l,high]之间，递归newKuaiSu
                return newKuaiSu(nums, l, high, k);
            } else if (high == k) {
                // Step 2: 排列[low,k]的值，查找nums[k]应在的位置，赋值给low
                low = newSort(nums, k, low);
                // Step 3: 在[low,h]之间，递归newKuaiSu
                return newKuaiSu(nums, low, h, k);
            } else {
                // 交换nums[low]和nums[high]
                int t = nums[low];
                nums[low] = nums[high];
                nums[high] = t;
                low++;
                high--;
            }
        }
        return nums[k];
    }

    private int newSort(int[] nums, int k, int n) {
        if (n == k) {
            return k;
        } else if (n > k) {
            while (n > k) {
                // 记录位置k的值kv
                int kvalue = nums[k];
                // 从n往前找第一个比kv小的数
                while (n > k && nums[n] >= kvalue) {
                    n--;
                }
                if (n <= k) {
                    // 没有比kv小的数
                    return k;
                } else {
                    // 把比kv小的数，在位置n，放在k的位置
                    nums[k] = nums[n];
                    // 从k+1往后，找第一个比kv大的数
                    k++;
                    while (n > k && nums[k] <= kvalue) {
                        k++;
                    }
                    // 没有比kv大的数，把kv放在n的位置
                    if (n <= k) {
                        nums[n] = kvalue;
                        return k;
                    } else {
                        // 位置k的数比kv大，放在位置n，把kv放在位置k
                        nums[n] = nums[k];
                        nums[k] = kvalue;
                        n--;
                    }
                }
            }
        } else {
            while (n < k) {
                // 记录位置k的值kv
                int kvalue = nums[k];
                // 从n往后找第一个比kv大的数
                while (n < k && nums[n] <= kvalue) {
                    n++;
                }
                if (n >= k) {
                    // 没有比kv大的数
                    return k;
                } else {
                    // 把比kv小的数，位置n，放在位置k
                    nums[k] = nums[n];
                    // 从k-1往前，找第一个比kv小的数
                    k--;
                    while (n < k && nums[k] >= kvalue) {
                        k--;
                    }
                    // 没有比kv小的数
                    if (n >= k) {
                        nums[n] = kvalue;
                        return k;
                    } else {
                        // 位置k的数比kv小，放在位置n，把kv放在位置k
                        nums[n] = nums[k];
                        nums[k] = kvalue;
                        n++;
                    }
                }
            }

        }
        return k;
    }

    private int kuaisu(int k, int[] nums) {
        kuaisu(nums, 0, nums.length - 1, k - 1);
        return nums[k - 1];
    }

    private int kuaisu(int nums[], int l, int h, int k) {

        int low = l;
        int high = h;

        int p = nums[low];
        while (high > low) {
            while (high > low && p > nums[high]) {
                high--;
            }
            if (high > low) {
                nums[low] = nums[high];
                nums[high] = p;
            }
            while (high > low && p < nums[low]) {
                low++;
            }
            if (high > low) {
                nums[high] = nums[low];
                nums[low] = p;
            }
        }
        if (k == low) {
            return nums[k];
        } else if (k == high) {
            return nums[k];
        } else {
            if (k < low) {
                return kuaisu(nums, l, high - 1, k);
            } else {
                // k > high
                return kuaisu(nums, low + 1, h, k);
            }
        }
    }

    private int charu(int k, int[] nums) {
        // 最大值 由大到小排列
        if (k > nums.length / 2) {
            // 由右到左(从小到大)
            for (int i = 0; i < nums.length - k + 1; i++) {
                for (int j = 0; j < nums.length - i - 1; j++) {
                    if (nums[j] < nums[nums.length - 1 - i]) {
                        int t = nums[j];
                        nums[j] = nums[nums.length - 1 - i];
                        nums[nums.length - 1 - i] = t;
                    }
                }
            }
        } else {
            // 由左到右(从大到小)
            for (int i = 0; i < k; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[i] < nums[j]) {
                        int t = nums[j];
                        nums[j] = nums[i];
                        nums[i] = t;
                    }
                }
            }
        }
        for (int i : nums) {
            System.out.print(i + " ");
        }
        return nums[k - 1];
    }

    private int maopao(int k, int[] nums) {
        // 最大值 由大到小排列
        if (k > nums.length / 2) {
            // 由右到左(从小到大)
            for (int i = 0; i < nums.length - k + 1; i++) {
                for (int j = 0; j < nums.length - i - 1; j++) {
                    if (nums[j] < nums[j + 1]) {
                        int t = nums[j];
                        nums[j] = nums[j + 1];
                        nums[j + 1] = t;
                    }
                }
            }
        } else {
            // 由左到右
            for (int i = 0; i < k; i++) {
                for (int j = nums.length - 1 - i; j > 0; j--) {
                    if (nums[j] > nums[j - 1]) {
                        int t = nums[j];
                        nums[j] = nums[j - 1];
                        nums[j - 1] = t;
                    }
                }
            }
        }
        return nums[k - 1];
    }

    private void log(int[] nums) {
        for (int i : nums) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
