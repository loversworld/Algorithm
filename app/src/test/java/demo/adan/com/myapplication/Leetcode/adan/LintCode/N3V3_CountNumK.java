package demo.adan.com.myapplication.Leetcode.adan.LintCode;

/**
 * created by adan on 2018/8/2.
 */
public class N3V3_CountNumK {

    /**
     * 描述
     * 计算数字k在0到n中的出现的次数，k可能是0~9的一个值
     * <p>
     * 样例
     * 例如n=12，k=1，在 [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]，我们发现1出现了5次 (1, 10, 11, 12)
     */
    public void test() {
        int result = countNum(1, 12);
        System.out.println("result = " + result);
    }

    private int countNum(int k, int n) {
        int count = 1;
        int result = 0;

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
            if (n < 10) {
                result++;
            }

        }

        return result;
    }
}
