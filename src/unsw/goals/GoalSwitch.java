package unsw.goals;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.obstacle.Switch;

/**
 * The goal for activating all switches.
 * 
 * @author z5208485
 *
 */
public class GoalSwitch implements GoalComponent {
	private Dungeon d;

	/**
	 * Creates a new switch goal in the dungeon.
	 * 
	 * @param d - Dungeon
	 */
	public GoalSwitch(Dungeon d) {
		this.d = d;
	}

	/**
	 * Implements the check for ensuring that all switches are activated.
	 */
	@Override
	public boolean goalCompleted() {
		for (Entity e : d.getEntities()) {
			if (e instanceof Switch && !((Switch) e).getIsTriggered()) {
				return false;
			}
		}

		return true;
	}
}
