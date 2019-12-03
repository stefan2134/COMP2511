package unsw.goals;

import java.util.List;

/**
 * Representation of an OR composite goal.
 * 
 * @author z5208485
 *
 */
public class GoalOrComposite extends GoalComposite implements GoalComponent {
	/**
	 * Creates a new composite goal.
	 */
	public GoalOrComposite() {
		super();
	}

	/**
	 * Checks that at least 1 subgoal is completed.
	 */
	@Override
	public boolean goalCompleted() {
		// Gets list of subgoals
		List<GoalComponent> subGoals = getSubGoals();

		// Ensures that at least 1 subgoal is completed
		for (GoalComponent g : subGoals) {
			if (g.goalCompleted()) {
				return true;
			}
		}

		return false;
	}
}
