package elvis.netty;

public abstract class OurServlet {

    public void service(OurRequest request, OurResponse response) throws Exception {
        // 如果请求方法为GET
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            doGet(request, response);
        } else {
            doPost(request, response);
        }
    }

    public abstract void doGet(OurRequest request, OurResponse response) throws Exception;

    public abstract void doPost(OurRequest request, OurResponse response) throws Exception;
}