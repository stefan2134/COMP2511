package unsw.item;

import unsw.dungeon.Player;

/**
 * A sword in the dungeon.
 * 
 * @author z5208485
 *
 */
public class Sword extends Item {
	private int hitsLeft;

	/**
	 * Creates a new sword at specified coordinates.
	 * 
	 * @param x    - X coordinate
	 * @param y    - Y coordinate
	 * @param name - Name of entity
	 */
	public Sword(int x, int y, String name) {
		super(x, y, name, new PickUpOne());
		hitsLeft = 5;
	}

	/**
	 * Defines player interaction with a sword.
	 */
	@Override
	public void playerInteract(Player p) {
		// Gets player to pickup sword
		getPickUpBehaviour().pickUp(p, this);
	}

	/**
	 * Performs a hit with the sword. Sword's durability decreases.
	 */
	public void performHit() {
		hitsLeft--;
	}

	/**
	 * 
	 * @return hitsLeft - Returns the number of hits that can be made with the
	 *         sword.
	 */
	public int getHitsLeft() {
		return hitsLeft;
	}
}
