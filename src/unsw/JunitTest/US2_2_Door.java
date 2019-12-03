package unsw.JunitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Player;
import unsw.item.Key;
import unsw.obstacle.Door;

/**
 * Tests that keys can open doors.
 * 
 * @author z5208485
 *
 */
public class US2_2_Door {
	Dungeon dun; // The dungeon
	Player p1; // The player
	Entity k1; // A key
	Entity k2; // A key
	Entity d1; // A door
	Entity d2; // A door

	/**
	 * Tests that each key can open only one corresponding door. The player can't
	 * pass through a door if they aren't carrying the corresponding key. A key is
	 * to disappear after it opens a door.
	 */
	@Test
	public void testUS2_2() {
		// Creates new 10x10 dungeon
		dun = new Dungeon(10, 10);

		// Places player at coordinates (1, 1)
		p1 = new Player(dun, 1, 1, "Player");
		dun.setPlayer(p1);

		// Creates a new key with id 1 at coordinates (1, 2)
		k1 = new Key(1, 2, "Key", 1);
		dun.addEntity(k1);

		// Ensures that the player can pick up the key
		p1.moveDown();
		p1.keyInteract();
		assertEquals(p1.numOfItem("Key"), 1);

		// Creates a new door with id 1 at coordinates (1, 3)
		d1 = new Door(1, 3, "Door", 1);
		dun.addEntity(d1);

		// Creates a new door with id 2 at coordinates (1, 4)
		d2 = new Door(1, 4, "Door", 2);
		dun.addEntity(d2);

		// Ensures that the player can open door 1
		p1.moveDown();
		assertEquals(p1.getX(), 1);
		assertEquals(p1.getY(), 3);
		
		// Ensures that the key has disappeared
		assertEquals(dun.getEntitiesById("Key", 1).size(), 0);

		// Ensures that the player can't open door 2
		p1.moveDown();
		assertEquals(p1.getX(), 1);
		assertEquals(p1.getY(), 3);
	}
}