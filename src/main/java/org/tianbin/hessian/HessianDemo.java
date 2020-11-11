package org.tianbin.hessian;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.caucho.hessian.io.SerializerFactory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by nibnait on 2020/11/12
 */
public class HessianDemo {

    private SerializerFactory serializerFactory;

    @Before
    public void before() {
        serializerFactory = new SerializerFactory();
        serializerFactory.setAllowNonSerializable(true);
    }

    @Test
    public void test01() throws IOException {
        Son data = new Son();
        data.setName("儿子");
        data.setAge(6);
        data.setPhone("123");
        System.out.println("要序列化的对象: " + data);

        // 序列化
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Hessian2Output output = new Hessian2Output(os);
        output.setSerializerFactory(serializerFactory);
        output.writeObject(data);
        output.flush();
        byte[] bytes = os.toByteArray();
        output.close();
        System.out.println("序列化后的二进制数据: " + Arrays.toString(bytes));

        // 反序列化
        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        Hessian2Input input = new Hessian2Input(is);
        input.setSerializerFactory(serializerFactory);
        Object obj = input.readObject();
        input.close();
        System.out.println("反序列化对象: " + obj);
    }

    @Data
    class Parent {
        private String name;
        private int age;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Son extends Parent implements Serializable {
        private String name;
        private String phone;
    }

    @Test
    public void test00() throws Exception {
        int data = 1;
        System.out.println("要序列化的对象: " + data);

        // 序列化
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Hessian2Output output = new Hessian2Output(os);
        output.setSerializerFactory(serializerFactory);
        output.writeObject(data);
        output.flush();
        byte[] bytes = os.toByteArray();
        output.close();
        System.out.println("序列化后的二进制数据: " + Arrays.toString(bytes));

        // 反序列化
        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        Hessian2Input input = new Hessian2Input(is);
        input.setSerializerFactory(serializerFactory);
        Object obj = input.readObject();
        input.close();
        System.out.println("反序列化对象: " + obj);
    }

}
