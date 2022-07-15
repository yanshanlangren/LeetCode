package elvis.JNI;

public class JNITIME {
    public native void testPrintArray(int[] a);

    public static void main(String[] args) {
        int a[] = {1, 2, 3, 4, 5};
        int k = 0;
        System.loadLibrary("TestJNITIME");
        JNITIME jnitime = new JNITIME();
        long startTime;
        long endTime;
        startTime = System.currentTimeMillis();
        for (; k < 500; k++) {
            jnitime.testPrintArray(a);
            System.out.println(" ");
        }
        endTime = System.currentTimeMillis();
        System.out.print("The time use is:");
        System.out.println(endTime - startTime);
    }
}
