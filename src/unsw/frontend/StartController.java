package unsw.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller for start screen
 * 
 * @author z5208485
 *
 */
public class StartController {
	@FXML
	private Button startButton;

	@FXML
	private Button explainButton;

	private SelectLevelScreen levelScreen;
	private ExplainScreen explainScreen;

	@FXML
	void handleStartButton(ActionEvent event) {
		levelScreen.start();
	}

	@FXML
	void handleExplainButton(ActionEvent event) {
		explainScreen.start();
	}

	/**
	 * Sets level screen
	 * 
	 * @param levelScreen - Level screen
	 */
	public void setLevelScreen(SelectLevelScreen levelScreen) {
		this.levelScreen = levelScreen;
	}

	/**
	 * Sets explain screen
	 * 
	 * @param explainScreen - Explain screen
	 */
	public void setExplainScreen(ExplainScreen explainScreen) {
		this.explainScreen = explainScreen;

	}
}
