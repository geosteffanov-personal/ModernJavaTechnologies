package tictactoe;
public class Player {
	private String name;
	private String usedSymbol;

	public Player(String name, String usedSymbol) {
		this.name = name;
		this.usedSymbol = usedSymbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsedSymbol() {
		return usedSymbol;
	}

	public void setUsedSymbol(String usedSymbol) {
		this.usedSymbol = usedSymbol;
	}

}
