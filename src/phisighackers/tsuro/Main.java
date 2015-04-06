package phisighackers.tsuro;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import phisighackers.tsuro.agent.ai.AIPlayerAgent;
import phisighackers.tsuro.core.GameManager;
import phisighackers.tsuro.internet.TsuroClient;
import phisighackers.tsuro.internet.TsuroServer;

public class Main{
	
	public static void main(final String[] args) {
		final JFrame frame=new JFrame("Tsuro");
		final JRadioButton client=new JRadioButton("Client");
		final JRadioButton server=new JRadioButton("Server");
		final JTextField address=new JTextField("2");
		final JTextField port=new JTextField("8876");
		final JLabel adrOrNum=new JLabel("Number of Players: ");
		{
			ButtonGroup group=new ButtonGroup();
			server.setSelected(true);
			group.add(client);
			group.add(server);
			client.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					if(client.isSelected()){
						adrOrNum.setText("Server Address: ");
						address.setText("localhost");
					}
				}
			});
			server.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					if(server.isSelected()){
						adrOrNum.setText("Number of Players: ");
						address.setText("2");
					}
				}
			});
		}
		{
			JPanel northPanel=new JPanel();
			northPanel.add(client);
			northPanel.add(server);
			frame.add(northPanel, BorderLayout.NORTH);
		}
		{
			JPanel centerPanel=new JPanel();
			centerPanel.setLayout(new GridLayout(2, 2));
			centerPanel.add(adrOrNum);
			centerPanel.add(address);
			centerPanel.add(new JLabel("Server Port: "));
			centerPanel.add(port);
			frame.add(centerPanel);
		}
		{
			JButton connect=new JButton("Start");
			connect.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
					int portn=Integer.valueOf(port.getText());
					if(server.isSelected()){
						GameManager man=new GameManager();
						man.join(new AIPlayerAgent());
						new Thread(new TsuroServer(portn, man, Integer.valueOf(address.getText()))).start();
					}else{
						new Thread(new TsuroClient(address.getText(), portn)).start();
					}
				}
			});
			frame.add(connect, BorderLayout.SOUTH);
		}
		{
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.pack();
			frame.setVisible(true);
		}
		
		
	}

}
