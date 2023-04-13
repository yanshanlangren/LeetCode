package elvis.file;

import java.io.File;

public class FileTest {

    public static void main(String[] args){
        File f = new File("");
        if(!f.exists()){
            f.mkdirs();
        }
    }
}
