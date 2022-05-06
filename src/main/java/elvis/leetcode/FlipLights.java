package elvis.leetcode;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class FlipLights {
    int n;
    int origin;
    int presses;
    Set<Integer> s = new HashSet<>();

    public int flipLights(int n, int presses) {
        if (presses == 0)
            return 1;
        this.n = n;
        this.presses = presses;
        this.origin = (int) Math.pow(2.0, n) - 1;
        flip(origin, 0);
        return s.size();
    }

    public void flip(int num, int press) {
        System.out.println("num:" + num + ",press:" + press);
        if (press == this.presses) {
            s.add(num);
            return;
        }
        //将所有灯泡的状态反转（即开变为关，关变为开）
        int num1 = num ^ origin;
        flip(num1, press + 1);

        //将编号为偶数的灯泡的状态反转
        int num2 = num;
        for (int i = 2; i < origin; i <<= 2) {
            num2 ^= i;
        }
        flip(num2, press + 1);

        //将编号为奇数的灯泡的状态反转
        int num3 = num;
        for (int i = 1; i < origin; i <<= 2) {
            num3 ^= i;
        }
        flip(num3, press + 1);

        //将编号为 3k+1 的灯泡的状态反转（k = 0, 1, 2, ...)
        int num4 = num;
        for (int i = 1; i < origin; i <<= 3) {
            num4 ^= i;
        }
        flip(num4, press + 1);
    }


    public static void main(String[] args) {
        FlipLights s = new FlipLights();
//        System.out.println(s.flipLights(1, 1));
//        System.out.println(s.flipLights(2, 1));
//        System.out.println(s.flipLights(3, 1));
        System.out.println(s.flipLights(4, 100));
    }
}
