package unsw.JunitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import unsw.dungeon.*;
import unsw.goals.GoalExit;
import unsw.item.*;
import unsw.obstacle.*;

/**
 * Tests the players basic movement.
 * 
 * @author z5208485
 *
 */
public class US1_1_Move {
	Dungeon dun; // The dungeon
	Player p1; // The player
	Entity w1; // The wall
	Entity d1; // The door
	Entity s1; // The switch
	Entity s2; // The sword
	Entity t1; // The treasure
	Entity k1; // The key
	Entity p2; // The potion
	Entity b1; // The boulder

	/**
	 * Tests that the player can only move up, down, left and right one square at a
	 * time
	 */
	@Test
	public void testUS1_1_1() {
		// Creates new 10x10 dungeon
		dun = new Dungeon(10, 10);

		// Places player at coordinates (1, 1)
		p1 = new Player(dun, 1, 1, "Player");
		dun.setPlayer(p1);
		
		// Moves player down
		p1.moveDown();
		assertEquals(p1.getX(), 1);
		assertEquals(p1.getY(), 2);

		// Moves player right
		p1.moveRight();
		assertEquals(p1.getX(), 2);
		assertEquals(p1.getY(), 2);

		// Moves player up
		p1.moveUp();
		assertEquals(p1.getX(), 2);
		assertEquals(p1.getY(), 1);

		// Moves player left
		p1.moveLeft();
		assertEquals(p1.getX(), 1);
		assertEquals(p1.getY(), 1);
	}

	/**
	 * Tests that the player can't move into a square that has a wall or a closed
	 * door.
	 */
	@Test
	public void testUS1_1_2() {
		// Creates new 10x10 dungeon
		dun = new Dungeon(10, 10);

		// Places player at coordinates (1, 1)
		p1 = new Player(dun, 1, 1, "Player");
		dun.setPlayer(p1);

		// Creates a new wall at coordinates (1, 2)
		w1 = new Wall(1, 2, "Wall");
		dun.addEntity(w1);

		// Ensures that the player can't move down into the wall
		p1.moveDown();
		assertEquals(p1.getX(), 1);
		assertEquals(p1.getY(), 1);

		// Adds a closed door at coordinates (2, 1)
		d1 = new Door(2, 1, "Door", 0);
		dun.addEntity(d1);

		// Ensures that the player can't move right into the closed door
		p1.moveRight();
		assertEquals(p1.getX(), 1);
		assertEquals(p1.getY(), 1);
	}

	/**
	 * Tests that the player can move across floor switches
	 */
	@Test
	public void testUS1_1_3() {
		// Creates new 10x10 dungeon
		dun = new Dungeon(10, 10);

		// Places player at coordinates (1, 1)
		p1 = new Player(dun, 1, 1, "Player");
		dun.setPlayer(p1);

		// Creates a new switch at coordinates (1, 2)
		d1 = new Switch(1, 2, "Switch");
		dun.addEntity(d1);

		// Ensures that the player can move across the switch
		p1.moveDown();
		assertEquals(p1.getX(), 1);
		assertEquals(p1.getY(), 2);
		p1.moveDown();
		assertEquals(p1.getX(), 1);
		assertEquals(p1.getY(), 3);
	}

	/**
	 * Ensures that the player can pass through items
	 */
	@Test
	public void testUS1_1_4() {
		// Creates new 10x10 dungeon
		dun = new Dungeon(10, 10);
		dun.setGoals(new GoalExit(dun));
		
		// Places player at coordinates (1, 1)
		p1 = new Player(dun, 1, 1, "Player");
		dun.setPlayer(p1);

		// Creates a new sword at coordinates (1, 2)
		s2 = new Sword(1, 2, "Sword");
		dun.addEntity(s2);

		// Ensures that the player can pass through the sword
		p1.moveDown();
		assertEquals(p1.getY(), 2);

		// Creates new treasure at coordinates (1, 3)
		t1 = new Treasure(1, 3, "Treasure");
		dun.addEntity(t1);

		// Ensures that the player can pass through the treasure
		p1.moveDown();
		assertEquals(p1.getY(), 3);

		// Creates new treasure at coordinates (1, 4)
		k1 = new Key(1, 4, "Key", 1);
		dun.addEntity(k1);

		// Ensures that the player can pass through the key
		p1.moveDown();
		assertEquals(p1.getY(), 4);

		// Creates a new potion at coordinates (1, 5)
		p2 = new Potion(1, 5, "Potion");
		dun.addEntity(p2);

		// Ensures that the player can pass through the potion
		p1.moveDown();
		assertEquals(p1.getY(), 5);
	}

	/**
	 * Tests that the player can't move through a boulder
	 */
	@Test
	public void testUS1_1_5() {
		// Creates new 10x10 dungeon
		dun = new Dungeon(10, 10);
		
		// Places player at coordinates (1, 1)
		p1 = new Player(dun, 1, 1, "Player");
		dun.setPlayer(p1);

		// Adds a boulder at coordinates (2, 1)
		b1 = new Boulder(2, 1, "Boulder");
		dun.addEntity(b1);

		// Adds a wall at coordinates (3, 1)
		w1 = new Wall(3, 1, "Wall");
		dun.addEntity(w1);

		// Ensures that the player can't move through the boulder
		p1.moveRight();
		assertEquals(p1.getX(), 1);
		assertEquals(p1.getY(), 1);
	}
}
