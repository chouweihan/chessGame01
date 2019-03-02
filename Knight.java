import java.io.Serializable;

public class Knight extends Piece implements Serializable{
	
	private final String whiteP = "/pic/whiteKnight.jpg"; 
	private final String blackP = "/pic/blackKnight.jpg";
	
	public Knight(boolean color, Moves move) {
		super(color, move);
		
		if (color) {
			super.setImage(whiteP);
		} else {
			super.setImage(blackP);
		}
	}

	@Override
	public boolean isValidMove(int srcX, int srcY, int destX, int destY) {
		if(move.Llong(srcX, srcY, destX, destY) || move.Lshort(srcX, srcY, destX, destY))
			return true;
		return false;
	}
}
