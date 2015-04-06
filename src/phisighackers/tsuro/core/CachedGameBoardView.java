package phisighackers.tsuro.core;

import java.awt.Color;

import phisighackers.tsuro.core.GameBoardView;
import phisighackers.tsuro.core.Tile;

public class CachedGameBoardView implements GameBoardView {

	GameBoardView board;
	
	Tile[][] tile=new Tile[6][6];
	int playerNum;
	int[] startingPoint;
	Tile[] handTiles;
	Color[] colors;
	boolean firstUpdate=true;
	int id;
	
	public CachedGameBoardView(GameBoardView board){
		this.board=board;
	}
	
	public void update(){
		if(firstUpdate){
			playerNum=board.playerNum();
			startingPoint=board.startingPoint();
			colors=board.color();
			id=board.id();
			firstUpdate=false;
		}
		tile=board.getAll();
		handTiles=board.handTiles();
	}
	
	@Override
	public Tile get(int r, int c) {
		return tile[r][c];
	}

	@Override
	public int playerNum() {
		return playerNum;
	}

	@Override
	public int startingPoint(int p) {
		return startingPoint[p];
	}

	@Override
	public Tile[] handTiles() {
		return handTiles;
	}

	@Override
	public Tile[][] getAll() {
		return tile;
	}

	@Override
	public Color color(int p) {
		return colors[p];
	}

	@Override
	public int id() {
		return id;
	}

	@Override
	public int[] startingPoint() {
		return startingPoint;
	}

	@Override
	public Color[] color() {
		return colors;
	}

}
