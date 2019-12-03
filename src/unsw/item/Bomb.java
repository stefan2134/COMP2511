package unsw.item;

import java.util.Iterator;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Player;

/**
 * A bomb in the dungeon. A bomb can be used to destroy adjacent walls.
 * 
 * @author z5208485
 *
 */
public class Bomb extends Item {
	/**
	 * Creates a bomb at the specified coordinates.
	 * 
	 * @param x    - X coordinate
	 * @param y    - Y coordinate
	 * @param name - Name of entity
	 */
	public Bomb(int x, int y, String name) {
		super(x, y, name, new PickUpMany());
	}

	/**
	 * Defines player interaction with bombs.
	 */
	@Override
	public void playerInteract(Player p) {
		// Gets player to pickup bomb
		getPickUpBehaviour().pickUp(p, this);
	}
	
	/**
	 * Gets the bomb to explode, destroying adjacent walls.
	 * 
	 * @param player
	 */
	public void explode(Player p) {
		// Removes bomb from players list of items
		p.removeItem(this);
		
		// Goes through entities in dungeon
		Dungeon d = p.getDungeon();
		Iterator<Entity> entityIterator = d.getEntities().iterator();
		while (entityIterator.hasNext()) {
			Entity e = entityIterator.next();
			// Destroys adjacent walls, rendering them not visible to the player.
			if (e.getName().equals("Wall") && adjacentCoord(p.getX(), p.getY(), e.getX(), e.getY())) {
				entityIterator.remove();
				d.removeEntity(e);
				e.setIsVisible(false);
			}
		}		
	}
}
