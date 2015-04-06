package phisighackers.tsuro.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import phisighackers.tsuro.core.GameBoardView;

public class GameUI extends JFrame{

	HandPanel rightPanel;
	JLabel text;
	
	static{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
	
	private static final long serialVersionUID = 3656440384391018585L;

	public GameUI(GameBoardView view, MouseListener ml, Color mycolor){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setTitle("Tsuro");
		
		text=new JLabel();
		rightPanel=new HandPanel(view, ml, mycolor);
		//rightPanel.add(new JLabel("Player"));
		
		
		GameBoardUI board=new GameBoardUI(view);
		
		
		
		add(text, BorderLayout.SOUTH);
		add(rightPanel, BorderLayout.EAST);
		add(board, BorderLayout.CENTER);
		
		
		pack();
		setVisible(true);
	}
	
	public void update(){
		rightPanel.update();
	}

	public void display(String text) {
		this.text.setText(text==null?"":text);
	}

}