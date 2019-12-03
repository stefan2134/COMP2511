package unsw.dungeon;

import unsw.dungeon.Entity;

public class Enemy extends Entity implements Observer {
	/**
	 * Creates a new enemy at specified coordinates.
	 * 
	 * @param x    - X coordinate
	 * @param y    - Y coordinate
	 * @param name - Name of entity
	 */
	public Enemy(int x, int y, String name) {
		super(x, y, name);
	}

	/**
	 * Updates the enemy with the location of the player so the enemy can move
	 * accordingly.
	 */
	@Override
	public void update(Player p) {
		// Attempts to make an optimal move
		if (!moveToFromPlayer(p)) {
			// If optimal move can't be made then perform any available move
			moveObstruction(p);

		}

		// Interacts with player if they are at the same coordinates
		if (p.getX() == getX() && p.getY() == getY())
			playerInteract(p);
	}

	/**
	 * Attempt to make an optimal move.
	 * 
	 * @param p - Player
	 * @return - Returns true if an optimal valid movement can be made, otherwise
	 *         false.
	 */
	public boolean moveToFromPlayer(Player p) {
		// If the player is carrying a potion run away
		int d = p.numOfItem("Potion") > 0 ? (-1) : 1;

		// Move right to/from player
		if (getX() < p.getX() && !p.getDungeon().checkObstacle(getX() + d, getY())) {
			setX(getX() + d);
			return true;
		}

		// Move left to/from player
		if (getX() > p.getX() && !p.getDungeon().checkObstacle(getX() - d, getY())) {
			setX(getX() - d);
			return true;
		}

		// Move down to/from player
		if (getY() < p.getY() && !p.getDungeon().checkObstacle(getX(), getY() + d)) {
			setY(getY() + d);
			return true;
		}

		// Move up to/from player
		if (getY() > p.getY() && !p.getDungeon().checkObstacle(getX(), getY() - d)) {
			setY(getY() - d);
			return true;
		}
		

		return false;
	}

	/**
	 * Moves enemy in any available direction.
	 * 
	 * @param p - Player
	 */
	public void moveObstruction(Player p) {
		// Move right
		if (!p.getDungeon().checkObstacle(getX() + 1, getY())) {
			setX(getX() + 1);
			return;
		}

		// Move down
		if (!p.getDungeon().checkObstacle(getX(), getY() + 1)) {
			setY(getY() + 1);
			return;
		}

		// Move left
		if (!p.getDungeon().checkObstacle(getX() - 1, getY())) {
			setX(getX() - 1);
			return;
		}

		// Move up
		if (!p.getDungeon().checkObstacle(getX(), getY() - 1)) {
			setY(getY() - 1);
			return;
		}
	}

	/**
	 * Defines interaction between player and enemy.
	 */
	@Override
	public void playerInteract(Player p) {
		p.receiveAttack(this);
	}
}
