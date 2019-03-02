import java.io.Serializable;

/**
 * This class checks if destination and source matches up with a certain move
 * type It also checks if there's a collision between pieces and movement
 * 
 * @author Wei-Han Chou
 * @version 1.0
 */
public class Moves implements Serializable {

	private transient Squares[][] square;

	public Moves(Squares[][] s) {
		square = s;
	}
	
	public void setSquare(Squares[][] s) {
		square = s;
	}
	
	// Move up any spot
	public boolean vertical(int srcX, int srcY, int destX, int destY) {
		if (srcX == destX && destY != srcY) // if its a vertical move
			if (checkCollisionVertical(srcX, srcY, destY)) // checks collision
				return true;
		return false;
	}

	// Move up 1 spot
	public boolean up1(int srcX, int srcY, int destX, int destY) {
		if (srcX == destX && destY == (srcY - 1))
			return true;
		return false;
	}

	// Move up 1 spot, collision not allowed
	public boolean up1C(int srcX, int srcY, int destX, int destY) {
		if (srcX == destX && destY == (srcY - 1))
			if (checkDestinationCollision(destX, destY))
				return true;
		return false;
	}

	// Move up 2 spot
	public boolean up2(int srcX, int srcY, int destX, int destY) {
		if (srcX == destX && destY == (srcY - 2) && srcY == 6)
			if (checkCollisionUp2(srcX, srcY))
				return true;
		return false;
	}

	// move down 1
	public boolean down1(int srcX, int srcY, int destX, int destY) {
		if (srcX == destX && destY == (srcY + 1))
			return true;
		return false;
	}

	// move down 1 spot, collision not allowed
	public boolean down1C(int srcX, int srcY, int destX, int destY) {
		if (srcX == destX && destY == (srcY + 1))
			if (checkDestinationCollision(destX, destY))
				return true;
		return false;
	}

	// move down 2
	public boolean down2(int srcX, int srcY, int destX, int destY) {
		if (srcX == destX && destY == (srcY + 2) && srcY == 1)
			if (checkCollisionDown2(srcX, srcY))
				return true;
		return false;
	}

	// moves horizontally as long as there are no collision
	public boolean horizontal(int srcX, int srcY, int destX, int destY) {
		if (srcY == destY && destX != srcX)
			if (checkCollisionHorizontal(srcY, srcX, destX))
				return true;
		return false;
	}

	public boolean horiz1(int srcX, int srcY, int destX, int destY) {
		if (srcY == destY && Math.abs(destX - srcX) == 1)
			return true;
		return false;
	}

	// move diagonally
	public boolean diag(int srcX, int srcY, int destX, int destY) {
		if (Math.abs(srcX - destX) == Math.abs(srcY - destY))
			if (checkCollisionDiagonal(srcX, srcY, destX, destY))
				return true;
		return false;
	}

	// move 1 diagonal space
	public boolean diag1(int srcX, int srcY, int destX, int destY) {
		if (Math.abs(srcX - destX) == Math.abs(srcY - destY) && Math.abs(srcY - destY) == 1)
			return true;
		return false;
	}

	// L move with long side on horizontal
	public boolean Llong(int srcX, int srcY, int destX, int destY) {
		if (Math.abs(srcX - destX) == 1 && Math.abs(srcY - destY) == 2)
			return true;
		return false;
	}

	// L move with long side on vertical
	public boolean Lshort(int srcX, int srcY, int destX, int destY) {
		if (Math.abs(srcX - destX) == 2 && Math.abs(srcY - destY) == 1)
			return true;
		return false;
	}

	//
	//
	// following methods checks for collision on moves
	//
	//
	private boolean checkDestinationCollision(int destX, int destY) {
		if (square[destX][destY].getPiece() != null)
			return false;
		return true;
	}

	private boolean checkCollisionUp2(int srcX, int srcY) {
		if (square[srcX][srcY - 1].getPiece() != null)
			return false;
		return true;
	}

	private boolean checkCollisionDown2(int srcX, int srcY) {
		if (square[srcX][srcY + 1].getPiece() != null)
			return false;
		return true;
	}

	private boolean checkCollisionDiagonal(int srcX, int srcY, int destX, int destY) {
		if ((srcX - destX) < 0 && (srcY - destY) < 0) { // towards South East
			for (int i = srcX + 1, j = srcY + 1; i < destX; i++, j++)
				if (square[i][j].getPiece() != null)
					return false;
		} else if ((srcX - destX) > 0 && (srcY - destY) > 0) { // towards north west
			for (int i = srcX - 1, j = srcY - 1; i > destX; i--, j--)
				if (square[i][j].getPiece() != null)
					return false;
		} else if ((srcX - destX) > 0 && (srcY - destY) < 0) { // towards south east
			for (int i = srcX - 1, j = srcY + 1; i > destX; i--, j++)
				if (square[i][j].getPiece() != null)
					return false;
		} else // towards north east
			for (int i = srcX + 1, j = srcY - 1; i < destX; i++, j--)
				if (square[i][j].getPiece() != null)
					return false;

		return true; // if no collisions at all
	}

	private boolean checkCollisionHorizontal(int srcY, int srcX, int destX) {
		if (srcX > destX) { // if piece is moving left
			for (int i = (srcX - 1); i > destX; i--)
				if (square[i][srcY].getPiece() != null)
					return false;
		} else // if piece is moving right
			for (int i = (srcX + 1); i < destX; i++)
				if (square[i][srcY].getPiece() != null)
					return false;

		return true; // no collision
	}

	private boolean checkCollisionVertical(int srcX, int srcY, int destY) {
		if (srcY > destY) { // if piece is moving upwards
			for (int i = (srcY - 1); i > destY; i--)
				if (square[srcX][i].getPiece() != null)
					return false;
		} else // if piece is moving downwards
			for (int i = (srcY + 1); i < destY; i++)
				if (square[srcX][i].getPiece() != null)
					return false;
		return true; // if no collision
	}
}