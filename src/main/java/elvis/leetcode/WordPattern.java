package elvis.leetcode;

import java.util.Arrays;
import java.util.HashMap;

public class WordPattern {
    public boolean wordPattern(String pattern, String s) {
        String[] s_segs = s.split(" ");
        if (s_segs.length != pattern.length())
            return false;
        HashMap<String, Character> map = new HashMap<String, Character>();
        for (int i = 0; i < s_segs.length; i++) {
            char ch = pattern.charAt(i);
            String s_seg = s_segs[i];
            if (map.containsKey(s_seg)) {
                if (pattern.charAt(i) != map.get(s_seg))
                    return false;
            } else if(!map.containsValue(ch)){
                map.put(s_seg, ch);
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean wordPattern_stream(String pattern, String s) {
        String[] s_segs = s.split(" ");
        if (s_segs.length != pattern.length())
            return false;
        HashMap<String, Character> map = new HashMap<String, Character>();
//        Arrays.stream(s_segs)
        for (int i = 0; i < s_segs.length; i++) {
            char ch = pattern.charAt(i);
            String s_seg = s_segs[i];
            if (map.containsKey(s_seg)) {
                if (pattern.charAt(i) != map.get(s_seg))
                    return false;
            } else if(!map.containsValue(ch)){
                map.put(s_seg, ch);
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        WordPattern wp = new WordPattern();
        System.out.println(wp.wordPattern("abba", "dog dog dog dog"));
    }
}
