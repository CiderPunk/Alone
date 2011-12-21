package net.ciderpunk.Alone.Map;

import java.util.List;
import java.util.Random;

import net.ciderpunk.GameBase.entities.LinkedEntity;

public class Room extends LinkedEntity {

	protected MapCoord Coord;
	protected int  Width, Height;
	protected Map Owner;
	protected List<Connection> lConnections;
	
	public Room(MapCoord oCoord, Map oOwner, Room oParent, EDirection dir){
		this(oCoord, oOwner);
		this.addConnect(oParent,oCoord,dir);
	}
	
	
	
	public Room(MapCoord oCoord, Map oOwner){
		Coord = oCoord;
		Width = 1;
		Height = 1;
		oOwner.addRoom(this);
	}
	
	
	protected void growVertical(EDirection iDir){
		//get next row to expand to
		int iYPos = (iDir == EDirection.Up ? Coord.Y - 1 : Coord.Y + Height + 1);
		for(int i = Coord.X; i < Coord.X + Width; i++){
			if (Owner.getRoom(i, iYPos) != null){
				return;
			}
		}
		Height++;
		if (iDir == EDirection.Up){
			Coord.Y--;
		}
	}
	
	
	
	protected void growHorizontal(EDirection iDir){
		//get next row to expand to
		int iXPos = (iDir == EDirection.Left ? Coord.getX() -1 : Coord.getX() + Width + 1);
		for(int i = Coord.Y; i < Coord.Y + Width; i++){
			if (Owner.getRoom(iXPos, i) != null){
				return;
			}
		}
		Width++;
		if (iDir == EDirection.Left){
			Coord.X--;
		}
	}
	
	protected void addFeature(MapCoord oCoord, EDirection iDir, int iDepth){
		Room oRoom = Owner.getRoom(iDestX, iDestY);
		if (oRoom == null){
			oRoom = new Room(iDestX,iDestY,Owner,this, iDir);
			this.addConnect(oRoom, iX, iY, iDir);
			oRoom.Grow(iDepth < 4 ? 10: 8);
			oRoom.Connect((iDepth < 3 ? 1: 0), 1, iDepth + 1);
		}
		else{
			this.addConnect(oRoom, iX, iY, iDir);
			oRoom.addConnect(this, iDestX, iDestY, Direction.OppositeDir(iDir));
		}
		
	}
		
	protected void addConnect(Room oRoom, MapCoord oCoord, EDirection iDir){
		lConnections.add(new Connection(oRoom, oCoord, Direction.OppositeDir(iDir) ));
	}

	
	
	public void Connect(int iMinConnects, int iMaxConnects, int iDepth){
		Random oRnd = new Random();
		int iConnects = oRnd.nextInt(iMaxConnects) + iMinConnects;
		for (int i = 0; i < iConnects; i++){
			EDirection iDir = Direction.getDirection(oRnd.nextInt(3));
			switch(iDir){
				case Up:
					addFeature(new MapCoord(Coord.X + oRnd.nextInt(Width), Coord.Y) , iDir, iDepth);
					break;
				case Down:
					addFeature(new MapCoord(Coord.X + oRnd.nextInt(Width), Coord.Y  + Height) , iDir, iDepth);
					break;
				case Left:
					addFeature(new MapCoord(Coord.X , Coord.Y  + oRnd.nextInt(Height)) , iDir, iDepth);
					break;
				case Right:
					addFeature(X + Width + 1, Y + oRnd.nextInt(Height), iDir);
					break;
				
			}
		}
	}
	
	public void Grow(int iGrowChance){
		Random oRnd = new Random();
		while(oRnd.nextInt(10) < iGrowChance){
			iGrowChance--;
			EDirection iDir = Direction.getDirection(oRnd.nextInt(4));
			switch(iDir){
				case Up:
				case Down:
					growVertical(iDir);
					break;
				case Left:
				case Right:
					growHorizontal(iDir);
					break;
			}
		}
	}
	


	public Boolean Contains(int x, int y){
		if (y >= Coord.Y && y  <= Coord.Y + Height){
			if (x >= Coord.X && x <= Coord.X + Width){
				return true;
			}
		}
		return false;
	}
	
	
}
