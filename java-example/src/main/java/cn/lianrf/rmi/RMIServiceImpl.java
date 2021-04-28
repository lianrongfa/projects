package cn.lianrf.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by lianrongfa on 2018/5/14.
 */
public class RMIServiceImpl extends UnicastRemoteObject implements RMIServiceI{
    public RMIServiceImpl() throws RemoteException {

    }

    @Override
    public String sayHello(String message)throws RemoteException {
        return "hello -> "+message;
    }
}
