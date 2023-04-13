package elvis.yield;

public class YieldTest extends Thread {
    private String name;
//    public String getName(){
//        return this.name;
//    }

    public YieldTest(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println("-----------" + this.getName() + ": " + i);
//            if (i == 30)
            Thread.yield();
        }
    }

    public static void main(String[] args) {
        YieldTest yt1 = new YieldTest("钟煜");
        YieldTest yt2 = new YieldTest("管文清");
        yt1.start();
        yt2.start();
    }
}
