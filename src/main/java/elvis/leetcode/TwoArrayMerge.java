package elvis.leetcode;

public class TwoArrayMerge {
    public int[] twoArrayMerge(int[] l1, int[] l2) {
        int len1 = l1.length;
        int len2 = l2.length;
        int[] res = new int[len1 + len2];
        int c1 = 0, c2 = 0, cr = 0;
        while (c1 < len1 && c2 < len2) {
            if (l1[c1] < l2[c2])
                res[cr++] = l1[c1++];
            else
                res[cr++] = l2[c2++];
        }
        if (c1 < len1)
            System.arraycopy(l1, c1, res, cr, len1 - c1);
        if (c2 < len2)
            System.arraycopy(l2, c2, res, cr, len1 - c2);
        return res;
    }

    public static void main(String[] args) {
        TwoArrayMerge t = new TwoArrayMerge();
//        System.out.println(t.twoArrayMerge(new int[]{1, 2, 3}, new int[]{0, 2, 4}));
//        System.out.println(t.twoArrayMerge(new int[]{1, 3,  5, 6, 10}, new int[]{0, 2, 4}));
    }
}
