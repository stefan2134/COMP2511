package unsw.item;

/**
 * A key in the dungeon. Key can be used to open a door and only that door.
 * 
 * @author z5208485
 *
 */
public class Key extends Item {
	/**
	 * Creates a key with given id at the specified coordinates.
	 * 
	 * @param x    - X coordinate
	 * @param y    - Y coordinate
	 * @param name - Name of entity
	 * @param id   - Id of key
	 */
	public Key(int x, int y, String name, int id) {
		super(x, y, name, new PickUpOne());
		setId(id);
	}

}
