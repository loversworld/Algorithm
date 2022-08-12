package demo.adan.com.myapplication.Leetcode.adan;

import org.junit.Test;

/**
 * @author adan
 * on 2022/8/12
 * ClassName：
 */
public class N14最长公共前缀 {

    @Test
    public void main() {
        System.out.println(longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        System.out.println(longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
        System.out.println(longestCommonPrefix(new String[]{"ab", "a"}));
    }

    public String longestCommonPrefix(String[] strs) {
        int length = strs[0].length();
        for (int i = 1; i < strs.length; i++) {
            length = Math.min(strs[i].length(), length);
        }
        for (int i = 1; i < strs.length; i++) {
            length = getLength(strs[i], strs[0], length);
        }
        return strs[0].substring(0, length);
    }

    private int getLength(String str1, String str2, int length) {
        for (int j = 0; j < length; j++) {
            if (str1.charAt(j) != str2.charAt(j)) return j;
        }
        return length;
    }


}
