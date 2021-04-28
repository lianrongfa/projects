package cn.lianrf.serializable;

import java.io.*;

/**
 * Created by lianrongfa on 2018/5/12.
 */
public class SerialVersionUid implements Serializable{

    /**
     * serialVersionUID保证class是原来的class，即为一个唯一标识位，即使是class文件内容改变，但是uid相同，依旧视为原对象。
     */
    private static final long serialVersionUID = -4700850899941599308L;
    private int age;
    private String name;
    private String name2;

    public static void main(String[] args) {

        SerialVersionUid object = buildObject();


        try {
            //serializableObject(object);

            deSerializableObject();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static void deSerializableObject() throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream=new ObjectInputStream(new FileInputStream(new File("SerialVersionUid")));

        SerialVersionUid o = (SerialVersionUid)inputStream.readObject();

        System.out.println(o);
    }

    private static void serializableObject(SerialVersionUid object) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(new File("SerialVersionUid")));

        outputStream.writeObject(object);
    }


    public static SerialVersionUid buildObject() {
        SerialVersionUid serialVersionUid = new SerialVersionUid();
        serialVersionUid.setAge(18);
        serialVersionUid.setName("tom");
        return serialVersionUid;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SerialVersionUid{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
