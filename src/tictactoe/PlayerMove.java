package tictactoe;

public class PlayerMove {
	private Player player;
	private int xCoord;
	private int yCoord;

	public PlayerMove(Player player, int xCoord, int yCoord) {
		this.player = player;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}

	public Player getPlayer() {
		return player;
	}

	public int getXCoord() {
		return xCoord;
	}

	public int getYCoord() {
		return yCoord;
	}

	public String toString() {
		return player.getName() + ":: moves at: " + xCoord + ", " + yCoord;
	}

}
