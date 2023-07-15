package algorithm.leetcode;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author lsh
 * @date 2023/6/10 10:57
 * <p>
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * <p>
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 */
public class Three {

    @Test
    public void test() {
        System.out.println();
        System.out.println(lengthOfLongestSubstring("cdd"));//2
        System.out.println(lengthOfLongestSubstring(" a r "));//3
        System.out.println(lengthOfLongestSubstring("au"));//2
        System.out.println(lengthOfLongestSubstring("abca"));//3
        System.out.println(lengthOfLongestSubstring("au"));//2
        System.out.println(lengthOfLongestSubstring("dvdfe"));//4
        System.out.println(lengthOfLongestSubstring("dvvdf"));//3
        System.out.println(lengthOfLongestSubstring("pwwkew"));//3
        System.out.println(lengthOfLongestSubstring("abcabcbb"));//3
        System.out.println(lengthOfLongestSubstring("bbbbbb"));//1
        System.out.println(lengthOfLongestSubstring("aab"));//2
    }

    //试试暴力解法 9%
//    public int lengthOfLongestSubstring2(String s) {
//        if(s.length()==0){
//            return 0;
//        }
//        int max = 1;
//        for (int i = 0; i < s.length(); i++) {
//            HashSet<Character> set = new HashSet<>(s.length());
//            set.add(s.charAt(i));
//            for (int j = i + 1; j < s.length(); j++) {
//                if (set.contains(s.charAt(j))) {
//                    break;
//                }else {
//                    set.add(s.charAt(j));
//                    max = Math.max(max, set.size());
//                }
//            }
//        }
//        return max;
//    }


    //滑动窗口，对两个for的优化
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int max = 1;
        int start = 0;
        HashMap<Character, Integer> map = new HashMap<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i)) && start < i) {
                start = Math.max(start, map.get(s.charAt(i)));
            }
            max = Math.max(max, i - start + 1);
            map.put(s.charAt(i), i + 1);
        }
        return map.size() == s.length() ? map.size() : max;
    }

}
