package demo.adan.com.myapplication.Leetcode.awei;

import org.junit.Test;

public class N20有效的括号 {
    @Test
    public void main() {
        boolean r = isValid("()");
        System.out.println(r);
    }

    public boolean isValid(String s) {
        String t = s;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i+1; j < s.length(); j++) {
                if (s.charAt(i) == '(') {
                    if (s.charAt(j) == ')') {
//                        t.
                    }
                }
            }
        }
        return true;
    }
}
