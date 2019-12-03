package unsw.frontend;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * How to play screen for game
 * 
 * @author z5208485
 *
 */
public class ExplainScreen {
	private Stage stage;
	private String title;
	private ExplainController controller;
	private Scene scene;

	/**
	 * Creates new explain screen in stage
	 * 
	 * @param stage - Stage in which to display screen
	 * @throws IOException
	 */
	public ExplainScreen(Stage stage) throws IOException {
		this.stage = stage;
		title = "Explain Screen";

		controller = new ExplainController();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ExplainView.fxml"));
		loader.setController(controller);

		// load into a Parent node called root
		Parent root = loader.load();
		scene = new Scene(root);
	}

	/**
	 * Displays screen
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
	public ExplainController getController() {
		return controller;
	}

}
