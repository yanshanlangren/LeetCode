package elvis.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamConstructor {

    public Stream constructor1(String[] input){
        return Stream.of(input);
    }

    public Stream constructor2(String[] input){
        return Arrays.stream(input);
    }

    public Stream constuctor3(String[] input){
        return Arrays.asList(input).stream();
    }


    static List<User> list = Arrays.asList(
            new User("张三", 11),
            new User("王五", 20),
            new User("王五", 91),
            new User("张三", 8),
            new User("李四", 44),
            new User("李四", 44),
            new User("李四", 44)
    );

    public static void forEach(){
        list.stream().forEach(u -> System.out.println(u.toString()));
    }

    public static void sorted(){
        System.out.println("-----排序前-----");
        list.stream().forEach(u->System.out.println(u.toString()));
        System.out.println("-----排序后-----");
        list.stream().sorted(Comparator.comparing(User::getAge)).forEach(u-> System.out.println(u.toString()));
    }

    public static void filter(){
        System.out.println("-----过滤前-----");
        list.stream().forEach(u->System.out.println(u.toString()));
        System.out.println("-----过滤后-----");
        list.stream().filter(u->u.getAge()>30).forEach(u->System.out.println(u.toString()));

    }

    public static void limit(){
        list.stream().limit(3).forEach(u->System.out.println(u.toString()));
    }

    public static void distinct(){
        System.out.println("-----去重前-----");
        list.stream().forEach(u->System.out.println(u.toString()));
        System.out.println("-----去重后-----");
        list.stream().distinct().forEach(u->System.out.println(u.toString()));
    }

    public static void statistics(){
        IntSummaryStatistics sum = list.stream().mapToInt(u->u.getAge()).summaryStatistics();
        System.out.println("总共人数：" + sum.getCount());
        System.out.println("平均年龄：" + sum.getAverage());
        System.out.println("最大年龄：" + sum.getMax());
        System.out.println("最小年龄：" + sum.getMin());
        System.out.println("年龄之和：" + sum.getSum());
    }

    //打印所有年龄
    public static void getAllAges(){
        List<Integer> ages = list
                .stream()
                .map(User::getAge)
                .collect(Collectors.toList());
        ages.forEach(a->System.out.println(a));
    }

    public static void toUpperCase(){
        List<String> words = Arrays.asList("aaa", "vvvv", "cccc");
        words.stream().map(u->u.toUpperCase()).forEach(w->System.out.println(w));
    }

    public static void findFirst(){
        User u = list.stream().findFirst().get();
        System.out.println(u.toString());
    }

    public static void main(String[] args){
//        sorted();
//        list.stream().sorted(new MyComparator()).forEach(u-> System.out.println(u.toString()));

//        filter();
//        list.stream().filter(new MyPredicate()).forEach(u->System.out.println(u.toString()));

//        limit();

//        list.stream().skip(3).forEach(u->System.out.println(u.toString()));

//        distinct();
//        User u4 = list.get(4);
//        User u5 = list.get(5);
//        if(u4==u5){
//            System.out.println("==");
//        } else{
//            System.out.println("not ==");
//        }
//
//        if(u4.equals(u5)){
//            System.out.println("equals");
//        } else{
//            System.out.println("not equals");
//        }

//        statistics();

        getAllAges();

//        toUpperCase();

//        findFirst();

    }
}
