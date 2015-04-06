package phisighackers.tsuro.core;

public class Position {
	public boolean onBoard;
	
	public int x;
	public int y;
	
	public byte pin;
	
	public Position(int x, int y, byte pin){
		this.x=x;
		this.y=y;
		this.pin=pin;
		onBoard=true;
	}
	
	public Position(byte pin){
		this.pin=pin;
		onBoard=false;
	}
}
