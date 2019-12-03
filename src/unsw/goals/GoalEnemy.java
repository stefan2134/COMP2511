package unsw.goals;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;

/**
 * Goal for killing all enemies.
 * 
 * @author z5208485
 *
 */
public class GoalEnemy implements GoalComponent {
	private Dungeon d;

	/**
	 * Creates new enemy goal in the dungeon.
	 * 
	 * @param d - Dungeon
	 */
	public GoalEnemy(Dungeon d) {
		this.d = d;
	}

	/**
	 * Checks that all enemies have been killed.
	 */
	@Override
	public boolean goalCompleted() {
		for (Entity e : d.getEntities()) {
			if (e.getName().equals("Enemy")) {
				return false;
			}
		}

		return true;
	}
}
