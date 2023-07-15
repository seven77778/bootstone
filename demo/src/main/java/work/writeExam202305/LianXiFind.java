package work.writeExam202305;

import org.junit.Test;

/**
 * @author lsh
 * @date 2023/5/16 17:52
 * <p>
 * 比较一个源字符串和一个目标字符串，
 * 如果在源字符串中包含目标字符串全部字符，输出所包含的第一个最小子串；
 * 如果不存在，输出空。
 * 样例:输入 "BPDAUNZHGAHSIWBADNC"，输出 "BDN" 满足要求的解  "BADN"
 * 要求：时间复杂度为O(n^2)
 */
public class LianXiFind {
    public static String getLeafSequence(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        int[] left = new int[256];
        int[] right = new int[256];
        for (int i = 0; i < s.length(); i++) {
            left[s.charAt(i) - 'a']++;
            right[t.charAt(i) - 'a']++;
        }
        int i = 0, j = 0, sum = 0;
        while (i < s.length() && j < t.length()) {
            if (left[s.charAt(i) - 'a'] == right[t.charAt(j) - 'a']) {
                i++;
                j++;
            } else {
                sum += right[t.charAt(j) - 'a'] - left[s.charAt(i) - 'a'];
                i++;
                j++;
            }
        }
        return sum == 0 ? "" : s.substring(i - sum);
    }

    public static void main(String[] args) {
        String s = "BPDAUNZHGAHSIWBADNC";
        String t = "BDN";
        String result = getLeafSequence(s, t);
        System.out.println(result); // 输出 "BADN"
    }

    @Test
    public void test() {
        System.out.println(getLeafSequence("BPDAUNZHGAHSIWBADNC", "BDN"));
    }
}
