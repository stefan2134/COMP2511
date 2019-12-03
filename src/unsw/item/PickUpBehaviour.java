package unsw.item;

import unsw.dungeon.Player;

/**
 * Interface for the pick up behaviour of an item.
 * 
 * @author z5208485
 *
 */
public interface PickUpBehaviour {
	public boolean pickUp(Player p, Item i);
}
