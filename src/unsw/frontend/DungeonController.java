package unsw.frontend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import unsw.dungeon.*;
import unsw.item.Item;

/**
 * A JavaFX controller for the dungeon.
 * 
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

	@FXML
	private GridPane squares;

	private List<ImageView> initialEntities;

	private Player player;

	private Dungeon dungeon;

	@FXML
	private Button backButton;

	@FXML
	private GridPane itemsGrid;

	private SelectLevelScreen levelScreen;

	/**
	 * Creates a new dungeon controller for a level
	 * 
	 * @param dungeon         - Level's dungeon
	 * @param initialEntities - Entities to be loaded into dungeon
	 */
	public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
		this.dungeon = dungeon;
		this.player = dungeon.getPlayer();
		this.initialEntities = new ArrayList<>(initialEntities);

		// Adds listener to dungeon. Display the end screen when the level is completed.
		ChangeListener<String> changeListener = new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// Display end screen if the game is lost or won
				if (newValue.equals("LOSS")) {
					displayEndScreen("You Lose");
				} else if (newValue.equals("WIN")) {
					displayEndScreen("You Win!");
				}
			}
		};

		this.dungeon.getGameStateProperty().addListener(changeListener);

	}

	/**
	 * Displays the items a player carries in a horizontal grid
	 */
	public void displayCarriedItems() {
		// Resets the grid pane
		itemsGrid.getChildren().clear();

		// Goes through every item the player is carrying
		for (Item i : player.getItems()) {
			// Adds a corresponding image to the items grid
			String imgPath = "";
			if (i.getName().equals("Bomb")) {
				imgPath = "/bomb.png";
			} else if (i.getName().equals("Key")) {
				imgPath = "/key.png";
			} else if (i.getName().equals("Potion")) {
				imgPath = "/brilliant_blue_new.png";
			} else if (i.getName().equals("Sword")) {
				imgPath = "/greatsword_1_new.png";
			} else if (i.getName().equals("Treasure")) {
				imgPath = "/gold_pile.png";
			}

			ImageView view = new ImageView(new Image(imgPath));
			itemsGrid.addRow(0, view);
		}
	}

	@FXML
	public void initialize() {
		Image ground = new Image("/dirt_0_new.png");

		// Add the ground first so it is below all other entities
		for (int x = 0; x < dungeon.getWidth(); x++) {
			for (int y = 0; y < dungeon.getHeight(); y++) {
				squares.add(new ImageView(ground), x, y);
			}
		}

		// Adds entities
		for (ImageView entity : initialEntities) {
			squares.getChildren().add(entity);
		}
	}

	@FXML
	public void handleKeyPress(KeyEvent event) {
		switch (event.getCode()) {
		case UP:
			player.moveUp();
			break;
		case DOWN:
			player.moveDown();
			break;
		case LEFT:
			player.moveLeft();
			break;
		case RIGHT:
			player.moveRight();
			break;
		case E:
			player.keyInteract();
			break;
		case R:
			player.useBomb();
			break;
		default:
			break;
		}

		displayCarriedItems();
	}

	@FXML
	public void handleBackButton(ActionEvent event) {
		levelScreen.start();
	}

	/**
	 * Sets the select level screen for when the quit button is pressed
	 * 
	 * @param levelScreen - Level selection screen
	 */
	public void setLevelScreen(SelectLevelScreen levelScreen) {
		this.levelScreen = levelScreen;
	}

	/**
	 * Calculates the width needed to display the dungeon and the UI
	 * 
	 * @return
	 */
	public int getWidth() {
		return dungeon.getWidth() * 32 > 250 ? dungeon.getWidth() * 32 : 250;
	}

	/**
	 * Calculates the height needed to display the dungeon and the UI
	 * 
	 * @return
	 */
	public int getHeight() {
		return dungeon.getHeight() * 32 + 100;
	}

	/**
	 * Displays the end screen when the level ends
	 * 
	 * @param gameState - Final outcome of game
	 */
	public void displayEndScreen(String gameState) {
		try {
			EndScreen endScreen = new EndScreen(levelScreen.getStage());
			endScreen.getController().setSelectScreen(levelScreen);
			endScreen.getController().setOutcome(gameState);
			endScreen.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
