package unsw.JunitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Player;
import unsw.item.Key;

/**
 * Tests that the player can pickup and drop keys.
 * 
 * @author z5208485
 *
 */
public class US2_1_Key {
	Dungeon dun; // The dungeon
	Player p1; // The player
	Entity k1; // A key
	Entity k2; // A key
	Entity d1; // A door
	Entity d2; // A door

	/**
	 * Tests that a key can be picked up by a player when they move into the square
	 * containing it.
	 */
	@Test
	public void testUS2_1_1() {
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
	}

	/**
	 * Tests that a player can only carry 1 key at a time
	 */
	@Test
	public void testUS2_1_2() {
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

		// Creates a new key with id 2 at coordinates (1, 3)
		k1 = new Key(1, 2, "Key", 1);
		dun.addEntity(k1);

		// Ensures that the player can't pick up the second key
		p1.moveDown();
		p1.keyInteract();
		assertTrue(p1.numOfItem("Key") != 2);
	}

	// Ensures that a player can drop the key on the square they are standing on.
	@Test
	public void testUS2_1_3() {
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

		// Moves player down
		p1.moveDown();

		// Ensure that the player can drop the key
		p1.dropKey();
		assertEquals(p1.numOfItem("Key"), 0);
	}
}
