package phisighackers.tsuro.agent;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import phisighackers.tsuro.core.Agent;
import phisighackers.tsuro.core.CachedGameBoardView;
import phisighackers.tsuro.core.GameBoardView;
import phisighackers.tsuro.core.Tile;
import phisighackers.tsuro.gui.GameUI;
import phisighackers.tsuro.gui.TileUI;

public class UserPlayerAgent implements Agent {

	CachedGameBoardView view;
	GameUI ui;
	Color color;
	
	{
		Random rand=new Random();
		color=new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
	}
	
	Tile tileOnAction;
	
	@Override
	public int getStartingPoint() {
		return new Random().nextInt(36);
	}

	@Override
	public void setGameBoardView(GameBoardView gbv) {
		view=new CachedGameBoardView(gbv);
		ui=new GameUI(view, new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				Tile t=((TileUI)e.getSource()).tile;
				tileOnAction=t;
				synchronized(UserPlayerAgent.this){
					UserPlayerAgent.this.notify();
				}
			}
		}, color);
	}

	@Override
	public Tile requestMove() {
		synchronized(this){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return tileOnAction;
	}

	@Override
	public void update() {
		view.update();
		ui.update();
		ui.repaint();
	}

	@Override
	public Color getColor() {
		return color;
	}
	
	@Override
	public void display(String text) {
		ui.display(text);
	}

}
