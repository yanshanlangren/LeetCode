package elvis.netty;

public class HelloWorldServlet extends OurServlet{
    @Override
    public void doGet(OurRequest request, OurResponse response) throws Exception {
        doPost(request, response);
    }

    @Override
    public void doPost(OurRequest request, OurResponse response) throws Exception {
        response.write("this is the first Servlet");
    }
}
