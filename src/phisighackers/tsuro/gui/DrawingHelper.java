package phisighackers.tsuro.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import phisighackers.tsuro.core.Tile;

public class DrawingHelper {

	static double[] xMapping={1./3, 2./3, 1, 1, 2./3, 1./3, 0, 0};
	static double[] yMapping={0, 0, 1./3, 2./3, 1, 1, 2./3, 1./3};
	
	public static void drawTile(Graphics2D g, Dimension dim, Tile tile){
		g.setColor(new Color(102, 54, 26));
		g.fillRect(0, 0, dim.width, dim.height);
		
		g.setColor(Color.WHITE);
		
		int width=dim.width-2;
		int height=dim.height-2;
		
		for(int i=0;i<tile.connection.length;i++){
			int id=tile.connection[i];
			
			g.drawLine((int)Math.round(xMapping[i]*width)+1, (int)Math.round(yMapping[i]*height)+1,
					(int)Math.round(xMapping[id]*width)+1, (int)Math.round(yMapping[id]*height)+1);
		}
		
		g.setColor(Color.BLACK);
		
		g.drawLine(0, 0, 0, height+2);
		g.drawLine(0, 0, width+2, 0);
	}
	
}
