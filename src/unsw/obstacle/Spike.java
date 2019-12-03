package unsw.obstacle;

import unsw.dungeon.Entity;
import unsw.dungeon.Player;

/**
 * Spike in the dungeon. Player dies when they interact with spikes.
 * 
 * @author z5208485
 *
 */
public class Spike extends Entity{
	public Spike(int x, int y, String name) {
		super(x, y, "Spike");
	}

	/**
	 * The level ends when the player interacts with the spikes.
	 */
	@Override
	public void playerInteract(Player p) {
		// Sets the game state as lost
		p.getDungeon().getGameStateProperty().setValue("LOSS");
	}
}
