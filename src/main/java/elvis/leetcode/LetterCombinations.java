package elvis.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinations {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.length() == 0)
            return res;
        res.add("");
        for (char c : digits.toCharArray()) {
            List<String> tmp = new ArrayList<>();
            for (String last : res) {
                if (c < '8')
                    for (int i = 0; i < 3; i++) {
                        tmp.add(last + (char) ((c - '2') * 3 + i + 'a'));
                    }
                if (c == '7') {
                    tmp.add(last + 's');
                }
                if (c == '8') {
                    tmp.add(last + 't');
                    tmp.add(last + 'u');
                    tmp.add(last + 'v');
                }
                if (c == '9') {
                    tmp.add(last + 'w');
                    tmp.add(last + 'x');
                    tmp.add(last + 'y');
                    tmp.add(last + 'z');
                }
            }

            res = tmp;
        }
        return res;
    }

    public static void main(String[] args) {
        LetterCombinations l = new LetterCombinations();
        System.out.println(l.letterCombinations("23"));
    }
}
