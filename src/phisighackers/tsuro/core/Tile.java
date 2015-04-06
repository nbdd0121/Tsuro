package phisighackers.tsuro.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Tile implements Serializable {
	
	private static final long serialVersionUID = -2448402153320715096L;
	
	
	public static final Tile[] allTiles;
	public static final ArrayList<Tile> allTilesRotated=new ArrayList<>();
	
	
	static{
		byte[][] conn={
			{4, 6, 5, 7, 0, 2, 1, 3},
			{3, 2, 1, 0, 7, 6, 5, 4},
			{4, 7, 6, 5, 0, 3, 2, 1},
			{7, 5, 6, 4, 3, 1, 2, 0},
			{6, 3, 4, 1, 2, 7, 0, 5},
			{2, 4, 0, 7, 1, 6, 5, 3},
			
			{7, 6, 4, 5, 2, 3, 1, 0},
			{4, 5, 6, 7, 0, 1, 2, 3},
			{2, 7, 0, 4, 3, 6, 5, 1},
			{2, 5, 0, 4, 3, 1, 7, 6},
			{7, 3, 5, 1, 6, 2, 4, 0},
			{7, 6, 3, 2, 5, 4, 1, 0},
			
			{1, 0, 3, 2, 5, 4, 7, 6},
			{3, 6, 5, 0, 7, 2, 1, 4},
			{5, 4, 7, 6, 1, 0, 3, 2},
			{4, 2, 1, 5, 0, 3, 7, 6},
			{4, 3, 5, 1, 0, 2, 7, 6},
			
			{5, 3, 6, 1, 7, 0, 2, 4},
			{6, 7, 4, 5, 2, 3, 0, 1},
			{3, 5, 4, 0, 2, 1, 7, 6},
			{6, 4, 7, 5, 1, 3, 0, 2},
			{1, 0, 7, 4, 3, 6, 5, 2},
			{1, 0, 6, 7, 5, 4, 2, 3},
			
			{7, 2, 1, 4, 3, 6, 5, 0},
			{4, 5, 7, 6, 0, 1, 3, 2},
			{1, 0, 4, 6, 2, 7, 3, 5},
			{6, 7, 3, 2, 5, 4, 0, 1},
			{1, 0, 7, 5, 6, 3, 4, 2},
			{3, 6, 4, 0, 2, 7, 1, 5},
			
			{5, 4, 3, 2, 1, 0, 7, 6},
			{7, 4, 5, 6, 1, 2, 3, 0},
			{4, 2, 1, 6, 0, 7, 3, 5},
			{2, 5, 0, 7, 6, 1, 4, 3},
			{1, 0, 5, 6, 7, 2, 3, 4},
			{6, 4, 5, 7, 1, 2, 0, 3},
			
		};
		
		allTiles=new Tile[conn.length];
		
		for(int i=0;i<conn.length;i++){
			allTiles[i]=new Tile(conn[i]);
		}
		
		
	}
	
	public byte[] connection;
	public int index;
	public Tile deg90;
	
	/**
	 * Create a tile object using given connection
	 * @param conn connection between pins
	 */
	public Tile(byte[] conn){
		connection=conn;
		index=allTilesRotated.size();
		allTilesRotated.add(this);
		
		byte[] _90=genClockwise(conn);
		if(Arrays.equals(conn, _90)){
			deg90=this;
			return;
		}else{
			deg90=new Tile(_90, false);
		}
		
		byte[] _180=genClockwise(_90);
		if(Arrays.equals(conn, _180)){
			deg90.deg90=this;
			return;
		}else{
			deg90.deg90=new Tile(_180, false);
		}
		
		byte[] _270=genClockwise(_180);
		if(Arrays.equals(_90, _270)){
			deg90.deg90.deg90=deg90;
			return;
		}else{
			deg90.deg90.deg90=new Tile(_270, false);
			deg90.deg90.deg90.deg90=this;
		}
		
	}
	
	public Tile(byte[] conn, boolean b){
		connection=conn;
		
		index=allTilesRotated.size();
		allTilesRotated.add(this);
	}
	
	private byte genClockwise(byte b){
		return (byte)((b+2)%8);
	}
	
	private byte[] genClockwise(byte[] connection){
		byte[] con=new byte[8];
		for(byte i=0;i<8;i++){
			con[genClockwise(i)]=genClockwise(connection[i]);
		}
		return con;
	}
	
	public Tile rotate90(){
		return deg90;
	}
	
	public Tile rotate180(){
		return deg90.deg90;
	}
	
	public Tile rotate270(){
		return deg90.deg90.deg90;
	}
	
	
	public int getIndex(){
		return index;
	}
	
	public static Tile valueOf(int index){
		return allTilesRotated.get(index);
	}

	private Object writeReplace() {
		return new TileSerialized(index);
	}
	
	private static class TileSerialized implements Serializable{
		
		private static final long serialVersionUID = 7932328284161989506L;
		
		int id;
		
		public TileSerialized(int i){
			id=i;
		}
		
		private Object readResolve() {
			return valueOf(id);
		}
	}
	
}
