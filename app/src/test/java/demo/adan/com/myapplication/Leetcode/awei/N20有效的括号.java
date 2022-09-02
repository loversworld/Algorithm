package demo.adan.com.myapplication.Leetcode.awei;

import org.junit.Test;

public class N20有效的括号 {
    @Test
    public void main() {
        boolean r = isValid("()[]{}");
        System.out.println(r);
    }

    public boolean isValid(String s) {
        String t = "";
        for (int i = 0; i < s.length(); i++) {
            if (t.length() > 0) {
                int l = s.indexOf(t) - 1;
                int r = l + t.length() + 1;
                if ((s.charAt(r) == ')' && s.charAt(l) == '(') ||
                        s.charAt(r) == ']' && s.charAt(l) == '[' ||
                        s.charAt(r) == '}' && s.charAt(l) == '{') {
                    t = s.substring(l,r+1);
                }
            }else  {
                if ((s.charAt(i) == ')' && s.charAt(i-1) == '(') ||
                        s.charAt(i) == ']' && s.charAt(i-1) == '[' ||
                        s.charAt(i) == '}' && s.charAt(i-1) == '{') {
                    t = s.substring(i-1,i+1);
                }
            }
        }
        if (t.equals(s)) {
            return true;
        }
        return false;
    }
}
