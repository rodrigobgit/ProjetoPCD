package game;

import environment.Cell;
import environment.Coordinate;
import environment.Direction;

public class BotPlayer extends Player {
	private long interval;
	private int move=0;
	
	public BotPlayer(int id, Game game, byte strength,int debuffMultiplier) {
		super(id, game, strength,debuffMultiplier);
		interval=game.REFRESH_INTERVAL;
		
	}
	
	public void run() {
		
		while(true) {
			if(move==1) {
				try {
					sleep(interval*getDebuffMultiplier());
				
					rollDice();					
				
				
				} catch (InterruptedException e) {
				
					e.printStackTrace();
				}
			
			}
			
		
		
		
		
		}
	}
	public void rollDice() {
		//escolha da direção para mover
		double random=Math.random();
		Direction direction = null;
		
		if (random<0.25) {
			direction=environment.Direction.UP;}
			
		if (random<0.50 && random>0.25) {
			direction=environment.Direction.DOWN;}
		
		if (random<0.75 && random>0.5) {
			direction=environment.Direction.LEFT;}
			
		if (random<1 && random>0.75) {
			direction=environment.Direction.RIGHT;}
			
			
		
		movePlayer(direction, getCurrentCell());
	}
	public void movePlayer(Direction dir,Cell cell) {
		
		//calculo da coordenada da proxima cell		
		Coordinate atual=cell.getPosition();
		Coordinate next=atual.translate(dir.getVector());
		
		//verificar se está dentro do board		
		if(next.getX()>=0 && next.getY()>=0 && next.getX()<30 && next.getY()<30) {
			Cell nextCell=game.getCell(next);
			//mete a proxima cell a ter este jogador,muda a cell atual do jogador para a próxima e apaga o seu registo na cell de que vai sair
			cell.clear();
			nextCell.put(this);
			setCurrentCell(nextCell);
			
			
						
			game.notifyChange();
		}
		else {
			//se estiver nos limites do board, é dada uma segunda chance para mover em vez de estar à espera do REFRESH_INTERVAL
			rollDice();
		}
		
		
		
		
	}
	
	public void setMove(int move) {
		this.move = move;
	}
	
	
	
	
	public boolean isHumanPlayer() {
		return false;
	}
}