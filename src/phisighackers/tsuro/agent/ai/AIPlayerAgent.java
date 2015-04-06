package phisighackers.tsuro.agent.ai;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import phisighackers.tsuro.core.Agent;
import phisighackers.tsuro.core.CachedGameBoardView;
import phisighackers.tsuro.core.GameBoardView;
import phisighackers.tsuro.core.Tile;
import phisighackers.tsuro.gui.GameUI;

public class AIPlayerAgent implements Agent {

	CachedGameBoardView view;
	GameUI ui;
	Color color;
	ExecutorService pool=Executors.newFixedThreadPool(4);
	
	{
		Random rand=new Random();
		color=new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
	}
	
	@Override
	public int getStartingPoint() {
		return new Random().nextInt(36);
	}

	@Override
	public void setGameBoardView(GameBoardView gbv) {
		view=new CachedGameBoardView(gbv);
		ui=new GameUI(view, null, color);
		ui.setTitle("Tsuro - Controlled by AI");
	}

	@Override
	public Tile requestMove() {
		Tile[] hand=view.handTiles();
		HashSet<Tile> ts=new HashSet<>();
		for(Tile t:hand){
			if(t!=null){
				ts.add(t);
				ts.add(t.rotate90());
				ts.add(t.rotate180());
				ts.add(t.rotate270());
			}
		}
		if(ts.isEmpty()){
			return null;
		}
		
		Tile[] tiles=ts.toArray(new Tile[ts.size()]);
		ArrayList<ArrayList<Future<Integer>>> futures=new ArrayList<>();
		for(final Tile t:tiles){
			ArrayList<Future<Integer>> fl=new ArrayList<>();
			for(int i=0;i<8;i++){
				fl.add(pool.submit(new Callable<Integer>(){
					@Override
					public Integer call() throws Exception {
						GameBoardViewSimulation cloned=new GameBoardViewSimulation(view);
						cloned.put(t);
						if(!cloned.lose())
						{
							int counter=0;
							for(int i=0;i<2048;i++)
							{
								counter+=new MonteCarloSimulation(cloned).simulate();
							}
							return counter;
						}
						return -1;
					}
				}));
			}
			futures.add(fl);
		}
		
		Tile tile=null;
		int round=Integer.MIN_VALUE;
		
		for(int i=0;i<tiles.length;i++){
			int result=0;
			for(Future<Integer> f:futures.get(i)){
				try {
					result+=f.get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
			if(result>round){
				tile=tiles[i];
				round=result;
			}
		}
		
		if(tile==null){
			return ts.iterator().next();
		}
		return tile;
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
