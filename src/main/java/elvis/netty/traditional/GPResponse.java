package elvis.netty.traditional;

import java.io.OutputStream;

public class GPResponse {

    private OutputStream outputStream;


    public GPResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    /**
     * 输出 ，需要遵守HTTP协议。
     * 按照HTTP规范输出字符串。
     * @param outMsg
     */
    public void write(String outMsg) throws Exception{
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\n")
                .append("Content-Type: text/html;\n")
                .append("\r\n")
                .append(outMsg);
        outputStream.write(sb.toString().getBytes());
    }
}
