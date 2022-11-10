package game;


import java.util.EventListener;
import java.util.Observable;
import environment.Cell;
import environment.Coordinate;

public class Game extends Observable {

	public static final int DIMY = 30;
	public static final int DIMX = 30;
	private static final int NUM_PLAYERS = 150;
	private static final int NUM_FINISHED_PLAYERS_TO_END_GAME=3;

	public static final long REFRESH_INTERVAL = 100;
	public static final double MAX_INITIAL_STRENGTH = 3;
	public static final long MAX_WAITING_TIME_FOR_MOVE = 2000;
	public static final long INITIAL_WAITING_TIME = 10000;

	protected Cell[][] board;

	public Game() {
		board = new Cell[Game.DIMX][Game.DIMY];
	
		for (int x = 0; x < Game.DIMX; x++) 
			for (int y = 0; y < Game.DIMY; y++) 
				board[x][y] = new Cell(new Coordinate(x, y),this);
	}
	
	/** 
	 * @param player 
	 */
	public void addPlayerToGame(Player player) {
		
		Cell initialPos=getRandomCell();
		
		initialPos.put(player);
		player.setCurrentCell(initialPos);
			if(!player.isHumanPlayer())
				player.setMove(1);
		notifyChange();
		System.out.println("Sou o jogador " + player.getIdentification()+ " e fiquei parado nas coordenadas " + initialPos.getPosition().toString()+ "por causa do jogador " + initialPos.getPlayer().getIdentification());
		
		
					
				 
				
			
		
			
		
		
		
		
		// To update GUI
		
		
	}

	public Cell getCell(Coordinate at) {
		return board[at.x][at.y];
	}

	/**	
	 * Updates GUI. Should be called anytime the game state changes
	 */
	public void notifyChange() {
		setChanged();
		notifyObservers();
	}

	public Cell getRandomCell() {
		Cell newCell=getCell(new Coordinate((int)(Math.random()*Game.DIMX),(int)(Math.random()*Game.DIMY)));
		return newCell; 
	}
	
	
	public int maxPlayers() {
		return NUM_PLAYERS;
	}
	
}