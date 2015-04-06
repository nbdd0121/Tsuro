package phisighackers.tsuro.core;

import java.awt.Color;


public interface GameBoardView {
	
	/* Needed! */
	int playerNum();
	Tile[][] getAll();
	int[] startingPoint();
	Color[] color();
	
	/* Helper! */
	Tile get(int r, int c);
	int startingPoint(int p);
	
	public Color color(int p);
	public Tile[] handTiles();
	int id();

}
