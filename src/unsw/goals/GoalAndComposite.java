package unsw.goals;

import java.util.List;

/**
 * Representation of an AND composite goal.
 * 
 * @author z5208485
 *
 */
public class GoalAndComposite extends GoalComposite implements GoalComponent {	
	/**
	 * Creates a new composite goal.
	 */
	public GoalAndComposite() {
		super();
	}
	
	/**
	 * Checks that all subgoals are completed
	 */
	@Override
	public boolean goalCompleted() {
		// Gets list of subgoals
		List<GoalComponent> subGoals = getSubGoals();
		
		// Ensures that all subgoals are completed
		for (GoalComponent g : subGoals) {
			if (!g.goalCompleted()) {
				return false;
			}
		}
		
		return true;
	}
}
