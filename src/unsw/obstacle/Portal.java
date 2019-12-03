package unsw.obstacle;

import java.util.List;

import unsw.dungeon.Entity;
import unsw.dungeon.Player;

/**
 * A portal in the dungeon.
 * 
 * @author z5208485
 *
 */
public class Portal extends Entity {
	/**
	 * Creates a new portal with given id at the specified coordinates.
	 * 
	 * @param x    - X coordinate
	 * @param y    - Y coordinate
	 * @param name - Name of entity
	 * @param id   - Id of portal
	 */
	public Portal(int x, int y, String name, int id) {
		super(x, y, name);
		setId(id);
	}

	/**
	 * Defines player interaction with a portal.
	 */
	@Override
	public void playerInteract(Player p) {
		// Gets list of portals with id of current portal
		List<Entity> portals = p.getDungeon().getEntitiesById("Portal", getId());

		// No action is taken if a linked portal can't be found.
		if (portals.size() < 2) {
			return;
		}

		// Gets linked portal
		Portal linkedPortal = (Portal) portals.get(0) != this ? (Portal) portals.get(0) : (Portal) portals.get(1);

		// Teleports player to linked portal
		p.setX(linkedPortal.getX());
		p.setY(linkedPortal.getY());
	}
}
