package elvis.JNI;

public class HelloWorld {
    public native void sayHello(String url, String url_shake, String url_freeze, String num);

    public static void main(String[] args) {
        System.loadLibrary("winproject1");
        HelloWorld tNative = new HelloWorld();
        tNative.sayHello("E:\\VSWorkspace\\hello\\images\\xxx.gif", "", "", "2");
    }
}
