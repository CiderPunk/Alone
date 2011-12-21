package net.ciderpunk.Alone.Map;

import java.util.Random;

public class Connection {
	
	Room oConnectedRoom;
	MapCoord oCoord;
	EDirection Dir;
	
	Connection(Room oRoom, MapCoord coord, EDirection dir){
		oConnectedRoom = oRoom;
		oCoord = coord;
		Dir = dir;
	}

	public synchronized Room getConnectedRoom() {
		return oConnectedRoom;
	}


	public synchronized MapCoord getCoord() {
		return oCoord;
	}


	public synchronized EDirection getDir() {
		return Dir;
	}
		
}
