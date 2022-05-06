package elvis.netty.traditional;

public abstract class GPServlet {

    public void service(GPRequest request, GPResponse response) throws Exception{
        // 如果请求方法为GET
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            doGet(request,response);
        }else {
            doPost(request,response);
        }
    }

    public abstract void doGet(GPRequest request,GPResponse response) throws Exception;

    public abstract void doPost(GPRequest request,GPResponse response) throws Exception;
}