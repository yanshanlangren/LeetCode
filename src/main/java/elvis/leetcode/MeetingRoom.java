package elvis.leetcode;

import java.util.Arrays;

public class MeetingRoom {
    public int meetingRoom(int[][] arr) {
        Integer[] t = new Integer[arr.length * 2];
        for (int i = 0; i < arr.length; i++) {
            t[2 * i] = arr[i][0];
            t[2 * i + 1] = -arr[i][1];
        }
        Arrays.sort(t, (o1, o2) -> Math.abs(o1) - Math.abs(o2));
        int max = 0, count = 0;
        for (int i = 0; i < t.length; i++) {
            if (t[i] >= 0)
                count++;
            else
                count--;
            if (count > max) {
                max = count;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        MeetingRoom mr = new MeetingRoom();
//        System.out.println(mr.meetingRoom(new int[][]{{0, 30}, {5, 10}, {15, 20}}));
//        System.out.println(mr.meetingRoom(new int[][]{{7, 10}, {2, 4}}));
        System.out.println(mr.meetingRoom(new int[][]{{7, 10}, {2, 4}, {2, 8}, {3, 6}}));
    }
}
