package tictactoe;
public class Field {
	private String symbol;
	private boolean marked;

	Field(String symbol) {
		this.symbol = symbol;
		this.marked = false;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public boolean getMarked() {
		return this.marked;
	}

	public void setMarked(boolean marked) {
		this.marked = marked;
	}

}
