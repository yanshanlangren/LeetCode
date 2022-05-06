package elvis.test;

import java.util.Scanner;

public class ValidParentheses {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        int big = 0, mid = 0, small = 0;
        char[] arr = input.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            switch (arr[i]) {
                case '{':
                    big++;
                    break;
                case '}':
                    if (--big < 0) {
                        System.out.println("False");
                        return;
                    }
                    break;
                case '[':
                    mid++;
                    break;
                case ']':
                    if (--mid < 0) {
                        System.out.println("False");
                        return;
                    }
                    break;
                case '(':
                    small++;
                    break;
                case ')':
                    if (--small < 0) {
                        System.out.println("False");
                        return;
                    }
                    break;
            }
        }
        if (big == 0 && mid == 0 && small == 0)
            System.out.println("True");
        else
            System.out.println("False");
    }
}
