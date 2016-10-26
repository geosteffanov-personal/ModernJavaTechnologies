package tictactoe;

import java.util.Scanner;

public class TicTacToe {
	public static final int DEFAULT_PLAYER_COUNT = 2;

	private Player[] players;
	private int playerCount;

	private Board board;

	private boolean setPlayers(Player[] players) {
		if (players.length != playerCount) {
			return false;
		}

		this.players = new Player[playerCount];

		for (int i = 0; i < playerCount; i++) {
			this.players[i] = players[i];
		}

		return true;
	}

	private boolean isMarked(PlayerMove move) {
		int xCoord = move.getXCoord();
		int yCoord = move.getYCoord();

		if (board.isMarked(xCoord, yCoord)) {
			return true;
		}
		return false;
	}

	private boolean isField(PlayerMove move) {
		int xCoord = move.getXCoord();
		int yCoord = move.getYCoord();

		boolean validX = (xCoord >= 0 && xCoord < board.getSize());
		boolean validY = (yCoord >= 0 && yCoord < board.getSize());

		return validX && validY;
	}

	private boolean validMove(PlayerMove move) {
		return isField(move) && !isMarked(move);
	}

	private String promptPlayer(Player player) {
		int maxFieldNumber = board.getSize() * board.getSize();
		String name = player.getName();

		String message = name + ", please input your move (" + 1 + "-" + maxFieldNumber + ")>\n";

		return message;
	}

	private PlayerMove parseMove(Player player, String move) {
		int inputToInt = Integer.parseInt(move) - 1;
		int xCoord = inputToInt / board.getSize();
		int yCoord = inputToInt % board.getSize();

		return new PlayerMove(player, xCoord, yCoord);
	}

	private boolean executeMove(PlayerMove move) {
		int xCoord = move.getXCoord();
		int yCoord = move.getYCoord();
		String symbol = move.getPlayer().getUsedSymbol();

		return board.markField(symbol, xCoord, yCoord);
	}

	private boolean gameDraw() {
		return board.isFilled();
	}

	public TicTacToe() {
		playerCount = DEFAULT_PLAYER_COUNT;

		board = new Board(Board.DEFAULT_SIZE);
	}

	public void play() {
		int playerTurn = 0;
		Scanner scanner = new Scanner(System.in);
		Player[] players = new Player[playerCount];

		for (int i = 0; i < playerCount; i++) {
			System.out.println("Player " + (i + 1) + ", please input your name: ");
			String name = scanner.nextLine();

			System.out.println(name + ", please input your used symbol: ");
			String symbol = scanner.nextLine();

			players[i] = new Player(name, symbol);
		}
		
		setPlayers(players);

		while (true) {
			playerTurn = playerTurn % playerCount;
			Player currentPlayer = players[playerTurn];

			System.out.println(promptPlayer(currentPlayer));
			System.out.println(board);

			String playerInput = scanner.nextLine();

			PlayerMove move = parseMove(currentPlayer, playerInput);

			if (validMove(move)) {
				if (executeMove(move)) {
					System.out.println(board);
					System.out.println("Congratulations!\n" + currentPlayer.getName() + ", you win!");
					scanner.close();
					return;
				}

				if (gameDraw()) {
					System.out.println(board);
					System.out.println("It's a draw!");
					scanner.close();
					return;
				}

				playerTurn++;
			} else {
				System.out.println("Invalid move! Try again!");
			}
		}
	}
}
