package phisighackers.tsuro.core;

import java.util.ArrayList;
import java.util.Collections;

import phisighackers.util.MoreArrays;

public class GameManager {
	ArrayList<Agent> agents=new ArrayList<>();
	boolean[] died;

	GameBoard board;
	
	int upperAgentsLimit=8;
	int lowerAgentsLimit=2;
	
	public void join(Agent ag) 
	{
		if(agents.size()>=upperAgentsLimit){
			throw new RuntimeException("Number of user exceeds upper limit of "+upperAgentsLimit);
		}
		agents.add(ag);
	}
	
	public void leave(Agent ag){
		agents.remove(ag);
	}
	
	private void makeMove(int player, Tile t)
	{
		if(t==null&&MoreArrays.isEmpty(board.handTile(player))){
			updatePlayers();
			for(int i=0;i<board.playerNum;i++){
				if(!died[i]){
					agents.get(i).display("You are one of the winner! (�?�･ω･)�?�");
					while(true);
				}
			}
			while(true);
		}
		
		Tile[] tiles=board.handTile(player);
		int i;
		for(i=0;i<tiles.length;i++){
			Tile tt=tiles[i];
			if(tt==null){
				continue;
			}
			if(tt==t||tt.rotate90()==t||tt.rotate180()==t||tt.rotate270()==t){
				tiles[i]=null;
				break;
			}
		}
		if(i==tiles.length){
			throw new RuntimeException("Cheat detected");
		}
		
		Position pos=GameHelper.getConn(board, board.startingPoint(player));
		board.put(pos.x, pos.y, t);
		
		updatePlayers();
	}
	
	private void updatePlayers() {
		int alive=0;
		for(int i=0;i<board.playerNum;i++){
			if(died[i])continue;
			if(!GameHelper.getConn(board, board.startingPoint(i)).onBoard){
				agents.get(i).display("You are not likely to win ლ(╹◡╹ლ)");
				died[i]=true;
				for(Tile t:board.handTile(i)){
					if(t!=null){
						board.deck.add(t);
					}
				}
				Collections.shuffle(board.deck);
			}else{
				alive++;
			}
		}
		updateAll();
		if(alive==1){
			for(int i=0;i<board.playerNum;i++){
				if(!died[i]){
					agents.get(i).display("You are the winner! (っ･ω･)っ");
					agents.get(i).update();
					while(true);
				}
			}
		}
	}
	
	public void updateAll(){
		for(int j=0;j<agents.size();j++){
			agents.get(j).update();
		}
	}

	public void start(){
		if(agents.size()<lowerAgentsLimit){
			throw new RuntimeException("You need at least "+lowerAgentsLimit+" players to start game.");
		}
		
		board=new GameBoard(agents.size());
		
		died=new boolean[agents.size()];
		
		for(int i=0;i<agents.size();i++){
			board.startingPoint[i]=agents.get(i).getStartingPoint();
			board.colors[i]=agents.get(i).getColor();
			
			board.draw(i);
			board.draw(i);
			board.draw(i);
		}
		
		for(int i=0;i<agents.size();i++){
			agents.get(i).setGameBoardView(new DirectGameBoardView(board, i));
		}
		
		while(true){
			for(int i=0;i<agents.size();i++){
				if(died[i])continue;
				
				updateAll();
				
				agents.get(i).display("It's your turn (〜￣▽￣)〜");
				makeMove(i, agents.get(i).requestMove());
				
				if(died[i])continue;
					
				agents.get(i).display(" ");
				board.draw(i);
			}
		}
		
		
	}

	public int playerNum() {
		return agents.size();
	}
	
}
