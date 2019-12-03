package unsw.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller for how to play screen
 * 
 * @author z5208485
 *
 */
public class ExplainController {
	@FXML
	private Button backButton;

	private StartScreen startScreen;

	@FXML
	public void handleBackButton(ActionEvent event) {
		startScreen.start();
	}

	/**
	 * Sets the start screen
	 * 
	 * @param startScreen - Start screen of game
	 */
	public void setStartScreen(StartScreen startScreen) {
		this.startScreen = startScreen;
	}

}
