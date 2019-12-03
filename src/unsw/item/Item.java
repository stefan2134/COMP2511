package unsw.item;

import unsw.dungeon.Entity;

/**
 * Representation of an item that can be carried by a player.
 * 
 * @author z5208485
 *
 */
public class Item extends Entity {
	private PickUpBehaviour pickUpBehaviour;

	/**
	 * Item constructor
	 * 
	 * @param x    - X coordinate of item
	 * @param y    - Y coordinate of item
	 * @param name - Name of item
	 */
	public Item(int x, int y, String name, PickUpBehaviour pickUpBehaviour) {
		super(x, y, name);
		this.pickUpBehaviour = pickUpBehaviour;
	}

	/**
	 * 
	 * @return - Returns pickup behaviour.
	 */
	public PickUpBehaviour getPickUpBehaviour() {
		return pickUpBehaviour;
	}
}
