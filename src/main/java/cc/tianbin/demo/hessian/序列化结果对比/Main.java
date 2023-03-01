package cc.tianbin.demo.hessian.序列化结果对比;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

import java.io.*;

/**
 * Created by nibnait on 2020/11/12
 */
public class Main {

    public static <T> byte[] hessianSerialize(T t) {
        byte[] data = null;

        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            HessianOutput output = new HessianOutput(os);
            output.writeObject(t);

            data = os.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    public static <T> T hessianDeserialize(byte[] data) {
        if (data == null) {
            return null;
        }
        Object result = null;

        try {
            ByteArrayInputStream is = new ByteArrayInputStream(data);
            HessianInput input = new HessianInput(is);

            result = input.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return (T) result;
    }

    public static <T> byte[] hessian2Serialize(T t) {
        byte[] data = null;

        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            Hessian2Output output = new Hessian2Output(os);

            output.writeObject(t);

            output.getBytesOutputStream().flush();
            output.completeMessage();
            output.close();

            data = os.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static <T> T hessian2Deserialize(byte[] data) {
        if (data == null) {
            return null;
        }
        Object result = null;

        try {
            ByteArrayInputStream is = new ByteArrayInputStream(data);
            Hessian2Input input = new Hessian2Input(is);

            result = input.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return (T) result;
    }

    public static <T> byte[] jdkSerialize(T t) {
        byte[] data = null;

        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ObjectOutputStream output = new ObjectOutputStream(os);

            output.writeObject(t);
            output.flush();
            output.close();

            data = os.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static <T> T jdkDeserialize(byte[] data) {
        if (data == null) {
            return null;
        }
        Object result = null;

        try {

            ByteArrayInputStream is = new ByteArrayInputStream(data);
            ObjectInputStream input = new ObjectInputStream(is);

            result = input.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return (T) result;
    }

    public static void main(String[] args) {
        Student student = new Student(1, "小明", "男");
        System.out.println(student);

        byte[] hessianSerializeByteArray = hessianSerialize(student);
        System.out.println("HessianOutput " + hessianSerializeByteArray.length);

        byte[] hessian2SerializeByteArray = hessian2Serialize(student);
        System.out.println("Hessian2Output " + hessian2SerializeByteArray.length);

        byte[] jdkSerializeByteArray = jdkSerialize(student);
        System.out.println("ObjectOutputStream " + jdkSerializeByteArray.length);

        /******************* 反序列化 *******************/
        System.out.println((Student) hessianDeserialize(hessianSerializeByteArray));
        System.out.println((Student) hessian2Deserialize(hessian2SerializeByteArray));
        System.out.println((Student) jdkDeserialize(jdkSerializeByteArray));
    }

}
