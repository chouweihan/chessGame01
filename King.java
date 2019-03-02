import java.io.Serializable;

public class King extends Piece implements Serializable {
	private final String whiteP = "/pic/whiteKing.jpg";
	private final String blackP = "/pic/blackKing.jpg";

	public King(boolean color, Moves move) {
		super(color, move);

		if (color) {
			super.setImage(whiteP);
		} else {
			super.setImage(blackP);
		}
	}

	@Override
	public boolean isValidMove(int srcX, int srcY, int destX, int destY) {
		if (move.diag1(srcX, srcY, destX, destY) 
				|| move.up1(srcX, srcY, destX, destY)
				|| move.down1(srcX, srcY, destX, destY) 
				|| move.horiz1(srcX, srcY, destX, destY))
			return true;
		return false;
	}
}
