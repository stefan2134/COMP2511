package unsw.obstacle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import unsw.dungeon.Entity;
import unsw.dungeon.Player;

public class Door extends Entity {
	BooleanProperty isOpen;

	/**
	 * Creates a new door with a given id at the specified coordinates.
	 * 
	 * @param x    - X coordinate
	 * @param y    - Y coordinate
	 * @param name - Name of entity
	 * @param id   - Id of door
	 */
	public Door(int x, int y, String name, int id) {
		super(x, y, name);
		setId(id);
		isOpen = new SimpleBooleanProperty();
		isOpen.setValue(false);
	}

	/**
	 * Defines player interaction with a door. If the player possesses the
	 * corresponding key then the door is opened, otherwise it remains closed.
	 */
	@Override
	public boolean canMoveTo(Player p) {
		// Checks if the player has the matching key to open the door and is adjacent to
		// the door.
		if (adjacentCoord(p.getX(), p.getY(), getX(), getY()) && p.useKey(getId())) {
			// Removes the key from the players inventory
			isOpen.setValue(true);
		}

		return isOpen.getValue();
	}
	
	/**
	 * 
	 * @return isOpen - Returns property defining the open/closed state of the door
	 */
	public BooleanProperty getIsOpenProperty() {
		return isOpen;
	}
}
