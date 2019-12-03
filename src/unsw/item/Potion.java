package unsw.item;

import unsw.dungeon.Player;

/**
 * A potion in the dungeon.
 * 
 * @author z5208485
 *
 */
public class Potion extends Item {
	int stepsLeft = 10;

	/**
	 * Creates a potion with given coordinates.
	 * 
	 * @param x    - X coordinate
	 * @param y    - Y coordinate
	 * @param name - name of entity
	 */
	public Potion(int x, int y, String name) {
		super(x, y, name, new PickUpMany());
	}

	/**
	 * Defines player interaction with potions.
	 */
	@Override
	public void playerInteract(Player p) {
		// Gets player to pickup potion
		getPickUpBehaviour().pickUp(p, this);
	}

	/**
	 * Reduces the number of steps for which the potion can be used
	 */
	public void reduceStepsLeft() {
		stepsLeft--;
	}

	/**
	 * 
	 * @return stepsLeft - Returns the number of steps remaining
	 */
	public int getStepsLeft() {
		return stepsLeft;
	}
}
