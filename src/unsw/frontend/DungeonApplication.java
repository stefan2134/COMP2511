package unsw.frontend;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Dungeon game application. A player is to interact with obstacles and items to
 * accomplish goals.
 * 
 * @author z5208485
 *
 */
public class DungeonApplication extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		primaryStage.setTitle("Dungeon");

		StartScreen startScreen = new StartScreen(primaryStage);
		ExplainScreen explainScreen = new ExplainScreen(primaryStage);
		SelectLevelScreen selectScreen = new SelectLevelScreen(primaryStage);

		startScreen.getController().setExplainScreen(explainScreen);
		explainScreen.getController().setStartScreen(startScreen);

		startScreen.getController().setLevelScreen(selectScreen);
		selectScreen.getController().setStartScreen(startScreen);

		startScreen.start();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
