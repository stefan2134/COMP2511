package unsw.item;

import unsw.dungeon.Player;

/**
 * Implements pick up behaviour for items that can be collected multiple times.
 * 
 * @author z5208485
 *
 */
public class PickUpMany implements PickUpBehaviour {
	/**
	 * Pick up action.
	 */
	public boolean pickUp(Player p, Item i) {
		// Adds item to players list of items. Item is no longer visible.
		p.addItem(i);
		i.setIsVisible(false);
		
		// Removes item from dungeon.
		p.getDungeon().removeEntity(i);
		
		return true;
	}
}
