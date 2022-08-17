package demo.adan.com.myapplication.Leetcode.adan.LintCode;

import java.util.ArrayList;

/**
 * created by adan on 2018/7/26.
 */
public class N4V3_ChouShu {

    /**
     * 描述
     * 设计一个算法，找出只含素因子2，3，5 的第 n 小的数。
     * <p>
     * 符合条件的数如：1, 2, 3, 4, 5, 6, 8, 9, 10, 12...
     * <p>
     * 我们可以认为1也是一个丑数
     * <p>
     * 样例
     * 如果n = 9， 返回 10
     * <p>
     * 挑战
     * 要求时间复杂度为O(nlogn)或者O(n)
     */
    public void test() {
        int result = suzhizi2(1665);
        System.out.println("result = " + result);
    }

    private int suzhizi2(int n) {
        if (n < 7) {
            return n;
        } else {
            int min = 0;
            ArrayList<Long> nums = new ArrayList<>();
            // 0
            nums.add((long) 1);
            ArrayList<Long> ns = new ArrayList<>(nums);
            while (true) {
                ArrayList<Long> ns2 = new ArrayList<>();
                for (int i = 0; i < ns.size(); i++) {
                    long node = ns.get(i);
                    if (node == 1) {
                        ns2.add((long) 2);
                        ns2.add((long) 3);
                        ns2.add((long) 5);
                    } else if (node % 2 == 0) {
                        ns2.add(node * 2);
                    } else if (node % 3 == 0) {
                        ns2.add(node * 2);
                        ns2.add(node * 3);
                    } else if (node % 5 == 0) {
                        ns2.add(node * 2);
                        ns2.add(node * 3);
                        ns2.add(node * 5);
                    }
                }
                min = add(min, nums, ns2);
                ns = ns2;
                if (min >= n) {
                    break;
                }
            }
            return nums.get(n - 1).intValue();
        }
    }

    private int add(int min, ArrayList<Long> nums, ArrayList<Long> ns) {

        int start = min;
        for (int i = 0; i < ns.size(); i++) {
            for (int j = start; j <= nums.size(); j++) {
                long node = ns.get(i);

                if (node < 0) {
                    node = Integer.MAX_VALUE;
                    nums.add(node);
                }
                if (j == nums.size()) {
                    nums.add(j, node);
                    if (i == 0) {
                        min = j;
                    }
                    break;
                } else if (nums.get(j) > node) {
                    nums.add(j, node);
                    if (i == 0) {
                        min = j;
                    }
                    break;
                }
            }
        }
        return min;
    }

    private long suzhizi(int n) {
        long num = 0;
        while (n > 0) {
            num++;
            if (num < 7) {
                System.out.print(" " + num);
                n--;
            } else {
                long t = num;
                while (t > 6) {
                    if (t % 7 == 0 || t % 11 == 0 || t % 13 == 0 || t % 17 == 0 || t % 19 == 0 || t % 29 == 0) {
                        break;
                    }
                    if (t % 2 == 0) {
                        t /= 2;
                    } else if (t % 3 == 0) {
                        t /= 3;
                    } else if (t % 5 == 0) {
                        t /= 5;
                    } else {
                        break;
                    }
                }
                if (t < 7) {
                    // 是
                    System.out.print(" " + num);
                    n--;
                }
            }
        }
        System.out.println();
        return num;
    }


    // 个位 1 高位（2，n）+ 个位数>k?1:0
    // 十位 2 高位（3，n）+ 十位数>k?10:十位数==k?低位+1:0
    // 百位 3 高位（4，n）+ 百位数>k?100:百位数==k?低位+1:0
    // ...
    // 高位 n-1 高位（n）+n-1位>k?10的n-2次方:n-1==k?低位+1:0
    // 高位 n 0 + n位>=k ? +1 : 0
    private int getCount(int n, int k) {
        int count = 1;
        int result = 0;

        int x = n;
        while (n / count > 9) {
            // 高位
            result += n / (count * 10) * count;

            // 当前位
            int t = n / count % 10;
            if (t > k) {
                result += count;
            } else if (t == k) {
                if (count == 1) {
                    result++;
                } else {
                    result += n % count + 1;
                }
            }
            count *= 10;
        }

        // 最高位
        if (k != 0) {
            int t = n / count;
            if (t > k) {
                result += count;
            } else if (t == k) {
                result += n % count + 1;
            }
        } else {
            // 如果n<10
            result++;
        }


        return result;
    }

}
