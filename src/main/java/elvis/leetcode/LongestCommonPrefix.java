package elvis.leetcode;

public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        int len = Integer.MAX_VALUE;
        for (String str : strs) len = Math.min(str.length(), len);
        for (int i = 0; i < len; i++) {
            boolean flag = true;
            for (int j = 0; j < strs.length - 1; j++) {
                if (strs[j].charAt(i) != strs[j + 1].charAt(i))
                    flag = false;
                if (!flag)
                    return strs[0].substring(0, i);
            }
        }
        return strs[0].substring(0, len);
    }

    public static void main(String[] args) {
        LongestCommonPrefix l = new LongestCommonPrefix();
        System.out.println(l.longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        System.out.println(l.longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
        System.out.println(l.longestCommonPrefix(new String[]{""}));
    }
}
