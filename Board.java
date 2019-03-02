import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 * @author Wei-Han Chou
 * @version 1.0
 */
public class Board extends GridPane {

	private Button tiles[][];
	private Squares square[][];
	private GridPane pane = new GridPane();
	private final boolean white = true;
	private final boolean black = false;
	private ChessRules rule;
	private Moves move;
	Piece pieces[][];
	private transient Squares s;

	public Board(int row, int col, ChessRules r) {
		tiles = new Button[row][col];
		square = new Squares[row][col];
		createSquares();
		setSquares();
		move = new Moves(square);
		rule = r;
		setPieces();
		rule.setValues(square);
	}

	/**
	 * Create squares, stores in 2d array true = white, false = black
	 */
	public void createSquares() {
		for (int r = 0; r < tiles.length; r++) {
			for (int c = 0; c < tiles[r].length; c++) {
				// inside loop, because I want each square to have its own color boolean value
				s = new Squares(true);
				// checks if square should be black or white
				if ((r + (c + 1)) % 2 == 0) {
					s.setColor(false);
				} else {
					s.setColor(true);
				}
				square[r][c] = s;
				tiles[r][c] = s.getTile();
				// event handler
				tiles[r][c].setOnAction(new Event(tiles, r, c, this));
			}
		}
	}

	/**
	 * Adds squares to grid pane
	 */
	public void setSquares() {
		for (int r = 0; r < tiles.length; r++) {
			for (int c = 0; c < tiles[0].length; c++) {
				// adds the tiles to gridpane
				pane.add(tiles[r][c], r, c);
			}
		}
	}

	/**
	 * Sets initial Pieces.
	 */
	public void setPieces() {

		square[0][0].addPiece(new Rook(black, move));
		square[1][0].addPiece(new Knight(black, move));
		square[2][0].addPiece(new Bishop(black, move));
		square[3][0].addPiece(new King(black, move));
		square[4][0].addPiece(new Queen(black, move));
		square[5][0].addPiece(new Bishop(black, move));
		square[6][0].addPiece(new Knight(black, move));
		square[7][0].addPiece(new Rook(black, move));

		for (int i = 0; i < 8; i++) {
			square[i][1].addPiece(new Pawn(black, move));
			square[i][6].addPiece(new Pawn(white, move));
		}

		square[0][7].addPiece(new Rook(white, move));
		square[1][7].addPiece(new Knight(white, move));
		square[2][7].addPiece(new Bishop(white, move));
		square[3][7].addPiece(new King(white, move));
		square[4][7].addPiece(new Queen(white, move));
		square[5][7].addPiece(new Bishop(white, move));
		square[6][7].addPiece(new Knight(white, move));
		square[7][7].addPiece(new Rook(white, move));
	}

	/**
	 * Passes current clicked row/column into rules Rule will validate what to do
	 * with the pieces at those location.
	 * 
	 * @param r clicked row
	 * @param c clicked column
	 */
	public void validate(int r, int c) {
		if (!rule.getActive()) {
			rule.setSourceClick(r, c);
		} else {
			rule.setDestClick(r, c);
			rule.move(r, c);
		}
	}
	
	/**
	 * Piece array specifying all piece locations
	 */
	public void createPieceState() {
		pieces = new Piece[square.length + 1][square.length];
		for (int r = 0; r < square.length; r++)
			for (int c = 0; c < square.length; c++) {
				pieces[r][c] = square[r][c].getPiece();
			}
		//Creates extra piece(last piece moved) just to check which color went last
		if(rule.getPiece() != null)
			pieces[square.length][square.length - 1] = rule.getPiece();
	}
	
	
	public Piece[][] getPieceState() {
		return pieces;
	}
	
	/**
	 * Changes board pieces to a previous save state
	 */
	public void restoreSaveState(Piece[][] p) {
		for (int r = 0; r < square.length; r++)
			for (int c = 0; c < square.length; c++) {
				square[r][c].removePiece(); 		//removes all pieces from board
				square[r][c].addPiece(p[r][c]); 	//adds save state pieces to board
				if(square[r][c].getPiece() != null) //Gives to move set to pieces
					square[r][c].getPiece().setMove(move);
			}
			
			//sets to correct player turn after restoring save state
			if(p[square.length][square.length - 1] != null)
				rule.setPlayerTurn(!p[square.length][square.length - 1].getColor());
			
	}	

	public GridPane getPane() {
		return pane;
	}
}
