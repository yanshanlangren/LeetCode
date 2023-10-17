package elvis.accessability.pack;

/**
 * 同包非子类可以调用除private 之外的所有方法
 */
public class SamePackNonChild {
    public void test(){
        AccessabilityTest at = new AccessabilityTest();
        at.publicMethod();
        at.protectedMethod();
        at.defaultMethod();
    }

    public static void main(String[] args){

    }
}
