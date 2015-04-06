package phisighackers.tsuro.internet;

import java.io.IOException;
import java.net.ServerSocket;

import javax.swing.JDialog;

import phisighackers.tsuro.core.Agent;
import phisighackers.tsuro.core.GameManager;

import com.nwgjb.xrmi.RMIConnection;

public class TsuroServer implements Runnable{
	ServerSocket socket;
	GameManager man;
	int playerNum;
	int port;
	JDialog dialog=new JDialog();
	
	public TsuroServer(int port, GameManager man, int p){
		this.port=port;
		this.man=man;
		playerNum=p;
	}
	
	public void run(){
		try {
			socket=new ServerSocket(port);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		dialog.setVisible(true);
		dialog.setSize(500, 50);
		dialog.setResizable(false);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		while(man.playerNum()<playerNum){
			dialog.setTitle("Wait for "+(playerNum-man.playerNum())+" more players, then the game will start.");
			try {
				man.join((Agent)new RMIConnection(socket.accept(), null).getBind());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		dialog.dispose();
		dialog=null;
		man.start();
	}
}
