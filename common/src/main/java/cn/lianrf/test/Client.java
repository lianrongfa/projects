package cn.lianrf.test;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress("127.0.0.1",8007));

            OutputStream outputStream = socket.getOutputStream();

            outputStream.write("hi ni hao a".getBytes());

            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];

            inputStream.read(bytes);

            System.out.println(new String(bytes));

            outputStream.close();
            inputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();

        RedissonClient redssion = Redisson.create();

        RLock lock = redssion.getLock("");
        lock.lock();

        lock.unlock();
    }
}
