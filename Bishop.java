import java.io.Serializable;

public class Bishop extends Piece implements Serializable{
	
	private final String whiteP = "/pic/whiteBishop.jpg"; 
	private final String blackP = "/pic/blackBishop.jpg";
	
	public Bishop(boolean color, Moves move) {
		super(color, move);
		
		if (color) {
			super.setImage(whiteP);
		} else {
			super.setImage(blackP);
		}
	}
	
	@Override
	public boolean isValidMove(int srcX, int srcY, int destX, int destY) {
		if(move.diag(srcX, srcY, destX, destY))
			return true;
		return false;
	}
}
