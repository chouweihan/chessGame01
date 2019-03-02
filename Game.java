import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


/**
 * Main for chess
 * @author Wei-Han Chou
 * @version 1.0
 */
public class Game extends Application {
	
	private ChessRules rules = new ChessRules();
	
	public void start(Stage primaryStage) throws Exception {
		BorderPane border = new BorderPane();
		
		Board board = new Board(rules.getRow(), rules.getColumn(), rules);
		Menu menu = new Menu(board, primaryStage);
		
		final int appWidth = 900;
	    final int appHeight = 800;
	    border.setCenter(board.getPane());
	    border.setRight(menu.getVbox());
	    
	    Scene scene = new Scene(border, appWidth, appHeight);

	    primaryStage.setTitle("Chess Game");
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}
	
    public static void main(String[] args) {
        launch(args);
    }

}
