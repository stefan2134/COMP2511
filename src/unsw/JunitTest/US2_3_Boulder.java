package unsw.JunitTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Player;
import unsw.goals.GoalExit;
import unsw.obstacle.Boulder;
import unsw.obstacle.Switch;
import unsw.obstacle.Wall;

/**
 * Tests that the player correctly interacts with boulders.
 * 
 * @author z5208485
 *
 */
public class US2_3_Boulder {
	Dungeon dun; // The dungeon
	Player p1; // The player
	Entity b1; // A boulder
	Entity w1; // A wall
	Entity b2; // A boulder
	Switch s1; // A switch

	/**
	 * Tests that the player can only push one boulder at time.
	 */
	@Test
	public void testUS2_3_1() {
		// Creates new 10x10 dungeon
		dun = new Dungeon(10, 10);

		// Places player at coordinates (1, 1)
		p1 = new Player(dun, 1, 1, "Player");
		dun.setPlayer(p1);

		// Creates a new boulder at coordinates (2, 1)
		b1 = new Boulder(2, 1, "Boulder");
		dun.addEntity(b1);

		// Creates a new boulder at coordinates (3, 1)
		b2 = new Wall(3, 1, "Boulder");
		dun.addEntity(b2);

		// Ensures that the player can't push both boulders
		p1.moveRight();
		assertEquals(p1.getX(), 1);
		assertEquals(p1.getY(), 1);
	}

	/**
	 * Tests that a player can't push a boulder through a wall.
	 */
	@Test
	public void testUS2_3_2() {
		// Creates new 10x10 dungeon
		dun = new Dungeon(10, 10);

		// Places player at coordinates (1, 1)
		p1 = new Player(dun, 1, 1, "Player");
		dun.setPlayer(p1);

		// Creates a new boulder at coordinates (2, 1)
		b1 = new Boulder(2, 1, "Boulder");
		dun.addEntity(b1);

		// Creates a new wall at coordinates (3, 1)
		w1 = new Wall(3, 1, "Wall");
		dun.addEntity(w1);

		// Ensures that the player can't push the boulder through the wall.
		p1.moveRight();
		assertEquals(p1.getX(), 1);
		assertEquals(p1.getY(), 1);
	}

	/**
	 * Ensures that the player can only push an adjacent boulder. A boulder can be
	 * pushed one square only in the direction the player is facing.
	 */
	@Test
	public void testUS2_3_3to4() {
		// Creates new 10x10 dungeon
		dun = new Dungeon(10, 10);

		// Places player at coordinates (1, 1)
		p1 = new Player(dun, 1, 1, "Player");
		dun.setPlayer(p1);

		// Creates a new boulder at coordinates (2, 1)
		b1 = new Boulder(2, 1, "Boulder");
		dun.addEntity(b1);

		// Ensures that the player has moved the boulder 1 unit right
		p1.moveRight();
		assertEquals(dun.getEntitiesAtPos(3, 1).size(), 1);
	}

	/**
	 * When a boulder is pushed onto a floor switch, it is triggered. When a boulder
	 * is pushed off a floor switch, it is untriggered.
	 */
	@Test
	public void testUS2_3_5to6() {
		// Creates new 10x10 dungeon
		dun = new Dungeon(10, 10);
		dun.setGoals(new GoalExit(dun));
		
		// Places player at coordinates (1, 1)
		p1 = new Player(dun, 1, 1, "Player");
		dun.setPlayer(p1);

		// Creates a new boulder at coordinates (2, 1)
		b1 = new Boulder(2, 1, "Boulder");
		dun.addEntity(b1);

		// Creates a new switch at coordinates (3, 1)
		s1 = new Switch(3, 1, "Switch");
		dun.addEntity(s1);

		// Ensures that when the boulder is moved right the switch activates
		p1.moveRight();
		assertTrue(s1.getIsTriggered());

		// Ensures that when the boulder is moved right again the switch deactivates
		p1.moveRight();
		assertFalse(s1.getIsTriggered());
	}
}
