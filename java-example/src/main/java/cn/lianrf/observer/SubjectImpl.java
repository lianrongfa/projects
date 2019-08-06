package cn.lianrf.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lianrongfa on 2017/11/9.
 */
public class SubjectImpl implements Subject {
    ExecutorService executorService = Executors.newScheduledThreadPool(5);
    private int num;

    private List<ObServer> obServerList;

    public SubjectImpl() {
        this.obServerList = new ArrayList<ObServer>();
    }

    public void registerObServers(ObServer observer) {
        if (checkObServerList()) {
            obServerList.add(observer);
        }
    }

    public void removeObservers(ObServer observer) {
        if (checkObServerList()) {

        }
    }

    public void notifyObservers() {
        if (checkObServerList()) {
            if (num > 10) {
                for (ObServer item : obServerList) {
                    executorService.execute(item);
                }
            }
        }
    }

    private boolean checkObServerList() {
        if (obServerList != null) {
            return true;
        }
        return false;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
