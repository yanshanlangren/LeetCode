package elvis.leetcode;

public class FindCircleNum {
    int[] fa;

    public int findCircleNum(int[][] isConnected) {
        fa = new int[isConnected.length];
        for (int i = 0; i < isConnected.length; i++)
            fa[i] = i;
        for (int i = 0; i < isConnected.length; i++)
            for (int j = i + 1; j < isConnected.length; j++)
                if (isConnected[i][j] == 1)
                    union(i, j);
        return count();
    }

    public int find(int a) {
        return a == fa[a] ? a : (fa[a] = find(fa[a]));
    }

    public void union(int a, int b) {
        fa[find(a)] = fa[find(b)];
    }

    public int count() {
        int count = 0;
        for (int i = 0; i < fa.length; i++)
            if (fa[i] == i)
                count++;
        return count;
    }

    public static void main(String[] args) {
        FindCircleNum fcn = new FindCircleNum();
//        System.out.println(fcn.findCircleNum(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
        System.out.println(fcn.findCircleNum(new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}}));
    }
}
