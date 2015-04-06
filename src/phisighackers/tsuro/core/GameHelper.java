package phisighackers.tsuro.core;


public class GameHelper {
	GameBoard board;
	/*public static Tuple._2<Tuple._2<Integer, Integer>, Byte> getOneStepConnection(int startPos){
		if(startPos<12){
			return Tuple.as(Tuple.as(startPos/2, 0), (byte)(startPos%2));
		}else if(startPos<24){
			startPos-=12;
			return Tuple.as(Tuple.as(5, startPos/2), (byte)(startPos%2+2));
		}else if(startPos<36){
			startPos-=24;
			return Tuple.as(Tuple.as(5-startPos/2, 5), (byte)(startPos%2+4));
		}else{
			startPos-=36;
			return Tuple.as(Tuple.as(0, 5-startPos/2), (byte)(startPos%2+6));
		}
	}*/
	
	public static Position getConnPos(int startPos){
		if(startPos<12){
			return new Position(startPos/2, 0, (byte)(startPos%2));
		}else if(startPos<24){
			startPos-=12;
			return new Position(5, startPos/2, (byte)(startPos%2+2));
		}else if(startPos<36){
			startPos-=24;
			return new Position(5-startPos/2, 5, (byte)(startPos%2+4));
		}else{
			startPos-=36;
			return new Position(0, 5-startPos/2, (byte)(startPos%2+6));
		}
	}
	
	public static Position getInTileConn(GameBoardView board, Position pos){
		Tile t=board.get(pos.x, pos.y);
		if(t==null){
			return null;
		}
		return new Position(pos.x, pos.y, t.connection[pos.pin]);
	}
	
	public static Position getConnPin(GameBoardView board, Position pos){
		int nx=pos.x+connTableX[pos.pin];
		int ny=pos.y+connTableY[pos.pin];
		byte pin=connTablePin[pos.pin];
		
		if(nx<0){
			return new Position((byte)(40+pos.pin-2*pos.y));
		}else if(nx>5){
			return new Position((byte)(10+pos.pin+2*pos.y));
		}else if(ny<0){
			return new Position((byte)(pos.pin+2*pos.x));
		}else if(ny>5){
			return new Position((byte)(30+pos.pin-2*pos.x));
		}else{
			return new Position(nx, ny, pin);
		}
	}
	
	public static Position getConn(GameBoardView view, int startPos){
		Position pos=getConnPos(startPos);
		while(pos.onBoard){
			Position newpos=getInTileConn(view, pos);
			if(newpos==null){
				return pos;
			}
			pos=getConnPin(view, newpos);
		}
		return pos;
	}
	
	static byte[] connTableX={0, 0, 1, 1, 0, 0, -1, -1};
	static byte[] connTableY={-1, -1, 0, 0, 1, 1, 0, 0};
	static byte[] connTablePin={5, 4, 7, 6, 1, 0, 3, 2};
	/*
	public static Tuple._2<Tuple._2<Integer, Integer>, Byte> getOneStepConnection(GameBoardView board, Tuple._2<Tuple._2<Integer, Integer>, Byte> pos){
		Tile t=board.get(pos._1._1, pos._1._2);
		if(t==null){
			return null;
		}
		byte c=t.connection[pos._2];
		return Tuple.as(Tuple.as(pos._1._1+connTableX[c], pos._1._2+connTableY[c]), connTablePin[c]);
	}
	
	public static Tuple._2<Tuple._2<Integer, Integer>, Byte> getConnection(GameBoardView board, int startPos){
		Tuple._2<Tuple._2<Integer, Integer>, Byte> pos=getOneStepConnection(startPos);
		Tuple._2<Tuple._2<Integer, Integer>, Byte> newpos;
		while((newpos=getOneStepConnection(board, pos))!=null){
			if(newpos._1._1>=0&&newpos._1._1<=5&&newpos._1._2>=0&&newpos._1._2<=5){
				pos=newpos;
			}else{
				return null;
			}
		}
		return pos;
	}*/
	
}
