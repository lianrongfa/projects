package cn.lianrf.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by lianrongfa on 2018/5/14.
 *
 * RMI相当于是java版本的RPC 是是实现java分布式的解决方案
 */
public interface RMIServiceI extends Remote{
    String sayHello(String message) throws RemoteException;
}
