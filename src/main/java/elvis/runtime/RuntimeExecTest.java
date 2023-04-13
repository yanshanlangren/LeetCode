package elvis.runtime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class RuntimeExecTest {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Runtime r = Runtime.getRuntime();
        while (true) {
            System.out.println("============= type cmd ==============");
            String a = br.readLine();
            if (a != null && !a.trim().equals("")) {
                try {
                    Process process = r.exec(a);
                    BufferedReader output = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("GBK")));
                    String line;
                    while ((line = output.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
