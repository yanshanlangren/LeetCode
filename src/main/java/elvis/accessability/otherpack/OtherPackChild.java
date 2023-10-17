package elvis.accessability.otherpack;

import elvis.accessability.pack.AccessabilityTest;

public class OtherPackChild extends AccessabilityTest {
    public void test(){
        protectedMethod();
        publicMethod();
    }

    public static void main(String[] args){
        OtherPackChild apc = new OtherPackChild();
        apc.test();
    }
}
