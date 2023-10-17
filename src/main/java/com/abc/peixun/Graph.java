package com.abc.peixun;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Graph {

    //深度优先 栈

    //广度优先 队列

    //了解什么是最小生成树
    //prim
    //kruskal

    //优先队列实现dijstra
    //https://leetcode.cn/problems/0i0mDW/
    public int minPathSumOld(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        PriorityQueue<Integer[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        q.offer(new Integer[]{0, 0, grid[0][0]});
        while (!q.isEmpty()) {
            Integer[] n = q.poll();
            int r = n[0];
            int c = n[1];
            if (visited[r][c]) {
                continue;
            }
            if (r == grid.length - 1 && c == grid[0].length - 1) {
                return n[2];
            }
            visited[r][c] = true;
            if (r + 1 < grid.length && !visited[r + 1][c]) {
                q.offer(new Integer[]{r + 1, c, n[2] + grid[r + 1][c]});
            }
            if (c + 1 < grid[0].length && !visited[r][c + 1]) {
                q.offer(new Integer[]{r, c + 1, n[2] + grid[r][c + 1]});
            }
        }
        return 0;
    }

    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[0][0] = grid[0][0];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i + 1 < grid.length) {
                    dp[i + 1][j] = Math.min(grid[i + 1][j] + dp[i][j], dp[i + 1][j]);
                }
                if (j + 1 < grid[0].length) {
                    dp[i][j + 1] = Math.min(grid[i][j + 1] + dp[i][j], dp[i][j + 1]);
                }
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }


    int[] p;

    //复习并查集
    public int find(int a) {
        return p[a] == a ? a : (p[a] = find(p[a]));
    }

    public void union(int a, int b) {
        p[find(a)] = p[find(b)];
    }

    //解题信物传送, 动态规划 + 最短路径


    public static void main(String[] args) {
        Graph g = new Graph();
//        System.out.println(g.minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
        System.out.println(g.minPathSum(new int[][]{{1, 2, 3}, {4, 5, 6}}));
    }
}
