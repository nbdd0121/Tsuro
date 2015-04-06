package phisighackers.tsuro.agent.ai;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

import phisighackers.tsuro.core.Tile;

public class MonteCarloSimulation {
	
	GameBoardViewSimulation view;
	LinkedList<Tile> unseenTiles=new LinkedList<>(Arrays.asList(Tile.allTiles));
	//Tile[] myTile;
	int current;
	Random rand=new Random();
	
	public MonteCarloSimulation(GameBoardViewSimulation v){
		this.view=new GameBoardViewSimulation(v);
		for(Tile[] t:view.tile){
			for(Tile tt:t){
				if(tt!=null){
					removeTile(tt);
				}
			}
		}
		/*for(Tile t:view.handTiles()){
			if(t!=null){
				removeTile(t);
			}
		}*/
		current=view.id();
		//myTile=Arrays.copyOf(view.handTiles(), 3);
	}

	private void removeTile(Tile t) {
		unseenTiles.remove(t);
		unseenTiles.remove(t.rotate90());
		unseenTiles.remove(t.rotate180());
		unseenTiles.remove(t.rotate270());
	}
	
	public void runOnce(){
		int index=rand.nextInt(unseenTiles.size());
		Tile t=unseenTiles.remove(index);
		switch(rand.nextInt(4)){
			case 1:
				t=t.rotate90();
				break;
			case 2:
				t=t.rotate180();
				break;
			case 3:
				t=t.rotate270();
				break;
		}
		view.put(current, t);
		current=(current+1)%view.playerNum();
	}
	
	public int simulate()
	{
		int c=0;
		while(true){
			runOnce();
			if(view.lose())
			{
				return c;
			}
			if(view.win()||unseenTiles.isEmpty()){
				return 100;
			}

			c++;
		}
	}
}
