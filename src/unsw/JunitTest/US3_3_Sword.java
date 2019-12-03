package unsw.JunitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.Entity;
import unsw.dungeon.Player;
import unsw.goals.GoalExit;
import unsw.item.Sword;

public class US3_3_Sword {
	Dungeon dun; // The dungeon
	Player p1; // The player
	Enemy e1; // An enemy
	Entity s1; // A sword

	/**
	 * Tests that If the player carries a sword, they will initiate a hit against an
	 * adjacent enemy. If an enemy is hit with a sword it is immediately destroyed.
	 * A player can perform only 5 hits with a sword before it disappears.
	 */
	@Test
	public void testUS3_3() {
		// Creates new 10x10 dungeon
		dun = new Dungeon(10, 10);
		dun.setGoals(new GoalExit(dun));
		
		// Places player at coordinates (1, 1)
		p1 = new Player(dun, 1, 1, "Player");
		dun.setPlayer(p1);

		// Places sword at coordinates (2, 1)
		s1 = new Sword(2, 1, "Sword");
		dun.addEntity(s1);

		// Player pick up the sword
		p1.moveRight();
		assertEquals(p1.numOfItem("Sword"), 1);

		// Places enemy at coordinates (3,1)
		e1 = new Enemy(3, 1, "Enemy");
		dun.addEntity(e1);

		// Ensures that when the player moves right the enemy dies
		p1.moveRight();
		for (Entity e : dun.getEntities()) {
			assertFalse(e.getName() == "Enemy");
		}

		// Adds a second enemy at coordinates (4,1)
		e1 = new Enemy(4, 1, "Enemy");
		dun.addEntity(e1);

		// Ensures that when the player moves right the enemy dies
		p1.moveRight();
		for (Entity e : dun.getEntities()) {
			assertFalse(e.getName() == "Enemy");
		}

		// Adds a third enemy at coordinates (5, 1)
		e1 = new Enemy(5, 1, "Enemy");
		dun.addEntity(e1);
		
		// Ensures that when the player moves right the enemy dies
		p1.moveRight();
		for (Entity e : dun.getEntities()) {
			assertFalse(e.getName() == "Enemy");
		}

		// Adds a fourth enemy at coordinates (6, 1)
		e1 = new Enemy(6, 1, "Enemy");
		dun.addEntity(e1);
		
		// Ensures that when the player moves right the enemy dies
		p1.moveRight();
		for (Entity e : dun.getEntities()) {
			assertFalse(e.getName() == "Enemy");
		}

		// Adds a fifth enemy at coordinates (7, 1)
		e1 = new Enemy(7, 1, "Enemy");
		dun.addEntity(e1);
		
		// Ensures that when the player moves right the enemy dies
		p1.moveRight();
		for (Entity e : dun.getEntities()) {
			assertFalse(e.getName() == "Enemy");
		}

		// After 5 hits, the sword should disappear
		assertEquals(p1.numOfItem("Sword"), 0);

	}

}
