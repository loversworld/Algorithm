package demo.adan.com.myapplication.Leetcode.adan;

import org.junit.Test;

/**
 * @author adan
 * on 2022/8/12
 * ClassName：
 */
public class N9回文数 {

    @Test
    public void main() {
        System.out.println("123 : " + isPalindrome(123));
        System.out.println("121 : " + isPalindrome(121));
        System.out.println("-121 : " + isPalindrome(-121));
        System.out.println("0 : " + isPalindrome(0));
        System.out.println("8 : " + isPalindrome(8));
        System.out.println("10 : " + isPalindrome(10));
        System.out.println("123454321 : " + isPalindrome(123454321));
    }

    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        if (x < 10) return true;
        int n = 0;
        for (int c = x; c > 0; c /= 10) {
            n = n * 10 + c % 10;
        }
        return n == x;
    }

}
