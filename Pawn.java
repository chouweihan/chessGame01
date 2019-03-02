import java.io.Serializable;

public class Pawn extends Piece implements Serializable{

	private final String whiteP = "/pic/whitePawn.jpg";
	private final String blackP = "/pic/blackPawn.jpg";

	public Pawn(boolean color, Moves move) {
		super(color, move);

		if (color) {
			super.setImage(whiteP);
		} else {
			super.setImage(blackP);
		}
	}

	@Override
	public boolean isValidMove(int srcX, int srcY, int destX, int destY) {
		if (color) { //White Piece movements
				if (move.up2(srcX, srcY, destX, destY) || move.up1C(srcX, srcY, destX, destY)) 
					return true;
		} else { //black piece movements
				if (move.down2(srcX, srcY, destX, destY) || move.down1C(srcX, srcY, destX, destY)) 
					return true;
		}
		return false;
	}

}
