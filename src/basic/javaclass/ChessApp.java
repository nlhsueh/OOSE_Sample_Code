package basic.javaclass;


/*
 * This is a incomplete code, fill in the code to make it run.
 */
public class ChessApp {

	public static void main(String[] args) {
		ChessGame game = new ChessGame();
		game.init(); //will generate 32 chesses
		Player p1 = new Player("John");
		Player p2 = new Player("Helen");
		
		game.addPlayer(p1);
		game.addPlayer(p2);
		game.show(); //show all status of the game
		
		int loc1 = 0;
		Chess c1 = game.getChess(loc1);
		p1.move(c1, 2);
		game.show(); //show all status of the game

	}

}

class Chess {
	String name;
	int id;
	int location;
}

class ChessGame {
	
}
