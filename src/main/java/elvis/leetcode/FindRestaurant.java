package elvis.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FindRestaurant {
    public String[] findRestaurant(String[] list1, String[] list2) {
        HashMap<String, Integer> m1 = new HashMap<>();
        int min = Integer.MAX_VALUE;
        List<String> r = new ArrayList<>();
        for (int i = 0; i < list1.length; i++)
            m1.put(list1[i], i);
        for (int i = 0; i < list2.length; i++) {
            if (m1.containsKey(list2[i])) {
                if (i + m1.get(list2[i]) < min) {
                    min = i + m1.get(list2[i]);
                    r.clear();
                    r.add(list2[i]);
                } else if (i + m1.get(list2[i]) == min) {
                    r.add(list2[i]);
                }
            }
        }
        return r.toArray(new String[0]);
    }

    public void sortColors(int[] nums) {
        int i = 0, j = 0, k = 0;
        for (int num : nums) {
            if (num == 0) {
                nums[i++] = 2;
                nums[j++] = 1;
                nums[k++] = 0;
            } else if (num == 1) {
                nums[j++] = 2;
                nums[k++] = 1;
            } else if (num == 2) {
                nums[k++] = 2;
            }
        }
    }


    public static void main(String[] args) {
        FindRestaurant f = new FindRestaurant();
//        System.out.println(f.findRestaurant(new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"}, new String[]{"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"}));
        f.sortColors(new int[]{2, 0, 2, 1, 1, 0});
    }
}
