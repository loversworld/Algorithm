package demo.adan.com.myapplication.Leetcode.adan;

import org.junit.Test;

/**
 * @author adan
 * on 2022/8/12
 * ClassName：
 */
public class N20有效的括号 {

    @Test
    public void main() {
        System.out.println(isValid("()"));
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid("(]"));
        System.out.println(isValid("([)]"));
        System.out.println(isValid("{[]}"));
        System.out.println(isValid("(()("));
    }

    public boolean isValid(String s) {
        if (s.length() % 2 != 0) return false;
        char[] chars = new char[s.length() / 2];
        int currentIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '(':
                case '[':
                case '{':
                    if (currentIndex >= chars.length) return false;
                    chars[currentIndex++] = s.charAt(i);
                    break;
                case ')':
                    if (currentIndex <= 0) return false;
                    if (chars[currentIndex - 1] != '(') return false;
                    currentIndex--;
                    break;
                case ']':
                    if (currentIndex <= 0) return false;
                    if (chars[currentIndex - 1] != '[') return false;
                    currentIndex--;
                    break;
                case '}':
                    if (currentIndex <= 0) return false;
                    if (chars[currentIndex - 1] != '{') return false;
                    currentIndex--;
                    break;
                default:
                    return false;
            }
        }
        return currentIndex == 0;
    }


}
