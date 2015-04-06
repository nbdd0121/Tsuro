package phisighackers.tsuro.core;

import java.awt.Color;


public interface Agent {
	
	public int getStartingPoint();
	public Color getColor();
	public void setGameBoardView(GameBoardView gbv);
	public Tile requestMove();
	public void update();
	void display(String text);

}
