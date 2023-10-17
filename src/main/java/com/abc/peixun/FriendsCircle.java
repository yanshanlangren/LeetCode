package com.abc.peixun;

public class FriendsCircle {

    int p[];

    int countCircles(int[][] arr) {
        p = new int[arr.length];
        for (int i = 0; i < p.length; i++) {
            p[i] = i;
        }
        for (int i = 0; i < p.length; i++) {
            for (int j = i + 1; j < p.length; j++) {
                if (arr[i][j] == 1) {
                    union(i, j);
                }
            }
        }

        int count = 0;
        for (int i = 0; i < p.length; i++) {
            if (find(i) == i) {
                count++;
            }
        }
        return count;
    }

    int find(int a) {
        return a == p[a] ? a : (p[a] = find(p[a]));
    }

    void union(int a, int b) {
        p[find(b)] = p[find(a)];
    }


    public static void main(String[] args) {
        FriendsCircle f = new FriendsCircle();
        System.out.println(f.countCircles(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
    }
}
