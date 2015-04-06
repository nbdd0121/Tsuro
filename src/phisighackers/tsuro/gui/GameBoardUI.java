package phisighackers.tsuro.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import phisighackers.tsuro.core.GameBoardView;
import phisighackers.tsuro.core.GameHelper;
import phisighackers.tsuro.core.Position;
import phisighackers.tsuro.core.Tile;

public class GameBoardUI extends JComponent {

	private static final long serialVersionUID = -718907939774886575L;
	private static BufferedImage background;
	static
	{
		try {
			background=ImageIO.read(new File("res/TsuroBoardGame.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	GameBoardView board;
	//TileUI[][] tile=new TileUI[6][6];

	public GameBoardUI(GameBoardView board){
		this.board=board;

		/*for(int i=0;i<6;i++){
			for(int j=0;j<6;j++){
				//TileUI t=new TileUI();
				//tile[i][j]=t;
			}
		}*/

	}

	public Dimension getPreferredSize(){
		return new Dimension(500, 500);
	}



	/*public void update(){
		for(int i=0;i<6;i++){
			for(int j=0;j<6;j++){
				//tile[i][j].tile=board.get(i, j);
			}
		}
	}*/

	public void paint(Graphics g){
		paintComponent(g);
		paintChildren(g);

		int rad=Math.min(getWidth(), getHeight());
		int margin=rad/20;
		rad-=margin;

		for(int i=0;i<board.playerNum();i++){
			int sp=board.startingPoint(i);

			Position ret=GameHelper.getConnPos(sp);

			int x=ret.x*rad/6+margin/2;
			int y=ret.y*rad/6+margin/2;
			x+=Math.round(rad/6*TileUI.xMapping[ret.pin]);
			y+=Math.round(rad/6*TileUI.yMapping[ret.pin]);

			g.setColor(board.color(i));
			g.fillOval(x-10, y-10, 18, 18);

			g.setColor(Color.BLACK);
			g.drawOval(x-5, y-5, 10, 10);
			g.drawOval(x-10, y-10, 18, 18);
		}

		for(int i=0;i<board.playerNum();i++){
			int sp=board.startingPoint(i);

			Position ret=GameHelper.getConn(board, sp);
			if(!ret.onBoard){
				ret=GameHelper.getConnPos(ret.pin);
			}
			int x=ret.x*rad/6+margin/2;
			int y=ret.y*rad/6+margin/2;
			x+=Math.round(rad/6*TileUI.xMapping[ret.pin]);
			y+=Math.round(rad/6*TileUI.yMapping[ret.pin]);

			g.setColor(board.color(i));
			g.fillOval(x-10, y-10, 18, 18);

			g.setColor(Color.BLACK);
			g.drawOval(x-10, y-10, 18, 18);
		}
	}

	public void paintChildren(Graphics g){
		//update();
		//super.paintChildren(g);
		int rad=Math.min(getWidth(), getHeight());
		int margin=rad/20;
		rad-=margin;

		for(int i=0;i<6;i++){
			for(int j=0;j<6;j++){
				int w=rad/6;
				int left=rad*i/6+margin/2;
				int top=rad*j/6+margin/2;
				//tile[i][j].setSize(w, w);
				Tile tile=board.get(i, j);
				if(tile!=null)
					DrawingHelper.drawTile((Graphics2D) g.create(left, top, w, w), new Dimension(w, w), tile);
				//tile[i][j].paint(g.create(left, top, left+w, top+w));
			}
		}
		//g.create(x, y, width, height)
	}

	public void paintComponent(Graphics gp){



		int rad=Math.min(getWidth(), getHeight());
		//int rad = 890;
		gp.drawImage(background, 0, 0, rad, rad, null);
		gp.setColor(Color.BLACK);

		/*for(int i=0; i<=18; i++)
		{
			if(i%3==0)
			{
				if(i==18)
				{
					rad--;
				}
				gp.drawLine(0, rad*i/18, rad, rad*i/18);
				gp.drawLine(rad*i/18, 0, rad*i/18, rad);
			}
			else
			{
				gp.drawLine(0, rad*i/18, 10, rad*i/18);
				gp.drawLine(rad, rad*i/18, rad-10, rad*i/18);

				gp.drawLine(rad*i/18, 0, rad*i/18, 10);
				gp.drawLine(rad*i/18, rad, rad*i/18, rad-10);
			}
		}*/
	}


}
