package unsw.frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 * Controller for end screen
 * 
 * @author z5208485
 *
 */
public class EndController {
	@FXML
	private Button endButton;

	@FXML
	private Text outcome;

	private SelectLevelScreen selectScreen;

	@FXML
	void handleEndButton(ActionEvent event) {
		selectScreen.start();
	}

	/**
	 * Sets level selection screen
	 * 
	 * @param selectScreen - Level selection screen
	 */
	public void setSelectScreen(SelectLevelScreen selectScreen) {
		this.selectScreen = selectScreen;
	}

	@FXML
	public void setOutcome(String outcome) {
		this.outcome.textProperty().setValue(outcome);
	}
}
