package elvis.leetcode;

public class IntToRoman {
    public String intToRoman(int num) {
        int pow = 0;
        char[] one = new char[]{'I', 'X', 'C', 'M'};
        char[] five = new char[]{'V', 'L', 'D'};
        String res = "";
        StringBuilder temp;
        while (num > 0) {
            int c = num % 10;
            temp = new StringBuilder();
            if (c < 4) {
                for (int i = 0; i < c; i++)
                    temp.append(one[pow]);
            }
            if (c == 4) {
                temp.append(one[pow]).append(five[pow]);
            }
            if (c == 5) {
                temp.append(five[pow]);
            }
            if (c > 5 && c < 9) {
                temp.append(five[pow]);
                for (int i = 5; i < c; i++)
                    temp.append(one[pow]);
            }
            if (c == 9) {
                temp.append(one[pow]).append(one[pow + 1]);
            }
            res = temp.append(res).toString();
            num /= 10;
            pow++;
        }
        return res;
    }
    public static void main(String[] rags){
        IntToRoman i = new IntToRoman();
        System.out.println(i.intToRoman(3));
        System.out.println(i.intToRoman(4));
        System.out.println(i.intToRoman(9));
        System.out.println(i.intToRoman(1994));
        System.out.println(i.intToRoman(58));
    }
}
