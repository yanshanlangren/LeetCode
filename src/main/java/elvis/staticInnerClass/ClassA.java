package elvis.staticInnerClass;

public class ClassA {
    public class ClassB {
        public void say() {
            System.out.println("hello");
        }
    }

    public static void main(String[] args) {
        ClassA a = new ClassA();
        ClassB b = a.new ClassB();
        b.say();
    }
}
