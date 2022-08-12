package demo.adan.com.myapplication.Leetcode.adan;

import org.junit.Test;

/**
 * @author adan
 * on 2022/8/12
 * ClassName：
 */
public class N13罗马数字转整数 {

    @Test
    public void main() {
        System.out.println(romanToInt("I"));
        System.out.println(romanToInt("II"));
        System.out.println(romanToInt("III"));
        System.out.println(romanToInt("IV"));
        System.out.println(romanToInt("V"));
        System.out.println(romanToInt("VI"));
        System.out.println(romanToInt("VII"));
        System.out.println(romanToInt("VIII"));
        System.out.println(romanToInt("IX"));
        System.out.println(romanToInt("X"));
        System.out.println(romanToInt("LVIII"));
        System.out.println(romanToInt("MCMXCIV"));
    }

    /**
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * <p>
     * <p>
     * I可以放在V(5) 和X(10) 的左边，来表示 4 和 9。
     * X可以放在L(50) 和C(100) 的左边，来表示 40 和90。
     * C可以放在D(500) 和M(1000) 的左边，来表示400 和900。
     * <p>
     * <p>
     * I、II、III、IV、V、VI、VII、VIII、IX、X
     */
    public int romanToInt(String s) {
        int result = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]) {
                case 'I':
                    // I可以放在V(5) 和X(10) 的左边，来表示 4 和 9。
                    if (i < chars.length - 1 && (chars[i + 1] == 'V' || chars[i + 1] == 'X')) {
                        result -= 1;
                    } else {
                        result += 1;
                    }
                    break;
                case 'V':
                    result += 5;
                    break;
                case 'X':
                    // X可以放在L(50) 和C(100) 的左边，来表示 40 和90。
                    if (i < chars.length - 1 && (chars[i + 1] == 'L' || chars[i + 1] == 'C')) {
                        result -= 10;
                    } else {
                        result += 10;
                    }
                    break;
                case 'L':
                    result += 50;
                    break;
                case 'C':
                    // C可以放在D(500) 和M(1000) 的左边，来表示400 和900。
                    if (i < chars.length - 1 && (chars[i + 1] == 'D' || chars[i + 1] == 'M')) {
                        result -= 100;
                    } else {
                        result += 100;
                    }
                    break;
                case 'D':
                    result += 500;
                    break;
                case 'M':
                    result += 1000;
                    break;
                default:
                    break;
            }
        }
        return result;
    }
}
