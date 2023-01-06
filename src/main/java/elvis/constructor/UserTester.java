package elvis.constructor;

public class UserTester {
    public static void main(String[] args) {
        UserFactory uf = User::new;

        User asd = uf.getUser("钟煜", "男");

        asd.interview();
    }
}
