package unsw.JunitTest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.Entity;
import unsw.dungeon.Player;
import unsw.goals.GoalExit;
import unsw.item.Potion;

/**
 * Tests the players interaction with potions.
 * 
 * @author z5208485
 *
 */
public class US3_2_Potion {

	Dungeon dun; // The dungeon
	Player p1; // The player
	Enemy e1; // An enemy
	Potion po1; // A potion

	/**
	 * Tests that when used the potion should render the player invincible to
	 * enemies and after consuming the potion, if the player collides with an enemy
	 * this results in the enemy's immediate destruction.
	 */
	@Test
	public void testUS3_2_1and3() {

		// Creates new 10x10 dungeon
		dun = new Dungeon(10, 10);
		dun.setGoals(new GoalExit(dun));

		// Places player at coordinates (1, 1)
		p1 = new Player(dun, 1, 1, "Player");
		dun.setPlayer(p1);

		// Creates a new player at coordinates (1, 3)
		e1 = new Enemy(1, 3, "Enemy");
		dun.addEntity(e1);

		// Creates a new potion at coordinates (1, 2)
		po1 = new Potion(1, 2, "Potion");
		dun.addEntity(po1);

		// Ensures that when the player collects the potion the enemy dies immediately
		// and the player remains alive
		p1.moveDown();
		assertEquals(dun.getEntitiesAtPos(1, 2).size(), 0);
		p1.moveDown();
		assertEquals(dun.getEntitiesAtPos(1, 2).size(), 0);
	}

	/**
	 * Ensures that the potion's effect last 10 steps after which the potion
	 * disappears
	 */
	@Test
	public void testUS3_2_2() {
		// Creates new 10x20 dungeon
		dun = new Dungeon(10, 20);

		// Places player at coordinates (1, 1)
		p1 = new Player(dun, 1, 1, "Player");
		dun.setPlayer(p1);

		// Creates a new potion at coordinates (1, 2)
		po1 = new Potion(1, 2, "Potion");
		dun.addEntity(po1);

		// Gets the player to collect the potion
		p1.moveDown();

		// Ensure that the effect lasts for 10 steps
		for (int i = 0; i < 9; i++) {
			assertEquals(po1.getStepsLeft(), 10 - i);
			p1.moveDown();
			assertEquals(p1.numOfItem("Potion"), 1);
		}
		p1.moveDown();
		assertEquals(p1.numOfItem("Potion"), 0);
	}

	/**
	 * After consuming the potion, if the player collides with an enemy this results
	 * in the enemy's immediate destruction.
	 */
	@Test
	public void testUS3_2_3() {

		// Creates new 10x10 dungeon
		dun = new Dungeon(10, 10);
		dun.setGoals(new GoalExit(dun));
		p1 = new Player(dun, 1, 1, "Player");
		dun.setPlayer(p1);

		// Places potion at coordinates (2, 1)
		po1 = new Potion(2, 1, "Potion");
		dun.addEntity(po1);

		// Player picks up the potion
		p1.moveRight();
		assertEquals(p1.numOfItem("Potion"), 1);

		// Places enemy at coord(1,1)
		e1 = new Enemy(1, 1, "Enemy");
		dun.addEntity(e1);

		// Moves the player left and ensures that the enemy dies
		p1.moveLeft();
		for (Entity e : dun.getEntities()) {
			assertFalse(e.getName() == "Enemy");
		}
	}

	/**
	 * Tests that Enemies should move away from the player if the player has
	 * consumed a potion.
	 */
	@Test
	public void testUS3_2_4() {
		// Creates new 10x10 dungeon
		dun = new Dungeon(10, 10);

		// Places player at coordinates (1, 1)
		p1 = new Player(dun, 1, 1, "Player");
		dun.setPlayer(p1);

		// Places potion at coordinates (2, 1)
		po1 = new Potion(2, 1, "Potion");
		dun.addEntity(po1);

		// Player pick up the potion
		p1.moveRight();
		assertEquals(p1.numOfItem("Potion"), 1);

		// Places enemy at coord(4, 1)
		e1 = new Enemy(4, 1, "Enemy");
		dun.addEntity(e1);

		// When the player moves right, the enemy should move away from player
		p1.moveRight();
		e1.update(p1);
		p1.moveRight();
		e1.update(p1);
		int numEnemies = 0;
		for (Entity e : dun.getEntities()) {
			if (e != null) {
				if (e.getName().equals("Enemy"))
					numEnemies++;
			}
		}
		assertEquals(numEnemies, 1);
	}
}
