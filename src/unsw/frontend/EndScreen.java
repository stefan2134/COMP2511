package unsw.frontend;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * End screen of game
 * 
 * @author z5208485
 *
 */
public class EndScreen {

	private Stage stage;
	private String title;
	private EndController controller;

	private Scene scene;

	/**
	 * Creates end screen for game
	 * 
	 * @param stage - Stage in which to create screen
	 * @throws IOException
	 */
	public EndScreen(Stage stage) throws IOException {
		this.stage = stage;
		title = "Level Completed";

		controller = new EndController();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("EndView.fxml"));
		loader.setController(controller);

		// load into a Parent node called root
		Parent root = loader.load();
		scene = new Scene(root);
	}

	/**
	 * Displays end screen
	 */
	public void start() {
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * 
	 * @return controller - Returns controller for screen
	 */
	public EndController getController() {
		return controller;
	}

}
