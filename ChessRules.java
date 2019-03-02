public class ChessRules {

	private int row;
	private int column;
	private int cRow;
	private int cCol;
	private int destRow = -1;
	private int destCol = -1;
	private final String redColor = "-fx-background-color: #ff0000";
	private boolean active = false; 
	private boolean playerTurn = true;

	private Piece piece = null;
	private Squares[][] square;
	
	ChessRules() {
		row = 8;
		column = 8;
	}
	
	/**
	 * Checks if square has null piece
	 */
	private boolean checkDestination(int r, int c) {
		if (square[r][c].getPiece() == null) 
			return true;
		
		if (square[r][c].getColorPiece() != playerTurn)
			return true;
		return false;
	}
	
	
	/**
	 * Moves pieces if valid
	 */
	public void move(int r, int c) {
		if (checkDestination(r,c)) {
			if (square[cRow][cCol].getPiece().isValidMove(cRow, cCol, destRow, destCol)) {
				square[cRow][cCol].removePiece();
				square[destRow][destCol].removePiece();
				square[destRow][destCol].addPiece(piece);
				playerTurn ^= true;
			} else
				square[destRow][destCol].setSquareColor(redColor);
		} else {
			System.out.println("invalid move");
			//sets background to red, so user know its invalid move
			setSquareRed(destRow, destCol);

		}
		setFalse();
	}
	
	
	private void setSquareRed(int r, int c) {
		square[r][c].setSquareColor(redColor);
	} //sets an invalid square click to red color
	
	
	//sets the destination click
	public void setSourceClick(int r, int c) {
		if (square[r][c].getPiece() != null 
				&& (square[r][c].getColorPiece() == playerTurn)) {
			active = true;
			cRow = r;
			cCol = c;
			piece = square[r][c].getPiece();

			//System.out.println(piece);
		} else
			setSquareRed(r, c);
			//System.out.println("null piece");
	}
	
	
	
	public void setDestClick(int r, int c) {
		destRow = r;
		destCol = c;
	}
	
	public void setFalse() { active = false; } //if active = false; player has not picked a valid piece
	public int getRow() { return row; }
	public void setPlayerTurn(boolean b) { playerTurn = b;}
	public int getColumn() { return column; }
	public Piece getPiece() { return piece;}
	public boolean getActive() {return active;}
	public void setValues(Squares[][] squareArray) { square = squareArray; }
}
