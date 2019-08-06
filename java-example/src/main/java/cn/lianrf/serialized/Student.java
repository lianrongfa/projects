package cn.lianrf.serialized;

import java.io.*;

/**
 * Created by lianrongfa on 2018/2/24.
 */
public class Student{

    public static void main(String[] args) {

        Source source = deSerialized();
        System.out.println(source);

    }

    private static void serialized() {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(new File("source")));
            Source source = new Source();
            outputStream.writeObject(source);
            source.setName("haha");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Source deSerialized(){
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(new File("source")));
            Object o = inputStream.readObject();
            return (Source)o;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}

class Source implements Serializable{

   private int i;
   private String name;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Source{" +
                "i=" + i +
                ", name='" + name + '\'' +
                '}';
    }
}