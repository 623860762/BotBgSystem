package rmi.weibotService;

import java.rmi.Remote;
import java.rmi.RemoteException;

import weiboApi.WeiboMsg;

public interface WeibotServiceRmi  extends Remote{
	public WeiboMsg processMsg(WeiboMsg pMsg) throws RemoteException;
	public WeiboMsg processCMD(WeiboMsg pMsg) throws RemoteException;
}
