package demo.adan.com.myapplication.Leetcode.awei;

import org.junit.Test;

public class N14最长公共前缀 {
    @Test
    public void main() {
//        String r = longestCommonPrefix(new String[]{"flower","flow","flight"});
//        String r = longestCommonPrefix(new String[]{"f", "flower", "flight"});
        String r = longestCommonPrefix(new String[]{"",""});
        System.out.println(r);
    }

//    public String longestCommonPrefix(String[] strs) {
//        int r = strs[0].length() - 1;
//        for (int i = 1; i < strs.length; i++) {
//            for (int j = 0; j < r; j++) {
//                if (strs[0].charAt(r) == strs[i].charAt(r)) {
//
//                }
//            }
//        }
//        return strs[0].substring(0,r+1);
//    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        int result = strs[0].length();
        for (int i = 0; i < strs[0].length(); i++) {
            result = i+1;
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length() || strs[0].charAt(i) != strs[j].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0].substring(0, result);
    }
}
