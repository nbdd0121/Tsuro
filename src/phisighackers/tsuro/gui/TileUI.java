package phisighackers.tsuro.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

import phisighackers.tsuro.core.Tile;

public class TileUI extends JComponent{
	
	private static final long serialVersionUID = -6613061054014294245L;
	
	static double[] xMapping={1./3, 2./3, 1, 1, 2./3, 1./3, 0, 0};
	static double[] yMapping={0, 0, 1./3, 2./3, 1, 1, 2./3, 1./3};
	
	public Tile tile;
	
	public TileUI(final MouseListener ml){
		setOpaque(false);
		if(ml!=null)addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1){
					ml.mouseClicked(e);
				}else{
					tile=tile.rotate90();
					repaint();
				}
			}
			
		});
	}
	
	public Dimension getPreferredSize(){
		return new Dimension(100, 100);
	}
	
	public Dimension getMinimumSize(){
		return new Dimension(100, 100);
	}
	
	public void paintComponent(Graphics gp){
		if(tile==null){
			return;
		}
		DrawingHelper.drawTile((Graphics2D) gp, getSize(), tile);
	
	}

}

