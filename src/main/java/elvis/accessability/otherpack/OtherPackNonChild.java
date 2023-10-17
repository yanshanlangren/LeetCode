package elvis.accessability.otherpack;

import elvis.accessability.pack.AccessabilityTest;

public class OtherPackNonChild {

    AccessabilityTest at = new AccessabilityTest();
    public static void main(String[] args){
        OtherPackNonChild tester = new OtherPackNonChild();
        tester.test();
    }
    public void test(){
        at.publicMethod();
    }
}
