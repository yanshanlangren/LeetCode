package elvis.stream;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

    public static class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return this.name;
        }

        public int getAge() {
            return this.age;
        }
    }

    public static void main(String[] args) {
//        System.out.println(Optional.of(null));
//        List<User> list = new ArrayList();
//        System.out.println(new ArrayList().add("hello"));
        List<User> list = new ArrayList(Arrays.asList(new User[]{new User("hello", 1), new User("ann", 2), new User("elvis", 3)}));
//        System.out.println(list);
        int res = list.stream().map(User::getAge).reduce(0, (a, b) -> a + b);
        System.out.println(res);

//        Integer i = 2, j = 1;
//        System.out.println(i * j);


        Stream<Integer> s = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6});
        int res1 = s.reduce(1, (a, b) -> a * b);
        System.out.println(res1);

        Stream<Integer> s1 = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6});
        Integer[] l = s1.filter(a -> a % 2 == 0).toArray(Integer[]::new);
        System.out.println(l);

        Stream<Integer> s2 = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6});
        int max = s2.max(Comparator.comparingInt(a -> a)).get();
        System.out.println(max);

        Stream<Integer> s3 = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6});
        System.out.println(s3.sorted((a, b) -> b - a).parallel().findFirst().orElse(1));

        Stream<Integer> s4 = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6});
        List<Integer> l4 = s4.collect(Collectors.toList());
        System.out.println(l4);

        Stream<Integer> s5 = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6});
        System.out.println(s5.parallel().findAny().get());
//        System.out.println(s4.collect(counting));


        int num3 = 15;
        for (int i = 1; i < 4; i<<=2) {
            System.out.println(i);
            num3 ^= i;
        }
        System.out.println(num3);

//        System.out.println(IntStream.range(1, 4).reduce(15, (x, y) -> x ^ y));
        System.out.println("===========================");

        AtomicInteger rrr = new AtomicInteger(15);
        Stream.iterate(1, i -> i <<= 2).peek(i -> {System.out.println(i);rrr.updateAndGet(x -> x ^ i);}).allMatch(i -> i < 3);
        System.out.println(rrr);
//        Stream.iterate(1, i -> i << 2)
//                .peek(n -> {
//                    if (n < 100) System.out.println(n);
//                })
//                .limit(5)
//                .forEach(System.out::println);
//                .allMatch(n -> n < 100);
//        Stream.iterate(1, i -> i <= 10, i -> i+1).forEach(System.out::println);
    }

//    public static Integer counting(){
//        return 0;
//    }
}
