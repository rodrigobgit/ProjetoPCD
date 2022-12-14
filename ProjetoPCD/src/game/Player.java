package game;



import environment.Cell;
import environment.Coordinate;
import environment.Direction;

/**
 * Represents a player.
 * @author luismota
 *
 */
public abstract class Player extends Thread  {


	protected  Game game;
	private Cell current;
	private int id;
	private int debuffMultiplier;
	private byte currentStrength;
	protected byte originalStrength;

	
	

	public Player(int id, Game game, byte strength,int debuffMultiplier) {
		super();
		this.debuffMultiplier=debuffMultiplier;
		this.id = id;
		this.game=game;
		currentStrength=strength;
		originalStrength=strength;
	}

	public abstract boolean isHumanPlayer();
	
	@Override
	public String toString() {
		return "Player [id=" + id + ", currentStrength=" + currentStrength + ", getCurrentCell()=" + getCurrentCell()
		+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public byte getCurrentStrength() {
		return currentStrength;
	}


	public int getIdentification() {
		return id;
	}
	public Cell getCurrentCell() {
		return current;
	}
	public void setCurrentCell(Cell cell) {
		this.current=cell;
	}
	public int getDebuffMultiplier() {
		return debuffMultiplier;
	}
	public void setMove(int move) {
		
	}
}
