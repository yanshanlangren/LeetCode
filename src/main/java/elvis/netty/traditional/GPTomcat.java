package elvis.netty.traditional;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class GPTomcat {
    /**
     * 默认端口号。
     */
    private int port = 8080;

    private ServerSocket server;

    private Map<String, GPServlet> servletMap = new HashMap<String, GPServlet>();

    /**
     * 读取配置文件
     */
    private Properties webProperties = new Properties();

    /**
     * 初始化函数
     */
    private void init() {
        String base_path = "E:\\GitWorkspace\\test\\src\\main\\resources\\";
        try {
//            FileInputStream fis = new FileInputStream(WEB_INF + "web.properties");
            FileInputStream fis = new FileInputStream(base_path + "web.properties");
            webProperties.load(fis);
            for (Object k: webProperties.keySet()) {
                String key = k.toString();
                if (key.endsWith(".url")) {
                    String servletName = key.replaceAll("\\.url$","");
                    String url = webProperties.getProperty(key);
                    String className = webProperties.getProperty(servletName + ".className");
                    System.out.println("className:" + className);
                    GPServlet obj = (GPServlet) Class.forName(className).newInstance();
                    servletMap.put(url,obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 服务就绪，准备启动
     */
    public void start() {
        init();

        try {
            server = new ServerSocket(this.port);
            System.out.println("TomCat已经启动。。。。端口号为:" + this.port);

            // 等待用户请求
            while (true) {
                Socket client = server.accept();
                // 处理http请求
                process(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 处理的业务逻辑。
     * @param client
     * @throws Exception
     */
    private void process(Socket client) throws Exception {
        InputStream inputStream = client.getInputStream();
        OutputStream outputStream = client.getOutputStream();

        // 转换为GPRequest
        GPRequest request = new GPRequest(inputStream);
        GPResponse response = new GPResponse(outputStream);

        // 从协议中获取url。
        String url = request.getUrl();

        if (servletMap.containsKey(url)) {
            servletMap.get(url).service(request,response);
        }else {
//            response.write("404-Not-Found");
        }

        outputStream.flush();
        outputStream.close();

        inputStream.close();
        client.close();
    }

    public static void main(String[] args) {
        new GPTomcat().start();
    }
}