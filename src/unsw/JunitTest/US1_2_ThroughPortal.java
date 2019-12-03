package unsw.JunitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import unsw.dungeon.*;
import unsw.obstacle.Portal;

/**
 * Tests the portal functionality.
 * 
 * @author z5208485
 *
 */
public class US1_2_ThroughPortal {
	Dungeon dun; // The dungeon
	Player p1; // The player
	Portal po1a; // Portal with id 1
	Portal po1b; // Portal with id 1
	Portal po2a; // Portal with id 2
	Portal po2b; // Portal with id 2

	/**
	 * Ensures that every portal has a link to another portal with the same id. If a
	 * player moves into an adjacent portal they should get teleported to the
	 * corresponding linked portal.
	 */
	@Test
	public void testUS1_2() {
		// Creates new 10x10 dungeon
		dun = new Dungeon(10, 10);

		// Places player at coordinates (1, 1)
		p1 = new Player(dun, 1, 1, "Player");
		dun.setPlayer(p1);

		// Creates a new portal with id 1 at coordinates (2, 1)
		po1a = new Portal(2, 1, "Portal", 1);
		dun.addEntity(po1a);

		// Creates a new portal with id 1 at coordinates (4, 1)
		po1b = new Portal(4, 1, "Portal", 1);
		dun.addEntity(po1b);

		// Ensures that portals po1a and po1b have the same id
		assertEquals(po1a.getId(), 1);
		assertEquals(po1b.getId(), 1);
		
		// Creates a new portal with id 2 at coordinates (5, 1)
		po2a = new Portal(5, 1, "Portal", 2);
		dun.addEntity(po2a);

		// Creates a new portal with id 2 at coordinates (9, 9)
		po2b = new Portal(9, 9, "Portal", 2);
		dun.addEntity(po2b);
		
		// Ensures that the player is teleported from po1a to po1b
		p1.moveRight();
		assertEquals(p1.getX(), 4);
		assertEquals(p1.getY(), 1);
		
		// Ensures that the player is teleported from po2a to po2b
		p1.moveRight();
		assertEquals(p1.getX(), 9);
		assertEquals(p1.getY(), 9);
		
		// Ensures that the player can step out of portal po2b
		p1.moveUp();
		assertEquals(p1.getX(), 9);
		assertEquals(p1.getY(), 8);
	}
}
