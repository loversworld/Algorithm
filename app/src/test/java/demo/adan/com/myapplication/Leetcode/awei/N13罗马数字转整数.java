package demo.adan.com.myapplication.Leetcode.awei;

import org.junit.Test;

public class N13罗马数字转整数 {

    @Test
    public void main(){
        int r = romanToInt("MCMXCIV");
        System.out.println(r);
    }

    public int romanToInt(String s) {
        int r = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c0 = chars[i];
            char c1 = '0';
            if (i + 1 < chars.length) {
                c1 = chars[i + 1];
            }
            if (c0 == 'I') {
                if (c1 == 'V') {
                   r += 5 - 1;
                   return r;
                }else if (c1 == 'X') {
                    r += 10 - 1;
                    return r;
                }else  {
                    r += 1;
                }
            }else if (c0 == 'V'){
                r += 5;
            }else if (c0 == 'X') {
                if (c1 == 'L') {
                    r += 50 - 10;
                    i ++;
                }else if (c1 == 'C') {
                    r += 100 - 10;
                    i ++;
                }else  {
                    r += 10;
                }
            }else if (c0 == 'L'){
                r += 50;
            }else if (c0 == 'C') {
                if (c1 == 'D') {
                    r += 500 - 100;
                    i ++;
                }else if (c1 == 'M') {
                    r += 1000 - 100;
                    i ++;
                }else  {
                    r += 100;
                }
            }else if (c0 == 'D'){
                r += 500;
            }else if (c0 == 'M'){
                r += 1000;
            }
        }
        return r;
    }
}
