package cn.lianrf.nio;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.Iterator;

/**
 * Created by lianrongfa on 2018/1/18.
 */
public class PathAndFilesDemo {
    @Test
    public void test1(){
        Path path = Paths.get("E:\\SQL");
        try {
            Files.write(path,"fwjoigwio".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Iterator<Path> iterator = path.iterator();


        try {
            BufferedReader bufferedReader = Files.newBufferedReader(path);
            int read = bufferedReader.read();
            System.out.println(read);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test2(){
        FileSystem fileSystem = FileSystems.getDefault();
        Path path = fileSystem.getPath("Test1.java");
        Path parent = path.getParent();
        System.out.println(path);
    }
}
