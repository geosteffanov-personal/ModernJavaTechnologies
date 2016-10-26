package tictactoe;

public class Board {
	public static final int DEFAULT_SIZE = 3;

	private Field[][] board;
	private int size;

	private boolean checkMainDiagonal() {
		for (int i = 0; i < size - 1; i++) {
			String firstSymbol = board[i][i].getSymbol();
			String secondSymbol = board[i + 1][i + 1].getSymbol();

			if (!firstSymbol.equals(secondSymbol)) {
				return false;
			}

		}
		return true;

	}

	private boolean checkSecondaryDiagonal() {
		for (int i = 0; i < size - 1; i++) {
			String firstSymbol = board[i][size - i - 1].getSymbol();
			String secondSymbol = board[i + 1][size - i - 2].getSymbol();

			if (!firstSymbol.equals(secondSymbol)) {
				return false;
			}
		}

		return true;
	}

	private boolean checkColumn(int yCoord) {
		for (int i = 0; i < size - 1; i++) {
			String firstSymbol = board[i][yCoord].getSymbol();
			String secondSymbol = board[i + 1][yCoord].getSymbol();

			if (!firstSymbol.equals(secondSymbol)) {
				return false;
			}
		}
		return true;
	}

	private boolean checkRow(int xCoord) {
		for (int j = 0; j < size - 1; j++) {
			String firstSymbol = board[xCoord][j].getSymbol();
			String secondSymbol = board[xCoord][j + 1].getSymbol();

			if (!firstSymbol.equals(secondSymbol)) {
				return false;
			}
		}
		return true;
	}

	private boolean hasWon(int xCoord, int yCoord) {
		if (xCoord == yCoord && checkMainDiagonal()) {
			return true;
		}
		if (xCoord + yCoord == size - 1 && checkSecondaryDiagonal()) {
			return true;
		}
		return checkRow(xCoord) || checkColumn(yCoord);
	}

	public boolean isFilled() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if(!board[i][j].getMarked()) {
					return false;
				}
			}
		}
		return true;
	}
	
	private void initializeEmptyFields() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j] = new Field(new Integer(i * size + j + 1).toString());
			}
		}
	}

	public Board() {
		this.size = DEFAULT_SIZE;

		board = new Field[size][size];
		initializeEmptyFields();
	}

	public Board(int size) {
		this.size = size;

		board = new Field[size][size];
		initializeEmptyFields();
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (j == 0) {
					result.append(" " + board[i][j].getSymbol());
				} else {
					result.append(" | " + board[i][j].getSymbol());
				}
			}
			if (i != size - 1) {
				result.append("\n");

				for (int j = 0; j < size; j++) {
					if (j == 0) {
						result.append("---");
					} else {
						result.append("|---");
					}
				}
			}
			result.append("\n");
		}

		return result.toString();
	}

	public int getSize() {
		return size;
	}

	public boolean markField(String symbol, int xCoord, int yCoord) {
		board[xCoord][yCoord].setSymbol(symbol);
		board[xCoord][yCoord].setMarked(true);

		return hasWon(xCoord, yCoord);
	}

	public boolean isMarked(int xCoord, int yCoord) {
		return board[xCoord][yCoord].getMarked();
	}

	public Field getField(int xCoord, int yCoord) {
		return board[xCoord][yCoord];
	}
}
