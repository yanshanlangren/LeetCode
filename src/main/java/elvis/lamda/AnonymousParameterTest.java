package elvis.lamda;

import java.util.Arrays;

public class AnonymousParameterTest {

    public static String[] persons = new String[]{"John", "Eason", "Ann", "Edward", "Elvis", "Shawn", "Joey", "Vincent", "Sherry"};

    public static void main(String[] args) {


        /**
         * 1.下方创建一个匿名内部线程, 线程中使用到了外部方法的name变量, name被隐式地声明了final, 一旦尝试改变name, 编译器就会报错
         */
//        String name = persons[0];
////        name = name +" hello";
//        new Thread(){
//            @Override
//            public void run(){
//                System.out.println(name);
//            }
//        }.start();

        /**
         * 2. filter过滤(惰性求值)
         */
//        Arrays.stream(persons)
//                .filter(person->person.length()<4)
//                .forEach(person ->System.out.println(person));

        /**
         * 3. collect及早求值
         */
//        List<String> list = Arrays.stream(persons)
//                .filter(name->name.endsWith("n"))
//                .collect(Collectors.toList());
//        System.out.println(list.size());

        /**
         * 4. reduce规约
         */
        String asd = Arrays.stream(persons).reduce("Here are my friends", (a, b) -> a + "," + b);
        System.out.println(asd);
    }

}
