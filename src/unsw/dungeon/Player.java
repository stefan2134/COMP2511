package unsw.dungeon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import unsw.item.Bomb;
import unsw.item.Item;
import unsw.item.Potion;
import unsw.item.Sword;

/**
 * The player entity. A player can carry items and move around the level
 * performing tasks.
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements Subject {
	private Dungeon dungeon;
	private ArrayList<Item> items;
	private List<Observer> observers;

	/**
	 * Create a player positioned in square (x,y)
	 * 
	 * @param x
	 * @param y
	 */
	public Player(Dungeon dungeon, int x, int y, String name) {
		super(x, y, name);
		this.dungeon = dungeon;
		items = new ArrayList<Item>();
		observers = new ArrayList<Observer>();
	}

	/**
	 * Moves the player up if there is no obstacle.
	 */
	public void moveUp() {
		if (getY() > 0 && !dungeon.checkObstacle(getX(), getY() - 1)) {
			y().set(getY() - 1);
			usePotion();
		}
		interactWithEntities();
		notifyObservers();
	}

	/**
	 * Moves the player down if there is no obstacle.
	 */
	public void moveDown() {
		if (getY() < dungeon.getHeight() - 1 && !dungeon.checkObstacle(getX(), getY() + 1)) {
			y().set(getY() + 1);
			usePotion();
		}
		interactWithEntities();
		notifyObservers();
	}

	/**
	 * Moves the player left if there is no obstacle.
	 */
	public void moveLeft() {
		if (getX() > 0 && !dungeon.checkObstacle(getX() - 1, getY())) {
			x().set(getX() - 1);
			usePotion();
		}
		interactWithEntities();
		notifyObservers();
	}

	/**
	 * Moves the player right if there is no obstacle.
	 */
	public void moveRight() {
		if (getX() < dungeon.getWidth() - 1 && !dungeon.checkObstacle(getX() + 1, getY())) {
			x().set(getX() + 1);
			usePotion();
		}
		interactWithEntities();
		notifyObservers();
	}

	/**
	 * Gets the player to interact with all entities that on their current square.
	 */
	public void interactWithEntities() {
		List<Entity> eList = dungeon.getEntitiesAtPos(getX(), getY());
		for (Entity e : eList) {
			e.playerInteract(this);
		}
	}

	/**
	 * Adds item to the players list of items.
	 * 
	 * @param item - Item to be collected
	 */
	public void addItem(Item item) {
		items.add(item);
	}

	/**
	 * 
	 * @return dungeon - Returns the dungeon the player is in.
	 */
	public Dungeon getDungeon() {
		return dungeon;
	}

	/**
	 * Counts the number of items the player carries that are of a given type.
	 * 
	 * @param name - Name of item.
	 * @return numCarried - Number of items carried.
	 */
	public int numOfItem(String name) {
		int numCarried = 0;
		for (Item i : items) {
			if (i.getName().equals(name)) {
				numCarried++;
			}
		}
		return numCarried;
	}

	/**
	 * Gets the player to pick up key.
	 */
	public void pickUpKey() {
		List<Entity> eList = dungeon.getEntitiesAtPos(getX(), getY());
		for (Entity e : eList) {
			if (e.getName().equals("Key")) {
				((Item) e).getPickUpBehaviour().pickUp(this, (Item) e);
			}
		}
	}

	/**
	 * Gets the player to drop the key.
	 */
	public void dropKey() {
		for (Item i : items) {
			// If player is carrying the key
			if (i.getName().equals("Key")) {
				// Drop the key on the square the player is standing on.
				i.setX(getX());
				i.setY(getY());
				dungeon.addEntity(i);
				i.setIsVisible(true);
				removeItem(i);
				return;
			}
		}
	}

	/**
	 * Removes item if it exists in the items list.
	 * 
	 * @param i - Item to be removed.
	 * @return - Returns true if the item is successfully removed, false if the item
	 *         does not exist in the list.
	 */
	public boolean removeItem(Item i) {
		return items.remove(i);
	}

	/**
	 * Gets the player to interact with key.
	 */
	public void keyInteract() {
		// If the player is carrying a key then drop it.
		if (numOfItem("Key") == 1) {
			dropKey();
			// If a player is not carrying a key then pick it up.
		} else {
			pickUpKey();
		}
		notifyObservers();
	}

	/**
	 * Uses key if it is of specified id.
	 * 
	 * @param id - Id of key
	 * @return - Returns true if a door is opened, otherwise false.
	 */
	public boolean useKey(int id) {
		Iterator<Item> itemIterator = items.iterator();
		while (itemIterator.hasNext()) {
			Item i = itemIterator.next();
			if (i.getName().equals("Key") && i.getId() == id) {
				itemIterator.remove();
				items.remove(i);
				return true;
			}
		}

		return false;
	}

	/**
	 * Implements the method for adding an observer to the list of observers.
	 */
	@Override
	public void addObserver(Observer o) {
		observers.add(o);
	}

	/**
	 * Implements the method for removing an observer from the list of observers.
	 */
	@Override
	public void remObserver(Observer o) {
		observers.remove(o);
	}

	/**
	 * Implements the method for notifying every observer in the list of observers.
	 */
	@Override
	public void notifyObservers() {	
		List<Observer> observersCopy = new ArrayList<Observer>();
		observersCopy.addAll(observers);
		for (Observer o : observersCopy) {
			o.update(this);
		}
	}

	/**
	 * Handles players response to an enemy hit.
	 * 
	 * @param enemy - Enemy who performed the attack
	 */
	public void receiveAttack(Enemy enemy) {
		// If the player is carrying a potion they kill the enemy
		if (numOfItem("Potion") > 0) {
			dungeon.removeEntity(enemy);
			enemy.setIsVisible(false);
			remObserver(enemy);
			dungeon.goalsCompleted();
			// If the player is carrying a sword they use it
		} else if (numOfItem("Sword") > 0) {
			useSword(enemy);
			remObserver(enemy);
			dungeon.goalsCompleted();
			// If the player has no defences they die and the level ends
		} else {
			// Sets the game state as lost
			dungeon.getGameStateProperty().setValue("LOSS");
		}
	}

	/**
	 * Player uses a sword against the enemy.
	 * 
	 * @param enemy - Enemy to be attacked
	 */
	public void useSword(Enemy enemy) {
		// Finds the players sword
		List<Item> swords = getItemsByName("Sword");

		if (!swords.isEmpty()) {
			Sword s = (Sword) swords.get(0);

			// Sword performs a hit
			s.performHit();

			// If the sword is used up the player discards it
			if (s.getHitsLeft() == 0) {
				items.remove(s);
			}

			// The player kills the enemy
			dungeon.removeEntity(enemy);
			enemy.setIsVisible(false);
		}
	}

	/**
	 * Player uses a potion against the enemy.
	 * 
	 * @param enemy - Enemy to be attacked
	 */
	public void usePotion() {
		// Gets the first potion collected
		List<Item> potions = getItemsByName("Potion");

		if (!potions.isEmpty()) {
			Potion p = (Potion) potions.get(0);

			// Reduces the number of uses left
			p.reduceStepsLeft();

			// If the potion is used up the player discards it
			if (p.getStepsLeft() == 0) {
				items.remove(p);
			}
		}
	}

	/**
	 * Gets a list of items with a given name that the player carries
	 * 
	 * @param name - Name of item to look for
	 * @return list - List of items
	 */
	public List<Item> getItemsByName(String name) {
		List<Item> list = new ArrayList<Item>();
		for (Item i : items) {
			if (i.getName().equals(name)) {
				list.add(i);
			}
		}
		return list;
	}

	/**
	 * Drops a bomb, destroying adjacent walls.
	 */
	public void useBomb() {
		// Gets a list of the players bombs
		List<Item> bombs = getItemsByName("Bomb");
		// If the player is carrying a bomb then use the first available one.
		if (!bombs.isEmpty()) {
			((Bomb) (bombs.get(0))).explode(this);
		}
		notifyObservers();
	}
	
	/**
	 * 
	 * @return items - Returns a list of the items a player is carrying
	 */
	public List<Item> getItems() {
		return items;
	}
}
