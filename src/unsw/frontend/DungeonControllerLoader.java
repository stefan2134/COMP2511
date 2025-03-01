package unsw.frontend;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import unsw.dungeon.*;
import unsw.item.*;
import unsw.obstacle.*;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * 
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

	private List<ImageView> entities;

	// Images
	private Image playerImage;
	private Image wallImage;
	private Image boulderImage;
	private Image doorImage;
	private Image switchImage;
	private Image exitImage;
	private Image swordImage;
	private Image potionImage;
	private Image portalImage;
	private Image treasureImage;
	private Image enemyImage;
	private Image keyImage;
	private Image bombImage;
	private Image spikeImage;

	/**
	 * Creates new dungeon controller loader for a level
	 * 
	 * @param filename - Path of level
	 * @throws FileNotFoundException
	 */
	public DungeonControllerLoader(String filename) throws FileNotFoundException {
		super(filename);
		entities = new ArrayList<>();
		playerImage = new Image("/human_new.png");
		wallImage = new Image("/brick_brown_0.png");
		boulderImage = new Image("/boulder.png");
		doorImage = new Image("/closed_door.png");
		switchImage = new Image("/pressure_plate.png");
		exitImage = new Image("/exit.png");
		swordImage = new Image("/greatsword_1_new.png");
		potionImage = new Image("/brilliant_blue_new.png");
		portalImage = new Image("/portal.png");
		treasureImage = new Image("/gold_pile.png");
		enemyImage = new Image("/deep_elf_master_archer.png");
		keyImage = new Image("/key.png");
		bombImage = new Image("/bomb.png");
		spikeImage = new Image("/spikes.png");
	}

	@Override
	public void onLoad(Entity player) {
		ImageView view = new ImageView(playerImage);
		addEntity(player, view);
	}

	@Override
	public void onLoad(Wall wall) {
		ImageView view = new ImageView(wallImage);
		addEntity(wall, view);
	}

	@Override
	public void onLoad(Boulder boulder) {
		ImageView view = new ImageView(boulderImage);
		addEntity(boulder, view);

	}

	@Override
	public void onLoad(Door door) {
		ImageView view = new ImageView(doorImage);
		addEntity(door, view);

		// Doors image depends on state of door
		ChangeListener<Boolean> changeListener = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				String path = "";
				// If the door is open
				if (newValue == true) {
					path = "/open_door.png";
					// If the door is closed
				} else {
					path = "/closed_door.png";
				}

				view.setImage(new Image(path));
			}
		};

		door.getIsOpenProperty().addListener(changeListener);
	}

	@Override
	public void onLoad(Switch switch1) {
		ImageView view = new ImageView(switchImage);
		addEntity(switch1, view);

	}

	@Override
	public void onLoad(Exit exit) {
		ImageView view = new ImageView(exitImage);
		addEntity(exit, view);

	}

	@Override
	public void onLoad(Sword sword) {
		ImageView view = new ImageView(swordImage);
		addEntity(sword, view);

	}

	@Override
	public void onLoad(Treasure treasure) {
		ImageView view = new ImageView(treasureImage);
		addEntity(treasure, view);

	}

	@Override
	public void onLoad(Key key) {
		ImageView view = new ImageView(keyImage);
		addEntity(key, view);

	}

	@Override
	public void onLoad(Potion potion) {
		ImageView view = new ImageView(potionImage);
		addEntity(potion, view);

	}

	@Override
	public void onLoad(Portal portal) {
		ImageView view = new ImageView(portalImage);
		addEntity(portal, view);

	}

	@Override
	public void onLoad(Enemy enemy) {
		ImageView view = new ImageView(enemyImage);
		addEntity(enemy, view);
	}

	@Override
	public void onLoad(Bomb bomb) {
		ImageView view = new ImageView(bombImage);
		addEntity(bomb, view);
	}

	@Override
	public void onLoad(Spike spike) {
		ImageView view = new ImageView(spikeImage);
		addEntity(spike, view);
	}

	private void addEntity(Entity entity, ImageView view) {
		// Binds entity visibility to its corresponding image view
		entity.getIsVisibleProperty().bindBidirectional(view.visibleProperty());

		// Ensures that the image tracks the entities position
		trackPosition(entity, view);
		entities.add(view);
	}

	/**
	 * Set a node in a GridPane to have its position track the position of an entity
	 * in the dungeon.
	 *
	 * By connecting the model with the view in this way, the model requires no
	 * knowledge of the view and changes to the position of entities in the model
	 * will automatically be reflected in the view.
	 * 
	 * @param entity
	 * @param node
	 */
	private void trackPosition(Entity entity, Node node) {
		GridPane.setColumnIndex(node, entity.getX());
		GridPane.setRowIndex(node, entity.getY());
		entity.x().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				GridPane.setColumnIndex(node, newValue.intValue());
			}
		});
		entity.y().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				GridPane.setRowIndex(node, newValue.intValue());
			}
		});
	}

	/**
	 * Create a controller that can be attached to the DungeonView with all the
	 * loaded entities.
	 * 
	 * @return
	 * @throws FileNotFoundException
	 */
	public DungeonController loadController() throws FileNotFoundException {
		return new DungeonController(load(), entities);
	}

}
