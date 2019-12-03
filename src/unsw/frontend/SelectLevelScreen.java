package unsw.frontend;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Screen where the player selects a level
 * 
 * @author z5208485
 *
 */
public class SelectLevelScreen {
	private Stage stage;
	private String title;
	private SelectLevelController controller;
	private Scene scene;

	/**
	 * Creates a new level selection screen
	 * 
	 * @param stage - Stage to create screen in
	 * @throws IOException
	 */
	public SelectLevelScreen(Stage stage) throws IOException {
		this.stage = stage;
		title = "Select Screen";

		controller = new SelectLevelController(this);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LevelView.fxml"));
		loader.setController(controller);

		// load into a Parent node called root
		Parent root = loader.load();
		scene = new Scene(root);

	}

	/**
	 * Displays level selection screen
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
	public SelectLevelController getController() {
		return controller;
	}

	/**
	 * 
	 * @return stage - Gets stage for screen
	 */
	public Stage getStage() {
		return stage;
	}
}
