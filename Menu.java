import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Menu with open and save options
 * @author Wei-Han Chou
 *
 */
public class Menu {

	private Button open = new Button("OPEN");
	private Button save = new Button("SAVE");
	private FileChooser fileChooser = new FileChooser();
	private VBox vbox;
	private Board board;
	Piece pieces[][];
	Piece newPieces[][];
	private Stage stage;

	public Menu(Board board, Stage stage) {
		this.board = board;
		this.stage = stage;
	}

	public VBox getVbox() {
		final int width = 100;
		final int height = 70;
		save.setMinWidth(width);
		save.setMinHeight(height);

		save.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				vbox.setStyle("-fx-background-color: #98f442");
				board.createPieceState();
				pieces = board.getPieceState();
				ObjectOutput out = null;
				try {
					out = new ObjectOutputStream(new FileOutputStream("MyGame.bin"));
					out.writeObject(pieces);
					out.close();
					System.out.println("succesful");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		open.setMinWidth(width);
		open.setMinHeight(height);
		open.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				vbox.setStyle("-fx-background-color: #41d3f4");
				ObjectInputStream in = null;

				try {
					fileChooser.setTitle("Open Resource File");
					in = new ObjectInputStream(new FileInputStream(fileChooser.showOpenDialog(stage)));
					newPieces = (Piece[][]) in.readObject();
					in.close();
					board.restoreSaveState(newPieces);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});

		vbox = new VBox(50, open, save);
		vbox.setStyle("-fx-background-color: #4250f4");
		return vbox;
	}

}
