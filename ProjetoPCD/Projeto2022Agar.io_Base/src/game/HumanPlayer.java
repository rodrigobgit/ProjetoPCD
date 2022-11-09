package game;

import environment.Cell;
import environment.Coordinate;
import environment.Direction;

/**
 * Class to demonstrate a player being added to the game.
 * @author luismota
 *
 */
public class HumanPlayer extends Player {
	private Direction nextDirection;
	private int move;
	private long interval=Game.REFRESH_INTERVAL;
	public HumanPlayer(int id, Game game, byte strength,int debuffMultiplier) {
		super(id, game, strength,debuffMultiplier);
	}
	
	public void run() {
		while(true)
			try {
				sleep(interval*getDebuffMultiplier());
				
				if(move==1) {
					
					movePlayer(nextDirection, getCurrentCell());
				}
				else {
					
				}
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
	}
	
	
	public void movePlayer(Direction dir,Cell cell) {
		
		//calculo da coordenada da proxima cell	
		Coordinate atual=cell.getPosition();
		Coordinate next=atual.translate(dir.getVector());
	
	
		//verificar se está dentro do board		
		if(next.getX()>=0 && next.getY()>=0 && next.getX()<30 && next.getY()<30) {
			Cell nextCell=game.getCell(next);
			//mete a proxima cell a ter este jogador,muda a cell atual do jogador para a próxima e apaga o seu registo na cell de que vai sair
			nextCell.setPlayer(this);
			setCurrentCell(nextCell);
			cell.setPlayer(null);				
			game.notifyChange();
			setMove(0);	
		}
	}
	
	
	public void setNextDirection(Direction direction) {
		this.nextDirection=direction;
		setMove(1);
	}
	public void setMove(int move) {
		this.move = move;
	}
	public int getMove() {
		return move;
	}
	
	
	public boolean isHumanPlayer() {
		return true;
	}
}
