package elvis.leetcode;

import java.util.HashMap;
import java.util.Map;

public class RomanToInt {
    public int romanToInt(String s) {
        Map<Character, Integer> m = new HashMap<>();
        m.put('I', 1);
        m.put('V', 5);
        m.put('X', 10);
        m.put('L', 50);
        m.put('C', 100);
        m.put('D', 500);
        m.put('M', 1000);
        char[] ch = s.toCharArray();
        int res = 0;
        for (int i = 0; i < ch.length; i++) {
            if (i < ch.length - 1 && m.get(ch[i]) < m.get(ch[i + 1]))
                res -= m.get(ch[i]);
            else
                res += m.get(ch[i]);
        }
        return res;
    }
    public static void main(String[] args){
        RomanToInt r = new RomanToInt();
        System.out.println(r.romanToInt("III"));
        System.out.println(r.romanToInt("IV"));
        System.out.println(r.romanToInt("IX"));
        System.out.println(r.romanToInt("LVIII"));
        System.out.println(r.romanToInt("MCMXCIV"));
    }
}
