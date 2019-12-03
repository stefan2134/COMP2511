package unsw.obstacle;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Player;

/**
 * Exit in the dungeon.
 * 
 * @author z5208485
 *
 */
public class Exit extends Entity {
	/**
	 * Creates a new exit at the specified coordinates.
	 * 
	 * @param x    - X coordinate
	 * @param y    - Y coordinate
	 * @param name - Name of entity
	 */
	public Exit(int x, int y, String name) {
		super(x, y, name);
	}

	/**
	 * The level ends when the player goes through the exit.
	 */
	@Override
	public void playerInteract(Player p) {
		// Checks if the goals have been completed
		Dungeon d = p.getDungeon();
		if (!d.goalsCompleted()) {
			// Sets the game state as lost
			d.getGameStateProperty().setValue("LOSS");
		}
	}
}
