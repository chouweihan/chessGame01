import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

	
public class Squares {
	
	private boolean squareColor;
	private Button tile;
	private Piece piece = null;
	public Squares(boolean color) {
	    squareColor = color; 

	}	
	
	public Button getTile() {
		final double size = 100.0;
		tile = new Button();
		tile.setMinWidth(size);
		tile.setMinHeight(size);
		 if(squareColor) {
			 // if white
			 tile.setStyle("-fx-background-color: #e6e6fa");
		 } else {
			 // if black
			 tile.setStyle("-fx-background-color: #a2e8f0");
		 }
		 return tile;
	}
	
	public void removePiece() {
		tile.setGraphic(null);
		piece = null;
	}
	
	public void addPiece(Piece p) {
		piece = p;
		if(p != null)
			tile.setGraphic(new ImageView(new Image(p.getPic())));
	}
	
	public void setSquareColor(String style) {
		tile.setStyle(style);
	}
	
	public void setColor(boolean color) {
		squareColor = color;
	}
	
	public Piece getPiece() {
		return piece;
	}
	
	public boolean getColorPiece() {
		return piece.getColor();
	}

}
