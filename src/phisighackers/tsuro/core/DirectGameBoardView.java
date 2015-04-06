package phisighackers.tsuro.core;

import java.awt.Color;

public class DirectGameBoardView implements GameBoardView {

	GameBoard board;
	int current;
	
	public DirectGameBoardView(GameBoard b, int player){
		board=b;
		current=player;
	}
	
	@Override
	public Tile get(int r, int c){
		return board.get(r, c);
	}
	
	@Override
	public int playerNum(){
		return board.playerNum;
	}
	
	@Override
	public int startingPoint(int p){
		return board.startingPoint(p);
	}
	
	@Override
	public Tile[] handTiles(){
		return board.copyHandTile(current);
	}

	@Override
	public Tile[][] getAll() {
		return board.getAll();
	}

	@Override
	public Color[] color() {
		return board.color();
	}
	
	@Override
	public Color color(int p) {
		return board.color(p);
	}

	@Override
	public int id() {
		return current;
	}

	@Override
	public int[] startingPoint() {
		return board.startingPoint();
	}

}