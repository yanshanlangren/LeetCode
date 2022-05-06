package elvis.netty.traditional;

import java.io.IOException;
import java.io.InputStream;

public class GPRequest {

    private String method;

    private String url;

    public GPRequest(InputStream in) {
        String content = "";
        byte[] buff = new byte[1024];
        int len = 0;
        try {
            if ((len = in.read(buff)) > 0) {
                content = new String(buff, 0, len);
            }
            System.out.println("content:" + content);
            String line = content.split("\\n")[0];
            System.out.println("line:" + line);
            String[] arr = line.split("\\s");

            this.method = arr[0];
            this.url = arr[1].split("\\s")[0];

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取请求方式。
     *
     * @return
     */
    public String getMethod() {
        return this.method;
    }

    public String getUrl() {
        return this.url;
    }
}