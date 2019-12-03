package unsw.JunitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.Entity;
import unsw.dungeon.Player;
import unsw.goals.GoalExit;
import unsw.item.Potion;
import unsw.item.Sword;
import unsw.item.Treasure;

/**
 * Tests if players can pickup items.
 * 
 * @author z5208485
 *
 */
public class US3_1_PickUpItems {
	Dungeon dun; // The dungeon
	Player p1; // The player
	Entity s1; // A sword
	Entity s2; // A sword
	Entity t1; // Treasure
	Entity t2; // Treasure
	Entity p2; // A potion
	Entity p3; // A potion

	/**
	 * Tests that the player can pick up items by standing in the same square as the item.
	 */
	@Test
	public void testUS3_2_1() {
		// Creates new 10x10 dungeon
		dun = new Dungeon(10, 10);
		dun.setGoals(new GoalExit(dun));
		
		// Places player at coordinates (1, 1)
		p1 = new Player(dun, 1, 1, "Player");
		dun.setPlayer(p1);

		// Creates a new sword at coordinates (1, 2)
		s1 = new Sword(1, 2, "Sword");
		dun.addEntity(s1);
		
		// Ensures that the player picks up the sword
		p1.moveDown();
		assertEquals(p1.numOfItem("Sword"), 1);

		// Creates new treasure at coordinates (1, 3)
		t1 = new Treasure(1, 3, "Treasure");
		dun.addEntity(t1);
		
		// Ensures that the player picks up the treasure
		p1.moveDown();
		assertEquals(p1.numOfItem("Treasure"), 1);

		// Creates a new potion at coordinates (1, 4)
		p2 = new Potion(1, 4, "Potion");
		dun.addEntity(p2);
		
		// Ensures that the player picks up the potion
		p1.moveDown();
		assertEquals(p1.numOfItem("Potion"), 1);
	}

	/**
	 * Ensures that the player can collect unlimited treasure and invisibility potions.
	 */
	@Test
	public void testUS3_1_2() {
		// Creates new 10x10 dungeon
		dun = new Dungeon(10, 10);
		dun.setGoals(new GoalExit(dun));
		
		// Places player at coordinates (1, 1)
		p1 = new Player(dun, 1, 1, "Player");
		dun.setPlayer(p1);

		// Creates new treasure at coordinates (1, 2)
		t1 = new Treasure(1, 2, "Treasure");
		dun.addEntity(t1);
		
		// Ensures that the player can pick up the treasure
		p1.moveDown();
		assertEquals(p1.numOfItem("Treasure"), 1);

		// Creates new treasure at coordinates (1, 3)
		t2 = new Treasure(1, 3, "Treasure");
		dun.addEntity(t2);
		
		// Ensures that the player can pick up the treasure
		p1.moveDown();
		assertEquals(p1.numOfItem("Treasure"), 2);

		// Creates a new potion at coordinates (1, 4)
		p2 = new Potion(1, 4, "Potion");
		dun.addEntity(p2);
		
		// Ensures that the player can pick up the potion
		p1.moveDown();
		assertEquals(p1.numOfItem("Potion"), 1);

		// Creates a new potion at coordinates (1, 5)
		p3 = new Potion(1, 5, "Potion");
		dun.addEntity(p3);
		
		// Ensures that the player can pick up the potion
		p1.moveDown();
		assertEquals(p1.numOfItem("Potion"), 2);
	}

	/**
	 * Tests that a player can only pick up and carry one sword.
	 */
	@Test
	public void testUS3_1_3() {
		// Creates new 10x10 dungeon
		dun = new Dungeon(10, 10);

		// Places player at coordinates (1, 1)
		p1 = new Player(dun, 1, 1, "Player");
		dun.setPlayer(p1);

		// Creates a new sword at coordinates (1, 2)
		s1 = new Sword(1, 2, "Sword");
		dun.addEntity(s1);
		
		// Ensures that the player can pick up the sword
		p1.moveDown();
		assertEquals(p1.numOfItem("Sword"), 1);

		// Creates a new sword at coordinates (1, 3)
		s2 = new Sword(1, 3, "Sword");
		dun.addEntity(s1);
		
		// Ensures that the player can't pick up the sword
		p1.moveDown();
		assertEquals(p1.numOfItem("Sword"), 1);
	}
}
