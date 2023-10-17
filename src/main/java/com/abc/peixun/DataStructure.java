package com.abc.peixun;

import java.util.Stack;

public class DataStructure {

    //天气温度 单调栈 [71, 72, 69, 76, 73] -> [1, 2, 1, 0, 0]
    public int[] temperatures(int[] temp) {
        Stack<Integer> s = new Stack<>();
        int[] ans = new int[temp.length];
        s.push(0);
        for (int i = 1; i < temp.length; i++) {
            int t = temp[i];
            while (!s.isEmpty() && temp[s.peek()] < t) {
                int idx = s.pop();
                ans[idx] = i - idx;
            }
            s.push(i);
        }
        return ans;
    }

    //树遍历 深度优先 递归

    public static void main(String[] args) {
        DataStructure ds = new DataStructure();
        int[] ans = ds.temperatures(new int[]{71, 72, 69, 76, 73});
        System.out.println(ans);
    }
}
