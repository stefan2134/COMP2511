package unsw.obstacle;

import unsw.dungeon.Entity;
import unsw.dungeon.Player;

/**
 * A wall in a dungeon.
 * 
 * @author z5208485
 *
 */
public class Wall extends Entity {
	/**
	 * Creates a new wall at specified coordinates.
	 * 
	 * @param x
	 * @param y
	 * @param name
	 */
    public Wall(int x, int y, String name) {
        super(x, y, name);
    }

    /**
     * Overrides implementation in entity. A player can't move into a wall.
     */
    @Override
	public boolean canMoveTo(Player p) {
		return false;
	}
}
