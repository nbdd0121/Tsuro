package phisighackers.tsuro.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import phisighackers.tsuro.core.GameBoardView;
import phisighackers.tsuro.core.Tile;

public class HandPanel extends JPanel {

	private static final long serialVersionUID = 6565325538147829374L;
	
	GameBoardView view;
	TileUI[] hands=new TileUI[3];
	
	public Dimension getPreferredSize(){
		return new Dimension(100, 500);
	}
	
	public HandPanel(GameBoardView view, MouseListener ml, Color color){
		
		add(new JLabel("<html>You are <pre style=\"background: #"+ Integer.toHexString(color.getRGB()&0xFFFFFF) +"\">   </>"));
		
		this.view=view;
		for(int i=0;i<hands.length;i++){
			hands[i]=new TileUI(ml);
			add(hands[i]);
		}
	}

	public void update() {
		Tile[] hand=view.handTiles();
		for(int i=0;i<hands.length;i++){
			hands[i].tile=hand[i];
		}
	}
	
	
	
}
