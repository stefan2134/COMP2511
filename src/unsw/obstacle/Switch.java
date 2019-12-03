package unsw.obstacle;

import unsw.dungeon.Entity;

/**
 * A switch in the dungeon.
 * 
 * @author z5208485
 *
 */
public class Switch extends Entity {
	private boolean isTriggered;

	/**
	 * Creates a new switch at specified coordinates.
	 * 
	 * @param x    - X coordinate
	 * @param y    - Y coordinate
	 * @param name - Name of entity
	 */
	public Switch(int x, int y, String name) {
		super(x, y, name);
		isTriggered = false;
	}

	/**
	 * Changes the state of the switch.
	 * 
	 * @param isTriggered - True if a boulder is on the switch, otherwise false.
	 */
	public void setIsTriggered(boolean isTriggered) {
		this.isTriggered = isTriggered;
	}

	/**
	 * 
	 * @return isTriggered - Returns the state of the switch.
	 */
	public boolean getIsTriggered() {
		return isTriggered;
	}
}
