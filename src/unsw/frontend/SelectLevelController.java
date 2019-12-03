package unsw.frontend;

import java.io.File;
import java.io.IOException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Controller for level selection screen
 * 
 * @author z5208485
 *
 */
public class SelectLevelController {

	@FXML
	private Button backButton;

	@FXML
	private Button dungeonButton;

	@FXML
	private ChoiceBox<String> chooseLevelBox;

	@FXML
	private ImageView exampleImage;

	private SelectLevelScreen selectScreen;

	private StartScreen startScreen;

	private StringProperty selectedLevel;

	/**
	 * Creates a new select level controller linked to a specified select screen.
	 * 
	 * @param selectScreen - Level select screen
	 */
	public SelectLevelController(SelectLevelScreen selectScreen) {
		this.selectScreen = selectScreen;
		selectedLevel = new SimpleStringProperty();
	}

	@FXML
	public void initialize() {
		// String array of levels
		String levels[] = { "advanced", "boulders", "maze", "spicy", "test", "marking" };

		// Observable list of levels
		ObservableList<String> levelsList = FXCollections.observableArrayList(levels);

		// Assigns choices for the choice box
		chooseLevelBox.setItems(levelsList);

		// Binds choice to selectedLevel property
		selectedLevel.bindBidirectional(chooseLevelBox.valueProperty());

		// Changing the checkbox selection changes the images displayed
		ChangeListener<String> changeListener = new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// New image path
				String path = "examples/" + newValue + ".png";

				// If the image file doesn't exist then display a default image
				File tempFile = new File("./images/" + path);
				if (!tempFile.isFile()) {
					path = "examples/default.png";
				}

				Image newImage = new Image(path);
				exampleImage.setImage(newImage);
			}
		};

		chooseLevelBox.valueProperty().addListener(changeListener);

		// Sets advanced as the default level
		chooseLevelBox.setValue("advanced");
	}

	@FXML
	void handleDungeonButton(ActionEvent event) throws IOException {
		DungeonScreen dungeonScreen = new DungeonScreen(selectScreen.getStage(), selectedLevel.getValue());

		dungeonScreen.getController().setLevelScreen(selectScreen);

		dungeonScreen.start();
	}

	@FXML
	void handleBackButton(ActionEvent event) {
		startScreen.start();
	}

	/**
	 * Sets start screen for when back button is pressed
	 * 
	 * @param startScreen - Start screen of game
	 */
	public void setStartScreen(StartScreen startScreen) {
		this.startScreen = startScreen;

	}
}
