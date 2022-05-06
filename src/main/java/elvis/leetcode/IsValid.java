package elvis.leetcode;

import java.util.Stack;

public class IsValid {
    public boolean isValid(String s) {
        Stack<Integer> st = new Stack<>();
        for (char c : s.toCharArray()) {
            switch (c) {
                case '(':
                    st.push(0);
                    break;
                case ')':
                    if (st.empty() || st.pop() != 0)
                        return false;
                    break;
                case '[':
                    st.push(1);
                    break;
                case ']':
                    if (st.empty() || st.pop() != 1)
                        return false;
                    break;
                case '{':
                    st.push(2);
                    break;
                case '}':
                    if (st.empty() || st.pop() != 2)
                        return false;
                    break;
            }
        }
        if (st.empty())
            return true;
        return false;
    }
}
