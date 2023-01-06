package elvis.classloader;

public class Base {
    static String staticPara = init("基类静态变量");

    static {
        System.out.println("基类静态代码块");
    }


    String derivedNormalPara = Base.init("基类普通变量");

    {
        System.out.println("基类普通代码块");
    }

    public Base() {
        System.out.println("基类构造函数");
    }

    static String init(String asd) {
        System.out.println(asd);
        return "";
    }
}
