package phisighackers.tsuro.internet;

import java.io.IOException;
import java.net.Socket;

import phisighackers.tsuro.agent.UserPlayerAgent;

import com.nwgjb.xrmi.RMIConnection;


public class TsuroClient implements Runnable{

	String addr;
	int port;
	
	public TsuroClient(String addr, int port){
		this.addr=addr;
		this.port=port;
	}

	@Override
	public void run() {
		try {
			new RMIConnection(new Socket(addr, port), new UserPlayerAgent());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
