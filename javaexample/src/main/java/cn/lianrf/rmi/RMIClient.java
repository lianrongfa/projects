package cn.lianrf.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by lianrongfa on 2018/5/14.
 */
public class RMIClient {
    public static void main(String[] args) {
        try {
            RMIServiceI remote = (RMIServiceI)Naming.lookup("rmi://localhost:8080/sayHello");
            System.out.println(remote.sayHello("lian"));
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
