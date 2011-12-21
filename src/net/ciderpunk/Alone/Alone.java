package net.ciderpunk.Alone;

import java.awt.Dimension;
import java.awt.Graphics2D;

import net.ciderpunk.Alone.GameState.AloneGame;
import net.ciderpunk.GameBase.IGameState;

public class Alone extends net.ciderpunk.GameBase.Screen implements Runnable{

	protected Alone(){

		
	}
	

	public void run() {
		IGameState oState = new AloneGame();
		oState.init();
		
		RunMainLoop(oState);
		
	}

	
	
}
