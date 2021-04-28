package cn.lianrf.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by lianrongfa on 2018/5/14.
 */
public class RMIServer  {

    public static void main(String[] args) {
        try {
            RMIServiceI rmiService = new RMIServiceImpl();

            LocateRegistry.createRegistry(8080);

            Naming.bind("rmi://localhost:8080/sayHello",rmiService);

            System.out.println("rmi 服务启动成功");


        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
