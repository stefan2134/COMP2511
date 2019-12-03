package unsw.frontend;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Screen where the user plays the game
 * 
 * @author z5208485
 *
 */
public class DungeonScreen {
	private Stage stage;
	private String title;
	private DungeonController controller;
	private Scene scene;

	/**
	 * Creates a new level screen
	 * 
	 * @param stage     - Stage in which to render the level
	 * @param levelName - Name of level
	 * @throws IOException
	 */
	public DungeonScreen(Stage stage, String levelName) throws IOException {
		this.stage = stage;
		title = "Dungeon Map";
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(levelName + ".json");

		controller = dungeonLoader.loadController();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
		loader.setController(controller);

		// load into a Parent node called root
		Parent root = loader.load();
		scene = new Scene(root, controller.getWidth(), controller.getHeight());

		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				controller.handleKeyPress(e);
			}
		});
		root.requestFocus();

	}

	/**
	 * Displays level
	 */
	public void start() {
		stage.setTitle(title);
		stage.setScene(scene);

		stage.show();
	}

	/**
	 * 
	 * @return controller - Returns controller for dungeon
	 */
	public DungeonController getController() {
		return controller;
	}
}
