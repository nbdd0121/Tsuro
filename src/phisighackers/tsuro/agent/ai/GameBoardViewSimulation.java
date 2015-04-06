package phisighackers.tsuro.agent.ai;

import java.awt.Color;
import java.util.Arrays;

import phisighackers.tsuro.core.GameBoardView;
import phisighackers.tsuro.core.GameHelper;
import phisighackers.tsuro.core.Position;
import phisighackers.tsuro.core.Tile;
import phisighackers.util.MoreArrays;

public class GameBoardViewSimulation implements GameBoardView {

	Tile[][] tile=new Tile[6][6];
	int playerNum;
	int[] startingPoint;
	Tile[] handTiles;
	Color[] colors;
	int id;
	
	public GameBoardViewSimulation(GameBoardView snapshot){
		playerNum=snapshot.playerNum();
		startingPoint=Arrays.copyOf(snapshot.startingPoint(), playerNum);
		colors=Arrays.copyOf(snapshot.color(), playerNum);
		id=snapshot.id();
		tile=MoreArrays.dup(snapshot.getAll());
		handTiles=Arrays.copyOf(snapshot.handTiles(), 3);
	}
	
	@Override
	public Tile get(int r, int c) {
		return tile[r][c];
	}
	
	public void put(int r, int c, Tile t){
		tile[r][c]=t;
	}
	
	public void put(int p, Tile t){
		Position pos=GameHelper.getConn(this, startingPoint[p]);
		put(pos.x, pos.y, t);
	}
	
	public void put(Tile t){
		put(id, t);
	}

	@Override
	public Tile[][] getAll() {
		return tile;
	}

	@Override
	public int playerNum() {
		return playerNum;
	}

	@Override
	public int startingPoint(int p) {
		return startingPoint[p];
	}

	public Color color(int p) {
		return colors[p];
	}

	public Tile[] handTiles() {
		return handTiles;
	}

	public int id() {
		return id;
	}

	public boolean lose() {
		return lose(id);
	}

	public boolean lose(int id) {
		return !GameHelper.getConn(this, startingPoint[id]).onBoard;
	}

	public int[] startingPoint() {
		return startingPoint;
	}

	public Color[] color() {
		return colors;
	}

	public boolean win() {
		for(int i=0;i<playerNum;i++){
			if(i!=id){
				if(!lose(id))return false;
			}
		}
		return true;
	}

}
