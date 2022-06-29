package elvis.concurrent.join;

public class JoinTest extends Thread {
    public int n = 0;

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("j:" + n++);
        }

    }

    public static void main(String[] args) {
        JoinTest j = new JoinTest();
        j.start();
        new Thread() {
            @Override
            public void run() {
//                try {
////                    j.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println("final:" + j.n);
            }
        }.start();
    }
}
