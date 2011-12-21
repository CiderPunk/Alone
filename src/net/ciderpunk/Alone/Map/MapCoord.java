package net.ciderpunk.Alone.Map;

public class MapCoord {
	
	protected int X,Y;
	
	public MapCoord(int x, int y){
		X = x;
		Y = y;
	}
	
	public MapCoord Move(EDirection iDir){
		switch(iDir){
			case Up:
				return new MapCoord(this.getX(), this.getY() - 1);
			case Down:
				return new MapCoord(this.getX(), this.getY() + 1);
			case Left:
				return new MapCoord(this.getX() - 1, this.getY());
			default:
				return new MapCoord(this.getX() + 1, this.getY());
		}
	}
	
	public synchronized int getX() {
		return X;
	}


	public synchronized void setX(int x) {
		X = x;
	}


	public synchronized int getY() {
		return Y;
	}


	public synchronized void setY(int y) {
		Y = y;
	}


	
}
