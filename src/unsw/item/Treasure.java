package unsw.item;

import unsw.dungeon.Player;

/**
 * Treasure in the dungeon. Can be collected by the player to complete goals.
 * 
 * @author z5208485
 *
 */
public class Treasure extends Item {
	/**
	 * Creates new treasure at the specified coordinates.
	 * 
	 * @param x    - X coordinate
	 * @param y    - Y coordinate
	 * @param name - Name of entity
	 */
	public Treasure(int x, int y, String name) {
		super(x, y, name, new PickUpMany());
	}

	/**
	 * Defines player interaction with treasure. The player can collect an unlimited
	 * amount.
	 */
	@Override
	public void playerInteract(Player p) {
		// Gets player to pickup treasure
		getPickUpBehaviour().pickUp(p, this);
		
		// Checks if goals have been completed
		p.getDungeon().goalsCompleted();
	}
}
