package cn.lianrf.observer;

/**
 * Created by lianrongfa on 2017/11/10.
 */
public class WeObServer implements ObServer {
    private String name;

    public WeObServer(String name) {
        this.name = name;
    }

    public void update() {
        System.out.println("-----"+name);
    }

    public void run() {
        update();
    }
}
