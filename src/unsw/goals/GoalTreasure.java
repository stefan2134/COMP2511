package unsw.goals;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;

/**
 * Goal for collected all the treasure.
 * 
 * @author z5208485
 *
 */
public class GoalTreasure implements GoalComponent {
	private Dungeon d;

	/**
	 * Creates a new treasure goal in the dungeon.
	 * 
	 * @param d - Dungeon
	 */
	public GoalTreasure(Dungeon d) {
		this.d = d;
	}

	/**
	 * Implements the check for ensuring that all treasure is collected.
	 */
	@Override
	public boolean goalCompleted() {
		for (Entity e : d.getEntities()) {
			if (e.getName().equals("Treasure")) {
				return false;
			}
		}

		return true;
	}
}
