package elvis.leetcode;

import java.util.Stack;

public class AddBinary {
    public String addBinary(String a, String b) {
        int lenA = a.length(), lenB = b.length();
        int len = Math.max(lenA, lenB) + 1;
        int carry = 0;
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < len - 1; i++) {
            if (lenA - 1 - i >= 0)
                carry += a.charAt(lenA - 1 - i) - '0';
            if (lenB - 1 - i >= 0)
                carry += b.charAt(lenB - 1 - i) - '0';
            s.push(carry & 1);
            carry >>= 1;
        }
        if (carry == 1)
            s.push(1);
        StringBuilder sb = new StringBuilder();
        while (!s.empty()) {
            sb.append(s.pop());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        AddBinary ab = new AddBinary();
//        System.out.println(ab.addBinary("11", "1"));
//        System.out.println(ab.addBinary("1010", "1011"));
        System.out.println(ab.addBinary("1010", "0001"));
    }
}
