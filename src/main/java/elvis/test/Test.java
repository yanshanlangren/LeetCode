package elvis.test;

import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Test {

    public static class Student {
        int age;

        Student(int age) {
            this.age = age;
        }
    }

    public void changeAge(Student st) {
        st.age = 20;
    }

    int[] toSort;

    public void fastSort(int start, int end) {
        if (start + 1 > end)
            return;
        int left = 0, right = end - start;
        int[] ret = new int[end - start + 1];
        for (int i = start + 1; i <= end; i++)
            if (toSort[i] < toSort[start])
                ret[left++] = toSort[i];
            else
                ret[right--] = toSort[i];
        ret[left] = toSort[start];
        System.arraycopy(ret, 0, toSort, start, end - start + 1);
        fastSort(start, left - 1);
        fastSort(left + start + 1, end);
    }

    public String minimumWindowSubstring(String S, String T) {
        int left = 0, right = 0, min = Integer.MAX_VALUE, flag = 0;
        int sigma = 1;
        //初始化
        HashMap<Character, Integer> check = new HashMap<>();
        for (char c : T.toCharArray())
            check.put(c, 0);

        //找到第一个包含全部字符的串
        for (int i = 0; i < S.length(); i++) {
            if (check.containsKey(S.charAt(i)))
                check.put(S.charAt(i), check.get(S.charAt(i)) + 1);
            sigma = 1;
            for (char c : T.toCharArray()) {
                if ((sigma *= (check.get(c))) == 0)
                    break;
            }
            if (sigma == 1) {
                min = i + 1;
                right = i + 1;
                break;
            }
        }
        while (right != left) {
            char ch = S.charAt(left);
            left++;

            //left滑动
            if (check.containsKey(ch)) {
                check.put(ch, check.get(ch) - 1);
                sigma = 1;
                for (char c : T.toCharArray()) {
                    if ((sigma *= (check.get(c))) == 0)
                        break;
                }
            } else {
                if (right - left < min && sigma == 1) {
                    min = right - left;
                    flag = left;
                }
            }

            //right滑动
            if (sigma == 0 && right < S.length()) {
                if (check.containsKey(S.charAt(right))) {
                    check.put(S.charAt(right), check.get(S.charAt(right)) + 1);
                    sigma = 1;
                    for (char c : T.toCharArray()) {
                        if ((sigma *= (check.get(c))) == 0)
                            break;
                    }
                }
                right++;
            }
        }
        return S.substring(flag, flag + min);
    }

    public void testPriorityQueue() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        System.out.println(queue.poll());
        System.out.println(queue.peek());
    }

    public static void main(String[] args) {

        HashMap<String, List<String>> m = new HashMap<>();
        m.values().stream().collect(Collectors.toList());
        Test t = new Test();
        long t0 = System.currentTimeMillis();

//        System.out.println(t.minimumWindowSubstring("ADOBECODEBANC", "ABCDE"));
//        t.testPriorityQueue();
        String a = "abc";
        String b = new String("abc");
        System.out.println("abc".intern());
        System.out.println("abc".hashCode());
        System.out.println(b.equals(a));

        System.out.println(System.currentTimeMillis() - t0 + " ms");
    }
}
