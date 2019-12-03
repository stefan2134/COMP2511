package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Entity {
	// IntegerProperty is used so that changes to the entities position can be
	// externally observed.
	private IntegerProperty x, y;
	private String name;
	private int id = -1;
	private BooleanProperty isVisible;

	/**
	 * Create an entity positioned in square (x,y)
	 * 
	 * @param x
	 * @param y
	 */
	public Entity(int x, int y, String name) {
		this.x = new SimpleIntegerProperty(x);
		this.y = new SimpleIntegerProperty(y);
		this.name = name;
		isVisible = new SimpleBooleanProperty();
		isVisible.set(true);
	}

	/**
	 * 
	 * @return x - X coordinate
	 */
	public IntegerProperty x() {
		return x;
	}

	/**
	 * 
	 * @return y - Y coordinate
	 */
	public IntegerProperty y() {
		return y;
	}

	/**
	 * 
	 * @return y - Y coordinate as an integer
	 */
	public int getY() {
		return y().get();
	}

	/**
	 * 
	 * @return x - X coordinate as an integer
	 */
	public int getX() {
		return x().get();
	}

	/**
	 * Sets the y coordinate of the entity
	 * 
	 * @param y - Y coordinate
	 */
	public void setY(int y) {
		this.y.set(y);
	}

	/**
	 * Sets the x coordinate of the entity
	 * 
	 * @param x - X coordinate
	 */
	public void setX(int x) {
		this.x.set(x);
	}

	/**
	 * 
	 * @return name - Returns the name of the entity
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return id - Returns the id of the entity
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the entity's id
	 * 
	 * @param id - Id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Default implementation for player interaction with entity.
	 * 
	 * @param p - Player to interact with
	 */
	public void playerInteract(Player p) {
		return;
	}

	/**
	 * Default implementation for player moving into the square containing an
	 * entity.
	 * 
	 * @return - Returns true if the enemy can move into the square, otherwise
	 *         false.
	 */
	public boolean canMoveTo(Player p) {
		return true;
	}

	/**
	 * Checks if a set of coordinates are vertically or horizontally adjacent.
	 * 
	 * @param x1 - X coordinate of set 1
	 * @param y1 - Y coordinate of set 1
	 * @param x2 - X coordinate of set 2
	 * @param y2 - Y coordinate of set 2
	 * @return - Returns true if coordinates are adjacent, otherwise false
	 */
	public boolean adjacentCoord(int x1, int y1, int x2, int y2) {
		// Same x coordinate
		if (x1 == x2) {
			// Max vertical separation of 1 unit
			if (y1 == y2 + 1 || y1 == y2 - 1) {
				return true;
			}
		// Same y coordinate
		} else if (y1 == y2) {
			// Max horizontal separation of 1 unit
			if (x1 == x2 + 1 || x1 == x2 - 1) {
				return true;
			}
		}

		return false;
	}
	
	/**
	 * Sets the visibility property.
	 * 
	 * @param isVisible - True if visible otherwise false
	 */
	public void setIsVisible(boolean isVisible) {
		this.isVisible.set(isVisible);
	}
	
	/**
	 * 
	 * @return isVisible - Returns visibility property
	 */
	public BooleanProperty getIsVisibleProperty() {
		return isVisible;
	}
}
