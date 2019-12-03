/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import unsw.goals.GoalComponent;
import unsw.item.Item;
import unsw.obstacle.Switch;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {
	private int width, height;
	private List<Entity> entities;
	private Player player;
	private GoalComponent goals;
	private StringProperty gameState;

	/**
	 * Creates dungeon with a given width and height.
	 * 
	 * @param width
	 * @param height
	 */
	public Dungeon(int width, int height) {
		this.width = width;
		this.height = height;
		entities = new ArrayList<>();
		player = null;
		gameState = new SimpleStringProperty();
		gameState.setValue("IN_PLAY");
	}

	/**
	 * 
	 * @return width - Returns width of dungeon
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * 
	 * @return width - Returns height of dungeon
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * 
	 * @return player - Returns player in dungeon.
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Sets the player in the dungeon.
	 * 
	 * @param player - Player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * Adds an entity to the list of entities in the dungeon.
	 * 
	 * @param entity - Entity to be added
	 */
	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	/**
	 * 
	 * @return entities - Returns list of entities in dungeon.
	 */
	public List<Entity> getEntities() {
		return entities;
	}

	/**
	 * Checks if a player can move into coordinates (x, y) unimpeded.
	 * 
	 * @param x - X coordinate
	 * @param y - Y coordinate
	 * @return - Returns true if there is an obstacle, otherwise false.
	 */
	public boolean checkObstacle(int x, int y) {
		for (Entity e : entities) {
			// Ensures that the entity is not a null reference
			if (e != null) {
				// Checks if the player can move into the position of the entity
				if (e.getX() == x && e.getY() == y && !e.canMoveTo(player)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Returns a list of entities at the specified coordinates.
	 * 
	 * @param x - X coordinate
	 * @param y - Y coordinate
	 * @return eList - List of entities
	 */
	public List<Entity> getEntitiesAtPos(int x, int y) {
		List<Entity> eList = new ArrayList<Entity>();
		// Goes through entities
		for (Entity e : entities) {
			// Ensures that the entity is not a null reference
			if (e != null) {
				// Adds entity to list if it is at the specified coordinates
				if (e.getX() == x && e.getY() == y) {
					eList.add(e);
				}
			}
		}

		return eList;
	}

	/**
	 * Removes entity if it exists in the entities list.
	 * 
	 * @param e - Entity to be removed.
	 * @return - Returns true if the entity is successfully removed, false if the
	 *         entity does not exist in the list.
	 */
	public boolean removeEntity(Entity e) {
		return entities.remove(e);
	}

	/**
	 * Gets list of entities that are of a given type with a specified id.
	 * 
	 * @param name - Type of entity
	 * @param id   - Specified id
	 * @return eList - List of entities
	 */
	public List<Entity> getEntitiesById(String name, int id) {
		List<Entity> eList = new ArrayList<Entity>();
		// Goes through entities
		for (Entity e : entities) {
			// Ensures that the entity is not a null reference
			if (e != null) {
				// Adds entity to list if it is of the specified type and has the correct id
				if (e.getName().equals(name) && e.getId() == id) {
					eList.add(e);
				}
			}
		}

		return eList;
	}

	/**
	 * Checks if a boulder can be moved into coordinates (x, y)
	 * 
	 * @param x - X coordinate
	 * @param y - Y coordinate
	 * @return - True if it can be moved, otherwise false
	 */
	public boolean boulderObstruction(int x, int y) {
		// Gets entities at coordinates
		List<Entity> eList = getEntitiesAtPos(x, y);

		// If the entity is not an item nor a switch then a boulder can't be moved into
		// it's path.
		boolean result = false;
		for (Entity e : eList) {
			if (!(e instanceof Item || e.getName().equals("Switch"))) {
				result = true;
				break;
			}
		}

		return result;
	}

	/**
	 * Activates a switch at coordinates (x, y) if a boulder is moved on top.
	 * 
	 * @param x - X coordinate
	 * @param y - Y coordinate
	 */
	public void activateSwitch(int x, int y) {
		List<Entity> eList = getEntitiesAtPos(x, y);
		for (Entity e : eList) {
			if (e instanceof Switch && e.getName().equals("Switch")) {
				((Switch) e).setIsTriggered(true);
				goalsCompleted();
			}
		}
	}

	/**
	 * Deactivates a switch at coordinates (x, y) if a boulder is moved off it.
	 * 
	 * @param x - X coordinate
	 * @param y - Y coordinate
	 */
	public void deActivateSwitch(int x, int y) {
		List<Entity> eList = getEntitiesAtPos(x, y);
		for (Entity e : eList) {
			if (e instanceof Switch && e.getName().equals("Switch")) {
				((Switch) e).setIsTriggered(false);
			}
		}
	}

	/**
	 * Sets the goals that have to be completed for the level to successfully end.
	 * 
	 * @param goals - Goals to be completed
	 */
	public void setGoals(GoalComponent goals) {
		this.goals = goals;
	}
	
	/**
	 * 
	 * @return - Returns true if the goals have been completed, otherwise false.
	 */
	public boolean goalsCompleted() {
		// Goals completed
		if (goals.goalCompleted()) {
			// Updates game state
			gameState.setValue("WIN");
			return true;
		}
		
		return false;
	}
	
	/**
	 * 
	 * @return - Returns game state property.
	 */
	public StringProperty getGameStateProperty() {
		return gameState;
	}
}
