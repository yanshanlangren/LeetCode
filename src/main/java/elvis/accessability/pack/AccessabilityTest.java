package elvis.accessability.pack;

public class AccessabilityTest {
    public void publicMethod() {
        System.out.println("public 方法");
    }

    protected void protectedMethod() {
        System.out.println("protected 方法");
    }

    void defaultMethod() {
        System.out.println("default 方法");
    }

    private void privateMethod() {
        System.out.println("private 方法");
    }

    public AccessabilityTest(){
        System.out.println("这是AccessabilityTest的无参数构造器");
    }
}
