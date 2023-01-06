package elvis.constructor;

public class PrivateConstructor {

    private PrivateConstructor() {
    }

    public static void print() {
        System.out.println("123");
    }

    public static void main(String[] args){
        new PrivateConstructor();
    }
}
