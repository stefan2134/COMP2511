package unsw.frontend;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Start screen of game
 * 
 * @author z5208485
 *
 */
public class StartScreen {

	private Stage stage;
	private String title;
	private StartController controller;

	private Scene scene;

	/**
	 * Creates a new start screen.
	 * 
	 * @param stage- Stage on which to display the screen
	 * @throws IOException
	 */
	public StartScreen(Stage stage) throws IOException {
		this.stage = stage;
		title = "Start Screen";

		controller = new StartController();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("StartView.fxml"));
		loader.setController(controller);

		// load into a Parent node called root
		Parent root = loader.load();
		scene = new Scene(root);
	}

	/**
	 * Displays start screen
	 */
	public void start() {
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * 
	 * @return controller - Returns start screen controller
	 */
	public StartController getController() {
		return controller;
	}

}
