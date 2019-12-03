package unsw.goals;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class template for a composite goal.
 * 
 * @author z5208485
 *
 */
abstract public class GoalComposite {
	private List<GoalComponent> subGoals;

	/**
	 * Creates a new composite goal.
	 */
	public GoalComposite() {
		subGoals = new ArrayList<GoalComponent>();
	}

	/**
	 * Adds goal to list of subgoals
	 * 
	 * @param goal
	 */
	public void addSubGoal(GoalComponent goal) {
		subGoals.add(goal);
	}

	/**
	 * 
	 * @return subGoals - Returns list of subgoals
	 */
	public List<GoalComponent> getSubGoals() {
		return subGoals;
	}

	/**
	 * Checks that a goal has been completed
	 * 
	 * @return - Returns true if the goal is completed, otherwise false.
	 */
	abstract public boolean goalCompleted();
}
