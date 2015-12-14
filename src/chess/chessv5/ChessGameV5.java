package chess.chessv5;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * 規範每一個遊戲都應該有兩個玩家
 */
abstract class AbstractGame {
	abstract void setPlayers();

	abstract Player nextTurn();

	abstract Player changeTurn();
}

public class ChessGameV5 {
	public static void main(String args[]) {
		ChessGame game = new ChessGame();
	}
}

class ChessGame extends AbstractGame {
	public static final int BLACK = 0, RED = 1;
	Chess[] chesses;
	Player p1, p2;
	Player nextTurn; // next player
	ChessBoard cb;

	public ChessGame() {
		setPlayers();
		if (validatePlayers()) {
			nextTurn = p1;
			generateAllChess();
			setChessOwner(chesses, p1, p2);
			cb = makeChessBoard(this);
			setChessBoarding(cb, new RandomChessBoarding());
			// setChessBoarding(cb, new SimpleChessBoarding());

			play();
		}
		// cb.setPlayers();
	}

	private void setChessOwner(Chess[] allChess, Player p1, Player p2) {
		for (Chess c : allChess) {
			if (c.side == p1.getSide()) {
				c.setOwner(p1);
			} else if (c.side == p2.getSide()) {
				c.setOwner(p2);
			} else
				System.err.print("Error in setChessOwner");
		}
	}

	ChessBoard makeChessBoard(ChessGame g) {
		return new ChessBoard(g);
	}

	void play() {
		cb.showBoard();
		cb.target(1);
		cb.showBoard();
		// Scanner sc = new Scanner(System.in);
		// sc.nextLine();
		cb.target(2);
		cb.showBoard();
		cb.target(3);
		cb.showBoard();
		cb.target(4);
		cb.showBoard();
		cb.target(5);
		cb.showBoard();
		cb.target(6);
		cb.showBoard();
		cb.target(7);
		cb.showBoard();

		
	}

	public void setPlayers() {
		p1 = new Player(this, "Jack", BLACK);
		p2 = new Player(this, "Mary", RED);
	}

	Player changeTurn() {
		nextTurn = (nextTurn == p1) ? p2 : p1;
		return nextTurn;
	}

	private boolean validatePlayers() {
		if (p1 == null) {
			return false;
		} else if (p2 == null) {
			return false;
		}
		// 必須是要一黑一紅
		return (p1.isBLACK() && p2.isRED()) || (p1.isRED() && p2.isBLACK());
	}

	protected void setChessBoarding(ChessBoard cb, ChessBoarding boarding) {
		boarding.setLocation(chesses);
		cb.putChess(chesses);
	}

	Player nextTurn() {
		return nextTurn;
	}

	Chess makeChess(String name, int side) {
		return new Chess(name, side);
	}

	/*
	 * 採用 Factory Method 來產生棋子
	 */
	void generateAllChess() {
		chesses = new Chess[] { makeChess("將", BLACK), makeChess("士", BLACK),
				makeChess("士", BLACK), makeChess("象", BLACK),
				makeChess("象", BLACK), makeChess("車", BLACK),
				makeChess("車", BLACK), makeChess("馬", BLACK),
				makeChess("馬", BLACK), makeChess("包", BLACK),
				makeChess("包", BLACK), makeChess("卒", BLACK),
				makeChess("卒", BLACK), makeChess("卒", BLACK),
				makeChess("卒", BLACK), makeChess("卒", BLACK),
				makeChess("帥", RED), makeChess("仕", RED), makeChess("仕", RED),
				makeChess("相", RED), makeChess("相", RED), makeChess("俥", RED),
				makeChess("俥", RED), makeChess("傌", RED), makeChess("傌", RED),
				makeChess("炮", RED), makeChess("炮", RED), makeChess("兵", RED),
				makeChess("兵", RED), makeChess("兵", RED), makeChess("兵", RED),
				makeChess("兵", RED) };
	}

	// void setPlayers(Player p1, Player p2) {
	// this.p1 = p1;
	// this.p2 = p2;
	// }

	void showAllChess() {
		System.out.println("Players: " + p1 + " vs. " + p2);
		for (Chess c : chesses) {
			System.out.println(c);
		}
	}
}

class Chess {
	public static final int CREATED = 0;
	public static final int SELECTED = 1;
	public static final int KILLED = 2;

	private String name;
	int weight;
	int side;
	int loc;
	int status;
	Player owner;

	Location location;

	public Chess(String name, int side) {
		this.name = name;
		this.side = side;
		status = Chess.CREATED;
	}

	public void setOwner(Player p1) {
		this.owner = p1;
	}

	public String getName() {
		return name;
	}

	public Location getLocation() {
		return location;
	}

	void setLocation(Location location) {
		this.location = location;
	}

	public int getLoc() {
		return loc;
	}

	void setLoc(int i) {
		this.loc = i;
	}

	public String toString() {
		return name + "," + " loc=" + getLocation() + ", staus =" + status;
	}

	public void setStatus(int s) {
		this.status = s;
	}

	void select() {
		this.status = Chess.SELECTED;
	}

	void unSelect() {
		this.status = Chess.CREATED;
	}

	// 是否被選了
	public boolean isSelected() {
		return status == Chess.SELECTED ? true : false;
	}

	void move(int loc) {
		this.loc = loc;
	}

	public Player owner() {
		return null;
	}

	public void killed() {
		status = KILLED;
		loc = -1;
		location = null;
	}
}

class Player {
	AbstractGame game;
	String name;
	int side;

	public Player(AbstractGame game, String n, int side) {
		this.game = game;
		this.name = n;
		this.side = side;
	}

	public String toString() {
		return name;
	}

	public int getSide() {
		return side;
	}

	boolean isBLACK() {
		return (side == ChessGame.BLACK);
	}

	boolean isRED() {
		return (side == ChessGame.RED);
	}

	boolean myTurn() {
		return ((game.nextTurn()) == this);
	}
}

/*
 * Location of a chess. It is composed of (x,y) x should be in the range (0,3),
 * y: (0, 7)
 */
class Location {
	int x, y;

	public Location(int loc) {
		this(loc / 8, loc % 8);
	}

	public Location(int x, int y) {
		boolean xOK = (x >= 0 && x <= 3);
		boolean yOK = (y >= 0 && y <= 7);
		if (!(xOK && yOK)) {
			System.out.println("Location Error:" + x + ", " + y);
			System.exit(0);
		}
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	public static Location getLocation(int loc) {
		int x = (loc / 8);
		int y = (loc % 8);
		return new Location(x, y);
	}

	int getLoc() {
		int result = 8 * x + y;
		return result;
	}
}

class ChessBoard {
	// board 記錄每一個 Location 對應的棋子
	Map<Location, Chess> board = new HashMap<Location, Chess>();
	Location[] locs;
	Chess[] chesses;
	ChessGame game; // the game where the board plays on
	Chess selectedChess;

	// let chess board know game
	public ChessBoard(ChessGame g) {
		this.game = g;
	}

	// put the chess to the board
	public void putChess(Chess[] chesses) {
		this.chesses = chesses;
		locs = new Location[chesses.length];
		for (int i = 0; i < chesses.length; i++) {
			locs[i] = new Location(i);
		}
		for (int i = 0; i < chesses.length; i++) {
			Chess c = chesses[i];
			int loc = c.getLoc();
			c.setLocation(locs[loc]);
			if (c.getLocation() != locs[c.getLoc()])
				System.out.println("Error");
			board.put(locs[loc], c);
		}
	}

	private void kill(Chess killer, Chess c) {
		// 先假設 killer 可以 kill c
		Location killerLocation = killer.getLocation();
		board.put(killerLocation, null);
		board.put(c.getLocation(), killer);
		killer.move(c.getLoc());
		c.killed();
	}

	private void select(Chess c) {
		c.select();
		selectedChess = c;
		// System.out.println("selected chess" + c.getName());
	}

	/*
	 * 當使用者點擊 loc 位置時，依據當時的狀況判斷是 select, reselect, move or delete
	 */
	void target(int loc) {
		Chess c = (Chess) board.get(locs[loc]);
		if (c == null) {
			// move
			System.out.println(selectedChess.getName() + " move to " + loc);
			if (selectedChess != null) {
				selectedChess.move(loc);
				changeTurn();
			} else {
				// do nothing
			}
		}
		// select or kill
		else {
			if (selectedChess == null) {
				if (game.nextTurn() == c.owner) {
					System.out.println(c.owner.name + " Select " + c.getName());
					select(c);
				} else
					System.out.println("Not your turn");
			}
			// reselect or kill
			else {
				// reselect
				if (game.nextTurn() == c.owner) {
					System.out.println(c.owner.name + " Reselect "
							+ c.getName());
					selectedChess.unSelect();
					select(c);
				} else {
					Chess killer = selectedChess;
					System.out.println(killer.getName() + " kill "
							+ c.getName());
					kill(killer, c);
					changeTurn();
				}
			}
		}
	}
	
	void changeTurn() {
		game.changeTurn();
		selectedChess.unSelect();
		selectedChess = null;		
	}

	void showBoard() {
		for (int i = 0; i < locs.length; i++) {
			Chess c = (Chess) (board.get(locs[i]));
			boolean noChessOnLocation = (c == null) ? true : false;
			if (noChessOnLocation)
				System.out.print("  ");
			else if (c.isSelected())
				System.out.print("[" + c.getName() + "]");
			else
				System.out.print(c.getName());
			// new row
			if ((i + 1) % 8 == 0)
				System.out.println("");
			else
				System.out.print("\t");
		}
		System.out.println("Turn: " + game.nextTurn().name);
	}
}
