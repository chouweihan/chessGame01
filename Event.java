import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class Event implements EventHandler<ActionEvent> {

	private Button[][] tiles;
	private int row;
	private int col;
	private Board board;
	
	public Event(Button[][] boardTiles, int r, int c, Board b) {
		row = r;
		col = c;
		tiles = boardTiles;
		board = b;

	}

	@Override
	public void handle(ActionEvent event) {
		//sets board to original color
		for (int r = 0; r < tiles.length; r++) {
			for (int c = 0; c < tiles[r].length; c++) {
				// checks if square should be black or white
				if ((r + (c + 1)) % 2 == 0) {
					tiles[r][c].setStyle("-fx-background-color: #a2e8f0");
				} else {
					tiles[r][c].setStyle("-fx-background-color: #e6e6fa");
				}
			}
		}
		//changes valid square click to green
		tiles[row][col].setStyle("-fx-background-color: #42f459");
		//returns clicked coordinates to board
		board.validate(row, col);
	}

}
