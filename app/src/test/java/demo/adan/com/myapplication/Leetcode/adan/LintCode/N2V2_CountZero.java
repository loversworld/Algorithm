package demo.adan.com.myapplication.Leetcode.adan.LintCode;

/**
 * created by adan on 2018/8/2.
 */
public class N2V2_CountZero {

    /**
     * 描述
     * 设计一个算法，计算出n阶乘中尾部零的个数
     * <p>
     * 样例
     * 11! = 39916800，因此应该返回 2
     * <p>
     * 挑战
     * O(logN)的时间复杂度
     */
    public void test() {
        long result = countZero(11);
        System.out.println("result = " + result);
    }

    private long countZero(int n) {
        long result = 0;
        while (n >= 5) {
            n = n / 5;
            result += n;
        }
        return result;
    }
}
