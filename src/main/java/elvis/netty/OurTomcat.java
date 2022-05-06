package elvis.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

import java.io.FileInputStream;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class OurTomcat {
    /**
     * 默认端口号。
     */
    private int port = 8080;

    private ServerSocket server;

    private Map<String, OurServlet> servletMap = new HashMap<String, OurServlet>();

    /**
     * 读取配置文件
     */
    private Properties webProperties = new Properties();

    /**
     * 初始化函数
     */
    private void init() {
//        String WEB_INF = this.getClass().getResource("/").getPath();
        String WEB_INF = "E:\\GitWorkspace\\test\\src\\main\\resources\\";
        try {
            FileInputStream fis = new FileInputStream(WEB_INF + "web-netty.properties");
            webProperties.load(fis);
            for (Object k : webProperties.keySet()) {
                String key = k.toString();
                if (key.endsWith(".url")) {
                    String servletName = key.replaceAll("\\.url$", "");
                    String url = webProperties.getProperty(key);
                    String className = webProperties.getProperty(servletName + ".className");
                    Object _obj = Class.forName(className).newInstance();
                    OurServlet obj = (OurServlet) _obj;
                    servletMap.put(url, obj);
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

        // Boss线程
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        // worker线程
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        // 1.创建对象
        ServerBootstrap server = new ServerBootstrap();
        //2. 配置参数
        server.group(bossGroup, workerGroup)
                // 主线程处理类
                .channel(NioServerSocketChannel.class)
                // 子线程处理类 Handler
                .childHandler(new ChannelInitializer<SocketChannel>() {

                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        // 无锁化串行编程
                        // Netty 对Http的封装。对顺序有要求。
                        //
                        socketChannel.pipeline().addLast(new HttpResponseEncoder());
                        // HttpRequestDecoder 解码器
                        socketChannel.pipeline().addLast(new HttpRequestDecoder());
                        // 业务逻辑处理
                        socketChannel.pipeline().addLast(new OurTomcatHandler());
                    }
                })
                //针对主线程的配置，最大线程数128
                .option(ChannelOption.SO_BACKLOG, 128)
                // 针对子线程的配置，保持长连接
                .childOption(ChannelOption.SO_KEEPALIVE, true);
        try {
            // 3 启动服务器
            ChannelFuture f = server.bind(port).sync();
            System.out.println("Tomcat启动成功，监听的端口是：" + port);
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public class OurTomcatHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

            if (msg instanceof HttpRequest) {
//                System.out.println("hello");
                HttpRequest req = (HttpRequest) msg;

                // 转交给我们自己的request实现
                OurRequest request = new OurRequest(ctx, req);
                // 转交给我们自己的response实现
                OurResponse response = new OurResponse(ctx, req);
                // 实际业务处理
                String url = request.getUrl();

                if (servletMap.containsKey(url)) {
                    servletMap.get(url).service(request, response);
                } else {
                    response.write("404 - Not Found");
                }

            }
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        }

    }

    public static void main(String[] args) {
        new OurTomcat().start();
    }
}
