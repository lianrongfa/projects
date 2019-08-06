package cn.lianrf.observer;

/**
 * Created by lianrongfa on 2017/11/9.
 */
public interface Subject {
    void registerObServers(ObServer observer);
    void removeObservers(ObServer observer);
    void notifyObservers();
}
