package unsw.obstacle;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Player;

/**
 * Boulder in the dungeon. Can be pushed around by the player onto switches.
 * 
 * @author z5208485
 *
 */
public class Boulder extends Entity {
	/**
	 * Creates a boulder at the specified coordinates.
	 * 
	 * @param x    - X coordinate
	 * @param y    - Y coordinates
	 * @param name - Name of entity
	 */
	public Boulder(int x, int y, String name) {
		super(x, y, name);
	}

	/**
	 * Checks if a player can move a boulder. The player can only push the boulder
	 * if there is no obstacle behind it. The boulder is moved if possible.
	 */
	@Override
	public boolean canMoveTo(Player p) {
		// Determines which direction the boulder is to move
		String direction = pushDirection(p, this);

		// Gets the dungeon
		Dungeon d = p.getDungeon();

		// Moving boulder to the right
		if (direction.equals("right") && !d.boulderObstruction(getX() + 1, getY())) {
			d.deActivateSwitch(getX(), getY());
			setX(getX() + 1);
			d.activateSwitch(getX(), getY());
			return true;
		// Moving boulder to the left
		} else if (direction.equals("left") && !d.boulderObstruction(getX() - 1, getY())) {
			d.deActivateSwitch(getX(), getY());
			setX(getX() - 1);
			d.activateSwitch(getX(), getY());
			return true;
			// Moving boulder up
		} else if (direction.equals("up") && !d.boulderObstruction(getX(), getY() - 1)) {
			d.deActivateSwitch(getX(), getY());
			setY(getY() - 1);
			d.activateSwitch(getX(), getY());
			return true;
			// Moving boulder down
		} else if (direction.equals("down") && !d.boulderObstruction(getX(), getY() + 1)) {
			d.deActivateSwitch(getX(), getY());
			setY(getY() + 1);
			d.activateSwitch(getX(), getY());
			return true;
		}

		return false;
	}

	/**
	 * Determines which direction a player is to push a boulder.
	 * 
	 * @param p - Player who is adjacent to boulder
	 * @param b - Boulder to be pushed
	 * @return - A string containing the direction
	 */
	public String pushDirection(Player p, Boulder b) {
		if (p.getX() < b.getX()) {
			return "right";
		} else if (p.getX() > b.getX()) {
			return "left";
		} else if (p.getY() > b.getY()) {
			return "up";
		} else {
			return "down";
		}
	}
}
