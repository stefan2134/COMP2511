package unsw.item;

import unsw.dungeon.Player;

/**
 * Implements pick up behaviour for items that can only be collected once.
 * 
 * @author z5208485
 *
 */
public class PickUpOne implements PickUpBehaviour {
	/**
	 * Pick up action.
	 */
	public boolean pickUp(Player p, Item i) {
		// Checks if the player is already carrying an item of the same type.
		if (p.numOfItem(i.getName()) > 0) {
			// Player does not pick up item.
			return false;
		}

		// Adds item to players list of items. Item is no longer visible.
		p.addItem(i);
		i.setIsVisible(false);

		// Removes item from dungeon.
		p.getDungeon().removeEntity(i);

		return true;
	}
}
