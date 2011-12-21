package net.ciderpunk.Alone.Map;

import java.util.*;

public class Map {

	protected List<Room> lRooms;
	Room oHubRoom;
	
	
	public void generateMap(){
		oHubRoom = new Room(0, 0, this);
		oHubRoom.Grow(12);
		oHubRoom.Connect(2,2, 0);
	}
	
	
	public void addRoom(Room oRoom){
		lRooms.add(oRoom);
	}
	
	
	public Room getRoom(int iX, int iY){
		Iterator<Room> oIt = lRooms.iterator();
		while(oIt.hasNext()){
			Room oRoom = oIt.next();
			if (oRoom.Contains(iX, iY)){
				return oRoom;
			}
		}
		return null;
		
		
	}
	
	
}
