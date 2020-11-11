package os.jvm.classloader.自定义类加载器;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by nibnait on 2020/11/11
 */
@Data
@NoArgsConstructor
public class MyClassLoader extends ClassLoader {

    private String root;

    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    public String toString() {
        return "自定义类加载器。just fot test";
    }

    protected Class<?> findClass(String className) throws ClassNotFoundException {
        byte[] classData = loadClassData(className);

        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(className, classData, 0, classData.length);
        }
    }

    private byte[] loadClassData(String className) {

        try {
            String fileName = root + File.separatorChar + className.substring(className.lastIndexOf('.')+1, className.length()) + ".class";
            Path path = Paths.get(fileName);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
