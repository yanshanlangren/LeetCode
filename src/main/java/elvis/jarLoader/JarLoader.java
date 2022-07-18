package elvis.jarLoader;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarLoader {

    public final static String argPackage = "org/apache/commons/cli";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File jar = new File("D:\\apache-maven-3.6.1\\lib\\commons-cli-1.4.jar");
        JarFile jarFile = new JarFile(jar);
        URLClassLoader loader = new URLClassLoader(new URL[]{jar.toURL()}, Thread.currentThread().getContextClassLoader());
        Enumeration<JarEntry> jarEntries = jarFile.entries();
        while (jarEntries.hasMoreElements()) {
            JarEntry jarEntry = jarEntries.nextElement();
            String clsName = jarEntry.getName();
//            System.out.println(clsName);
            if (clsName.charAt(0) == '/') {
                clsName = clsName.substring(1);
            }
            if (jarEntry.isDirectory() || !clsName.startsWith(argPackage) || !clsName.endsWith(".class")) {
                continue;
            }
            clsName = clsName.substring(0, clsName.length() - 6);
            clsName = clsName.replace("/", ".");
            Class<?> cls = loader.loadClass(clsName);
            System.out.println(cls);
//            cls.getAnnotation()
        }
    }
}
