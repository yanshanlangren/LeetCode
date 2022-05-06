package elvis.netty.traditional;

public class FirstServlet extends GPServlet {
    public void doGet(GPRequest request, GPResponse response) throws Exception {
        doPost(request,response);
    }

    public void doPost(GPRequest request, GPResponse response) throws Exception {
        response.write("this is the first Servlet");
    }
}