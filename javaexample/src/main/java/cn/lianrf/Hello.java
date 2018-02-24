package cn.lianrf;

import org.junit.Test;

import java.io.*;

/**
 * Created by lianrongfa on 2018/1/2.
 */
public class Hello {
    public static void main(String[] args) throws Exception {
        Student stu=new Student();

        ByteArrayOutputStream byt=new ByteArrayOutputStream();

        ObjectOutputStream obj=new ObjectOutputStream(byt);

        obj.writeObject(stu);

        byte[] bytes=byt.toByteArray();
        System.out.println(bytes);


        ByteArrayInputStream byteInt=new ByteArrayInputStream(bytes);
        ObjectInputStream objInt=new ObjectInputStream(byteInt);
        Student stu2=new Student();
        stu2=(Student)objInt.readObject();

        System.out.println(stu2);
    }
}

