package phisighackers.tsuro.core;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * GameBoard
 * Describes abstract data storage, this class is not exposed to agent,
 * if you want to access the game board, use the GameBoardView
 * 
 * @author Gary
 *
 */
public class GameBoard implements GameBoardView{
	
	Tile[][] board=new Tile[6][6];
	LinkedList<Tile> deck;
	int playerNum;
	int[] startingPoint;
	Color[] colors;
	Tile[][] hand;
	
	{
		deck=new LinkedList<>(Arrays.asList(Tile.allTiles));
		Collections.shuffle(deck);
	}
	
	public GameBoard(int num){
		playerNum=num;
		startingPoint=new int[num];
		colors=new Color[num];
		hand=new Tile[num][3];
	}
	
	public Tile[][] getAll(){
		Tile[][] ret=new Tile[6][];
		for(int i=0;i<board.length;i++){
			ret[i]=Arrays.copyOf(board[i], 6);
		}
		return ret;
	}
	
	/**
	 * Get a tile on certain position
	 * @param r row
	 * @param c coloum
	 * @return the tile currently on that position
	 */
	public Tile get(int r, int c){
		return board[r][c];
	}
	
	/**
	 * Put a tile to a certain position
	 * @param r
	 * @param c
	 * @param t
	 */
	public void put(int r, int c, Tile t){
		board[r][c]=t;
	}
	
	public int startingPoint(int p){
		return startingPoint[p];
	}
	
	@Override
	public int[] startingPoint(){
		return Arrays.copyOf(startingPoint, playerNum);
	}
	
	public void draw(int player){
		if(deck.isEmpty())return;
		
		Tile[] hand=this.hand[player];
		for(int i=0;i<hand.length;i++){
			if(hand[i]==null){
				hand[i]=deck.pop();
				return;
			}
		}
		throw new AssertionError("A player cannot have more than three hands");
	}

	public Tile[] copyHandTile(int player) {
		return Arrays.copyOf(hand[player], 3);
	}
	
	public Tile[] handTile(int player) {
		return hand[player];
	}

	@Override
	public int playerNum() {
		return playerNum;
	}

	@Override
	public Tile[] handTiles() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Color color(int p) {
		return colors[p];
	}
	
	@Override
	public Color[] color(){
		return Arrays.copyOf(colors, playerNum);
	}

	@Override
	public int id() {
		throw new UnsupportedOperationException();
	}
	
	
	
}
