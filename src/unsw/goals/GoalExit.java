package unsw.goals;

import java.util.List;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Player;

/**
 * Goal for passing through exit.
 * 
 * @author z5208485
 *
 */
public class GoalExit implements GoalComponent {
	private Dungeon d;

	/**
	 * Creates an exit goal.
	 * 
	 * @param d
	 */
	public GoalExit(Dungeon d) {
		this.d = d;
	}

	/**
	 * Implements the check for ensuring that the player has reached the exit.
	 */
	@Override
	public boolean goalCompleted() {
		// Gets player
		Player p = d.getPlayer();

		// Gets list of entities at players position
		List<Entity> eList = d.getEntitiesAtPos(p.getX(), p.getY());

		// If the player is at the exit then the goal is completed
		for (Entity e : eList) {
			if (e.getName().equals("Exit")) {
				return true;
			}
		}

		return false;
	}

}
